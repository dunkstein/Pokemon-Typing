import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A world that is shown to the user when they faint in battle
 * 
 * @author Kenneth Li 
 * @version 1.0
 */
public class gameOver extends World
{
    // Visuals
    Label gameOver = new Label("Game Over", 50);
    Label gameOverReason = new Label("Your pokemon fainted", 50);
    Label tip = new Label ("Try using a potion next time", 50);
    Label score = new Label("Final Score: " + Player.score,50);
    Label repeat = new Label("<space> to try again", 30);
    GreenfootSound lose = new GreenfootSound("loseMusic.mp3");
    /**
     * Constructor for objects of class gameOver.
     * 
     */
    public gameOver()
    {    
        // Create a new world with 531x448 cells with a cell size of 1x1 pixels.
        super(531, 448, 1); 
        setBackground("fainted.jpg");
        //adds the labels to the screen
        addObject(gameOver, getWidth()/2, getHeight()/6);
        addObject(gameOverReason, getWidth()/2, getHeight()/6*2);
        addObject(tip, getWidth()/2, getHeight()/6*3);
        addObject(score, getWidth()/2, getHeight()/6*4);
        addObject(repeat, getWidth()/2, getHeight()/6*5);
        
        battleWorld.battleMusic.stop(); // stop music from the battleWorld
        lose.setVolume(20); // make music quieter
        lose.play(); // play losing music
    }
    public void act()
    {
        //Restart game if space bar is pressed
        if ("space".equals(Greenfoot.getKey())){
            titlePage gameWorld = new titlePage();
            Greenfoot.setWorld(gameWorld);
        }
    }
}
