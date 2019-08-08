import exception.GameAlreadyFinishedException;

import java.util.*;

/**
 * KataTenis for Wemanity code challenge
 *
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
        final Main game = new Main();

        // Initialize the arrays which holding if the player took the point at turn *index*
        final List<Short> firstPlayersPoints = new ArrayList<>(Collections.nCopies(4, (short) 0));
        final List<Short> secondPlayersPoints = new ArrayList<>(Collections.nCopies(4, (short) 0));

        int turn = 0;
        while (true) {
            int point = game.playPoint(); // Play point returns 0 or 1 meaning if the first (0) or the second (1) player won the point
            if (point == 0) {
                firstPlayersPoints.add(turn, (short) 1);
            } else {
                secondPlayersPoints.add(turn, (short) 1);
            }
            Boolean[] isWinner = game.calculateWinner(firstPlayersPoints, secondPlayersPoints); // Calculates if the game has ended (first boolean)
            // and if it is if the first player won (second boolean)
            if (isWinner[0]) {
                System.out.printf("\n\n*************\n\nWinner is player %d at turn %d\n\n**************\n\n", (isWinner[1] ? 1 : 2), (turn + 1));
                break;
            }
            int[] score = new int[0];
            try {
                score = game.calculateScore(firstPlayersPoints, secondPlayersPoints); // Calculate the score at this turn
            } catch (GameAlreadyFinishedException e) {
                e.printStackTrace();
            }
            turn++;
            game.printScore(score[0], score[1], turn); // Prints the score at console
        }
    }

    /**
     * Calculates if the game has ended and if the first player won
     *
     * @param firstPlayersPoints,  array holding for each turn if first player won the point (0 or 1)
     * @param secondPlayersPoints, array holding for each turn if first player won the point (0 or 1)
     * @return Boolean[], boolean array of 2 elements, first boolean if the game has ended, second boolean if first player was winner
     */
    protected Boolean[] calculateWinner(final List<Short> firstPlayersPoints, final List<Short> secondPlayersPoints) {

        int firstsPlayerWins = sum(firstPlayersPoints);
        int secondsPlayerWins = sum(secondPlayersPoints);
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
     *
     * @param firstPlayersPoints,  array holding for each turn if first player won the point (0 or 1)
     * @param secondPlayersPoints, array holding for each turn if first player won the point (0 or 1)
     * @return int[], int array of 2 elements, first int the score of first player, second int the score of second player
     */
    protected int[] calculateScore(final List<Short> firstPlayersPoints, final List<Short> secondPlayersPoints) throws GameAlreadyFinishedException {
        int firstsPlayerWins = sum(firstPlayersPoints);
        int secondsPlayerWins = sum(secondPlayersPoints);

        int scoreDifference = firstsPlayerWins - secondsPlayerWins;

        if ((firstsPlayerWins > 3 || secondsPlayerWins > 3) && (scoreDifference > 1 || scoreDifference < -1)) {
            throw new GameAlreadyFinishedException("The game has already finished");
        }

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

    protected void printScore(Integer firstPlayersScore, Integer secondPlayersScore, int turn) {
        System.out.printf("Turn: %d\nScore: %s - %s\n", turn, pointMappings.get(firstPlayersScore), pointMappings.get(secondPlayersScore));
    }


    protected int playPoint() {
        final Random random = new Random();
        return random.nextInt(2);
    }

    private int sum(final List<Short> points) {
        return Arrays.stream(points.stream().mapToInt(i -> i).toArray()).sum();
    }
}
