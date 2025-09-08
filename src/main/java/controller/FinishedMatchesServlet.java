package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Match;
import service.FinishedMatchesPersistenceService;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidParameterException;
import java.util.List;

@WebServlet("/matches")
public class FinishedMatchesServlet extends HttpServlet {
    private FinishedMatchesPersistenceService finishedMatchesPersistenceService;
    public static final int PAGE_SIZE_BY_DEFAULT = 5;
    @Override
    public void init() throws ServletException {
        this.finishedMatchesPersistenceService = (FinishedMatchesPersistenceService) getServletContext().getAttribute("finishedMatchesPersistenceService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getParameter("page");

        int pageNumber;
        try {
            pageNumber = (page == null) ? 1 : Integer.parseInt(page);
        } catch (NumberFormatException exception) {
            throw new InvalidParameterException("Empty page");
        }
        String playerName = req.getParameter("filter_by_player_name");
        List<Match> finishedMatches = finishedMatchesPersistenceService.getFinishedMatches(pageNumber, playerName, PAGE_SIZE_BY_DEFAULT);
        List<Match> matches = finishedMatchesPersistenceService.getFinishedMatches(pageNumber, playerName, PAGE_SIZE_BY_DEFAULT);
        req.setAttribute("matches", finishedMatches);
        req.setAttribute("pageNumber", pageNumber);
        req.setAttribute("filterName", playerName);
        req.setAttribute("pageSize", PAGE_SIZE_BY_DEFAULT);
        req.getRequestDispatcher("/matches.jsp").forward(req, resp);
    }
}
