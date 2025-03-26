package Program6;

import java.util.Random;

/**
 * The {@code Dice} class represents a standard six-sided die used in the game.
 * It provides a method to roll the die and return a random value between 1 and 6.
 */
class Dice {
    private Random random;

    /**
     * Constructs a new {@code Dice} object and initializes the random number generator.
     */
    public Dice() {
        random = new Random();
    }

    /**
     * Rolls the dice and returns a random integer between 1 and 6 (inclusive).
     *
     * @return The result of the dice roll, a value between 1 and 6.
     */
    public int roll() {
        return random.nextInt(6) + 1;
    }
}