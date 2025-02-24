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

    private void updateScorePanel() {
        scorePanel.removeAll();
        for (Player player : players) {
            JLabel playerLabel = new JLabel(player.getName() + ": " + player.getScore());
            scorePanel.add(playerLabel);
        }
        scorePanel.revalidate();
        scorePanel.repaint();
    }

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

    private void continueTurn() {
        endTurn();
    }
    
    private void checkWinCondition(Player player) {
        if (player.getScore() + turnScore >= WINNING_SCORE) {
            endTurn();
        }
    }
    
    private void mySetLookAndFeel() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException |
                 InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void log(String message) {
        gameLog.append(message + "\n"); 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BulldogGameGUI::new);
    }
}
