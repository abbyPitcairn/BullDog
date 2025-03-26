package Program6;

import java.util.ArrayList;

/**
 * The PlayerListModel class represents the model in an MVC pattern for managing a list of players.
 * It allows adding players, retrieving player details, updating scores, and notifying observers
 * about changes in the model.
 */
public class PlayerListModel {
    private ArrayList<Player> players;
    private ArrayList<ModelObserver> observers;

    /**
     * Constructs a new PlayerListModel with an empty list of players and observers.
     */
    public PlayerListModel() {
        players = new ArrayList<>();
        observers = new ArrayList<>();
    }

    /**
     * Adds a player to the list.
     * @param player The player to be added.
     */
    public void addPlayer(Player player) {
        players.add(player);
        notifyObservers();
    }

    /**
     * Retrieves the name of the player at the specified index.
     * @param index The index of the player.
     * @return The player's name, or null if the index is invalid.
     */
    public String getPlayerName(int index) {
        if (index >= 0 && index < players.size()) {
            return players.get(index).getName();
        }
        return null;
    }

    /**
     * Retrieves the score of the player at the specified index.
     * @param index The index of the player.
     * @return The player's score, or -1 if the index is invalid.
     */
    public int getPlayerScore(int index) {
        if (index >= 0 && index < players.size()) {
            return players.get(index).getScore();
        }
        return -1;
    }

    /**
     * Updates the score of a player at the specified index.
     * @param index The index of the player.
     * @param newScore The new score to be set.
     */
    public void setPlayerScore(int index, int newScore) {
        if (index >= 0 && index < players.size()) {
            players.get(index).setScore(newScore);
            notifyObservers();
        }
    }
    
    /**
     * Returns the list of players.
     * @return The list of players.
     */
    public ArrayList<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    /**
     * Adds an observer to the model.
     * @param observer The observer to be added.
     */
    public void addObserver(ModelObserver observer) {
        observers.add(observer);
    }

    /**
     * Notifies all registered observers about changes in the model.
     */
    private void notifyObservers() {
        for (ModelObserver observer : observers) {
            observer.modelChanged();
        }
    }
}
