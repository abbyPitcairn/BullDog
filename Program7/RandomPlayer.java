package Program7;

/**
 * A Random player that randomly decides to roll or stop using a 2-sided die.
 * 
 * @author Abigail Pitcairn and ChatGPT 4.0
 * @version April 9, 2025
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

    /**
     * Plays a turn where the player randomly decides to roll again or stop,
     * using a shared Random instance via a 2-sided Dice object.
     *
     * @param gameDice The 6-sided dice used for rolling scores.
     * @return The score earned during the turn.
     */
    @Override
    public int play(RandomDice gameDice) {
        int score = 0;
        Dice decisionDie = new Dice(2); // 2-sided die for decision making (1=keep going, 2=stop)

        while (decisionDie.roll() == 1) {  // Keep rolling while decision is 1
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
