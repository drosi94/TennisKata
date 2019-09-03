package tennis;

import exception.GameAlreadyFinishedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tennis.model.Player;
import tennis.model.Point;

import java.util.List;

public class GameState {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameState.class.getName());

    private Player player1;
    private Player player2;
    private int turn;

    private Player winner;
    private boolean gameFinished;

    public GameState(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void calculateScore() {
        if (gameFinished) {
            LOGGER.error("GameState: calculateScore() fatal exception");
            throw new GameAlreadyFinishedException("The game has already finished. You can not calculate the score.");
        }
        int firstsPlayerWins = countTrue(player1.getPointPerRoundList());
        int secondsPlayerWins = countTrue(player2.getPointPerRoundList());
        int scoreDifference = firstsPlayerWins - secondsPlayerWins;
        Point firstPlayersScore;
        Point secondPlayerScore;

        if (firstsPlayerWins <= 3 && secondsPlayerWins <= 3) {
            firstPlayersScore = Point.fromInteger(firstsPlayerWins * 15 - (firstsPlayerWins == 3 ? 5 : 0));
            secondPlayerScore = Point.fromInteger(secondsPlayerWins * 15 - (secondsPlayerWins == 3 ? 5 : 0));
        } else {
            if (scoreDifference == 1) {
                firstPlayersScore = Point.ADVANTAGE;
                secondPlayerScore = Point.FORTY;
            } else if (scoreDifference == 0) {
                firstPlayersScore = Point.FORTY;
                secondPlayerScore = Point.FORTY;
            } else {
                firstPlayersScore = Point.FORTY;
                secondPlayerScore = Point.ADVANTAGE;
            }
        }

        player1.setScore(firstPlayersScore);
        player2.setScore(secondPlayerScore);
    }


    public void calculateWinner() {
        int firstsPlayerWins = countTrue(player1.getPointPerRoundList());
        int secondsPlayerWins = countTrue(player2.getPointPerRoundList());
        int scoreDifference = firstsPlayerWins - secondsPlayerWins;

        gameFinished = (firstsPlayerWins > 3 || secondsPlayerWins > 3) && (scoreDifference > 1 || scoreDifference < -1);

        if (gameFinished) {
            winner = scoreDifference > 0 ? player1 : player2;
        }
    }

    public void printScore() {
        LOGGER.info(String.format("Turn: %d\nScore: %s - %s\n", turn, player1.getScore(), player2.getScore()));
    }

    private int countTrue(List<Boolean> list) {
        return (int) list.stream().filter(element -> element).count();
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}
