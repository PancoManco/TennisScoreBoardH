package controller;

import dto.MatchDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapper.MatchMapper;
import model.Match;
import service.NewMatchService;
import service.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

import static utils.RequestParameterUtil.validateParameters;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {
    NewMatchService newMatchService;
    OngoingMatchesService ongoingMatchesService;
    MatchMapper matchMapper;

    @Override
    public void init() {
        this.newMatchService = (NewMatchService) getServletContext().getAttribute("newMatchService");
        this.ongoingMatchesService = (OngoingMatchesService) getServletContext().getAttribute("ongoingMatchesService");
        this.matchMapper = (MatchMapper) getServletContext().getAttribute("matchMapper");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/new-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name1 = req.getParameter("player1");
        String name2 = req.getParameter("player2");
        validateParameters(name1, name2);

        // test dto
        MatchDto matchDto = newMatchService.createNewMatch(name1, name2);
        Match match = matchMapper.toEntity(matchDto);
        // test dto

        //  Match match = newMatchService.createNewMatch(name1,name2);
        UUID matchId = ongoingMatchesService.add(match);
        resp.sendRedirect("/match-score?uuid=" + matchId);

    }
}
