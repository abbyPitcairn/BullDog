package Program8;

/**
 * A Human player that decides each roll interactively.
 * 
 * @author Abigail Pitcairn and ChatGPT 4.0
 * @version April 16, 2025
 */
class HumanPlayer extends Player {

    /**
     * Constructs a HumanPlayer with a given name.
     * 
     * @param name The name of the player.
     */
    public HumanPlayer(String name) {
        super(name);
    }

    /**
     * Plays a turn where the player rolls the dice and decides whether to continue.
     * 
     * @param gameDice The dice used for rolling.
     * @return The score earned during the turn.
     */
    @Override
    protected boolean continueTurn(int turnScore) {
        return true;
    }
}

