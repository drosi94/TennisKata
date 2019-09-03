import logging.StaticAppender;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import tennis.model.Player;
import tennis.model.Point;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

public class TestMain {

    @BeforeEach
    public void clearLoggingStatements() {
        StaticAppender.clearEvents();
    }

    // Use case 'Calculate game score based on the points'
    @Test
    public void test_CalculateScore_ShouldBe1540_Success() {
        Main main = new Main();
        Player player1 = new Player();
        Player player2 = new Player();
        Boolean[] pointPerRoundFirstPlayer ={true, false, false, false};
        Boolean[] pointPerRoundSecondPlayer ={false, true, true, true};
        player1.setPointPerRoundList(Arrays.asList(pointPerRoundFirstPlayer));
        player2.setPointPerRoundList(Arrays.asList(pointPerRoundSecondPlayer));

        int[] score = main.calculateScore(player1, player2);

        assertNotNull(score);
        assertEquals(2, score.length);
        assertEquals(15, score[0]);
        assertEquals(40, score[1]);
    }

    // Use case 'Print score based on player points and turn'
    @Test
    public void test_PrintScore_ShouldPrintFortyForty_Success() {
        Main main = new Main();
        Point firstPlayerScore = Point.FORTY;
        Point scondPlayerScore = Point.FORTY;

        main.printScore(firstPlayerScore, scondPlayerScore, 1);

        assertThat(StaticAppender.getEvents()).extracting("message").containsOnly("Turn: 1\nScore: Forty - Forty\n");
    }
}
