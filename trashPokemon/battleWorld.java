import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*; // Used for mainly ArrayList, Random and Stack
/**
 * A class for the World that the player will use to battle an enemy
 * 
 * @author Isaac Chan, Kenneth Li 
 * @version 1.0
 */
public class battleWorld extends World
{
    private Random rand = new Random(); // To generate random enemy sprite
    
    // Generate a random monster for the enemy from a list of possible monsters
    // Note this is purely cosmetic
    // More images can be added in possibleMons if desired
    private String[] possibleMons = {"mon1.png", "mon2.png", "mon3.png"};
    private String chosenMon = possibleMons[rand.nextInt(possibleMons.length)];
    
    // Construct the objects for the Images and the Labels
    private Images box = new Images("textBox.png", 868, 154);
    private Images ui = new Images("battleUI.png", 472, 188);
    private Images select = new Images("selector.png", 28, 44);
    private Images fight = new Images("fightLabel.png", 120, 40);
    private Images bag = new Images("bagLabel.png", 72, 40);
    private Label potionQuantity = new Label(Player.potions + " x ", 60);
    private Label Notification = new Label("", 60);
    private Images potion = new Images("potion.png", 153, 39);
    private Images moveOne = new Images("pound.png", 30*3, 10*3);
    private Images moveTwo = new Images("razorleaf.png", 57*3, 11*3);
    private Images playerHealthBar = new Images("playerHPBar.png", 108*3, 42*3);
    private Images enemyHealthBar = new Images("enemyHPBar.png", 110*3, 36*3);
    private Images playerHPCount = new Images("hp.png", 143, 3*3);
    private Images enemyHPCount = new Images("hp.png", 143, 3*3);
    private Images pcMon = new Images("playerMon.png", 96*6, 96*6);
    private Images enemyMon = new Images(chosenMon, 96*4, 96*4);
    
    // Create data structures for the user's input (typing) and the typing prompts
    private Stack<String> userInput = new Stack<String>();
    private ArrayList<String> hard = new ArrayList<String>();
    private ArrayList<String> easy = new ArrayList<String>();
    private ArrayList<String> query = new ArrayList<String>();
    
    // Declare some Label variables, since Labels do not have an edit font size
    // feature, a declaration is used so new Label objects can be stored in these
    private Label typedText;
    private Label generatedWords;
    
    // Initialize some variables
    private int movesTaken = 0; // Stores the movesTaken by the user for score
    private int timer = 0; // Around 50-60 act() cycles per second
    private int damageDealt = 0; // Stores the damage done by the player
    private int enemyHP = 100; // Stores the enemy's health, it always starts at 100
    
    // Variables used in input vs query comparison
    private String typed; // A string var used to store userInput as a String
    private String generated; // A string var used to store query as a String
    
    // String variables to determine which action should be run
    private String curAction = "choosing"; // Which UI action are they on?
    private String curUIAction = "fight"; // Is the user in move selection?
    private String moveSelection = "one"; // Which move did they choose
    private String key = null; // For key press comparison to a String
    
    // All of the sounds, the music is stored as a public static so it can be stopped on titlePage
    public static GreenfootSound battleMusic = new GreenfootSound("battleMusic.mp3");
    private GreenfootSound bAttack = new GreenfootSound("Quick Attack.mp3");
    private GreenfootSound razorLeafAttack = new GreenfootSound("Razor Leaf.mp3");
    public static GreenfootSound winMusic = new GreenfootSound("victoryMusic.mp3");
    // Note on sounds: Sometimes BitStream error code 102 is raised
    // I cannot find a specific fix, but it (appears) to stem from having data
    // that isn't part of the sound file being stored in the file. Resaving
    // without the information data (author, publisher, etc) seems to have 
    // alleviated some of the issues
    
    
    /**
     * Constructor for objects of class battleWorld.
     * Includes some of the stuff that should be started upon the start up of
     * battleWorld
     */
    public battleWorld()
    {    
        // Create a new world with 960x576 cells with a cell size of 1x1 pixels.
        super(960, 576, 1);
        mapOne.music.stop(); // Stop music from the overworld
        setBackground("TestBackground.png"); // Set the background of the battle
        battleMusic.setVolume(15);
        winMusic.setVolume(15);
        bAttack.setVolume(20);
        razorLeafAttack.setVolume(20);
        battleMusic.playLoop();
        
        // Try to put the words from the url in TextReader into ArrayLists
        try
        {
            TextReader.readInto(hard, easy);
        } 
        catch(Exception e)
        {
            // The only way an error ever seems to be raised for this specific
            // code seems to be when the user is not connected to the internet
            // and Java tries to read the url
            System.out.println("Probably not connected to the internet");
        }
        
    }
    
    public void act()
    {
        // Set the String key variable to the current key pressed
        key = Greenfoot.getKey();
        
        // Add in the representations of the player and enemy's Pokemon clones
        addObject(pcMon, 160, 510);
        addObject(enemyMon, 640, 272);
        
        // If the current state is choosing between "Fight" and "Bag"
        if (curAction.equals("choosing"))
        {
            // Show player's current health
            addObject(playerHealthBar, 800, 330);
            addObject(playerHPCount, 863, 334);
            if (Player.HP < 100)
            {
                // Adjust image
                if (Player.HP <= 0)
                {
                    removeObject(playerHPCount);
                }
                else
                {
                    playerHPCount.resizePercent(Player.HP, 100);
                    int sizeChange = playerHPCount.getCurWidth()-playerHPCount.getOrgWidth();
                    int newXPos = 863 + sizeChange/2;
                    playerHPCount.setLocation(newXPos,334);
                }
            }
            
            // Show enemy's current health
            addObject(enemyHealthBar, 360, 160);
            addObject(enemyHPCount, 390, 164);
            
            // If the enemy took any damage, this should run
            if (enemyHP < 100)
            {
                // Adjust image
                if (enemyHP <= 0)
                {
                    // Get rid of the green health if the enemy is defeated
                    removeObject(enemyHPCount);
                }
                else
                {
                    // Resize the green health block
                    enemyHPCount.resizePercent(enemyHP, 100);
                    
                    int sizeChange = enemyHPCount.getCurWidth()-enemyHPCount.getOrgWidth();
                    int newXPos = 390 + sizeChange/2; // The x-position shift
                    
                    // Since Greenfoot center-aligns objects, we must shift it
                    // left when the size changes
                    enemyHPCount.setLocation(newXPos,164);
                }
            }
            
            // Draw UI elements onto the screen
            addObject(ui, 724, 482);
            addObject(select, 532, 482);
            addObject(fight, 614, 482);
            addObject(bag, 781, 482);
            
            // Check if the player won or lost
            if (enemyHP == 0) // The enemy is defeated
            {
                curAction = "win";
            }
            else if (Player.HP == 0) // The player was defeated
            {
                curAction = "lose";
            }
            
            // Check for moving the selector cursor
            if("right".equals(key) || "left".equals(key))
            {
                // If the cursor was on "Fight"
                if(curUIAction.equals("fight"))
                {
                    // Moves selector cursor for UI
                    select.setLocation(724, 482);
                    curUIAction = "bag";
                }
                else
                {
                    // Moves selector cursor for UI
                    select.setLocation(532, 482);
                    curUIAction = "fight";
                }
            }
            // If the user chose an option
            if("enter".equals(key))
            {
                // Remove UI elements that are not relevant
                removeObject(select);
                removeObject(fight);
                removeObject(bag);
                
                // If the cursor was on "Fight"
                if(curUIAction.equals("fight"))
                {
                    key = null;
                    curAction = "fighting"; // They are now choosing a move
                }
                else
                {
                    key = null;
                    curUIAction = "fight"; // Reset cursor in "choosing"
                    curAction = "inventory"; // They are in their "Bag"
                }
            }
        }
        // If the user is now choosing a move
        if (curAction.equals("fighting"))
        {
            addObject(moveOne, 602, 482); // Image for move text
            addObject(moveTwo, 815, 482); // Image for move text
            addObject(select, 532, 482); // Image for the select cursor
            if("right".equals(key) || "left".equals(key))
            {
                // If cursor is currently on move "one"
                if(moveSelection.equals("one"))
                {
                    select.setLocation(707, 482); // Moves cursor to move two
                    moveSelection = "two";
                }
                else
                {
                    select.setLocation(532, 482); // Moves cursor to move one
                    moveSelection = "one";
                }
            }
            // If the user wants to choose a move or back out to choosing
            else if("enter".equals(key) || "backspace".equals(key))
            {
                // Remove the images for the moves and cursor
                removeObject(moveOne);
                removeObject(moveTwo);
                removeObject(select);
                
                query.clear(); // Clear the query ArrayList
                
                // If the user wanted specifically to choose a move
                if("enter".equals(key))
                {
                    removeObject(ui); // Clear the ui box
                    key = null; // set key to valueless
                    
                    // Clear out the old userInput (if applicable)
                    while (!userInput.isEmpty())
                    {
                        userInput.pop();
                    }
                    
                    // If the user chose the first (easier) move
                    if(moveSelection.equals("one"))
                    {
                        // Set up the Labels (text)
                        typedText = new Label ("",40);
                        typedText.setFillColor(Color.GREEN);
                        generatedWords = new Label ("",40);
                        generatedWords.setFillColor(Color.BLACK);
                        generatedWords.setLineColor(Color.WHITE);
                        
                        // Generate words from the easy array
                        // 6 words
                        for (int i = 0; i < 6; i++)
                        {
                            String wordToAdd = easy.get(rand.nextInt(6492));
                            query.add(wordToAdd);
                            if (i != 5)
                            {
                                // Add a space after each word, excl. last
                                query.add(" ");
                            }
                        }
                    }
                    // The user chose the second (harder) move
                    else
                    {
                        // Set up the labels
                        typedText = new Label ("",30);
                        typedText.setFillColor(Color.GREEN);
                        generatedWords = new Label ("",30);
                        generatedWords.setFillColor(Color.BLACK);
                        generatedWords.setLineColor(Color.WHITE);
                        //Generate words from the hard array
                        // 6 words
                        for (int i = 0; i < 6; i++)
                        {
                            String wordToAdd = hard.get(rand.nextInt(3404));
                            query.add(wordToAdd);
                            if (i != 5)
                            {
                                query.add(" ");
                            }
                        }
                    }
                    curAction = "typing"; // User is now typing
                }
                
                // Backspace was pressed
                else
                {
                    curAction = "choosing"; // Return to choosing
                }
            }
        }
        
        // User chose "Bag"
        if (curAction.equals("inventory"))
        {
            // Draw the potion Images (quantity and "potion")
            potionQuantity.setFillColor(Color.GRAY);
            potionQuantity.setValue(Player.potions + " x ");
            addObject(potionQuantity, 660, 480);
            addObject(potion, 783, 480);
            addObject(select, 600, 480);
            
            // Use a potion or back out to "choosing"
            if("enter".equals(key) || "backspace".equals(key))
            {
                // Get rid of potion text
                removeObject(potionQuantity);
                removeObject(potion);
                removeObject(select);
                
                // Player wants to use a potion
                if("enter".equals(key))
                {
                    // Use potion
                    key = null;
                    
                    // Are there any potions left to use?
                    if (Player.potions > 0)
                    {
                        // Consumes a turn, so the enemy attacks after usage
                        Player.usePotion();
                        curAction = "enemy";
                    }
                    // There are no potions left
                    else
                    {
                        curAction = "choosing"; // Let the user choose again
                    }
                }
                // Player wanted to back out to "choosing"
                else 
                {
                    curAction = "choosing";
                }
            }
        }
        // The player is now typing
        if (curAction.equals("typing"))
        {
            // Draw the user's typed text and the prompt
            addObject(box, 480, 465); // Textbox
            addObject (generatedWords, getWidth()/2, getHeight()/4*3);
            
            // The generated text as one string for comparison
            generated = String.join("", query);
            
            // The label to show the query and the user's input
            generatedWords.setValue(generated);
            addObject (typedText, getWidth()/2, getHeight()/6*5);
            
            timer += 1; // For the timer that checks how long the user took
            
            // If the user pressed a key
            if (key != null){
                // User wants to delete a character
                if ("backspace".equals(key)){
                    if (!userInput.isEmpty()){
                        userInput.pop();
                    }
                // User entered a non-space character
                } else if(key != "space"){
                    userInput.push(Character.toString(key.charAt(0)));
                // User wants to enter a space
                } else {
                    userInput.push(Character.toString(' '));
                }
                // Turn the userInput stack into a String for comparison
                typed = String.join("", userInput);
                // Set the value of the typedText label to the typed String
                typedText.setValue(typed);
                
                // If the user's input was correct and lines up with the query
                if(generated.equals(typed))
                {
                    removeObject(box); // Get rid of the text box
                    curAction = "done"; // User is now done typing
                }
            }
        }
        // If the user has finished typing
        if (curAction.equals("done")){
                typedText.setValue(""); // Makes Labels effectively invisible
                generatedWords.setValue("");
                movesTaken += 1; // The user has taken a move
                
                // Calculate damage based on move used
                if (moveSelection.equals("one"))
                {
                    damageDealt = 10;
                }
                else
                {
                    damageDealt = 40;
                }
                // Reward player for typing quickly
                if (timer/50 < 15)
                {
                    damageDealt += 10;
                }
                // Penalize player for typing slowly
                else if (timer/50 < 25)
                {
                    damageDealt -= 10;
                }
                // Penalize player for typing very slowly
                else if (timer/50 > 25)
                {
                    damageDealt = 0;
                }
                
                enemyHP -= damageDealt; // Damage the enemy
                
                // Reset timer
                timer = 0;
                
                // Play a sound based on the attack the user used
                if (moveSelection.equals("one"))
                {
                    bAttack.play();
                }else
                {
                    razorLeafAttack.play();
                }
                // Reset move selection
                moveSelection = "one";
                
                // Now showing the player how much damage they did
                curAction = "damageOfPlayer";
        }
        if(curAction.equals("damageOfPlayer"))
        {
            addObject(box, 480, 465); // Add textbox
            // Setup the Label
            Notification.setValue("You dealt " + damageDealt + " damage!");
            Notification.setFillColor(Color.BLACK);
            Notification.setLineColor(Color.WHITE);
            
            // Draw Label
            addObject(Notification, 480, 465);
            addObject(select, 890, 500); // Cursor prompt
            
            // Continue if enter is pressed
            if ("enter".equals(key))
            {
                // Clear the notifications
                removeObject(select);
                removeObject(box);
                removeObject(Notification);
                key = null;
                // Is the enemy dead after the player's attack?
                if (enemyHP <= 0)
                {
                    enemyHP = 0;
                    // Player attacks first, if enemy dies, they should not attack
                    curAction = "choosing";
                }
                else // Enemy's turn will start
                {
                    curAction = "enemy";
                }
            }
        }
        // If the enemy is now attacking
        if (curAction.equals("enemy"))
        {
            Player.takeDamage(); // Player takes 10 damage
            // Play sound
            bAttack.play();
            // Now showing how much damage the player took
            curAction = "damageOfEnemy"; 
        }
        // If the player is now being notified of the damge they took
        if (curAction.equals("damageOfEnemy"))
        {
            // Setup Label and textbox
            addObject(box, 480, 465);
            Notification.setValue("You took 10 damage!");
            Notification.setFillColor(Color.BLACK);
            Notification.setLineColor(Color.WHITE);
            addObject(Notification, 480, 465);
            addObject(select, 890, 500);
            // Does the player want to continue via pressing "enter"
            if ("enter".equals(key))
            {
                key = null;
                // Clear notifications
                removeObject(select);
                removeObject(Notification);
                removeObject(box);
                curAction = "choosing"; // User is choosing b/w "Fight"/"Bag"
            }
        }
        // If the user has won the battle
        if (curAction.equals("win"))
        {
            int ScoreIncrease = 0;
            if (movesTaken <= 5)
            {
                ScoreIncrease = 100 + (500/movesTaken);
            }
            else
            {
                ScoreIncrease = 100;
            }
            
            Player.adjustScore(ScoreIncrease);
            curAction = "scoreNotification"; // Showing user new score
        }
        // If the player is being shown their new score
        if (curAction.equals("scoreNotification"))
        {
            // Setup the notification for the player's new score
            addObject(box, 480, 465);
            removeObject(ui);
            Notification.setValue("Your score is now " + Player.score);
            Notification.setFillColor(Color.BLACK);
            Notification.setLineColor(Color.WHITE);
            addObject(Notification, 480, 465);
            
            // Stop the battleMusic and loop the win music
            battleMusic.stop();
            winMusic.playLoop();
            
            // If the player wants to continue via pressing "enter"
            if("enter".equals(key))
            {
                // Clear the notification images
                removeObject(select);
                removeObject(Notification);
                removeObject(box);
                winMusic.stop(); // Stop playing the winMusic
                
                // Checks to determine which level the user returns to
                if(Player.curWorld.equals("Level One"))
                {
                    mapOne retWorld = new mapOne();
                    Greenfoot.setWorld(retWorld);
                }
                else if(Player.curWorld.equals("Level Two"))
                {
                    mapTwo retWorld = new mapTwo();
                    Greenfoot.setWorld(retWorld);
                }
                else
                {
                    mapThree retWorld = new mapThree();
                    Greenfoot.setWorld(retWorld);
                }
            }
        }
        // If the user has lost
        if (curAction.equals("lose"))
        {
            // Show user the game over screen
            gameOver screen = new gameOver();
            Greenfoot.setWorld(screen);
        }
    }
}
