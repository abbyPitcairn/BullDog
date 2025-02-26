package Program5;

import java.util.*;

/**
 * A Human player that decides each roll interactively.
 */
class HumanPlayer extends Player {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a HumanPlayer with a given name.
     * 
     * @param name The name of the player.
     */
    public HumanPlayer(String name) {
        super(name);
    }

    /**
     * Plays a turn where the player rolls the dice and decides whether to continue.
     * 
     * @param gameDice The dice used for rolling.
     * @return The score earned during the turn.
     */
    @Override
    public int play(Dice gameDice) {
        int score = 0;
        while (true) {
            int roll = gameDice.roll();
            System.out.println(getName() + " rolled " + roll);
            if (roll == 6) {
                System.out.println(getName() + " lost the turn!");
                return 0;
            }
            score += roll;
            System.out.print("Current score: " + score + ". Roll again? (y/n): ");
            if (!scanner.nextLine().equalsIgnoreCase("y")) {
                break;
            }
        }
        return score;
    }
}
