import java.util.List;

/**
 * KataTenis for Wemanity code challenge
 *
 * @author vdrosatos
 */
public class Main {
    public int calculateScore(List<Boolean> pointPerRound) {
        int points = (int) pointPerRound.stream().filter(won -> won).count();
        return points * 15 - (points == 3 ? 5 : 0);
    }
}
