package dao.Impl;

import dao.IMatchDao;
import exception.DataBaseException;
import lombok.extern.slf4j.Slf4j;
import model.Match;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import utils.HibernateUtil;
import java.util.List;

import static exception.ErrorMessages.*;

@Slf4j
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
            log.info("Match Saved");
        }
        catch (HibernateException e) {
            log.error(ERROR_SAVING_MATCH,e);
            throw new DataBaseException(ERROR_SAVING_MATCH);
        }
    }
    @Override
    public List<Match> findAll(int offset, int pageSize) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            List<Match> matches = session.createQuery("FROM Match", Match.class)
                    .setFirstResult(offset)
                    .setMaxResults(pageSize)
                    .list();
            session.getTransaction().commit();
            log.debug("Matches list found size : {}",matches.size());
            return matches;
        } catch (HibernateException e) {
            log.error(ERROR_FINDING_ALL_MATCHES,e);
            throw new DataBaseException(ERROR_FINDING_ALL_MATCHES);
        }
    }

    @Override
    public List<Match> findByPlayerNamePaginated(int offset, int pageSize, String playerName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            String nameForQuery = "%" + playerName + "%";
            List<Match> matches = session.createQuery("FROM Match where player1.name ILIKE :name or player2.name ILIKE :name", Match.class)
                    .setParameter("name", nameForQuery)
                    .setFirstResult(offset)
                    .setMaxResults(pageSize)
                    .list();
            session.getTransaction().commit();
            log.debug("Matches list found size {} by playerName {}",matches.size(),playerName);
            return matches;
        } catch (HibernateException e) {
            log.error(ERROR_FINDING_BY_NAME_MATCHES,e);
            throw new DataBaseException(ERROR_FINDING_BY_NAME_MATCHES);
        }
    }
@Override
    public long countAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
             long result =session.createQuery("SELECT COUNT(m) FROM Match m", Long.class)
                    .uniqueResult();
             log.info("Matches found counter : {}", result);
             return result;
        } catch (HibernateException e) {
            log.error(ERROR_COUNTING_ALL_MATCHES,e);
            throw new DataBaseException(ERROR_COUNTING_ALL_MATCHES);
        }
    }
    @Override
    public long countByPlayerName(String playerName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String nameForQuery = "%" + playerName + "%";
             long result =session.createQuery(
                            "SELECT COUNT(m) FROM Match m WHERE m.player1.name ILIKE :name OR m.player2.name ILIKE :name",
                            Long.class)
                    .setParameter("name", nameForQuery)
                    .uniqueResult();
            log.info("Matches counter found {} by playerName {}", result,playerName);
            return result;
        } catch (HibernateException e) {
            log.error(ERROR_COUNTING_ALL_MATCHES_BY_PLAYER_NAME,e);
            throw new DataBaseException(ERROR_COUNTING_ALL_MATCHES_BY_PLAYER_NAME);
        }
    }


}
