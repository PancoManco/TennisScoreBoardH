package model;

public class TieBreakGame {
    private int pointsPlayer1 = 0;
    private int pointsPlayer2 = 0;

    private final Player player1;
    private final Player player2;

    public TieBreakGame(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;
    }

    public void playPoint(Player scorer) {
        if (scorer == player1) pointsPlayer1++;
        else pointsPlayer2++;
    }

    public boolean isGameOver() {
        return (pointsPlayer1 >= 7 || pointsPlayer2 >= 7)
               && Math.abs(pointsPlayer1 - pointsPlayer2) >= 2;
    }

    public String getScore(Player player) {
        int p1 = pointsPlayer1;
        int p2 = pointsPlayer2;
        return player == player1 ? (p1 + " (" + p2 + ")") : (p2 + " (" + p1 + ")");
    }
}