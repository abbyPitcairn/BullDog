package Program7;

/**
 * The {@code FakeRandom} class simulates a dice roll using a predefined sequence of numbers.
 * This is useful for testing purposes where a specific sequence of rolls is required.
 * It extends the {@code RandomDice} class and overrides the {@code roll()} method.
 * 
 * @author Abigail Pitcairn and ChatGPT 4.0
 * @version April 9, 2025
 */
public class FakeRandom extends RandomDice {
    private int[] sequence; 
    private int currentIndex; 

    /**
     * Constructs a {@code FakeRandom} object with a specified number of sides for the dice
     * and a predefined sequence of integers to simulate the rolls.
     * 
     * @param sides The number of sides of the dice (e.g., 6 for a standard die).
     * @param sequence The array of integers representing the sequence of dice rolls.
     */
    public FakeRandom(int sides, int[] sequence) {
        super(sides);  
        this.sequence = sequence;
        this.currentIndex = 0;
    }

    /**
     * Simulates a dice roll by returning the next number in the predefined sequence.
     * If the sequence has been exhausted, it returns -1 to indicate no more rolls.
     * 
     * @return The next number in the sequence or -1 if the sequence is exhausted.
     */
    @Override
    public int roll() {
        if (currentIndex >= sequence.length) {
            return -1;  
        }
        currentIndex++;  
        return sequence[currentIndex - 1];
    }
}


