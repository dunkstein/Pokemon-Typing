import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
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
        
        // Stop mapOne music in case of "reset" button usage
        mapOne.music.stop();
        
        // Stop battleWorld music in case of "reset" button usage
        battleWorld.battleMusic.stop();
        
        
        // In case of using Greenfoot's "reset" button, the list of valid bad guys
        // should be cleared in order to reset the enemy spawns
        badGuy.clearValid();
        
        // Add in the text containing 1. The title and 2. The next screen prompt
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
