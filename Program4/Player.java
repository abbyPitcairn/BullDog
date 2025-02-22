package Program4;

/********************************************************/
/* Bulldog Game                                        */
/* Implementation of the Bulldog dice game              */
/* Players can use different strategies to maximize     */
/* their score without rolling a 6                      */
/********************************************************/

import java.util.*;

/**
 * Abstract class representing a Player in the Bulldog game.
 */
public abstract class Player {
    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public abstract int play();
}

/**
 * A Player that always rolls only once.
 */
class WimpPlayer extends Player {
    public WimpPlayer(String name) {
        super(name);
    }

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
 * A Human player that decides each roll interactively.
 */
class HumanPlayer extends Player {
    private Scanner scanner = new Scanner(System.in);

    public HumanPlayer(String name) {
        super(name);
    }

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
 * A Random player that randomly decides to roll or stop.
 */
class RandomPlayer extends Player {
    public RandomPlayer(String name) {
        super(name);
    }

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
 */
class FifteenPlayer extends Player {
    public FifteenPlayer(String name) {
        super(name);
    }

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
 */
class UniquePlayer extends Player {
    public UniquePlayer(String name) {
        super(name);
    }

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
