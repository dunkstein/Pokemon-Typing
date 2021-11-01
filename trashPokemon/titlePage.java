import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class titlePage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class titlePage extends World
{
    Label titleLabel = new Label("Trash Pokemon", 100);
    Label startLabel = new Label("Press <space> to Start", 100);
    /**
     * Constructor for objects of class titlePage.
     * 
     */
    public titlePage()
    {    
        // Create a new world with 960x600 cells with a cell size of 1x1 pixels.
        super(960, 600, 1);
        Greenfoot.start();
<<<<<<< Updated upstream
        addObject(titleLabel, getWidth()/2, getHeight()/3);
=======
        addObject(titleLabel, getWidth()/2, getHeight()/4);
>>>>>>> Stashed changes
        addObject(startLabel, getWidth()/2, (getHeight()/3)*2);
    }
    public void act()
    {
        //Start game if space bar is pressed
        if (Greenfoot.isKeyDown("space")){
            MyWorld gameWorld = new MyWorld();
            Greenfoot.setWorld(gameWorld);
        }
    }
}
