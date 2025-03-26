package Program6;

/**
 * A player that stops rolling once their turn score reaches 15.
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
     * @param gameDice The dice used for rolling.
     * @return The score earned during the turn.
     */
    @Override
    public int play(Dice gameDice) {
        int score = 0;
        while (score < 15) {
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