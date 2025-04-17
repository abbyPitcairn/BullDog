package Program8;

import java.util.Random;

/**
 * The {@code Dice} class represents a die with a specified number of sides.
 * It extends {@code RandomDice} and provides an implementation for the {@code roll()} method 
 * to simulate rolling the die.
 * A shared {@code Random} object is used to generate random numbers for the rolls.
 * 
 * @author Abigail Pitcairn and ChatGPT 4.0
 * @version April 9, 2025
 */
public class Dice extends RandomDice {
    private static Random random = new Random();

    /**
     * Constructs a new {@code Dice} object with the specified number of sides.
     * 
     * @param sides The number of sides of the die.
     */
    public Dice(int sides) {
        super(sides); 
    }

    /**
     * Rolls the dice and returns a random integer between 1 and the number of sides.
     * 
     * @return A random integer between 1 and the number of sides of the die.
     */
    @Override
    public int roll() {
        return random.nextInt(sides) + 1;
    }

    /**
     * Constructs a new {@code Dice} object with a default of 6 sides.
     * This is an overloaded constructor for a standard 6-sided die.
     */
    public Dice() {
        this(6);
    }
}
