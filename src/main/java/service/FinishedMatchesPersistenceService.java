package service;

import dao.Impl.MatchDao;
import dto.MatchDto;
import mapper.MatchMapper;
import model.Match;

import java.util.List;

public class FinishedMatchesPersistenceService {
    private final MatchDao matchDao = MatchDao.getInstance();
    private final MatchMapper matchMapper= MatchMapper.INSTANCE;

    public void persistMatch(MatchDto matchDto) {
        Match match =matchMapper.toEntity(matchDto);
        matchDao.save(match);
    }

    public List<MatchDto> getFinishedMatches(int pageNumber, String playerName, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        if (playerName == null) {
            return matchDao.findAll(offset, pageSize).stream().map(matchMapper::toDTO).toList();
        }
        return matchDao.findByPlayerNamePaginated(offset, pageSize, playerName).stream().map(matchMapper::toDTO).toList();
   }

    public long getFinishedMatchesCount(String playerName) {
        if (playerName == null || playerName.trim().isEmpty()) {
            return matchDao.countAll();
        }
        return matchDao.countByPlayerName(playerName);
    }
}
