package model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class MatchScore {
    private final Player player1;
    private final Player player2;

    public MatchScore(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        sets.add(new SetScore(player1, player2));
    }

    private final List<SetScore> sets = new ArrayList<>();
    private int setsWonPlayer1 = 0;
    private int setsWonPlayer2 = 0;
    private boolean matchOver = false;
    private final int SETS_TO_WIN = 2;

    public void playPoint(String playerName) {
        if (matchOver) return;
        Player scorer = getPlayerByName(playerName);
        Player opponent = (scorer == player1) ? player2 : player1;
        SetScore currentSet = getCurrentSet();
        currentSet.playPoint(scorer, opponent);
        if (currentSet.isSetOver()) {
            updateSetsWon(currentSet);
            if (isMatchWon()) {
                matchOver = true;
            } else {
                sets.add(new SetScore(player1, player2));
            }
        }
    }
    private void updateSetsWon(SetScore set) {
        if (set.getGames(player1) > set.getGames(player2)) setsWonPlayer1++;
        else setsWonPlayer2++;
    }
    private boolean isMatchWon() {
        return setsWonPlayer1 == SETS_TO_WIN || setsWonPlayer2 == SETS_TO_WIN;
    }
    public boolean isMatchOver() {
        return matchOver;
    }

    public String getWinner() {
        return (setsWonPlayer1 > setsWonPlayer2) ? player1.getName() : player2.getName();
    }
    public Player getWinnerPlayer() {
        return (setsWonPlayer1 > setsWonPlayer2) ? player1 : player2;
    }
    public SetScore getCurrentSet() {
        return sets.get(sets.size() - 1);
    }
    private Player getPlayerByName(String name) {
        if (player1.getName().equalsIgnoreCase(name)) return player1;
        return player2;
    }


}
