package dao.Impl;

import dao.IPlayerDao;
import exception.DataBaseException;
import model.Player;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;
import java.util.Optional;

import static exception.ErrorMessages.ERROR_FINDING_BY_NAME;
import static exception.ErrorMessages.ERROR_SAVING_PLAYER;

public class PlayerDao implements IPlayerDao {
    private final static PlayerDao INSTANCE = new PlayerDao();

    private PlayerDao() {
    }

    public static PlayerDao getInstance() {
        return INSTANCE;
    }
    @Override
    public Optional<Player> findByName(String name) {
        String hql = "from Player where name=:name";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var query = session.createQuery(hql, Player.class).setParameter("name", name).uniqueResult();
            return Optional.ofNullable(query);
        }
        catch (HibernateException e) {
            throw new DataBaseException(ERROR_FINDING_BY_NAME);
        }
    }
    @Override
    public void save(Player player) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(player);
            tx.commit();
        }
        catch (HibernateException e) {
            throw new DataBaseException(ERROR_SAVING_PLAYER);
            //todo rollback
        }
    }
}
