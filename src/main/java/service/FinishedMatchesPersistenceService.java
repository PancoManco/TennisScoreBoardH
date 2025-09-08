package service;

import dao.Impl.MatchDao;
import model.Match;

import java.util.List;

public class FinishedMatchesPersistenceService {
    private final MatchDao matchDao = MatchDao.getInstance();

    public void persistMatch(Match match) {
        matchDao.save(match);
    }

    public List<Match> getFinishedMatches(int pageNumber, String playerName, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        if (playerName == null) {
            return matchDao.findAll(offset, pageSize);
        }
        return matchDao.findByPlayerNamePaginated(offset, pageSize, playerName);
   }

    public long getFinishedMatchesCount(String playerName) {
        if (playerName == null || playerName.trim().isEmpty()) {
            return matchDao.countAll();
        }
        return matchDao.countByPlayerName(playerName);
    }
}
