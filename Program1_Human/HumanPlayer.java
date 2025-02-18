import java.util.*;

/********************************************************/
/* Abigail Pitcairn                                     */
/* COS 420, Spring 2025                                 */
/* HumanPlayer: subclass of Player                      */
/* Interactive player; requires user interaction        */
/********************************************************/

public class HumanPlayer extends Player {

    /**Constructor
     * @param name - name of the player
     */
    public HumanPlayer(String name) {
        super(name);
    }

    
    /**Execute HumanPlayer's turn.
     * HumanPlayer provides an interactive interface that will
     * continue to roll until user chooses to end turn or if a six is rolled.
     * @return HumanPlayer's final score for the current turn. 
     */
    @Override
    public int play() {
        Scanner scanner = new Scanner(System.in);
        int turnScore = 0;
        while (true) {
            int roll = (int)(Math.random() * 6) + 1;
            System.out.println("  " + getName() + " rolled a " + roll);
            if (roll == 6) {
                System.out.println("  Rolled a 6!");
                return 0;
            }
            turnScore += roll;
            System.out.println("  " + "Turn score so far: " + turnScore);
            System.out.print("  " + "Continue? (y/n): ");
            if (scanner.next().equalsIgnoreCase("n")) {
                return turnScore;
            }       
        }        
    }

}
