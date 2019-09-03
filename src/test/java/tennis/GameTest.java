package tennis;

import exception.GameAlreadyFinishedException;
import logging.StaticAppender;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;


public class GameTest {
    private Game game;

    public GameTest() {
        game = Game.getInstance();
    }

    @BeforeEach
    public void clearLoggingStatements() {
        StaticAppender.clearEvents();
    }

    @Test
    public void playPoint_GenerateNumberZeroOr1_Success() {
        int point = game.playPoint();
        assertTrue(point == 0 || point == 1);
    }

    @Test
    public void calculateScore_ShouldBeZeroFifteen_Success() throws GameAlreadyFinishedException {
        final Boolean[] firstPlayersPoints = {false};
        final Boolean[] secondPlayersPoints = {true};
        final int[] score = game.calculateScore(Arrays.asList(firstPlayersPoints), Arrays.asList(secondPlayersPoints));
        assertEquals(score[0], 0);
        assertEquals(score[1], 15);
    }

    @Test
    public void calculateScore_ShouldBeAdvanceForty_Success() throws GameAlreadyFinishedException {
        final Boolean[] firstPlayersPoints = {true, true, true, false, false, false, true};
        final Boolean[] secondPlayersPoints = {false, false, false, true, true, true, false};
        final int[] score = game.calculateScore(Arrays.asList(firstPlayersPoints), Arrays.asList(secondPlayersPoints));
        assertEquals(score[0], 41);
        assertEquals(score[1], 40);
    }

    @Test
    public void calculateScore_ShouldBeFortyAdvance_Success() throws GameAlreadyFinishedException {
        final Boolean[] firstPlayersPoints = {true, true, true, false, false, false, false, true, false};
        final Boolean[] secondPlayersPoints = {false, false, false, true, true, true, true, false, true};
        final int[] score = game.calculateScore(Arrays.asList(firstPlayersPoints), Arrays.asList(secondPlayersPoints));
        assertEquals(score[0], 40);
        assertEquals(score[1], 41);
    }

    @Test
    public void calculateScore_ShouldBeFortyForty_Success() throws GameAlreadyFinishedException {
        final Boolean[] firstPlayersPoints = {true, true, true, false, false, false, true, false};
        final Boolean[] secondPlayersPoints = {false, false, false, true, true, true, false, true};
        final int[] score = game.calculateScore(Arrays.asList(firstPlayersPoints), Arrays.asList(secondPlayersPoints));
        assertEquals(score[0], 40);
        assertEquals(score[1], 40);
    }

    @Test(expected = GameAlreadyFinishedException.class)
    public void calculateScore_ShouldThrowGameAlreadyFinishedException_Error() throws GameAlreadyFinishedException {
        final Boolean[] firstPlayersPoints = {true, true, true, false, false, false, true, true};
        final Boolean[] secondPlayersPoints = {false, false, false, true, true, true, false, false};
        final int[] score = game.calculateScore(Arrays.asList(firstPlayersPoints), Arrays.asList(secondPlayersPoints));
    }


    @Test
    public void calculateWinner_ShouldFirstPlayerWin_Success() {
        final Boolean[] firstPlayersPoints = {true, true, true, true};
        final Boolean[] secondPlayersPoints = {false, false, false, false};
        final Boolean[] isWinner = game.calculateWinner(Arrays.asList(firstPlayersPoints), Arrays.asList(secondPlayersPoints));
        assertTrue(isWinner[0]);
        assertTrue(isWinner[1]);
    }

    @Test
    public void calculateWinner_ShouldSecondPlayerWin_Success() {
        final Boolean[] firstPlayersPoints = {false, false, false, false};
        final Boolean[] secondPlayersPoints = {true, true, true, true};
        final Boolean[] isWinner = game.calculateWinner(Arrays.asList(firstPlayersPoints), Arrays.asList(secondPlayersPoints));
        assertTrue(isWinner[0]);
        assertFalse(isWinner[1]);
    }

    @Test
    public void calculateWinner_ShouldNeitherPlayerWin_Fail() {
        final Boolean[] firstPlayersPoints = {true, true, false};
        final Boolean[] secondPlayersPoints = {false, false, true};
        final Boolean[] isWinner = game.calculateWinner(Arrays.asList(firstPlayersPoints), Arrays.asList(secondPlayersPoints));
        assertFalse(isWinner[0]);
        assertNull(isWinner[1]);
    }


    @Test
    public void printScore_ShouldPrintFortyForty_Success() {
        game.printScore(40, 40, 7);
        assertThat(StaticAppender.getEvents()).extracting("message").containsOnly("Turn: 7\nScore: Forty - Forty\n");
    }

    @Test
    public void printScore_ShouldPrintAdvanceForty_Success() {
        game.printScore(41, 40, 8);
        assertThat(StaticAppender.getEvents()).extracting("message").containsOnly("Turn: 8\nScore: Advance - Forty\n");

    }
}
