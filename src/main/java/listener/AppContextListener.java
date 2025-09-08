package listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import service.FinishedMatchesPersistenceService;
import service.MatchScoreCalculationService;
import service.NewMatchService;
import service.OngoingMatchesService;
import utils.HibernateUtil;

@WebListener
public class AppContextListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {

        HibernateUtil.initDatabase(); //todo сделать скрипт для добавления данных в базу данных для проверки пагинации
        sce.getServletContext().setAttribute("finishedMatchesPersistenceService", new FinishedMatchesPersistenceService());
        sce.getServletContext().setAttribute("matchScoreCalculationService", new MatchScoreCalculationService());
        sce.getServletContext().setAttribute("newMatchService", new NewMatchService());
        sce.getServletContext().setAttribute("ongoingMatchesService", new OngoingMatchesService());
    }
}
