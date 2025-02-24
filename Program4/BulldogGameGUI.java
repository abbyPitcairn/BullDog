package Program4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * The BulldogGameGUI class represents the graphical user interface (GUI) for the Bulldog Dice Game.
 * It manages the game flow, including handling player interactions, rolling dice, updating scores,
 * and displaying game logs. This class uses Swing components to create the GUI and manage the game's state.
 * @author Abigail Pitcairn
 * @version Feb 23, 2025
 */
public class BulldogGameGUI {
    private JFrame frame;
    private JTextArea gameLog;
    private JPanel scorePanel;
    private JButton rollButton, endTurnButton, continueButton;
    private List<Player> players;
    private int currentPlayerIndex;
    private int turnScore;
    final private int WINNING_SCORE = 104;

    /**
     * Constructor to initialize the GUI components, set up event listeners, and start the game.
     * It sets the look and feel of the UI, creates the main game window, initializes the players, 
     * and starts the first turn.
     */
    public BulldogGameGUI() {
        mySetLookAndFeel();
        frame = new JFrame("Bulldog Dice Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        scorePanel = new JPanel();
        scorePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Center with spacing
        frame.add(scorePanel, BorderLayout.NORTH);

        gameLog = new JTextArea();
        gameLog.setEditable(false);
        frame.add(new JScrollPane(gameLog), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        rollButton = new JButton("Roll Dice");
        endTurnButton = new JButton("End Turn");
        continueButton = new JButton("Continue");
        buttonPanel.add(rollButton);
        buttonPanel.add(endTurnButton);
        buttonPanel.add(continueButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        initializePlayers();
        rollButton.addActionListener(this::rollDice);
        endTurnButton.addActionListener(e -> endTurn());
        continueButton.addActionListener(e -> continueTurn());

        frame.setVisible(true);

        nextTurn();
    }

    /**
     * Advances the game to the next player's turn.
     * If the current player is human, the appropriate buttons will be made visible for interaction.
     * If the player is AI, their turn is automatically played.
     */
    private void nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        Player currentPlayer = players.get(currentPlayerIndex);
        log(currentPlayer.getName() + "'s turn:");
        if (currentPlayer instanceof HumanPlayer) {
            rollButton.setVisible(true);
            endTurnButton.setVisible(true);
            continueButton.setVisible(false);
        } else {
            rollButton.setVisible(false);
            endTurnButton.setVisible(false);
            continueButton.setVisible(true);
            turnScore = currentPlayer.play();
            log(currentPlayer.getName() + " scored " + turnScore);
        }
    }

    /**
     * Initializes the players by asking the user for the number of players and their details,
     * including name and type (Human, Random, Fifteen, Unique, or Wimp).
     * Each player is added to the game, and a label for each player's score is added to the score panel.
     */
    private void initializePlayers() {
        players = new ArrayList<>();

        ImageIcon dogIcon = new ImageIcon("/Users/abigailpitcairn/eclipse-workspace/420Program1/src/Program4/DogIcon.jpg");

        int numPlayers = Integer.parseInt(JOptionPane.showInputDialog("Enter number of players (1-5):"));
        for (int i = 0; i < numPlayers; i++) {
            String name = (String) JOptionPane.showInputDialog(null, "Enter name for player " + (i + 1) + ":",
                    "Enter Player Name", JOptionPane.INFORMATION_MESSAGE, dogIcon, null, null);

            String[] options = {"Human", "Random", "Fifteen", "Unique", "Wimp"};
            int choice = JOptionPane.showOptionDialog(null, "Choose player type for " + name + ":",
                    "Player Type", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, dogIcon, options, options[0]);

            switch (choice) {
                case 0 -> players.add(new HumanPlayer(name));
                case 1 -> players.add(new RandomPlayer(name));
                case 2 -> players.add(new FifteenPlayer(name));
                case 3 -> players.add(new UniquePlayer(name));
                case 4 -> players.add(new WimpPlayer(name));
                default -> players.add(new RandomPlayer(name));
            }

            JLabel playerLabel = new JLabel(name + ": 0");
            scorePanel.add(playerLabel);
        }

        currentPlayerIndex = -1;
        turnScore = 0;
    }

    /**
     * Updates the score panel to display the latest score of all players.
     * This method is called after each turn to reflect the updated scores.
     */
    private void updateScorePanel() {
        scorePanel.removeAll();
        for (Player player : players) {
            JLabel playerLabel = new JLabel(player.getName() + ": " + player.getScore());
            scorePanel.add(playerLabel);
        }
        scorePanel.revalidate();
        scorePanel.repaint();
    }

    /**
     * Handles the event when the "Roll Dice" button is pressed.
     * The current player rolls the dice, and if the roll is not a 6, their score is updated.
     * If a 6 is rolled, the player loses the turn and the score resets.
     * 
     * @param e The action event triggered by the "Roll Dice" button.
     */
    private void rollDice(ActionEvent e) {
        Player currentPlayer = players.get(currentPlayerIndex);
        int roll = (int) (Math.random() * 6 + 1);
        log("   " + currentPlayer.getName() + " rolled a " + roll);

        if (roll == 6) {
            log(currentPlayer.getName() + " lost the turn!");
            turnScore = 0;
            endTurn();
        } else {
            turnScore += roll;
            checkWinCondition(currentPlayer);
            log("   Turn total: " + turnScore);
        }
    }

    /**
     * Ends the current player's turn, updates their total score, and checks if they have won.
     * If the player has won, the game ends and the "Roll Dice" and "End Turn" buttons are disabled.
     * If the game is not over, the next player's turn begins.
     */
    private void endTurn() {
        Player currentPlayer = players.get(currentPlayerIndex);
        currentPlayer.setScore(currentPlayer.getScore() + turnScore);
        log(currentPlayer.getName() + "'s total score: " + currentPlayer.getScore() + "\n");

        updateScorePanel();

        if (currentPlayer.getScore() >= WINNING_SCORE) {
            log(currentPlayer.getName() + " wins!");
            rollButton.setEnabled(false);
            endTurnButton.setEnabled(false);
        } else {
            turnScore = 0;
            nextTurn();
        }
    }

    /**
     * Continues the current player's turn by ending the turn and starting the next one.
     * This method is linked to the "Continue" button.
     */
    private void continueTurn() {
        endTurn();
    }

    /**
     * Checks if the current player's score (after adding the turn score) has reached the winning score.
     * If so, it ends the turn immediately.
     * 
     * @param player The current player whose score is being checked.
     */
    private void checkWinCondition(Player player) {
        if (player.getScore() + turnScore >= WINNING_SCORE) {
            endTurn();
        }
    }

    /**
     * Sets the look and feel of the user interface to Nimbus, a modern and clean UI theme.
     * If the Nimbus look and feel is not supported, it falls back to the default look and feel.
     */
    private void mySetLookAndFeel() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException |
                 InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Logs a message to the game's log area in the GUI.
     * 
     * @param message The message to be logged.
     */
    private void log(String message) {
        gameLog.append(message + "\n");
    }

    /**
     * The main method that launches the Bulldog Dice Game GUI application.
     * It ensures that the GUI is created on the Event Dispatch Thread for thread safety.
     * 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(BulldogGameGUI::new);
    }
}
