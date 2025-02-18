import java.util.*;

/********************************************************/
/* Abigail Pitcairn                                     */
/* COS 420, Spring 2025                                 */
/* Programming Assignment 1                             */
/* Program6: Executes Bulldog game simulation           */
/********************************************************/

public class Progam6 {

    public static void main(String[] args) {
        
        Scanner scnr = new Scanner(System.in);
        ArrayList<Player> players = new ArrayList<Player>();
        
        // User inputs desired number of players in this simulation
        System.out.print("Enter the number of players in this game: ");
        int numPlayers = scnr.nextInt();
        
        // Print instructions for game setup
        System.out.print("Please enter the player type and name for each player."
                + "\nPlayer types are Human, Random, Fifteen, Unique, or Wimp.\n");
        
        // User inputs the Type and Name for each player
        for (int i = 0; i < numPlayers; i++) {
            int currentPlayer = i+1;
            System.out.print("Enter player " + currentPlayer + " type: ");
            String type = scnr.next();
            System.out.print("Enter player " + currentPlayer + " name: ");
            String name = scnr.next();
            
            // Declare new player objects here based on user input
            switch (type.toLowerCase()) {
                case "human":
                    players.add(new HumanPlayer(name));
                    break;
                case "random":
                    players.add(new RandomPlayer(name));
                    break;
                case "fifteen":
                    players.add(new FifteenPlayer(name));
                    break;
                case "unique":
                    players.add(new UniquePlayer(name));
                    break;
                case "wimp":
                    players.add(new WimpPlayer(name));
                    break;
                default:
                    System.out.println("Invalid player type... please try again.");
                    i--;
                    break;
            }
        }
        
        System.out.println("\nBegin Game:\n");
        
        // Gameplay simulation begins and continues until gameWon == true
        boolean gameWon = false;
        while (!gameWon) {
            for (Player player : players) {
                System.out.println(player.getName() + "'s turn:");
                int turnScore = player.play();
                player.setScore(player.getScore() + turnScore);
                System.out.println("Final score for this turn: " + turnScore);
                System.out.println(player.getName() + "'s total score: " + player.getScore() + "\n");

                if (player.getScore() >= 104) {
                    System.out.println(player.getName() + " wins!");
                    gameWon = true;
                    break;
                }
            }
        }       
        scnr.close();
        
    }
}
