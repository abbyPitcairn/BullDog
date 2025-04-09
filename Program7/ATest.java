package Program7;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the {@link SevenPlayer} class to verify its behavior.
 * 
 * @author Abigail Pitcairn and ChatGPT 4.0
 * @version April 9, 2025
 */
public class ATest {

    private SevenPlayer player;
    private FakeRandom dice;

    /**
     * Sets up the test environment by initializing the {@link SevenPlayer} and 
     * preparing a fake dice object.
     */
    @Before
    public void setUp() {
        player = new SevenPlayer("TestPlayer");
    }

    /**
     * Test case where the player rolls a 6 and loses the turn.
     * This test simulates the scenario where the first roll is a 6, and the player
     * loses their turn, with the score being 0.
     */
    @Test
    public void testPlayerLosesTurnOnRoll6() {
        int[] rolls = {6};
        dice = new FakeRandom(6, rolls);       
        int score = player.play(dice);
        assertEquals(0, score);
    }

    /**
     * Test case where the player's score exceeds 7 and they stop rolling after reaching 7.
     * This test checks that the player does not continue rolling once their score exceeds 7,
     * and the final score should be 8.
     */
    @Test
    public void testPlayerStopsAfterExceeding7() {
        int[] rolls = {3, 3, 2};
        dice = new FakeRandom(6, rolls);        
        int score = player.play(dice);        
        assertEquals(8, score);  
    }

    /**
     * Test case where the player reaches exactly a score of 7 after three rolls.
     * This test simulates a sequence of three rolls that add up exactly to 7, and ensures
     * that the player stops once the score reaches 7.
     */
    @Test
    public void testPlayerExact7WithThreeRolls() {
        int[] rolls = {1, 2, 4}; 
        dice = new FakeRandom(6, rolls);        
        int score = player.play(dice);        
        assertEquals(7, score);
    }

    /**
     * Test case where the player reaches exactly a score of 7 after two rolls.
     * This test simulates a sequence of two rolls that add up exactly to 7, and ensures
     * that the player stops once the score reaches 7.
     */
    @Test
    public void testPlayerExact7WithTwoRolls() {
        int[] rolls = {2, 5};
        dice = new FakeRandom(6, rolls); 
        int score = player.play(dice);       
        assertEquals(7, score);
    }
    
    /**
     * Test case where the player reaches exactly a score of 7 after seven rolls.
     * This test simulates a sequence of seven rolls that add up exactly to 7, and ensures
     * that the player stops once the score reaches 7.
     */
    @Test
    public void testPlayerExact7WithSevenRolls() {
        int[] rolls = {1, 1, 1, 1, 1, 1, 1};
        dice = new FakeRandom(6, rolls); 
        int score = player.play(dice);       
        assertEquals(7, score);
    }
    
    /**
     * Test case where the player reaches exactly a score of 7 after six rolls.
     * This test simulates a sequence of six rolls that add up exactly to 7, and ensures
     * that the player stops once the score reaches 7.
     */
    @Test
    public void testPlayerExact7WithSixRolls() {
        int[] rolls = {1, 1, 1, 2, 1, 1};
        dice = new FakeRandom(6, rolls); 
        int score = player.play(dice);       
        assertEquals(7, score);
    }
    
    /**
     * Test case where the player reaches a maximum of 11 points.
     * This test simulates a sequence of three rolls that add up to 6, and ensures
     * that the player stops once the score passes 7 on the next roll.
     */
    @Test
    public void testPlayerExceeds7Max() {
        int[] rolls = {4, 1, 1, 5};
        dice = new FakeRandom(6, rolls); 
        int score = player.play(dice);       
        assertEquals(11, score);
    }
    
    /**
     * Test case where the player rolls a six after rolling up to six points for the turn.
     * This test simulates a sequence of two rolls that add up exactly to 6, and ensures
     * that the player stops and gets a score of 0 once the next roll is six.
     */
    @Test
    public void testPlayerLosesAt6() {
        int[] rolls = {4, 2, 6};
        dice = new FakeRandom(6, rolls); 
        int score = player.play(dice);       
        assertEquals(0, score);
    }
    
    /**
     * Test case where the player rolls three threes in a row.
     * This test simulates a sequence of two rolls that add up exactly to 9, and ensures
     * that the player stops and gets a score of 9.
     */
    @Test
    public void testPlayerReptitionOf3s() {
        int[] rolls = {3, 3, 3};
        dice = new FakeRandom(6, rolls); 
        int score = player.play(dice);       
        assertEquals(9, score);
    }
    
    /**
     * Test case where the player rolls and reaches 7 before a 6 is rolled.
     * This test simulates a sequence of three rolls that add up exactly to 7, and ensures
     * that the player stops and gets a score of 7 before the next number, 6, is rolled.
     */
    @Test
    public void testPlayerReaches7BeforeA6() {
        int[] rolls = {3, 2, 2, 6};
        dice = new FakeRandom(6, rolls); 
        int score = player.play(dice);       
        assertEquals(7, score);
    }
}
