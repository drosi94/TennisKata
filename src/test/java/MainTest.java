import exception.GameAlreadyFinishedException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainTest {

    @Test
    public void playPoint_GenerateNumberZeroOr1_Success() {
        int point = Main.playPoint();
        assertTrue(point == 0 || point == 1);
    }

    @Test
    public void calculateScore_ShouldBeZeroFifteen_Success() throws GameAlreadyFinishedException {
        final int[] firstPlayersPoints = {0, 0, 0, 0};
        final int[] secondPlayersPoints = {1, 0, 0, 0};
        final int[] score = Main.calculateScore(firstPlayersPoints, secondPlayersPoints);
        assertEquals(score[0], 0);
        assertEquals(score[1], 15);
    }

    @Test
    public void calculateScore_ShouldBeAdvanceForty_Success() throws GameAlreadyFinishedException {
        final int[] firstPlayersPoints = {1, 1, 1, 0, 0, 0, 1};
        final int[] secondPlayersPoints = {0, 0, 0, 1, 1, 1, 0};
        final int[] score = Main.calculateScore(firstPlayersPoints, secondPlayersPoints);
        assertEquals(score[0], 41);
        assertEquals(score[1], 40);
    }

    @Test
    public void calculateScore_ShouldBeFortyForty_Success() throws GameAlreadyFinishedException {
        final int[] firstPlayersPoints = {1, 1, 1, 0, 0, 0, 1, 0};
        final int[] secondPlayersPoints = {0, 0, 0, 1, 1, 1, 0, 1};
        final int[] score = Main.calculateScore(firstPlayersPoints, secondPlayersPoints);
        assertEquals(score[0], 40);
        assertEquals(score[1], 40);
    }

    @Test(expected = GameAlreadyFinishedException.class)
    public void calculateScore_ShouldThrowGameAlreadyFinishedException_Error() throws GameAlreadyFinishedException {
        final int[] firstPlayersPoints = {1, 1, 1, 0, 0, 0, 1, 1};
        final int[] secondPlayersPoints = {0, 0, 0, 1, 1, 1, 0, 0};
        final int[] score = Main.calculateScore(firstPlayersPoints, secondPlayersPoints);
    }
}
