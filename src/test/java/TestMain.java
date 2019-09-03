import org.junit.Test;
import tennis.model.Player;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TestMain {

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
}
