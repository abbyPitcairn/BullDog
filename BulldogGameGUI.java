package Program6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * The BulldogGameGUI class represents the graphical user interface (GUI) for the Bulldog Dice Game.
 * It manages the game flow, including handling player interactions, rolling dice, updating scores,
 * and displaying game logs. This class uses Swing components to create the GUI and manage the game's state.
 */
public class BulldogGameGUI {
    private JFrame frame;
    private JTextArea gameLog;
    private JPanel scorePanel;
    private JButton rollButton, endTurnButton, continueButton;
    private PlayerListModel playerListModel;
    private ScoreboardViewer scoreboardViewer;
    private int currentPlayerIndex;
    private int turnScore;
    final private int WINNING_SCORE = 104;
    private Dice dice;
    
    /**
     * Constructs the BulldogGameGUI and initializes the game window, player list, scoreboard, and buttons.
     */
    public BulldogGameGUI() {
        mySetLookAndFeel();
        frame = new JFrame("Bulldog Dice Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        playerListModel = new PlayerListModel(); // Initialize first
        scoreboardViewer = new ScoreboardViewer(playerListModel); // Ensure this is a JPanel

        scorePanel = new JPanel();
        scorePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        scorePanel.add(scoreboardViewer); // Add ScoreboardViewer to a panel, not directly to the frame
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

        dice = new Dice();
        initializePlayers();
        
        rollButton.addActionListener(this::rollDice);
        endTurnButton.addActionListener(e -> endTurn());
        continueButton.addActionListener(e -> endTurn());

        frame.setVisible(true);

        nextTurn();
    }


    /**
     * Initializes players by prompting the user for names and player types.
     */
    private void initializePlayers() {
        int numPlayers = Integer.parseInt(JOptionPane.showInputDialog("Enter number of players (1-5):"));
        for (int i = 0; i < numPlayers; i++) {
            String name = JOptionPane.showInputDialog("Enter name for player " + (i + 1) + ":");
            String[] options = {"Human", "Random", "Fifteen", "Unique", "Wimp"};
            int choice = JOptionPane.showOptionDialog(null, "Choose player type for " + name + ":",
                    "Player Type", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0 -> playerListModel.addPlayer(new HumanPlayer(name));
                case 1 -> playerListModel.addPlayer(new RandomPlayer(name));
                case 2 -> playerListModel.addPlayer(new FifteenPlayer(name));
                case 3 -> playerListModel.addPlayer(new UniquePlayer(name));
                case 4 -> playerListModel.addPlayer(new WimpPlayer(name));
                default -> playerListModel.addPlayer(new RandomPlayer(name));
            }
        }

        scoreboardViewer.updateScoreboard();
        currentPlayerIndex = -1;
        turnScore = 0;
    }
    
    /**
     * Moves the game to the next player's turn and updates the GUI accordingly.
     */
    private void nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % playerListModel.getPlayers().size();
        Player currentPlayer = playerListModel.getPlayers().get(currentPlayerIndex);
        log(currentPlayer.getName() + "'s turn:");
        if (currentPlayer instanceof HumanPlayer) {
            rollButton.setVisible(true);
            endTurnButton.setVisible(true);
            continueButton.setVisible(false);
        } else {
            rollButton.setVisible(false);
            endTurnButton.setVisible(false);
            continueButton.setVisible(true);
            turnScore = currentPlayer.play(dice);
            log(currentPlayer.getName() + " scored " + turnScore);
        }
    }

    /**
     * Rolls the dice for the current player and updates the game state accordingly.
     */
    private void rollDice(ActionEvent e) {
        Player currentPlayer = playerListModel.getPlayers().get(currentPlayerIndex);
        int roll = dice.roll();
        log("   " + currentPlayer.getName() + " rolled a " + roll);

        if (roll == 6) {
            log(currentPlayer.getName() + " lost the turn!");
            turnScore = 0;
            endTurn();
        } else {
            turnScore += roll;
            checkWinCondition(currentPlayer);
        }
    }

    /**
     * Ends the current player's turn, updates the score, and moves to the next player.
     */
    private void endTurn() {
        Player currentPlayer = playerListModel.getPlayers().get(currentPlayerIndex);
        playerListModel.setPlayerScore(currentPlayerIndex, currentPlayer.getScore() + turnScore);

        scoreboardViewer.updateScoreboard();

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
     * Checks the win condition of score >= 104 for the input player
     * @param player The player to check the score of
     */
    private void checkWinCondition(Player player) {
        if (player.getScore() + turnScore >= WINNING_SCORE) {
            endTurn();
        }
    }
    
    /**
     * Set the look and feel of the JSwing GUI to Nimbus
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
     * Logs messages to the game log text area.
     * @param message The message to be logged.
     */
    public void log(String message) {
        gameLog.append(message + "\n"); 
    }

    /**
     * The entry point of the Bulldog Dice Game application.
     * This method initializes the game GUI by creating an instance of {@code BulldogGameGUI}
     * and making it visible on the Event Dispatch Thread. It ensures that the GUI is created 
     * in the correct thread, following Swing's best practices for thread safety.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(BulldogGameGUI::new);
    }
}

