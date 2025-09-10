package Testing;
import model.Player;
import model.TieBreakGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TieBreakGameTest {
    Player p1, p2;
    TieBreakGame tieBreak;

    @BeforeEach
    void setUp() {
        p1 = Player.builder().name("Player1").build();
        p2 = Player.builder().name("Player2").build();
        tieBreak = new TieBreakGame(p1, p2);
    }

    @Test
    void testTieBreakWin7_5() {
        for (int i = 0; i < 7; i++) tieBreak.playPoint(p1);
        for (int i = 0; i < 5; i++) tieBreak.playPoint(p2);
        assertTrue(tieBreak.isGameOver());
    }

    @Test
    void testTieBreakContinuesAt6_6() {
        for (int i = 0; i < 6; i++) {
            tieBreak.playPoint(p1);
            tieBreak.playPoint(p2);
        }
        assertFalse(tieBreak.isGameOver());
    }

    @Test
    void testTieBreakWin10_8() {
        for (int i = 0; i < 10; i++) tieBreak.playPoint(p1);
        for (int i = 0; i < 8; i++) tieBreak.playPoint(p2);
        assertTrue(tieBreak.isGameOver());
    }
}
