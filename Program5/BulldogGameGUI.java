package Program5;

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
 * @version Feb 26, 2025
 */
public class BulldogGameGUI {
    private JFrame frame;
    private JTextArea gameLog;
    private JPanel scorePanel;
    private JButton rollButton, endTurnButton, continueButton;
    private List<Player> players;
    private int currentPlayerIndex;
    private int turnScore;
    private Dice gameDice;
    final private int WINNING_SCORE = 104;

    /**
     * Constructor to initialize the GUI components, set up event listeners, and start the game.
     */
    public BulldogGameGUI() {
        mySetLookAndFeel();
        frame = new JFrame("Bulldog Dice Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        scorePanel = new JPanel();
        scorePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
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

        gameDice = new Dice();
        initializePlayers();
        rollButton.addActionListener(this::rollDice);
        endTurnButton.addActionListener(e -> endTurn());
        continueButton.addActionListener(e -> continueTurn());

        frame.setVisible(true);

        nextTurn();
    }

    /**
     * Advances the game to the next player's turn.
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
            turnScore = currentPlayer.play(gameDice);
            log(currentPlayer.getName() + " scored " + turnScore);
        }
    }

    /**
     * Initializes the players by prompting user input.
     */
    private void initializePlayers() {
        players = new ArrayList<>();
        int numPlayers = Integer.parseInt(JOptionPane.showInputDialog("Enter number of players (1-5):"));
        for (int i = 0; i < numPlayers; i++) {
            String name = JOptionPane.showInputDialog("Enter name for player " + (i + 1) + ":");
            String[] options = {"Human", "Random", "Fifteen", "Unique", "Wimp"};
            int choice = JOptionPane.showOptionDialog(null, "Choose player type for " + name + ":",
                    "Player Type", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
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
     * Updates the score panel to reflect the current scores of all players.
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
     * Handles rolling the dice for the current player.
     */
    private void rollDice(ActionEvent e) {
        Player currentPlayer = players.get(currentPlayerIndex);
        int roll = gameDice.roll();
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
     * Ends the current player's turn and updates their score.
     */
    private void endTurn() {
        Player currentPlayer = players.get(currentPlayerIndex);
        currentPlayer.setScore(currentPlayer.getScore() + turnScore);
        updateScorePanel();
        if (currentPlayer.getScore() >= WINNING_SCORE) {
            log(currentPlayer.getName() + " wins!");
            rollButton.setEnabled(false);
            endTurnButton.setEnabled(false);
            continueButton.setEnabled(false);
        } else {
            turnScore = 0;
            nextTurn();
        }
    }

    /**
     * Continues the turn when the "Continue" button is pressed.
     */
    private void continueTurn() {
        endTurn();
    }

    /**
     * Checks if the current player's score reaches the winning condition.
     */
    private void checkWinCondition(Player player) {
        if (player.getScore() + turnScore >= WINNING_SCORE) {
            endTurn();
        }
    }

    /**
     * Sets the look and feel of the GUI to Nimbus.
     */
    private void mySetLookAndFeel() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Logs messages to the game log text area.
     */
    private void log(String message) {
        gameLog.append(message + "\n");
    }

    /**
     * The main method to launch the Bulldog Dice Game GUI.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(BulldogGameGUI::new);
    }
}
