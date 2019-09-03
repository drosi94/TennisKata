import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TestMain {

    // Use case 'Calculate play score based on the points'
    @Test
    public void test_CalculateScore() {
        Main main = new Main();
        Boolean[] pointPerRound ={true, false, false};

        int score = main.calculateScore(Arrays.asList(pointPerRound));

        assertEquals(15, score);
    }
}
