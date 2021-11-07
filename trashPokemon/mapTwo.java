import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class mapTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class mapTwo extends World
{
    private Random rand = new Random();
    /**
     * Constructor for objects of class mapTwo.
     * 
     */
    public mapTwo()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(960, 586, 1); 
        setBackground("map2.png");
        NextLevelBox nextLevel = new NextLevelBox(400, 50);
        addObject(nextLevel, 700, 586);
        
        mapOne.music.playLoop();
        Player pc = new Player("Level Two");
        addObject(pc, 750, 40);
    }
    public void act()
    {
        //Start game if space bar is pressed
    }
}
