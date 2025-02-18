import java.util.Random;

/********************************************************/
/* Abigail Pitcairn                                     */
/* COS 420, Spring 2025                                 */
/* FifteenPlayer: subclass of Player                    */
/* Each turn, roll until reaching 15 or rolling a 6     */
/********************************************************/

public class FifteenPlayer extends Player {

    /**Constructor
     * @param name - name of the player
     */
    public FifteenPlayer(String name) {
        super(name);
    }
 
    /**Execute FifteenPlayer's turn.
     * FifteenPlayer will roll dice until their score for the current turn
     * is greater than or equal to 15, or if a six is rolled the score is 0.
     * @return FifteenPlayer's final score for the current turn. 
     */
    @Override
    public int play() {
        int turnScore = 0;
        Random rand = new Random();
        while (turnScore < 15) {
            int roll = rand.nextInt(6) + 1;
            System.out.println("  " + getName() + " rolled a " + roll);
            if (roll == 6) {
                System.out.println("  Rolled a 6! Turn over.");
                return 0;
            }
            turnScore += roll;
        }
        return turnScore;
    }

}
