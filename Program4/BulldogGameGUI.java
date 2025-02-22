package Program4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class BulldogGameGUI {
    private JFrame frame;
    private JTextArea gameLog;
    private JButton rollButton, endTurnButton;
    private List<Player> players;
    private int currentPlayerIndex;
    private int turnScore;

    public BulldogGameGUI() {
        frame = new JFrame("Bulldog Dice Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        gameLog = new JTextArea();
        gameLog.setEditable(false);
        frame.add(new JScrollPane(gameLog), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        rollButton = new JButton("Roll Dice");
        endTurnButton = new JButton("End Turn");
        buttonPanel.add(rollButton);
        buttonPanel.add(endTurnButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        initializePlayers();
        rollButton.addActionListener(this::rollDice);
        endTurnButton.addActionListener(e -> endTurn());

        frame.setVisible(true);
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
        }

        currentPlayerIndex = 0;
        turnScore = 0;
        log(players.get(currentPlayerIndex).getName() + "'s turn:");
    }

    private void rollDice(ActionEvent e) {
        Player currentPlayer = players.get(currentPlayerIndex);
        int roll = (int) (Math.random() * 6 + 1);
        log("   " + currentPlayer.getName() + " rolled " + roll);

        if (roll == 6) {
            log(currentPlayer.getName() + " lost the turn!");
            turnScore = 0;
            endTurn();
        } else {
            turnScore += roll;
            log("Current score: " + turnScore);
        }
    }

    private void endTurn() {
        Player currentPlayer = players.get(currentPlayerIndex);
        currentPlayer.setScore(currentPlayer.getScore() + turnScore);
        log(currentPlayer.getName() + "'s total score: " + currentPlayer.getScore());

        if (currentPlayer.getScore() >= 104) {
            log(currentPlayer.getName() + " wins!");
            rollButton.setEnabled(false);
            endTurnButton.setEnabled(false);
        } else {
            turnScore = 0;
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            log("\n" + players.get(currentPlayerIndex).getName() + "'s turn:");
        }
    }

    private void log(String message) {
        gameLog.append(message + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BulldogGameGUI::new);
    }
}