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
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(960, 540, 1);
        setBackground("Title.jpg");
        Greenfoot.start();
        addObject(titleLabel, getWidth()/2, getHeight()/3);
        addObject(startLabel, getWidth()/2, (getHeight()/3)*2);
    }
    public void act()
    {
        //Start game if space bar is pressed
        if ("space".equals(Greenfoot.getKey())){
            instructions gameWorld = new instructions();
            Greenfoot.setWorld(gameWorld);
        }
    }
}
