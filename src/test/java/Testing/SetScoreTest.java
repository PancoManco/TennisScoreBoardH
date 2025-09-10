package Testing;

import model.Player;
import model.SetScore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetScoreTest {
    Player p1, p2;
    SetScore set;

    @BeforeEach
    void setUp() {
        p1 = Player.builder().name("Player1").build();
        p2 = Player.builder().name("Player2").build();
        set = new SetScore(p1, p2);
    }

    private void winGame(Player winner, Player loser, int times) {
        for (int i = 0; i < times; i++) {
            for (int j = 0; j < 4; j++) {
                set.playPoint(winner, loser);
            }
        }
    }

    @Test
    void testWinSet6_0() {
        winGame(p1, p2, 6);
        assertTrue(set.isSetOver());
        assertEquals(6, set.getGames(p1));
    }

    @Test
    void testNoWinAt5_5() {
        winGame(p1, p2, 5);
        winGame(p2, p1, 5);
        assertFalse(set.isSetOver());
    }

    @Test
    void testWinSet7_5() {
        winGame(p1, p2, 6);
        winGame(p2, p1, 5);
        set.playPoint(p1, p2);
        for (int i = 0; i < 3; i++) set.playPoint(p1, p2);
        assertTrue(set.isSetOver());
    }

    @Test
    void testTieBreakTriggeredAt6_6() {
        winGame(p1, p2, 6);
        winGame(p2, p1, 6);
        assertFalse(set.isSetOver());
        set.playPoint(p1, p2);
        assertTrue(set.getCurrentGameScore(p1).contains("("));
    }
}