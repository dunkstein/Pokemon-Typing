import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * The first playable map the player will reach
 * Starts immediately after the instructions world
 * 
 * @author Kenneth Li, Isaac Chan
 * @version 1.0
 */
public class mapOne extends World
{
    // Music
    public static GreenfootSound music = new GreenfootSound("routeMusic.mp3");
    // the enemies
    private badGuy evilOne = new badGuy("topRight1", "enemyOne.png");
    private badGuy evilTwo = new badGuy("midLeft1", "enemyFour.png");
    private badGuy evilThree = new badGuy("bottom1", "enemyFive.png");
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public mapOne()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(960, 589, 1);
        setBackground("map1.png");
        
        // Box to go to next level (bottom right of screen)
        NextLevelBox nextLevel = new NextLevelBox(120, 25);
        addObject(nextLevel, 740, 580);
        
        // Invisible bounding boxes
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
        
        // Make music quieter and play on loop
        music.setVolume(15);
        music.playLoop();
        Player pc = new Player("Level One"); // Player object at level one
        if (Player.returning) // If the player just fought
        {
            // Place at stored position
            addObject(pc, Player.curPos[0], Player.curPos[1]); 
            badGuy.defeat(Player.nameOfbadGuy); // remove badGuy
            Player.firstEntry = false; // Not their first time in the world
            Player.returning = false; // They are no longer returning from battle
        }
        else
        {
            addObject(pc, 430, 135); // default position
        }
        
        // Make all badGuy(s) valid upon first load of the map
        if(Player.firstEntry)
        {
            evilOne.makeValid();
            evilTwo.makeValid();
            evilThree.makeValid();
        }
        // If the badGuy is valid, add them to the world
        if(evilOne.validity())
        {
            addObject(evilOne, 810, 135);
        }
        if(evilTwo.validity())
        {
            addObject(evilTwo, 430, 400);
        }
        if(evilThree.validity())
        {
            addObject(evilThree, 600, 500);
        }
    }
    public void act()
    {
    }
}
