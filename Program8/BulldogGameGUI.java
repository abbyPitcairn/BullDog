package Program8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * The {@code BulldogGameGUI} class represents the graphical user interface (GUI) for the Bulldog Dice Game.
 * It manages the game flow, including handling player interactions, rolling dice, updating scores,
 * and displaying game logs. This class uses Swing components to create the GUI and manage the game's state.
 * 
 * @author Abigail Pitcairn and ChatGPT 4.0
 * @version April 16, 2025
 */
public class BulldogGameGUI {
    private JFrame frame;
    private static JTextArea gameLog;
    private JPanel scorePanel;
    protected static JButton rollButton;
    protected static JButton endTurnButton;
    protected static JButton continueButton;
    
    /**
     * Constructs the BulldogGameGUI and initializes the game window, scoreboard, and buttons.
     * Sets up the frame, layout, buttons, and game log area. Also initializes the player list and scoreboard.
     */
    public BulldogGameGUI() {
        mySetLookAndFeel();
        frame = new JFrame("Bulldog Dice Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        scorePanel = new JPanel();
        scorePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        scorePanel.add(Referee.scoreboardViewer);
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
        
        rollButton.addActionListener(e -> Referee.rollDice(e, Referee.gameStatus));
        endTurnButton.addActionListener(e -> Referee.endTurn(Referee.gameStatus));
        continueButton.addActionListener(e -> Referee.endTurn(Referee.gameStatus));

        frame.setVisible(true);
    }
    
    /**
     * Sets the look and feel of the Swing GUI to Nimbus for better aesthetics.
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
     * Logs messages to the game log text area, appending them to the current log.
     * 
     * @param message The message to be logged in the game log.
     */
    public static void log(String message) {
        gameLog.append(message + "\n"); 
    }

}


