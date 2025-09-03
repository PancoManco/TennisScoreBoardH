package dao;

import model.Match;

import java.util.List;

public interface IMatchDao {
    void save(Match match);
    List<Match> findAll();
    List<Match> findByPlayerName();

}
