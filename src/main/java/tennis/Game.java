package tennis;

import tennis.model.Player;

import java.util.Random;

public class Game {
    private static Game singleInstance = null;

    private Game() {
    }

    public static Game getInstance() {
        // To ensure only one instance is created
        if (singleInstance == null) {
            singleInstance = new Game();
        }
        return singleInstance;
    }

    private GameState gameState;

    public int playPoint() {
        Random random = new Random();
        return random.nextInt(2);
    }
}
