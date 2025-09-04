package service;

import dao.Impl.MatchDao;
import model.Match;

public class FinishedMatchesPersistenceService {
    private final MatchDao matchDao = MatchDao.getInstance();

    public void persistMatch(Match match) {
        matchDao.save(match);
    }

//    public List<Match> getFinishedMatches() {
//
//    }
}
