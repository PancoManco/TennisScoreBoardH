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
    private final List<SetScore> sets = new ArrayList<>();
    private int setsWonPlayer1 = 0;
    private int setsWonPlayer2 = 0;
    private boolean matchOver = false;

    public MatchScore(String player1Name, String player2Name) {
        this.player1 = Player.builder().name(player1Name).build();
        this.player2 = Player.builder().name(player2Name).build();
        sets.add(new SetScore(player1, player2));
    }

    public void playPoint(String playerName) {
        if (matchOver) return;

        Player scorer = getPlayerByName(playerName);
        Player opponent = (scorer == player1) ? player2 : player1;

        SetScore currentSet = getCurrentSet();
        currentSet.playPoint(scorer, opponent);

        if (currentSet.isSetOver()) {
            if (currentSet.getGames(player1) > currentSet.getGames(player2)) setsWonPlayer1++;
            else setsWonPlayer2++;

//            System.out.println("✅ Сет выиграл " + (currentSet.getGames(player1) > currentSet.getGames(player2)
//                    ? player1.getName() : player2.getName()));

            if (setsWonPlayer1 == 2 || setsWonPlayer2 == 2) {
                matchOver = true;
            } else {
                sets.add(new SetScore(player1, player2));
            }
        }
    }
    public boolean isMatchOver() {
        return matchOver;
    }

    public String getWinner() {
        if (!matchOver) return "Матч не завершён";
        return (setsWonPlayer1 > setsWonPlayer2) ? player1.getName() : player2.getName();
    }

    public SetScore getCurrentSet() {
        return sets.get(sets.size() - 1);
    }

    private Player getPlayerByName(String name) {
        if (player1.getName().equalsIgnoreCase(name)) return player1;
        return player2;
    }


}
