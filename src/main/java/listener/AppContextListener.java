package listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;
import mapper.MatchMapper;
import service.FinishedMatchesPersistenceService;
import service.MatchScoreCalculationService;
import service.NewMatchService;
import service.OngoingMatchesService;

@Slf4j
@WebListener
public class AppContextListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("contextInitialized");
        MatchMapper matchMapper = MatchMapper.INSTANCE;
        //  HibernateUtil.initDatabase();
        sce.getServletContext().setAttribute("finishedMatchesPersistenceService", new FinishedMatchesPersistenceService());
        sce.getServletContext().setAttribute("matchScoreCalculationService", new MatchScoreCalculationService());
        sce.getServletContext().setAttribute("newMatchService", new NewMatchService());
        sce.getServletContext().setAttribute("ongoingMatchesService", new OngoingMatchesService());
        sce.getServletContext().setAttribute("matchMapper", matchMapper);
    }
}
