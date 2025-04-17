package Program8;

/**
 * The {@code RandomDice} class represents a dice object with a specified number of sides.
 * This class is abstract and provides a common base for different types of dice.
 * Subclasses must implement the {@code roll()} method to provide specific dice rolling behavior.
 * 
 * @author Abigail Pitcairn and ChatGPT 4.0
 * @version April 9, 2025
 */
public abstract class RandomDice {
    protected int sides;

    /**
     * Constructs a new {@code RandomDice} object with the specified number of sides.
     * 
     * @param sides The number of sides of the die.
     */
    public RandomDice(int sides) {
        this.sides = sides;
    }

    /**
     * Rolls the dice and returns the result.
     * This method must be implemented by subclasses to provide specific rolling behavior.
     * 
     * @return The result of the dice roll (an integer between 1 and the number of sides).
     */
    public abstract int roll();
}

