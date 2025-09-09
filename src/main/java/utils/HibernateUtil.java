package utils;

import exception.DataBaseException;
import exception.NotFoundException;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import model.Match;
import model.Player;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

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
        try (InputStream resource = HibernateUtil.class.getClassLoader().getResourceAsStream("someDataForPagination.sql");
        Session session = sessionFactory.openSession()
        ) {
            byte[] bytes = Objects.requireNonNull(resource).readAllBytes();
            String sql = new String(bytes);
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            throw new DataBaseException("Database error");
        }
        catch (IOException exception) {
            throw new NotFoundException("sql data is not found");
        }
    }
}
