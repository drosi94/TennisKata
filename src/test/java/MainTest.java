import exception.GameAlreadyFinishedException;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void playPoint_GenerateNumberZeroOr1_Success() {
        int point = Main.playPoint();
        assertTrue(point == 0 || point == 1);
    }

    @Test
    public void calculateScore_ShouldBeZeroFifteen_Success() throws GameAlreadyFinishedException {
        final Short[] firstPlayersPoints = {0, 0, 0, 0};
        final Short[] secondPlayersPoints = {1, 0, 0, 0};
        final int[] score = Main.calculateScore(Arrays.asList(firstPlayersPoints), Arrays.asList(secondPlayersPoints));
        assertEquals(score[0], 0);
        assertEquals(score[1], 15);
    }

    @Test
    public void calculateScore_ShouldBeAdvanceForty_Success() throws GameAlreadyFinishedException {
        final Short[] firstPlayersPoints = {1, 1, 1, 0, 0, 0, 1};
        final Short[] secondPlayersPoints = {0, 0, 0, 1, 1, 1, 0};
        final int[] score = Main.calculateScore(Arrays.asList(firstPlayersPoints), Arrays.asList(secondPlayersPoints));
        assertEquals(score[0], 41);
        assertEquals(score[1], 40);
    }

    @Test
    public void calculateScore_ShouldBeFortyForty_Success() throws GameAlreadyFinishedException {
        final Short[] firstPlayersPoints = {1, 1, 1, 0, 0, 0, 1, 0};
        final Short[] secondPlayersPoints = {0, 0, 0, 1, 1, 1, 0, 1};
        final int[] score = Main.calculateScore(Arrays.asList(firstPlayersPoints), Arrays.asList(secondPlayersPoints));
        assertEquals(score[0], 40);
        assertEquals(score[1], 40);
    }

    @Test(expected = GameAlreadyFinishedException.class)
    public void calculateScore_ShouldThrowGameAlreadyFinishedException_Error() throws GameAlreadyFinishedException {
        final Short[] firstPlayersPoints = {1, 1, 1, 0, 0, 0, 1, 1};
        final Short[] secondPlayersPoints = {0, 0, 0, 1, 1, 1, 0, 0};
        final int[] score = Main.calculateScore(Arrays.asList(firstPlayersPoints), Arrays.asList(secondPlayersPoints));
    }


    @Test
    public void calculateWinner_ShouldFirstPlayerWin_Success() {
        final Short[] firstPlayersPoints = {1, 1, 1, 1};
        final Short[] secondPlayersPoints = {0, 0, 0, 0};
        final Boolean[] isWinner = Main.calculateWinner(Arrays.asList(firstPlayersPoints), Arrays.asList(secondPlayersPoints));
        assertTrue(isWinner[0]);
        assertTrue(isWinner[1]);
    }

    @Test
    public void calculateWinner_ShouldSecondPlayerWin_Success() {
        final Short[] firstPlayersPoints = {0, 0, 0, 0};
        final Short[] secondPlayersPoints = {1, 1, 1, 1};
        final Boolean[] isWinner = Main.calculateWinner(Arrays.asList(firstPlayersPoints), Arrays.asList(secondPlayersPoints));
        assertTrue(isWinner[0]);
        assertFalse(isWinner[1]);
    }

    @Test
    public void calculateWinner_ShouldNeitherPlayerWin_Fail() {
        final Short[] firstPlayersPoints = {1, 1, 0};
        final Short[] secondPlayersPoints = {0, 0, 1};
        final Boolean[] isWinner = Main.calculateWinner(Arrays.asList(firstPlayersPoints), Arrays.asList(secondPlayersPoints));
        assertFalse(isWinner[0]);
        assertNull(isWinner[1]);
    }
}
