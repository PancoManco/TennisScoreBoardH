package dao;

import model.Match;

import java.util.List;

public interface IMatchDao {
    void save(Match match);
    List<Match> findAll(int offset, int pageSize);
    List<Match> findByPlayerNamePaginated(int offset, int pageSize, String playerName);
    long countAll();
}
