package tennis;

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

    public GameState(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void calculateScore() {
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
                firstPlayersScore = Point.ADVANCE;
                secondPlayerScore = Point.FORTY;
            } else if (scoreDifference == 0) {
                firstPlayersScore = Point.FORTY;
                secondPlayerScore = Point.FORTY;
            } else {
                firstPlayersScore = Point.FORTY;
                secondPlayerScore = Point.ADVANCE;
            }
        }

        player1.setScore(firstPlayersScore);
        player2.setScore(secondPlayerScore);
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
}
