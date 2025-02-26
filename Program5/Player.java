package Program5;

/**
 * Abstract class representing a Player in the Bulldog game.
 * All types of players (Human, Wimp, Random, Fifteen, Unique) extend this class.
 * A player has a name, a score, and a method to play their turn.
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
     * Abstract method for playing a turn using the provided dice.
     * 
     * @param gameDice The dice used for rolling.
     * @return The score earned during the turn.
     */
    public abstract int play(Dice gameDice);
}
