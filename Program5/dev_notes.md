# DEVELOPMENT NOTES
PROGRAM 5
FEB. 26, 2025

### Implementation:
i. attempt to get the AI tool to implement the improvement
ii. after 2-3 attempts, continue with tool OR implement by hand
iii. assess how well the tool improved the code

### Improvement 1: Get rid of "104"
 - This was already done and was manually implemented by myself in an effort to improve the AI's work on program 4.

### Improvement 2: Consolidate randomness
 - Gave AI the Player.java and BulldogGUI.java classes and the text from the assignment on improvement 2; AI returned a
   non-functional program that looked nothing like what it was given 
 
 - AI tool designed a "Dice" class that included methods for roll() and getScore() and the Dice constructor took a parameter, 
   int sides, which dictated how many sides the die would have (set a minimum requirement of 2 sides)
 - Prompted the AI to simplify the Dice class to only a constructor and roll() method
 
 - Gave AI the Player.java class, which currently includes Player interface as well as the five player subclasses and asked
   to implement the Dice class into the play() methods
 - AI had each player class create its own Dice object, despite previously having been told to implement Dice to "consolidate
   randomness" with only one instance. (At one point, the Dice class declared within it one static final Dice object; the AI
   changed this when it was asked to consolidate Dice to just constructor and roll())
 - After a few back and forth prompts/responses, I decided the best pipeline would have the play() methods take a Dice object 
   as a parameter. The AI was quickly/effectively able to implement this with more explicit instructions.
  
  - Gave AI the BulldogGUI.java class and asked it to implement the Dice object, which it did creating a Dice in the
    BulldogGUI constructor and then added it to fill the new parameter in all play() methods. 
    
### Improvement 3: Javadoc
 - This was easy to implement, although there was variation in the level of detail.
 - The code I provided it, Player.java and BulldogGUI.java, both came with Javadoc. During the revision process, the AI
   omitted the comments and at the end, had to be explicitly asked to add them back.
 - At one point, the AI left out the updateScorePanel() method from BullDogGUI.java entirely and had to be explicitly asked
   to add it back.
   
### Improvement 4: File Structure
 - The format of the classes that the AI provided was already structured in a way that could easily be split into separate
   classes:
    - Dice was its own class
    - BulldogGUI was its own class
    - Player was its own class
    - Each Player subclass was included in the same file as Player, but each could be moved to their own file without alteration
      to the code. 
      
### Overall Evaluation:
 AI tools are not that intelligent. They cannot implement broad, abstract changes to the structure and paths of a program.
 However, they are very effective at doing the "busy work" for you, and are competent enough for straight-forward tasks.
 It is moreso when you ask the AI to implement an IDEA that it will get it wrong, or give you something that isn't even code.
 On the contrary, if you give AI a clear, detailed gameplan, it can save lots of time. 
 For example, the AI was not very good at implementing the single Dice object across the project when prompted with a vague
 description of the desired resulting functionality. It got it very wrong, not just stylistically but functionally and practically. 
 Instead, the human programmer came up with a more detailed plan, and asked the AI to implement them in smaller phases; 
 Dice class, play() methods take Dice parameter, and then add one instance of Dice in the BulldogGUI constructor and pass it to 
 the play() methods when they're called. 
 It appears that the AI can only understand a certain level of abstraction, but the AI can still be incredibly effective if the
 programmer can reduce even a little bit of the ambiguity. 
 The only AI used in this project was ChatGPT. The IDE used was Eclipse, which does not offer AI help.  
