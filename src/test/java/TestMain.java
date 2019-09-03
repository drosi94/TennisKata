import org.junit.Test;
import static org.junit.Assert.*;

public class TestMain {

    // Use case 'Calculate play score based on the points'
    @Test
    public void test_CalculateScore() {
        Main main = new Main();
        Boolean[] points ={true, false, false};

        int score = main.calculateScore(points);

        assertEquals(15, score);
    }
}
