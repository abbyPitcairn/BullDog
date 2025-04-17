package Program8;

/**
 * A Wimp player that always rolls once and stops.
 * 
 * @author Abigail Pitcairn and ChatGPT 4.0
 * @version April 16, 2025
 */
class WimpPlayer extends Player {
    /**
     * Constructs a WimpPlayer with a given name.
     * 
     * @param name The name of the player.
     */
    public WimpPlayer(String name) {
        super(name);
    }

    /**
     * Plays a turn where the player rolls once and stops immediately.
     * 
     * @return false because WimpPlayer will not continue to roll. 
     */
    @Override
    protected boolean continueTurn(int turnScore) {
        return false;
    }
}