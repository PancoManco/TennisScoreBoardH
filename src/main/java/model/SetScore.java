package model;

public class SetScore {
    private int gamesPlayer1 = 0;
    private int gamesPlayer2 = 0;

    private final Player player1;
    private final Player player2;

    private GameScore currentGame;
    private TieBreakGame tieBreakGame;

    public SetScore(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentGame = new GameScore(player1, player2);
    }

    public void playPoint(Player scorer, Player opponent) {
        if (isTieBreak()) {
            playTieBreakPoint(scorer);
        } else {
            playRegularPoint(scorer, opponent);
        }
    }

    private void playTieBreakPoint(Player scorer) {
        if (tieBreakGame == null) tieBreakGame = new TieBreakGame(player1, player2);
        tieBreakGame.playPoint(scorer);
        if (tieBreakGame.isGameOver()) {
            incrementGames(scorer);
        }
    }

    private void playRegularPoint(Player scorer, Player opponent) {
        currentGame.playPoint(scorer, opponent);
        if (currentGame.isGameOver()) {
            incrementGames(scorer);
            currentGame = new GameScore(player1, player2);
        }
    }

    private void incrementGames(Player scorer) {
        if (scorer == player1) gamesPlayer1++;
        else gamesPlayer2++;
    }
    public boolean isSetOver() {
        return (hasMinimumGamesToWin() && gamesDifferenceAtLeastTwo()) || hasReachedSevenGames();
    }

    private boolean hasMinimumGamesToWin() {
        return gamesPlayer1 >= 6 || gamesPlayer2 >= 6;
    }

    private boolean gamesDifferenceAtLeastTwo() {
        return Math.abs(gamesPlayer1 - gamesPlayer2) >= 2;
    }

    private boolean hasReachedSevenGames() {
        return gamesPlayer1 == 7 || gamesPlayer2 == 7;
    }

    private boolean isTieBreak() {
        return gamesPlayer1 == 6 && gamesPlayer2 == 6;
    }

    public int getGames(Player player) {
        return player == player1 ? gamesPlayer1 : gamesPlayer2;
    }

    public String getCurrentGameScore(Player player) {
        if (tieBreakGame != null) return tieBreakGame.getScore(player);
        return currentGame.getScore(player);
    }

}