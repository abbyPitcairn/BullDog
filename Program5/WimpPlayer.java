package Program5;

/**
 * A Wimp player that always rolls once and stops.
 * @author Abigail Pitcairn
 * @version Feb 26, 2025
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
     * @param gameDice The dice used for rolling.
     * @return The score earned during the turn.
     */
    @Override
    public int play(Dice gameDice) {
        int roll = gameDice.roll();
        System.out.print("   Player " + getName() + " rolled " + roll);
        if (roll != 6) {
            System.out.println(" and chose not to continue, scoring " + roll + " for the turn.");
        } else {
            roll = 0;
            System.out.println(" and scored 0 for the turn.");
        }
        return roll;
    }
}
