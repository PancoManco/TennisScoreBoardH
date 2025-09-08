package service;

import dao.Impl.MatchDao;
import model.Match;

import java.util.List;

public class FinishedMatchesPersistenceService {
    private final MatchDao matchDao = MatchDao.getInstance();

    public void persistMatch(Match match) {
        matchDao.save(match);
    }

    public List<Match> getFinishedMatches() {
    return matchDao.findAll();
   }
}
