import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Subclass of the World class that show the user a victory screen upon
 * reaching the end of mapThree
 * 
 * @author Isaac Chan
 * @version 1.0
 */
public class victory extends World
{
    // Variables to store various elements of the victory screen
    public static GreenfootSound win = new GreenfootSound("endMusic.mp3");
    private Label message = new Label("Congratulations, You Won!", 80);
    private Label score = new Label("Your score: " + Player.score, 70);
    private Label repeat = new Label("Beat opponents in fewer moves to improve your score!", 45);
    private Label restart = new Label("<space> to play again", 40);
    
    /**
     * Constructor for objects of class victory.
     * 
     */
    public victory()
    {    
        // Create a new world with 960x600 cells with a cell size of 1x1 pixels.
        super(960, 600, 1); 
        mapOne.music.stop(); // Stop overworld music
        win.setVolume(15); // Make the very loud music quieter
        addObject(message, 480, 100);
        addObject(score, 480, 180);
        addObject(repeat, 480, 240);
        addObject(restart, 480, 400);
        setBackground("background.png");
        win.playLoop();
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
