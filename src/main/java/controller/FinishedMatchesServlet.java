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
import java.util.List;

@WebServlet("/matches")
public class FinishedMatchesServlet extends HttpServlet {
    private FinishedMatchesPersistenceService finishedMatchesPersistenceService;
    @Override
    public void init() throws ServletException {
        this.finishedMatchesPersistenceService = (FinishedMatchesPersistenceService) getServletContext().getAttribute("finishedMatchesPersistenceService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Match> matches = finishedMatchesPersistenceService.getFinishedMatches();

        req.setAttribute("matches", matches);
        req.getRequestDispatcher("/matches.jsp").forward(req, resp);
    }
}
