import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class titlePage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class titlePage extends World
{

    /**
     * Constructor for objects of class titlePage.
     * 
     */
    public titlePage()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(16*60, 9*60, 1);
        Greenfoot.start();
    }
    public void act()
    {
        if (Greenfoot.isKeyDown("space")) Greenfoot.setWorld(new MyWorld());
    }
}
