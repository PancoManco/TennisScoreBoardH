package controller;

import dao.Impl.PlayerDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Match;
import model.Player;
import service.NewMatchService;
import service.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

import static utils.RequestParameterUtil.validateParameters;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {
    NewMatchService newMatchService ;
    OngoingMatchesService ongoingMatchesService ;

    @Override
    public void init() throws ServletException {
        this.newMatchService = (NewMatchService) getServletContext().getAttribute("newMatchService");
        this.ongoingMatchesService = (OngoingMatchesService) getServletContext().getAttribute("ongoingMatchesService");
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/new-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name1 = req.getParameter("player1");
        String name2 = req.getParameter("player2");
        validateParameters(name1,name2);
        Match match = newMatchService.createNewMatch(name1,name2);
        UUID matchId = ongoingMatchesService.add(match);
       resp.sendRedirect("/match-score?uuid=" + matchId);

    }
}
