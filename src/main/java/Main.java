import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tennis.model.Player;
import tennis.model.Point;

import java.util.List;

/**
 * KataTenis for Wemanity code challenge
 *
 * @author vdrosatos
 */
public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class.getName());

    public int[] calculateScore(Player player1, Player player2) {
        int firstsPlayerWins = countTrue(player1.getPointPerRoundList());
        int secondsPlayerWins = countTrue(player2.getPointPerRoundList());
        int scoreDifference = firstsPlayerWins - secondsPlayerWins;
             int firstPlayersScore;
        int secondPlayerScore;

        if (firstsPlayerWins <= 3 && secondsPlayerWins <= 3) {
            firstPlayersScore = firstsPlayerWins * 15 - (firstsPlayerWins == 3 ? 5 : 0);
            secondPlayerScore = secondsPlayerWins * 15 - (secondsPlayerWins == 3 ? 5 : 0);
        } else {
            if (scoreDifference == 1) {
                firstPlayersScore = 41;
                secondPlayerScore = 40;
            } else if (scoreDifference == 0) {
                firstPlayersScore = 40;
                secondPlayerScore = 40;
            } else {
                firstPlayersScore = 40;
                secondPlayerScore = 41;
            }
        }

        return new int[]{firstPlayersScore, secondPlayerScore};
    }

    public void printScore(Point firstPlayerScore, Point secondPlayerScore, int turn) {
        LOGGER.info(String.format("Turn: %d\nScore: %s - %s\n", turn, firstPlayerScore, secondPlayerScore));
    }

    private int countTrue(List<Boolean> list) {
        return (int) list.stream().filter(element -> element).count();
    }
}
