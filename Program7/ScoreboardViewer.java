package Program7;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * A ScoreboardViewer that displays the names and scores of all players.
 * This class observes changes in the PlayerListModel and updates the UI when scores change.
 * 
 * @author Abigail Pitcairn and ChatGPT 4.0
 * @version March 26, 2025
 */
public class ScoreboardViewer extends JPanel implements ModelObserver {
    private PlayerListModel playerListModel;
    private JPanel scorePanel;

    /**
     * Constructs a ScoreboardViewer to display player names and scores.
     * @param playerListModel The model containing player data.
     */
    public ScoreboardViewer(PlayerListModel playerListModel) {
        this.playerListModel = playerListModel;
        this.playerListModel.addObserver(this);

        setLayout(new BorderLayout());

        scorePanel = new JPanel();
        scorePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Horizontal layout with spacing
        add(scorePanel, BorderLayout.CENTER);

        updateScoreboard();
    }

    /**
     * Updates the scoreboard display when the model changes.
     */
    @Override
    public void modelChanged() {
        updateScoreboard();
    }

    /**
     * Refreshes the scoreboard with the latest player scores.
     */
    protected void updateScoreboard() {
        scorePanel.removeAll();
        List<Player> players = playerListModel.getPlayers();
        
        for (Player player : players) {
            JLabel label = new JLabel(player.getName() + ": " + player.getScore());
            label.setFont(new Font("Arial", Font.BOLD, 14)); // Optional: Make text bold for readability
            scorePanel.add(label);
        }

        scorePanel.revalidate();
        scorePanel.repaint();
    }
}


