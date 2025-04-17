package Program8;

/**
 * The {@code GameStatus} class encapsulates the current state of the Bulldog Dice Game.
 * It is passed around during gameplay to allow consistent access to the current game status.
 * 
 * This class includes information like the current turn score, winning score,
 * current player index, and game-over status.
 * 
 * @author Abigail Pitcairn and ChatGPT 4.0
 * @version April 16, 2025
 */
public class GameStatus {
    private final int winningScore;
    private boolean gameOver;

    /**
     * Constructs a new GameStatus object with the given winning score.
     * 
     * @param winningScore The score a player must reach to win the game.
     */
    public GameStatus(int winningScore) {
        this.winningScore = winningScore;
        this.gameOver = false;
    }

    /**
     * Set the game to be over.
     * Boolean gameOver is set true and buttons are disabled.
     */
    public void setGameOver() {
        gameOver = true;
        BulldogGameGUI.rollButton.setEnabled(false);
        BulldogGameGUI.endTurnButton.setEnabled(false);
        BulldogGameGUI.continueButton.setEnabled(false);  
    }

}
