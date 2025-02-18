import java.util.Random;

/********************************************************/
/* Abigail Pitcairn                                     */
/* COS 420, Spring 2025                                 */
/* RandomPlayer: subclass of Player                     */
/* Plays randomly each turn                             */
/********************************************************/
public class RandomPlayer extends Player {

    public RandomPlayer(String name) {
        super(name);
    }

    /**Execute RandomPlayer's turn.
     * Each time RandomPlayer rolls, a random boolean is generated;
     * rolls until the boolean returns True or they roll a 6.
     * @return RandomPlayer's final score for the current turn. 
     */
    @Override
    public int play() {
        Random rand = new Random();
        int turnScore = 0;
        while (true) {
            int roll = rand.nextInt(6) + 1;
            System.out.println("  " + getName() + " rolled a " + roll);
            if (roll == 6) {
                System.out.println("  Rolled a 6! Turn over.");
                return 0;
            }
            turnScore += roll;
            if (rand.nextBoolean()) {
                return turnScore;
            }
        }
    }

}
