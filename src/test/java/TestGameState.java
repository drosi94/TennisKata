import exception.GameAlreadyFinishedException;
import logging.StaticAppender;
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

    @BeforeEach
    public void clearLoggingStatements() {
        StaticAppender.clearEvents();
    }

    // Use case 'Calculate game score based on the points'
    @Test
    public void test_CalculateScore_ShouldBe1540_Success() {
        Player player1 = new Player();
        Player player2 = new Player();
        GameState gameState = new GameState(player1, player2);
        Boolean[] pointPerRoundFirstPlayer ={true, false, false, false};
        Boolean[] pointPerRoundSecondPlayer ={false, true, true, true};
        player1.setPointPerRoundList(Arrays.asList(pointPerRoundFirstPlayer));
        player2.setPointPerRoundList(Arrays.asList(pointPerRoundSecondPlayer));

        gameState.calculateScore();

        assertEquals(Point.FIFTEEN, player1.getScore());
        assertEquals(Point.FORTY, player2.getScore());
    }

    @Test
    public void test_CalculateScore_ShouldThrowGameAlreadyFinishedException_Fail() {
        Player player1 = new Player();
        Player player2 = new Player();
        GameState gameState = new GameState(player1, player2);
        Boolean[] pointPerRoundFirstPlayer ={false, true, false, true, true, false, true, true};
        Boolean[] pointPerRoundSecondPlayer ={true, false, true, false, false, true, false, false};
        player1.setPointPerRoundList(Arrays.asList(pointPerRoundFirstPlayer));
        player2.setPointPerRoundList(Arrays.asList(pointPerRoundSecondPlayer));

        gameState.calculateWinner();

        Assertions.assertThrows(GameAlreadyFinishedException.class, gameState::calculateScore);
    }

    // Use case 'Print score based on player points and turn'
    @Test
    public void test_PrintScore_ShouldPrint40ADVANCE_Success() {
        Player player1 = new Player();
        Player player2 = new Player();
        GameState gameState = new GameState(player1, player2);
        player1.setScore(Point.FORTY);
        player2.setScore(Point.ADVANTAGE);
        gameState.setTurn(7);

        gameState.printScore();

        assertThat(StaticAppender.getEvents()).extracting("message").containsOnly("Turn: 7\nScore: 40 - ADVANTAGE\n");
    }

    // Use case 'Check if game is finished and calculate the winner'
    @Test
    public void test_CalculateWinner_ShouldGameNotFinished_Success() {
        Player player1 = new Player();
        Player player2 = new Player();
        GameState gameState = new GameState(player1, player2);
        Boolean[] pointPerRoundFirstPlayer ={true, false, false, false};
        Boolean[] pointPerRoundSecondPlayer ={false, true, true, true};
        player1.setPointPerRoundList(Arrays.asList(pointPerRoundFirstPlayer));
        player2.setPointPerRoundList(Arrays.asList(pointPerRoundSecondPlayer));

        gameState.calculateWinner();

        assertFalse(gameState.isGameFinished());
        assertNull(gameState.getWinner());
    }

    @Test
    public void test_CalculateWinner_ShouldGameFinishedAndPlayer2Won_Success() {
        Player player1 = new Player();
        Player player2 = new Player();
        GameState gameState = new GameState(player1, player2);
        Boolean[] pointPerRoundFirstPlayer ={true, false, false, false, false};
        Boolean[] pointPerRoundSecondPlayer ={false, true, true, true, true};
        player1.setPointPerRoundList(Arrays.asList(pointPerRoundFirstPlayer));
        player2.setPointPerRoundList(Arrays.asList(pointPerRoundSecondPlayer));

        gameState.calculateWinner();

        assertTrue(gameState.isGameFinished());
        assertSame(player2, gameState.getWinner());
    }

    @Test
    public void test_CalculateWinner_ShouldGameFinishedAndPlayer1Won_Success() {
        Player player1 = new Player();
        Player player2 = new Player();
        GameState gameState = new GameState(player1, player2);
        Boolean[] pointPerRoundFirstPlayer ={false, true, false, true, true, false, true, true};
        Boolean[] pointPerRoundSecondPlayer ={true, false, true, false, false, true, false, false};
        player1.setPointPerRoundList(Arrays.asList(pointPerRoundFirstPlayer));
        player2.setPointPerRoundList(Arrays.asList(pointPerRoundSecondPlayer));

        gameState.calculateWinner();

        assertTrue(gameState.isGameFinished());
        assertSame(player1, gameState.getWinner());
    }
}
