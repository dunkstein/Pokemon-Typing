import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class mapOne extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public mapOne()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(960, 589, 1);
        setBackground("map1.png");
        
        NextLevelBox nextLevel = new NextLevelBox(120, 25);
        addObject(nextLevel, 740, 580);
        
        ImpassableBox leftWall = new ImpassableBox (130,650);
        addObject(leftWall, 0,0);
        
        ImpassableBox firstLedge = new ImpassableBox (960,1);
        addObject(firstLedge, 232,325);
        
        ImpassableBox treeBetweenLedge = new ImpassableBox (1,100);
        addObject(treeBetweenLedge, getWidth()/3, getHeight()/2+100);
        
        ImpassableBox secondLedge = new ImpassableBox (410, 1);
        addObject(secondLedge, getWidth()/2+50,getHeight()/4*3);
        
        ImpassableBox treeUnder2Ledge = new ImpassableBox (1,100);
        addObject(treeUnder2Ledge,getWidth()/2+25 ,getHeight()/8*7);
        
        ImpassableBox top = new ImpassableBox (960,225);
        addObject(top, getWidth()/2,0);
        
        ImpassableBox aboveBigTree = new ImpassableBox (250,100);
        addObject(aboveBigTree, getWidth()/4-25,100);
        
        ImpassableBox bigTree = new ImpassableBox (150,100);
        addObject(bigTree, getWidth()/4-40,150);

        ImpassableBox nextToBad = new ImpassableBox (200,150);
        addObject(nextToBad, getWidth()/4*3-50,100);
        
        ImpassableBox rightWall = new ImpassableBox (120,589);
        addObject(rightWall, getWidth()-60,getHeight()/2);
        
        ImpassableBox bottomRightWall = new ImpassableBox (50,150);
        addObject(bottomRightWall, getWidth()/8*7,getHeight()/8*7);
        
        ImpassableBox bottom = new ImpassableBox (180,1);
        addObject(bottom, getWidth()/5*3, getHeight()-15);
        
        Player pc = new Player("Level One");
        addObject(pc, 430, 135);
        
        badGuy evilOne = new badGuy();
        addObject(evilOne, 810, 135);
    }
    public void act()
    {
    }
}
