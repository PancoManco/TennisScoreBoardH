package Testing;

import lombok.Cleanup;
import model.Match;
import model.Player;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import utils.HibernateUtil;

public class HibernateTest {

    @Test
    public void checkClassConnection() {
        @Cleanup var sessionFactory = HibernateUtil.getSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        Player player1 = new Player();
        player1.setName("Fadis");

        Player player2 = new Player();
        player2.setName("Iljas");

        Match match1 = new Match();
        match1.setPlayer1(player1);
        match1.setPlayer2(player2);
        match1.setWinner(player1);
        session.persist(match1);

        session.getTransaction().commit();

    }


    @Test
    public void checkH2() {
        @Cleanup var sessionFactory = HibernateUtil.getSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        session.getTransaction().commit();

    }

}
