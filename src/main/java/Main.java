import java.util.List;

/**
 * KataTenis for Wemanity code challenge
 *
 * @author vdrosatos
 */
public class Main {
    public int[] calculateScore(List<Boolean> pointPerRoundFirstPlayer, List<Boolean> pointPerRoundSecondPlayer) {
        int firstsPlayerWins = countTrue(pointPerRoundFirstPlayer);
        int secondsPlayerWins = countTrue(pointPerRoundSecondPlayer);
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

    private int countTrue(List<Boolean> list) {
        return (int) list.stream().filter(element -> element).count();
    }
}
