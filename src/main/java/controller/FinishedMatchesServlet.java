package controller;

import dto.MatchDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.FinishedMatchesPersistenceService;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class FinishedMatchesServlet extends HttpServlet {
    public static final int PAGE_SIZE_BY_DEFAULT = 5;
    private FinishedMatchesPersistenceService finishedMatchesPersistenceService;

    @Override
    public void init() throws ServletException {
        this.finishedMatchesPersistenceService = (FinishedMatchesPersistenceService) getServletContext().getAttribute("finishedMatchesPersistenceService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getParameter("page");
        String playerName = req.getParameter("filter_by_player_name");
        int pageNumber;
        try {
            pageNumber = (page == null || page.isEmpty()) ? 1 : Integer.parseInt(page);
        } catch (NumberFormatException exception) {
            pageNumber = 1;
        }
        List<MatchDto> matches = finishedMatchesPersistenceService.getFinishedMatches(pageNumber, playerName, PAGE_SIZE_BY_DEFAULT);
        long totalMatches = finishedMatchesPersistenceService.getFinishedMatchesCount(playerName);
        int totalPages = (int) Math.ceil((double) totalMatches / PAGE_SIZE_BY_DEFAULT);
        req.setAttribute("matches", matches);
        req.setAttribute("pageNumber", pageNumber);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("filterName", playerName);
        req.setAttribute("pageSize", PAGE_SIZE_BY_DEFAULT);
        req.getRequestDispatcher("/matches.jsp").forward(req, resp);
    }
}
