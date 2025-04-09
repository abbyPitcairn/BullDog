package Program7;

/**
 * A unique player that follows a different rolling strategy.
 * 
 * @author Abigail Pitcairn and ChatGPT 4.0
 * @version April 9, 2025
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

    /**
     * Plays a turn where the player rolls at least 3 times, then has a 70% chance to continue rolling.
     * 
     * @param gameDice The dice used for rolling.
     * @return The score earned during the turn.
     */
    @Override
    public int play(RandomDice gameDice) {
        int score = 0;
        int rolls = 0;
        while (rolls < 3 || (Math.random() > 0.3)) {
            int roll = gameDice.roll();
            System.out.println(getName() + " rolled " + roll);
            if (roll == 6) {
                System.out.println(getName() + " lost the turn!");
                return 0;
            }
            score += roll;
            rolls++;
        }
        return score;
    }
}