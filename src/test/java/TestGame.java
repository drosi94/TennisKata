import org.junit.Test;

import static org.junit.Assert.*;

import tennis.Game;

public class TestGame {
    // Test use case of playing a round with random winner
    @Test
    public void test_PlayPoint_ShouldBeInRange01_Success() {
        Game game = Game.getInstance();

        int point = game.playPoint();

        assertTrue(point == 1 || point == 0);
    }
}
