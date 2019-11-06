package tennis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tennis.model.Player;

import java.util.Random;

public class Game {
    private static Game singleInstance = null;

    private static final Logger LOGGER = LoggerFactory.getLogger(Game.class.getName());

    private Game() {
    }

    public static Game getInstance() {
        // To ensure only one instance is created
        if (singleInstance == null) {
            singleInstance = new Game();
        }
        return singleInstance;
    }

    public void play(Player player1, Player player2) {
        GameState gameState = new GameState(player1, player2);
        int turn = 0;
        do {
            gameState.calculateScore();
            gameState.printScore();
            if (playPoint() < 1) {
                player1.addPoint(true);
                player2.addPoint(false);
            } else {
                player1.addPoint(false);
                player2.addPoint(true);
            }
            gameState.setTurn(++turn);
            gameState.calculateWinner();
        } while (!gameState.isGameFinished());

        if (gameState.getWinner() != null) {
            LOGGER.info(String.format("Game winner: %s\nScore: %s - %s at turn %d", gameState.getWinner().getName(), player1.getScore(), player2.getScore(), gameState.getTurn()));
        }
    }

    public int playPoint() {
        Random random = new Random();
        return random.nextInt(2);
    }
}
