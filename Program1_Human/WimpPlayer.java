/********************************************************/
/* David Levine                                         */
/* Login ID: david.b.levine@maine.edu                   */
/* COS 497, Summer 2024                                 */
/* Programming Assignment 6                             */
/* WimpPlayer class: extends Player class               */
/*           A WimpPlayer always rolls the die once     */
/*      See Kettering University, CS-101, Prog 6        */
/********************************************************/

public class WimpPlayer extends Player {

    /********************************************************/
    /* Constructor: WimpPlayer                              */
    /* Purpose: Create a default WimpPlayer                 */
    /* Parameters:                                          */
    /*   none                                               */
    /********************************************************/
    public WimpPlayer () {
        this("Wimp");
    }

    /********************************************************/
    /* Constructor: WimpPlayer                              */
    /* Purpose: Create a new WimpPlayer object              */
    /* Parameters:                                          */
    /*   String name:  the name of the Player being created */
    /********************************************************/
    public WimpPlayer (String name) {
        super(name);
    }

    /********************************************************/
    /* Method:  play                                        */
    /* Purpose: Take one turn for this Player               */
    /*          One turn for a WimpPlayer is a single roll  */
    /* Parameters:                                          */
    /*   none                                               */
    /* Returns:                                             */
    /*   the score earned by the player on this turn,       */
    /*       which will be zero if a six was rolled         */
    /********************************************************/
    public int play() {
        int roll = (int) (Math.random()*6 + 1);
        System.out.print("   Player " + getName() + " rolled " + roll );
        if (roll != 6) {
            System.out.println(" and chose not to continue, scoring " 
                   + roll + " for the turn.");
        } else {
            roll = 0;
            System.out.println(" and scored 0 for the turn.");
        }
        return roll;
    }

}
