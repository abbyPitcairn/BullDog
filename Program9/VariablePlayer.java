package Program9;

/**
 * A player that stops rolling once their turn score reaches 15.
 * 
 * @author Abigail Pitcairn and ChatGPT 4.0
 * @version April 30, 2025
 */
class VariablePlayer extends Player {
    
    private int limit;
    /**
     * Constructs a FifteenPlayer with a given name.
     * 
     * @param name The name of the player.
     * @param limit The limit that the player will play to. 
     */
    public VariablePlayer(String name, int limit) {
        super(name);
        this.limit = limit;
    }

    /**
     * Plays a turn where the player stops rolling when their score reaches or exceeds limit.
     * 
     * @return true if the score's under limit, otherwise false.
     */
    @Override
    protected boolean continueTurn(int turnScore) {
        return turnScore < this.limit;
    }
}