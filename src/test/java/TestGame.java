import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import tennis.Game;

public class TestGame {

    private Game game;

    @Before
    public void before() {
        game = Game.getInstance();
    }

    // Test use case of Game to be singleton
    @Test
    public void test_Game_ShouldBeSingleton_Success() {
        Game newGame = Game.getInstance();


        assertSame(game, newGame);
    }

    // Test use case of playing a round with random winner
    @Test
    public void test_PlayPoint_ShouldBeInRange01_Success() {
        int point = game.playPoint();
        assertTrue(point == 1 || point == 0);
    }
}
