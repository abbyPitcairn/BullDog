package Program8;

/**
 * A player that stops rolling once their turn score reaches 15.
 * 
 * @author Abigail Pitcairn and ChatGPT 4.0
 * @version April 16, 2025
 */
class FifteenPlayer extends Player {
    /**
     * Constructs a FifteenPlayer with a given name.
     * 
     * @param name The name of the player.
     */
    public FifteenPlayer(String name) {
        super(name);
    }

    /**
     * Plays a turn where the player stops rolling when their score reaches or exceeds 15.
     * 
     * @return true if the score's under 15, otherwise false.
     */
    @Override
    protected boolean continueTurn(int turnScore) {
        if (turnScore >= 15)
            return false;
        return true;
    }
}