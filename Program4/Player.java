package Program4;

/********************************************************/
/* Bulldog Game                                         */
/* Implementation of the Bulldog dice game              */
/* Players can use different strategies to maximize     */
/* their score without rolling a 6                      */
/* @author Abigail Pitcairn                             */
/* @version Feb 23, 2025                                */
/********************************************************/

import java.util.*;

/**
 * Abstract class representing a Player in the Bulldog game.
 * All types of players (Human, Wimp, Random, Fifteen, Unique) extend this class.
 * A player has a name, a score, and a method to play their turn.
 */
public abstract class Player {
    private String name;
    private int score;

    /**
     * Constructor to initialize the player with a name and an initial score of 0.
     * 
     * @param name The name of the player.
     */
    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    /**
     * Gets the name of the player.
     * 
     * @return The name of the player.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the current score of the player.
     * 
     * @return The current score of the player.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Sets the score of the player.
     * 
     * @param score The new score to set for the player.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Abstract method to be implemented by subclasses to define how a player plays their turn.
     * The return value is the score earned by the player in a single turn.
     * 
     * @return The score earned by the player during their turn.
     */
    public abstract int play();
}

/**
 * A Human player that decides each roll interactively.
 * The player rolls the dice and chooses whether to roll again or stop based on their decision.
 */
class HumanPlayer extends Player {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructor to initialize a HumanPlayer with a name.
     * 
     * @param name The name of the player.
     */
    public HumanPlayer(String name) {
        super(name);
    }

    /**
     * Human player’s turn where they roll the dice and decide whether to continue or stop.
     * The player continues rolling until they decide to stop or roll a 6, in which case the turn ends.
     * 
     * @return The score earned by the player during their turn.
     */
    @Override
    public int play() {
        int score = 0;
        while (true) {
            int roll = (int) (Math.random() * 6 + 1);
            System.out.println(getName() + " rolled " + roll);
            if (roll == 6) {
                System.out.println(getName() + " lost the turn!");
                return 0;
            }
            score += roll;
            System.out.print("Current score: " + score + ". Roll again? (y/n): ");
            if (!scanner.nextLine().equalsIgnoreCase("y")) {
                break;
            }
        }
        return score;
    }
}

/**
 * A Player that always rolls only once.
 * The player rolls the dice once and stops, either scoring the value or losing the turn if a 6 is rolled.
 */
class WimpPlayer extends Player {

    /**
     * Constructor to initialize a WimpPlayer with a name.
     * 
     * @param name The name of the player.
     */
    public WimpPlayer(String name) {
        super(name);
    }

    /**
     * WimpPlayer’s turn where they roll the dice once and stop immediately.
     * If a 6 is rolled, the turn ends with a score of 0; otherwise, the roll value is scored.
     * 
     * @return The score earned by the player during their turn.
     */
    @Override
    public int play() {
        int roll = (int) (Math.random() * 6 + 1);
        System.out.print("   Player " + getName() + " rolled " + roll);
        if (roll != 6) {
            System.out.println(" and chose not to continue, scoring " + roll + " for the turn.");
        } else {
            roll = 0;
            System.out.println(" and scored 0 for the turn.");
        }
        return roll;
    }
}

/**
 * A Random player that randomly decides to roll or stop.
 * The player randomly chooses to continue rolling or stop after each roll.
 * If a 6 is rolled, the turn ends with a score of 0.
 */
class RandomPlayer extends Player {

    /**
     * Constructor to initialize a RandomPlayer with a name.
     * 
     * @param name The name of the player.
     */
    public RandomPlayer(String name) {
        super(name);
    }

    /**
     * RandomPlayer’s turn where they randomly decide to continue rolling or stop.
     * If a 6 is rolled, the turn ends with a score of 0; otherwise, the score is accumulated.
     * 
     * @return The score earned by the player during their turn.
     */
    @Override
    public int play() {
        int score = 0;
        Random rand = new Random();
        while (rand.nextBoolean()) {
            int roll = (int) (Math.random() * 6 + 1);
            System.out.println(getName() + " rolled " + roll);
            if (roll == 6) {
                System.out.println(getName() + " lost the turn!");
                return 0;
            }
            score += roll;
        }
        return score;
    }
}

/**
 * A player that stops rolling once their turn score reaches 15.
 * This player will stop rolling when the accumulated score reaches or exceeds 15, 
 * unless they roll a 6, which causes them to lose the turn.
 */
class FifteenPlayer extends Player {

    /**
     * Constructor to initialize a FifteenPlayer with a name.
     * 
     * @param name The name of the player.
     */
    public FifteenPlayer(String name) {
        super(name);
    }

    /**
     * FifteenPlayer’s turn where they stop rolling when their score reaches or exceeds 15.
     * If a 6 is rolled, the turn ends with a score of 0.
     * 
     * @return The score earned by the player during their turn.
     */
    @Override
    public int play() {
        int score = 0;
        while (score < 15) {
            int roll = (int) (Math.random() * 6 + 1);
            System.out.println(getName() + " rolled " + roll);
            if (roll == 6) {
                System.out.println(getName() + " lost the turn!");
                return 0;
            }
            score += roll;
        }
        return score;
    }
}

/**
 * A unique player that follows a different rolling strategy.
 * This player rolls at least 3 times, and then has a 70% chance to continue rolling.
 * The turn ends if a 6 is rolled.
 */
class UniquePlayer extends Player {

    /**
     * Constructor to initialize a UniquePlayer with a name.
     * 
     * @param name The name of the player.
     */
    public UniquePlayer(String name) {
        super(name);
    }

    /**
     * UniquePlayer’s turn where they roll at least 3 times and then have a 70% chance to continue.
     * If a 6 is rolled, the turn ends with a score of 0.
     * 
     * @return The score earned by the player during their turn.
     */
    @Override
    public int play() {
        int score = 0;
        int rolls = 0;
        while (rolls < 3 || (Math.random() > 0.3)) { // Rolls at least 3 times, then has a 70% chance to continue
            int roll = (int) (Math.random() * 6 + 1);
            System.out.println(getName() + " rolled " + roll);
            if (roll == 6) {
                System.out.println(getName() + " lost the turn!");
                return 0;
            }
            score += roll;
            rolls++;
        }
        return score;
    }
}
