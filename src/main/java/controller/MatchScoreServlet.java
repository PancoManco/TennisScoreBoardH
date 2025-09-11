package controller;

import dto.MatchDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapper.MatchMapper;
import model.Match;
import service.FinishedMatchesPersistenceService;
import service.MatchScoreCalculationService;
import service.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    MatchMapper matchMapper;
    private OngoingMatchesService ongoingMatchesService;
    private MatchScoreCalculationService matchScoreCalculationService;
    private FinishedMatchesPersistenceService finishedMatchesPersistenceService;

    @Override
    public void init() throws ServletException {
        this.ongoingMatchesService = (OngoingMatchesService) getServletContext().getAttribute("ongoingMatchesService");
        this.matchScoreCalculationService = (MatchScoreCalculationService) getServletContext().getAttribute("matchScoreCalculationService");
        this.finishedMatchesPersistenceService = (FinishedMatchesPersistenceService) getServletContext().getAttribute("finishedMatchesPersistenceService");
        this.matchMapper = (MatchMapper) getServletContext().getAttribute("matchMapper");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        Match match = ongoingMatchesService.getMatch(uuid);

        if (match.getWinner() != null) {
            ongoingMatchesService.delete(uuid);
        }
        req.setAttribute("match", match);
        req.setAttribute("uuid", uuid);
        req.setAttribute("matchScore", match.getMatchScore());
        req.getRequestDispatcher("/match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        String player = req.getParameter("winnerId");
        Match match = ongoingMatchesService.getMatch(uuid);
        matchScoreCalculationService.updatePoints(match, player);

        if (match.getWinner() != null) {
            MatchDto matchDto = matchMapper.toDTO(match);
            finishedMatchesPersistenceService.persistMatch(matchDto);
        }
        resp.sendRedirect("/match-score" + "?uuid=" + uuid);
    }
}
