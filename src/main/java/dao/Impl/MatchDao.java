package dao.Impl;

import dao.IMatchDao;
import exception.DataBaseException;
import model.Match;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import utils.HibernateUtil;
import java.util.List;
import static exception.ErrorMessages.ERROR_SAVING_MATCH;

public class MatchDao  implements IMatchDao {
    private final static MatchDao INSTANCE = new MatchDao();
    private MatchDao() {}
    public static MatchDao getInstance() {
        return INSTANCE;
    }

    @Override
    public void save(Match match) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(match);
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
            throw new DataBaseException(ERROR_SAVING_MATCH);
        }
    }
    @Override
    public List<Match> findAll() {
        return List.of();
    }

    @Override
    public List<Match> findByPlayerName() {
        return List.of();
    }
}
