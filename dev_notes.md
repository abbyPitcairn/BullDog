# Program 6 Development Notes 
### March 26, 2025
### Total Times: 
- Prompting: 40 minutes
- Correcting: 35 minutes

## Task 1: Build a Model

**Time spent prompting AI Tool**: ~2 minutes  
**Time spent correcting AI code**: ~2 minutes  

### Notes on this task process:
- Gave the AI tool the text from the assignment for this task.
- Received `PlayerListModel.java` in return.
- Copied and pasted this class into the Program6 project in Eclipse.
- Found errors related to the call to an object called `ModelObserver`, which did not exist in the project yet.
- Asked the AI to generate the `ModelObserver` class to accompany `PlayerListModel.java`.
- Copied and pasted the code into `ModelObserver.java` in the Program6 project (this way, `PlayerListModel` could be tested before proceeding).

## Task 2: Build a Viewer

**Time spent prompting AI Tool**: ~5 minutes  
**Time spent correcting AI code**: ~2 minutes  

### Notes on this task process:
- Gave the AI tool the text from the assignment for this task.
- Received `ScoreboardViewer.java` in return.
- Copied and pasted this class into the Program6 project.
- Found an error in the call to `playerListModel.getPlayers()` because that method didn't exist.
- Prompted the AI to go back and add `getPlayers()` to `PlayerListModel.java`, which it did successfully on the first try.
- The AI also added a `main` method to the `ScoreboardViewer` class, which was manually removed.

## Task 3: Integration into Bulldog

**Time spent prompting AI Tool**: ~30 minutes  
**Time spent correcting AI code**: ~20 minutes  

### Notes on this task process:
- Gave the AI tool the text from the assignment for this task (minus the debugging notes).
- Received `BulldogGUI.java` based on the previous iteration in Program4, updated with materials from the previous tasks.
- Errors appeared because the `Dice` from previous programs was not implemented.
- Prompted ChatGPT to add a Singleton `Dice` and provided it with `Dice.java` from the previous assignment.
- Manually changed the method name from `setScore` to the correct one: `playerListModel.setPlayerScore(currentPlayerIndex, currentPlayer.getScore() + turnScore);`.
- Began debugging the entire project at this point.
- Manually edited the output on the GUI to stop printing score totals and reformatted the "Turn total".
- Prompted the AI to show the scores at the top of the GUI again, as it stopped displaying the scores.
- Manually changed the method call `scoreboardViewer.updateScoreboard()` because the method name was wrong.
- Changed the visibility of the `updateScoreboard` method in `ScoreboardViewer.java`.
- Encountered an error where `playerListModel` could not `addObserver` because it was null. I provided the error message to ChatGPT, which recommended moving the initialization of `playerListModel` before creating `scoreboardViewer`. This change resolved the issue.
- Another error occurred stating that a `JFrame` window was being added to a container, which is not allowed. I provided this error message to ChatGPT, and it gave corrected code for the `BulldogGUI` constructor.
- This corrected code caused the same error to occur again.
- ChatGPT suggested ensuring that `scoreboardViewer` was a panel component, not a window. I provided `ScoreboardViewer.java` for review, and ChatGPT corrected it.
- Code worked successfully after that!
- Manually disabled the 'Continue' button when the game ended.
- Finally, to match the original layout of the GUI, I prompted ChatGPT to display scores on the scoreboard in a single line rather than stacking them.
- Conducted testing of the game, which worked with different numbers of players, different types of players, and various ways of meeting the win condition to end the game.
- As a final touch, I had ChatGPT update the Dice class based on feedback from a previous assignment, which checked for the single instance in the constructor and had a variable amount of sides; I manually updated the sides to set to 6 and tested the program a final time. 

## Task 4: Javadoc

**Time spent prompting AI Tool**: ~2 minutes  
**Time spent correcting AI code**: ~10 minutes  

### Notes on this task process:
- Tied this task into the previous task by asking for "thorough Javadoc" during previous edits.
- Javadoc was not added to the `main` method of `BulldogGUI.java`; I manually added it.
- Most of the time was spent proofreading, as very few modifications were needed.

## Overall Development Notes for Program 6:

Firstly, I provided the AI tool (ChatGPT free version) with the content from the assignment for each task. Then, it generated the code, and I placed the code into my project. It wasn't until step 3 that I really began to tweak things myself to get everything working together as desired. During Task 3, I went back and had ChatGPT make some edits to the code from previous tasks. Task 4 was simple, and ChatGPT could handle it with just a couple of prompts and very little intervention. For peace of mind, most of the time spent on Task 4 was dedicated to proofreading the code documentation.

I left for a while and came back to finish the write up and make a few final tweaks. 
