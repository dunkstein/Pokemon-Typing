PROJECT TITLE: P3A - Trash Pokemon <br/>
PURPOSE OF PROJECT: Game for ICS4U <br/>
VERSION/DATE: 11/9/2021 <br/>
HOW TO START THIS PROJECT: Open in Greenfoot <br/>
AUTHORS: Kenneth Li, Isaac Chan, Vincent Hsieh <br/>
USER INSTRUCTIONS: Follow on-screen instructions of game <br/>

![Game Preview](images/preview.png?raw=true "Preview")

------------------------------------------------------------------------
This game is a parody game of Pokemon, all assets used belong to their respective copyright holders
------------------------------------------------------------------------

DATA STRUCTURE USAGE:

2D Arrays: Used in the Player class
- Can be seen in the class, Animation_Table around line 120

Stack: Used in the battleWorld class
- The usage of the Stack itself can be seen around line 380
- The stack is called userInput. The stack was used mainly because you could remove (and add) to/from the top of the stack via pop
thus simulating a form of natural typing 

ArrayList: Used in the battleWorld class
- The ArrayLists used are called hard, easy and query.
- hard and easy are used to store words from the url, where hard stores words longer than 7 characters (also see TextReader class)
- query stores the words that the user must type, including any spaces. This will later be turned into a String for comparison
(see line 280 of battleWorld class)

