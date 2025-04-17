package Program8;

/**
 * Abstract class representing a Player in the Bulldog game.
 * All types of players (Human, Wimp, Random, Fifteen, Unique) extend this class.
 * A player has a name, a score, and a method to play their turn.
 * 
 * @author Abigail Pitcairn and ChatGPT 4.0
 * @version April 16, 2025
 */
public abstract class Player {
    private String name;
    private int score;

    /**
     * Constructs a Player with a given name and initializes the score to 0.
     * 
     * @param name The name of the player.
     */
    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    /**
     * Gets the name of the player.
     * 
     * @return The name of the player.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the current score of the player.
     * 
     * @return The current score of the player.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Sets the score of the player.
     * 
     * @param score The new score of the player.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Plays a turn by rolling dice until the strategy decides to stop,
     * or the player rolls a 6 and loses the turn.
     * 
     * @param dice The dice object to roll.
     * @return The score earned during this turn.
     */
    public String play(Dice dice) {
        String rollRecord = "    ";
        while (true) {
            int roll = dice.roll();
            rollRecord += roll + " ";        
            if (roll == 6) {
               Referee.turnScore = 0;
               break; 
            }
            Referee.turnScore += roll;
            Referee.checkWinCondition();
            if (!continueTurn(Referee.turnScore)) {
                break;
            }} 
        return rollRecord;
    }

    /**
     * Determines whether the player should continue rolling.
     * This method is implemented by each subclass to define its strategy.
     * 
     * @param turnScore The score accumulated during the current turn.
     * @return true if the player wants to roll again, false to stop.
     */
    protected abstract boolean continueTurn(int turnScore);
}
