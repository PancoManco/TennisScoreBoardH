package Testing;

import model.GameScore;
import model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameScoreTest {
    Player p1, p2;
    GameScore game;

    @BeforeEach
    void setUp() {
        p1 = Player.builder().name("Player1").build();
        p2 = Player.builder().name("Player2").build();
        game = new GameScore(p1, p2);
    }

    @Test
    void testPlayerWinsWithoutDeuce() {
        game.playPoint(p1, p2);
        game.playPoint(p1, p2);
        game.playPoint(p1, p2);
        game.playPoint(p1, p2);
        assertTrue(game.isGameOver());
        assertEquals("", game.getScore(p1));
    }

    @Test
    void testDeuce() {
        for (int i = 0; i < 3; i++) {
            game.playPoint(p1, p2);
            game.playPoint(p2, p1);
        }
        assertEquals("Deuce", game.getScore(p1));
        assertEquals("Deuce", game.getScore(p2));
    }

    @Test
    void testAdvantageAndWin() {
        for (int i = 0; i < 3; i++) {
            game.playPoint(p1, p2);
            game.playPoint(p2, p1);
        }
        game.playPoint(p1, p2);
        assertEquals("Advantage", game.getScore(p1));
        game.playPoint(p1, p2);
        assertTrue(game.isGameOver());
        assertEquals("Game", game.getScore(p1));
    }

    @Test
    void testRemoveAdvantage() {
        for (int i = 0; i < 3; i++) {
            game.playPoint(p1, p2);
            game.playPoint(p2, p1);
        }
        game.playPoint(p1, p2);
        game.playPoint(p2, p1);
        assertEquals("Deuce", game.getScore(p1));
    }
}