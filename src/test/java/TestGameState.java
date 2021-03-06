import exception.GameAlreadyFinishedException;
import logging.StaticAppender;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import tennis.GameState;
import tennis.model.Player;
import tennis.model.Point;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

public class TestGameState {

    private Player player1, player2;
    private GameState gameState;

    @Before
    public void beforeAll() {
        player1 = new Player();
        player2 = new Player();
        gameState = new GameState(player1, player2);
    }

    @BeforeEach
    public void clearLoggingStatements() {
        StaticAppender.clearEvents();
    }

    // Use case 'Calculate game score based on the points'
    @Test
    public void test_CalculateScore_ShouldBe1540_Success() {
        Boolean[] pointPerRoundFirstPlayer ={true, false, false, false};
        Boolean[] pointPerRoundSecondPlayer ={false, true, true, true};
        player1.setPointPerRoundList(Arrays.asList(pointPerRoundFirstPlayer));
        player2.setPointPerRoundList(Arrays.asList(pointPerRoundSecondPlayer));

        gameState.calculateScore();

        assertEquals(Point.FIFTEEN, player1.getScore());
        assertEquals(Point.FORTY, player2.getScore());
    }

    @Test
    public void test_CalculateScore_ShouldBe40ADVANTAGE_Success() {
        Boolean[] pointPerRoundFirstPlayer ={true, false, true, true, false, false, false};
        Boolean[] pointPerRoundSecondPlayer ={false, true, false, false, true, true, true};
        player1.setPointPerRoundList(Arrays.asList(pointPerRoundFirstPlayer));
        player2.setPointPerRoundList(Arrays.asList(pointPerRoundSecondPlayer));

        gameState.calculateScore();

        assertEquals(Point.FORTY, player1.getScore());
        assertEquals(Point.ADVANTAGE, player2.getScore());
    }

    @Test
    public void test_CalculateScore_ShouldBe4040_Success() {
        Boolean[] pointPerRoundFirstPlayer ={true, false, true, true, false, false};
        Boolean[] pointPerRoundSecondPlayer ={false, true, false, false, true, true};
        player1.setPointPerRoundList(Arrays.asList(pointPerRoundFirstPlayer));
        player2.setPointPerRoundList(Arrays.asList(pointPerRoundSecondPlayer));

        gameState.calculateScore();

        assertEquals(Point.FORTY, player1.getScore());
        assertEquals(Point.FORTY, player2.getScore());
    }

    @Test
    public void test_CalculateScore_ShouldBeADVANTAGE40_Success() {
        Boolean[] pointPerRoundFirstPlayer ={true, false, true, true, false, false, true};
        Boolean[] pointPerRoundSecondPlayer ={false, true, false, false, true, true, false};
        player1.setPointPerRoundList(Arrays.asList(pointPerRoundFirstPlayer));
        player2.setPointPerRoundList(Arrays.asList(pointPerRoundSecondPlayer));

        gameState.calculateScore();

        assertEquals(Point.ADVANTAGE, player1.getScore());
        assertEquals(Point.FORTY, player2.getScore());
    }

    @Test
    public void test_CalculateScore_ShouldThrowGameAlreadyFinishedException_Fail() {
        Boolean[] pointPerRoundFirstPlayer ={false, true, false, true, true, false, true, true};
        Boolean[] pointPerRoundSecondPlayer ={true, false, true, false, false, true, false, false};
        player1.setPointPerRoundList(Arrays.asList(pointPerRoundFirstPlayer));
        player2.setPointPerRoundList(Arrays.asList(pointPerRoundSecondPlayer));

        gameState.calculateWinner();

        Assertions.assertThrows(GameAlreadyFinishedException.class, gameState::calculateScore);
    }

    // Use case 'Print score based on player points and turn'
    @Test
    public void test_PrintScore_ShouldPrint40ADVANTAGE_Success() {
        player1.setScore(Point.FORTY);
        player2.setScore(Point.ADVANTAGE);
        gameState.setTurn(7);

        gameState.printScore();

        assertThat(StaticAppender.getEvents()).extracting("message").containsOnly("Turn: 7\nScore: 40 - ADVANTAGE\n");
    }

    // Use case 'Check if game is finished and calculate the winner'
    @Test
    public void test_CalculateWinner_ShouldGameNotFinished_Success() {
        Boolean[] pointPerRoundFirstPlayer ={true, false, false, false};
        Boolean[] pointPerRoundSecondPlayer ={false, true, true, true};
        player1.setPointPerRoundList(Arrays.asList(pointPerRoundFirstPlayer));
        player2.setPointPerRoundList(Arrays.asList(pointPerRoundSecondPlayer));

        gameState.calculateWinner();

        assertFalse(gameState.isGameFinished());
        assertNull(gameState.getWinner());
    }

    @Test
    public void test_CalculateWinner_ShouldGameFinishedAndPlayer1Won_Success() {
        Boolean[] pointPerRoundFirstPlayer ={false, true, false, true, true, false, true, true};
        Boolean[] pointPerRoundSecondPlayer ={true, false, true, false, false, true, false, false};
        player1.setPointPerRoundList(Arrays.asList(pointPerRoundFirstPlayer));
        player2.setPointPerRoundList(Arrays.asList(pointPerRoundSecondPlayer));

        gameState.calculateWinner();

        assertTrue(gameState.isGameFinished());
        assertSame(player1, gameState.getWinner());
    }

    @Test
    public void test_CalculateWinner_ShouldGameFinishedAndPlayer2WonSimple_Success() {
        Boolean[] pointPerRoundFirstPlayer ={true, false, false, false, false};
        Boolean[] pointPerRoundSecondPlayer ={false, true, true, true, true};
        player1.setPointPerRoundList(Arrays.asList(pointPerRoundFirstPlayer));
        player2.setPointPerRoundList(Arrays.asList(pointPerRoundSecondPlayer));

        gameState.calculateWinner();

        assertTrue(gameState.isGameFinished());
        assertSame(player2, gameState.getWinner());
    }

    @Test
    public void test_CalculateWinner_ShouldGameFinishedAndPlayer2WonComplicated_Success() {
        Boolean[] pointPerRoundFirstPlayer ={true, false, true, false, true, false, false, true, false, false};
        Boolean[] pointPerRoundSecondPlayer ={false, true, false, true, false, true, true, false, true, true};
        player1.setPointPerRoundList(Arrays.asList(pointPerRoundFirstPlayer));
        player2.setPointPerRoundList(Arrays.asList(pointPerRoundSecondPlayer));

        gameState.calculateWinner();

        assertTrue(gameState.isGameFinished());
        assertSame(player2, gameState.getWinner());
    }
}
