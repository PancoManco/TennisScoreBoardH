package controller;

import dao.Impl.PlayerDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Player;

import java.io.IOException;

import static utils.RequestParameterUtil.validateParameters;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/new-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name1 = req.getParameter("player1");
        String name2 = req.getParameter("player2");
        validateParameters(name1,name2);

     //   Player player1 = PlayerDao.findOrCreatePlayer(name1);
      //  Player player2 = PlayerDao.findOrCreatePlayer(name2);


    }
}
