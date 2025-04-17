package Program8;

/**
 * A unique player that follows a different rolling strategy.
 * 
 * @author Abigail Pitcairn and ChatGPT 4.0
 * @version April 16, 2025
 */
class UniquePlayer extends Player {
    /**
     * Constructs a UniquePlayer with a given name.
     * 
     * @param name The name of the player.
     */
    public UniquePlayer(String name) {
        super(name);
    }
    
    /** Stores how many rolls have already been rolled this turn **/
    private int uniquePlayDecider = 0;
    
    /** Generates random value of 1 or 2 **/
    private RandomDice uniquePlayDeciderDie = new Dice(2);

    /**
     * Plays a turn where the player rolls at least 2 times, then has a 50% chance to continue rolling.
     * 
     * @return true if UniquePlayer will continue the turn, false to end turn. 
     */
    @Override
    protected boolean continueTurn(int turnScore) {
        uniquePlayDecider++;
        if (uniquePlayDecider >= 2) {
            if (uniquePlayDeciderDie.roll() == 1) {
                uniquePlayDecider = 0;
                return true;
            }}
        uniquePlayDecider = 0;       
        return false;  
    }
}