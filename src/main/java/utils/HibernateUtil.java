package utils;

import Model.Student;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j
public final class HibernateUtil {

    @Getter
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            log.debug("Building Hibernate SessionFactory");

            return new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();
        } catch (Throwable ex) {
           log.error(ex.getMessage(), ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static void shutdown() {
        getSessionFactory().close();
    }

    private HibernateUtil() {}
}
