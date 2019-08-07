import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * KataTenis for Wemanity code challenge
 * @author vdrosatos
 */
public class Main {

    private static final Map<Integer, String> pointMappings = new HashMap<>();

    static {
        // Specify mappings between names and points
        pointMappings.put(0, "Love");
        pointMappings.put(15, "Fifteen");
        pointMappings.put(30, "Thirty");
        pointMappings.put(40, "Forty");
        pointMappings.put(41, "Advance");
    }

    public static void main(String[] args) {
        // Initialize the arrays which holding if the player took the point at turn *index*
        final int[] firstPlayersPoints = new int[100];
        final int[] secondPlayersPoints = new int[100];

        int turn = 0;
        while (true) {
            int point = playPoint(); // Play point returns 0 or 1 meaning if the first (0) or the second (1) player won the point
            if (point == 0) {
                firstPlayersPoints[turn] = 1;
            } else {
                secondPlayersPoints[turn] = 1;
            }
            Boolean[] isWinner = calculateWinner(firstPlayersPoints, secondPlayersPoints); // Calculates if the game it ends (first boolean)
                                                                                           // and if it is if the first player won (second boolean)
            if (isWinner[0]) {
                System.out.printf("\n\n*************\n\nWinner is player %d at turn %d\n\n**************\n\n", (isWinner[1] ? 1 : 2), (turn + 1));
                break;
            }
            int[] score = calculateScore(firstPlayersPoints, secondPlayersPoints); // Calculate the score at this turn
            turn++;
            printScore(score[0], score[1], turn); // Prints the score at console
        }
    }

    /**
     * Calculates if the game is ended and if the first player won
     * @param firstPlayersPoints, array holding for each turn if first player won the point (0 or 1)
     * @param secondPlayersPoints, array holding for each turn if first player won the point (0 or 1)
     * @return Boolean[], boolean array of 2 elements, first boolean if the game is ended, second boolean if first player was winner
     */
    private static Boolean[] calculateWinner(final int[] firstPlayersPoints, final int[] secondPlayersPoints) {
        int firstsPlayerWins = Arrays.stream(firstPlayersPoints).sum();
        int secondsPlayerWins = Arrays.stream(secondPlayersPoints).sum();
        int scoreDifference = firstsPlayerWins - secondsPlayerWins;

        boolean isGameEnded = (firstsPlayerWins > 3 || secondsPlayerWins > 3) && (scoreDifference > 1 || scoreDifference < -1);
        Boolean firstPlayerWon = null;

        if (isGameEnded) {
            firstPlayerWon = scoreDifference > 0;
        }

        return new Boolean[]{isGameEnded, firstPlayerWon};
    }

    /**
     * Calculates the score of the game at the specific turn
     * @param firstPlayersPoints, array holding for each turn if first player won the point (0 or 1)
     * @param secondPlayersPoints, array holding for each turn if first player won the point (0 or 1)
     * @return int[], int array of 2 elements, first int the score of first player, secont int the score of second player
     */
    private static int[] calculateScore(final int[] firstPlayersPoints, final int[] secondPlayersPoints) {
        int firstsPlayerWins = Arrays.stream(firstPlayersPoints).sum();
        int secondsPlayerWins = Arrays.stream(secondPlayersPoints).sum();

        int firstPlayersScore;
        int secondPlayerScore;

        if (firstsPlayerWins <= 3 && secondsPlayerWins <= 3) {
            firstPlayersScore = firstsPlayerWins * 15 - (firstsPlayerWins == 3 ? 5 : 0);
            secondPlayerScore = secondsPlayerWins * 15 - (secondsPlayerWins == 3 ? 5 : 0);
        } else {
            int scoreDifference = firstsPlayerWins - secondsPlayerWins;
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


    private static void printScore(Integer firstPlayersScore, Integer secondPlayersScore, int turn) {
        System.out.printf("Turn: %d\nScore: %s - %s\n", turn, pointMappings.get(firstPlayersScore), pointMappings.get(secondPlayersScore));
    }


    private static int playPoint() {
        final Random random = new Random();
        return random.nextInt(2);
    }
}
