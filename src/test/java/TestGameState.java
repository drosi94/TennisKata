import logging.StaticAppender;
import org.junit.Test;
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

    // Use case 'Print score based on player points and turn'
    @Test
    public void test_PrintScore_ShouldPrint40ADVANCE_Success() {
        Player player1 = new Player();
        Player player2 = new Player();
        GameState gameState = new GameState(player1, player2);
        player1.setScore(Point.FORTY);
        player2.setScore(Point.ADVANCE);
        gameState.setTurn(7);

        gameState.printScore();

        assertThat(StaticAppender.getEvents()).extracting("message").containsOnly("Turn: 7\nScore: 40 - ADVANCE\n");
    }
}
