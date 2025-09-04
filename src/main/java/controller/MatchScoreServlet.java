package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Match;
import service.FinishedMatchesPersistenceService;
import service.MatchScoreCalculationService;
import service.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {
    private OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
    private MatchScoreCalculationService scoreService = new MatchScoreCalculationService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        Match match = ongoingMatchesService.getMatch(uuid);

        req.setAttribute("match", match);
        req.setAttribute("uuid", uuid);
        req.setAttribute("matchScore", match.getMatchScore());
        req.getRequestDispatcher("/WEB-INF/jsp/match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        String player = req.getParameter("player");
        Match match = ongoingMatchesService.getMatch(uuid);
        scoreService.updatePoints(match, player);

       // FinishedMatchesPersistenceService.persist(match); с проверка на победителя

        resp.sendRedirect("/match-score" + "?uuid=" + uuid);
    }
}
