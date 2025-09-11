package model;

public class GameScore {

    private int pointsPlayer1 = 0;
    private int pointsPlayer2 = 0;
    private boolean advantagePlayer1 = false;
    private boolean advantagePlayer2 = false;
    private final Player player1;
    private final Player player2;

    public GameScore(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;
    }
    public void playPoint(Player scorer, Player opponent) {
        if (isDeuce()) {
            if (hasAdvantage(scorer)) {
                reset();
                scorerWinsGame = scorer;
            } else if (hasAdvantage(opponent)) {
                removeAdvantage(opponent);
            } else {
                giveAdvantage(scorer);
            }
        } else if (hasAdvantage(scorer)) {
            reset();
            scorerWinsGame = scorer;
        } else {
            addPoint(scorer);
        }
    }

    private void addPoint(Player player) {
        if (player == player1) pointsPlayer1++;
        else pointsPlayer2++;
    }

    private boolean isDeuce() {
        return pointsPlayer1 >= 3 && pointsPlayer2 >= 3 && pointsPlayer1 == pointsPlayer2;
    }

    private boolean hasAdvantage(Player player) {
        return (player == player1 && advantagePlayer1) || (player == player2 && advantagePlayer2);
    }

    private void giveAdvantage(Player player) {
        if (player == player1) advantagePlayer1 = true;
        else advantagePlayer2 = true;
    }

    private void removeAdvantage(Player player) {
        if (player == player1) advantagePlayer1 = false;
        else advantagePlayer2 = false;
    }

    private void reset() {
        pointsPlayer1 = 0;
        pointsPlayer2 = 0;
        advantagePlayer1 = false;
        advantagePlayer2 = false;
        scorerWinsGame = null;
    }

    private Player scorerWinsGame = null;

    public boolean isGameOver() {
        return scorerWinsGame != null ||
               (pointsPlayer1 >= 4 && pointsPlayer1 - pointsPlayer2 >= 2) ||
               (pointsPlayer2 >= 4 && pointsPlayer2 - pointsPlayer1 >= 2);
    }

    public String getScore(Player player) {
        if (scorerWinsGame != null) return "Game";
        if (isDeuce() && !advantagePlayer1 && !advantagePlayer2) return "Deuce";
        if (hasAdvantage(player)) return "Advantage";
        if (hasAdvantage(player == player1 ? player2 : player1)) return "";
        return pointToString(player == player1 ? pointsPlayer1 : pointsPlayer2);
    }

    private String pointToString(int points) {
        return switch (points) {
            case 0 -> "Love";
            case 1 -> "15";
            case 2 -> "30";
            case 3 -> "40";
            default -> "";
        };
    }
}
