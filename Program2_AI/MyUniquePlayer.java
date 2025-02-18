import java.util.Random;

/********************************************************/
/* Abigail Pitcairn                                     */
/* COS 420, Spring 2025                                 */
/* UniquePlayer: subclass of Player                     */
/* Each turn, roll until reaching either 16 or 10,      */
/*  or rolling a 6.                                     */
/********************************************************/

public class MyUniquePlayer extends Player {

    /**Constructor
     * @param name - name of the player
     */
    public MyUniquePlayer(String name) {
        super(name);
    }

    /* Boolean to track UniquePlayer's alterating turns */
    boolean everyOtherTurn = true;
    
    /**Execute UniquePlayer's turn.
     * UniquePlayer will roll dice until their score for the current turn
     * is greater than or equal to either 16 or 10, alternating these values
     * each turn; if a six is rolled the score is 0.
     * @return UniquePlayer's final score for the current turn. 
     */
    @Override
    public int play() {
        Random rand = new Random();
        everyOtherTurn = !everyOtherTurn;
        int turnScore = 0;
        while (true) {
            int roll = rand.nextInt(6) + 1;
            System.out.println("  " + getName() + " rolled a " + roll);
            if (roll == 6) {
                System.out.println("  Rolled a 6! Turn over.");
                return 0;
            }
            turnScore += roll;   
            if (everyOtherTurn && turnScore >= 16) {
                return turnScore;
            }
            if (!everyOtherTurn && turnScore >= 10) {
                return turnScore;
            }
        }
    }

}
