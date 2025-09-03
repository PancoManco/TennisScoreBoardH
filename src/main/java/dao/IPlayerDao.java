package dao;

import model.Player;

import java.util.Optional;

public interface IPlayerDao {
    Optional<Player> findByName(String name);
    void save(Player player);
}
