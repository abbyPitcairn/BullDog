package Program7;

/**
 * The {@code Referee} class represents the referee in the Bulldog Dice Game.
 * It follows the Singleton pattern to ensure only one instance of the referee exists
 * throughout the game. The referee is responsible for managing the game flow and controlling
 * the main logic, such as initializing players and handling the game state.
 * 
 * @author Abigail Pitcairn and ChatGPT 4.0
 * @version April 9, 2025
 */
public class Referee {

    private static Referee instance;

    /**
     * Private constructor to prevent direct instantiation.
     * Ensures that only one instance of the referee can be created.
     */
    private Referee() {}

    /**
     * Returns the singleton instance of the {@code Referee} class.
     * If the instance does not exist, it creates one.
     * 
     * @return The singleton instance of the {@code Referee}.
     */
    public static Referee getInstance() {
        if (instance == null) {
            instance = new Referee();
        }
        return instance;
    }

    /**
     * Starts the Bulldog Dice Game by initializing the game components and managing the game flow.
     * It creates a 6-sided dice, sets it in the GUI, initializes the players, and starts the first turn.
     */
    public void playGame() {
        Dice dice = new Dice(6);
        BulldogGameGUI.setDice(dice);
        BulldogGameGUI.initializePlayers();
        BulldogGameGUI.nextTurn();
    }

}

