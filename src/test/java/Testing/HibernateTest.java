package Testing;

import lombok.Cleanup;
import org.junit.jupiter.api.Test;
import utils.HibernateUtil;

public class HibernateTest {

    @Test
    public void checkH2() {
        @Cleanup var sessionFactory = HibernateUtil.getSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        session.getTransaction().commit();

    }

}
