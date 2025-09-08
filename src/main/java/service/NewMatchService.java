package service;

import dao.IPlayerDao;
import dao.Impl.PlayerDao;
import model.Match;
import model.MatchScore;
import model.Player;

import java.util.Optional;

public class NewMatchService {
    private final IPlayerDao playerDao = PlayerDao.getInstance();

    public Match createNewMatch(String playerName1, String playerName2) {
        Optional<Player> playerOneOpt = playerDao.findByName(playerName1);
        Optional<Player> playerTwoOpt = playerDao.findByName(playerName2);
        Player playerOne;
        Player playerTwo;
        if (playerOneOpt.isEmpty()) {
            playerOne = Player.builder().name(playerName1).build();
            playerDao.save(playerOne);
        } else playerOne = playerOneOpt.get();
        if (playerTwoOpt.isEmpty()) {
            playerTwo = Player.builder().name(playerName2).build();
            playerDao.save(playerTwo);
        } else playerTwo = playerTwoOpt.get();
        return  Match.builder()
                .player1(playerOne)
                .player2(playerTwo)
                .matchScore(new MatchScore(playerOne, playerTwo))
                .build();
    }
}
