package Testing;

import lombok.Cleanup;
import utils.HibernateUtil;

public class HibernateTest {


    public void checkH2() {
        @Cleanup var sessionFactory = HibernateUtil.getSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        session.getTransaction().commit();

    }

}
