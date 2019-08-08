import exception.GameAlreadyFinishedException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.Assert.*;

public class MainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    private Main game;

    public MainTest() {
        game = new Main();
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void playPoint_GenerateNumberZeroOr1_Success() {
        int point = game.playPoint();
        assertTrue(point == 0 || point == 1);
    }

    @Test
    public void calculateScore_ShouldBeZeroFifteen_Success() throws GameAlreadyFinishedException {
        final Short[] firstPlayersPoints = {0, 0, 0, 0};
        final Short[] secondPlayersPoints = {1, 0, 0, 0};
        final int[] score = game.calculateScore(Arrays.asList(firstPlayersPoints), Arrays.asList(secondPlayersPoints));
        assertEquals(score[0], 0);
        assertEquals(score[1], 15);
    }

    @Test
    public void calculateScore_ShouldBeAdvanceForty_Success() throws GameAlreadyFinishedException {
        final Short[] firstPlayersPoints = {1, 1, 1, 0, 0, 0, 1};
        final Short[] secondPlayersPoints = {0, 0, 0, 1, 1, 1, 0};
        final int[] score = game.calculateScore(Arrays.asList(firstPlayersPoints), Arrays.asList(secondPlayersPoints));
        assertEquals(score[0], 41);
        assertEquals(score[1], 40);
    }

    @Test
    public void calculateScore_ShouldBeFortyAdvance_Success() throws GameAlreadyFinishedException {
        final Short[] firstPlayersPoints = {1, 1, 1, 0, 0, 0, 0, 1, 0};
        final Short[] secondPlayersPoints = {0, 0, 0, 1, 1, 1, 1, 0, 1};
        final int[] score = game.calculateScore(Arrays.asList(firstPlayersPoints), Arrays.asList(secondPlayersPoints));
        assertEquals(score[0], 40);
        assertEquals(score[1], 41);
    }

    @Test
    public void calculateScore_ShouldBeFortyForty_Success() throws GameAlreadyFinishedException {
        final Short[] firstPlayersPoints = {1, 1, 1, 0, 0, 0, 1, 0};
        final Short[] secondPlayersPoints = {0, 0, 0, 1, 1, 1, 0, 1};
        final int[] score = game.calculateScore(Arrays.asList(firstPlayersPoints), Arrays.asList(secondPlayersPoints));
        assertEquals(score[0], 40);
        assertEquals(score[1], 40);
    }

    @Test(expected = GameAlreadyFinishedException.class)
    public void calculateScore_ShouldThrowGameAlreadyFinishedException_Error() throws GameAlreadyFinishedException {
        final Short[] firstPlayersPoints = {1, 1, 1, 0, 0, 0, 1, 1};
        final Short[] secondPlayersPoints = {0, 0, 0, 1, 1, 1, 0, 0};
        final int[] score = game.calculateScore(Arrays.asList(firstPlayersPoints), Arrays.asList(secondPlayersPoints));
    }


    @Test
    public void calculateWinner_ShouldFirstPlayerWin_Success() {
        final Short[] firstPlayersPoints = {1, 1, 1, 1};
        final Short[] secondPlayersPoints = {0, 0, 0, 0};
        final Boolean[] isWinner = game.calculateWinner(Arrays.asList(firstPlayersPoints), Arrays.asList(secondPlayersPoints));
        assertTrue(isWinner[0]);
        assertTrue(isWinner[1]);
    }

    @Test
    public void calculateWinner_ShouldSecondPlayerWin_Success() {
        final Short[] firstPlayersPoints = {0, 0, 0, 0};
        final Short[] secondPlayersPoints = {1, 1, 1, 1};
        final Boolean[] isWinner = game.calculateWinner(Arrays.asList(firstPlayersPoints), Arrays.asList(secondPlayersPoints));
        assertTrue(isWinner[0]);
        assertFalse(isWinner[1]);
    }

    @Test
    public void calculateWinner_ShouldNeitherPlayerWin_Fail() {
        final Short[] firstPlayersPoints = {1, 1, 0};
        final Short[] secondPlayersPoints = {0, 0, 1};
        final Boolean[] isWinner = game.calculateWinner(Arrays.asList(firstPlayersPoints), Arrays.asList(secondPlayersPoints));
        assertFalse(isWinner[0]);
        assertNull(isWinner[1]);
    }


    @Test
    public void printScore_ShouldPrintFortyForty_Success() {
        game.printScore(40, 40, 7);
        assertTrue("Turn: 7\nScore: Forty - Forty\n".contains(outContent.toString()));
    }

    @Test
    public void printScore_ShouldPrintAdvanceForty_Success() {
        game.printScore(41, 40, 8);
        assertTrue("Turn: 8\nScore: Advance - Forty\n".contains(outContent.toString()));
    }
}
