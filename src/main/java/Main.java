import tennis.Game;
import tennis.model.Player;

import java.util.Scanner;

/**
 * KataTenis for Wemanity code challenge
 *
 * @author vdrosatos
 */
public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Give first player's name:\n");
        String playerName1 = sc.nextLine();
        System.out.println("\n\nGive second player's name:\n");
        String playerName2 = sc.nextLine();

        Game.getInstance().play(new Player(playerName1), new Player(playerName2));
    }
}
