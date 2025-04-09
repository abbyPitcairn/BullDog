package Program7;

/**
 * A player that stops rolling once their turn score reaches 7.
 * 
 * @author Abigail Pitcairn and ChatGPT 4.0
 * @version April 9, 2025
 */
class SevenPlayer extends Player {
    /**
     * Constructs a SevenPlayer with a given name.
     * 
     * @param name The name of the player.
     */
    public SevenPlayer(String name) {
        super(name);
    }

    /**
     * Plays a turn where the player stops rolling when their score reaches or exceeds 7.
     * 
     * @param gameDice The dice used for rolling.
     * @return The score earned during the turn.
     */
    @Override
    public int play(RandomDice gameDice) {
        int score = 0;
        while (score < 7) {
            int roll = gameDice.roll();
            System.out.println(getName() + " rolled " + roll);
            if (roll == 6) {
                System.out.println(getName() + " lost the turn!");
                return 0;
            }
            score += roll;
        }
        return score;
    }
}