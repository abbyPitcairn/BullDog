package Program8;

import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;

/**
 * The {@code Referee} class represents the referee in the Bulldog Dice Game.
 * It follows the Singleton pattern to ensure only one instance of the referee exists
 * throughout the game. The referee is responsible for managing the game flow and controlling
 * the main logic, such as initializing players and handling the game state.
 * 
 * @author Abigail Pitcairn and ChatGPT 4.0
 * @version April 16, 2025
 */
public class Referee {

    private static Referee instance;
    final static int WINNING_SCORE = 104;
    protected static int currentPlayerIndex;
    protected static int turnScore;
    protected static PlayerListModel playerListModel;
    protected static ScoreboardViewer scoreboardViewer;
    protected static Dice dice;
    protected static GameStatus gameStatus;

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
        gameStatus = new GameStatus(WINNING_SCORE);  
        playerListModel = PlayerListModel.initializePlayers(); 
        scoreboardViewer = new ScoreboardViewer(playerListModel);    
        currentPlayerIndex = -1;
        turnScore = 0;
        dice = new Dice(6);
        new BulldogGameGUI();
        nextTurn(gameStatus);
    }
    
    /**
     * Moves the game to the next player's turn and updates the GUI accordingly.
     * Changes visibility of buttons based on whether the current player is a human or AI.
     */
    protected static void nextTurn(GameStatus gameStatus) {
        currentPlayerIndex = (currentPlayerIndex + 1) % playerListModel.getPlayers().size();
        Player currentPlayer = playerListModel.getPlayers().get(currentPlayerIndex);
        BulldogGameGUI.log(currentPlayer.getName() + "'s turn:");
        if (currentPlayer instanceof HumanPlayer) {
            BulldogGameGUI.rollButton.setVisible(true);
            BulldogGameGUI.endTurnButton.setVisible(true);
            BulldogGameGUI.continueButton.setVisible(false);
        } else {
            BulldogGameGUI.rollButton.setVisible(false);
            BulldogGameGUI.endTurnButton.setVisible(false);
            BulldogGameGUI.continueButton.setVisible(true);
            String rollRecord = currentPlayer.play(dice);
            BulldogGameGUI.log(rollRecord);
        }
    }
    
    /**
     * Rolls the dice for the current player and updates the game state accordingly.
     * If the roll is a 6, the player loses the turn. Otherwise, the score is updated.
     * 
     * @param e The ActionEvent triggered by the roll button.
     */
    protected static void rollDice(ActionEvent e, GameStatus gameStatus) {
        int roll = dice.roll();
        BulldogGameGUI.log("   You rolled a " + roll);
        if (roll == 6) {
            turnScore = 0;
            Referee.endTurn(gameStatus);
        } else {
            turnScore += roll;
        }
    }

    /**
     * Ends the current player's turn, updates the score, and moves to the next player.
     * If the current player wins, the game ends and the buttons are disabled.
     */
    protected static void endTurn(GameStatus gameStatus) {
        Player currentPlayer = playerListModel.getPlayers().get(currentPlayerIndex);
        BulldogGameGUI.log(currentPlayer.getName() + " scored " + turnScore);
        playerListModel.setPlayerScore(currentPlayerIndex, currentPlayer.getScore() + turnScore);
        scoreboardViewer.updateScoreboard();
        checkWinCondition();
        turnScore = 0;
        nextTurn(gameStatus); 
    }
    
    /**
     * Check the win condition for the current player.
     * If the current player's score is >= 104, end the game. 
     */
    public static void checkWinCondition() {
        Player currentPlayer = playerListModel.getPlayers().get(currentPlayerIndex);
        if (currentPlayer.getScore() >= WINNING_SCORE)  {
            BulldogGameGUI.log(currentPlayer.getName() + " wins!");
            gameStatus.setGameOver();
        }
    }
    
    /**
     * The entry point of the Bulldog Dice Game application.
     * This method initializes the game GUI by creating an instance of {@code BulldogGameGUI}
     * and making it visible on the Event Dispatch Thread. It ensures that the GUI is created 
     * in the correct thread, following Swing's best practices for thread safety.
     * 
     * Then, playGame is called from the Referee class to begin a single instance of a Game.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Referee.getInstance().playGame();
        });
    }
}

