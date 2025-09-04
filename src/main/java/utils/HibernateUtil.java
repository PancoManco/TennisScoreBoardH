package utils;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import model.Match;
import model.Player;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j
@UtilityClass
public class HibernateUtil {
    @Getter
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            log.debug("Building Hibernate SessionFactory");
            return new Configuration()
                    .configure()
                    .addAnnotatedClass(Match.class)
                    .addAnnotatedClass(Player.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

    public static void initDatabase() {

    }
}
