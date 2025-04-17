package Program8;

/**
 * A Random player that randomly decides to roll or stop using a 2-sided die.
 * 
 * @author Abigail Pitcairn and ChatGPT 4.0
 * @version April 16, 2025
 */
class RandomPlayer extends Player {

    /**
     * Constructs a RandomPlayer with a given name.
     *
     * @param name The name of the player.
     */
    public RandomPlayer(String name) {
        super(name);
    }
    
    /** Binary random decision dice **/
    RandomDice randomDecision = new Dice(2);

    /**
     * Plays a turn where the player randomly decides to roll again or stop,
     * using a shared Random instance via a 2-sided Dice object.
     * 
     * @return true if the random roll is 2, false if the random roll is 1.
     */
    @Override
    protected boolean continueTurn(int turnScore) {
        if (randomDecision.roll() == 1)
            return false;
        return true;
    }
}
