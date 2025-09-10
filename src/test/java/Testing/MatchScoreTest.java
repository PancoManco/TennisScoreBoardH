package Testing;

import model.MatchScore;
import model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchScoreTest {
    Player p1, p2;
    MatchScore match;

    @BeforeEach
    void setUp() {
        p1 = Player.builder().name("Player1").build();
        p2 = Player.builder().name("Player2").build();
        match = new MatchScore(p1, p2);
    }

    private void winGame(Player winner, Player loser, int times) {
        for (int i = 0; i < times; i++) {
            for (int j = 0; j < 4; j++) {
                match.playPoint(winner.getName());
            }
        }
    }

    private void winSet(Player winner, Player loser) {
        winGame(winner, loser, 6);
    }

    @Test
    void testWinMatch2_0() {
        winSet(p1, p2);
        winSet(p1, p2);
        assertTrue(match.isMatchOver());
        assertEquals("Player1", match.getWinner());
    }

    @Test
    void testWinMatch2_1() {
        winSet(p1, p2);
        winSet(p2, p1);
        winSet(p1, p2);
        assertTrue(match.isMatchOver());
        assertEquals("Player1", match.getWinner());
    }

    @Test
    void testNoProgressAfterMatchOver() {
        winSet(p1, p2);
        winSet(p1, p2);
        assertTrue(match.isMatchOver());

        int gamesBefore = match.getCurrentSet().getGames(p1);
        match.playPoint(p1.getName());
        int gamesAfter = match.getCurrentSet().getGames(p1);
        assertEquals(gamesBefore, gamesAfter);
    }
}
