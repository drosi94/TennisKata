import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TestMain {

    // Use case 'Calculate play score based on the points'
    @Test
    public void test_CalculateScore_ShouldBe15_Success() {
        Main main = new Main();
        Boolean[] pointPerRoundFirstPlayer ={true, false, false, false};
        Boolean[] pointPerRoundSecondPlayer ={false, true, true, true};

        int[] score = main.calculateScore(Arrays.asList(pointPerRoundFirstPlayer), Arrays.asList(pointPerRoundSecondPlayer));

        assertNotNull(score);
        assertEquals(2, score.length);
        assertEquals(15, score[0]);
        assertEquals(40, score[1]);
    }
}
