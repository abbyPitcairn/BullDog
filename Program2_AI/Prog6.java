

import java.util.*;

/**
 * Main class to run the Bulldog game.
 */
public class Prog6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of players: ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine();
        
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter name for player " + (i + 1) + ": ");
            String name = scanner.nextLine();
            System.out.print("Choose player type (1: Human, 2: Random, 3: Fifteen, 4: Unique, 5: Wimp, 6: MyUniquePlayer): ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    players.add(new HumanPlayer(name));
                    break;
                case 2:
                    players.add(new RandomPlayer(name));
                    break;
                case 3:
                    players.add(new FifteenPlayer(name));
                    break;
                case 4:
                    players.add(new UniquePlayer(name));
                    break;
                case 5:
                    players.add(new WimpPlayer(name));
                    break;
                case 6:
                    players.add(new MyUniquePlayer(name));
                    break;
                default:
                    System.out.println("Invalid choice, defaulting to RandomPlayer.");
                    players.add(new RandomPlayer(name));
            }
        }
        
        boolean gameOver = false;
        while (!gameOver) {
            for (Player player : players) {
                System.out.println("\n" + player.getName() + "'s turn:");
                int turnScore = player.play();
                player.setScore(player.getScore() + turnScore);
                System.out.println(player.getName() + "'s total score: " + player.getScore());
                
                if (player.getScore() >= 104) {
                    System.out.println(player.getName() + " wins!");
                    gameOver = true;
                    break;
                }
            }
        }
        
        scanner.close();
    }
}

