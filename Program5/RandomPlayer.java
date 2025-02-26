package Program5;

import java.util.Random;

/**
 * A Random player that randomly decides to roll or stop.
 */
class RandomPlayer extends Player {
    /**
     * Constructs a RandomPlayer with a given name.
     * 
     * @param name The name of the player.
     */
    public RandomPlayer(String name) {
        super(name);
    }

    /**
     * Plays a turn where the player randomly decides to roll again or stop.
     * 
     * @param gameDice The dice used for rolling.
     * @return The score earned during the turn.
     */
    @Override
    public int play(Dice gameDice) {
        int score = 0;
        Random rand = new Random();
        while (rand.nextBoolean()) {
            int roll = gameDice.roll();
            System.out.println(getName() + " rolled " + roll);
            if (roll == 6) {
                System.out.println(getName() + " lost the turn!");
                return 0;
            }
            score += roll;
        }
        return score;
    }
}