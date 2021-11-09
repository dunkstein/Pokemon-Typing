import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The third and final world the player will play in
 * 
 * @author Isaac Chan, Kenneth Li
 * @version 1.0
 */
public class mapThree extends World
{
    // Create all the bad guys
    private badGuy evilOne = new badGuy("midRight3", "enemyFive.png");
    private badGuy evilTwo = new badGuy("midLeft3", "enemyTwo.png");
    private badGuy evilThree = new badGuy("bottom3", "enemyOne.png");
    /**
     * Constructor for objects of class mapThree.
     * 
     */
    public mapThree()
    {    
        // Create a new world with 960x397 cells with a cell size of 1x1 pixels.
        super(960, 397, 1); 
        setBackground("map3.png");
        
        // Box to go to next area (victory screen)
        NextLevelBox nextLevel = new NextLevelBox(150, 50);
        addObject(nextLevel, 475, 586);
        
        // Bounding boxes
        ImpassableBox leftTree = new ImpassableBox (200,397);
        addObject(leftTree, 0, getHeight()/2);
        
        ImpassableBox rightTree = new ImpassableBox (200,397);
        addObject(rightTree, getWidth(), getHeight()/2);
        
        ImpassableBox ledge = new ImpassableBox (670,1);
        addObject(ledge, getWidth()/2+25, getHeight()/5-10);
        
        ImpassableBox bottomLeft= new ImpassableBox (400,125);
        addObject(bottomLeft, getWidth()/5, getHeight()/5*4+25);
        
        ImpassableBox bottomRight = new ImpassableBox (450,125);
        addObject(bottomRight, getWidth()/5*4, getHeight()/5*4+25);
        
        mapOne.music.playLoop(); // loop music
        Player pc = new Player("Level Three"); // user on final level
        
        if (Player.returning) // If returning from battle
        {
            addObject(pc, Player.curPos[0], Player.curPos[1]); // Stored pos.
            badGuy.defeat(Player.nameOfbadGuy); // Remove defeated badGuy
            Player.firstEntry = false; // not first time in map
            Player.returning = false; // already returned from fighting
        }
        else
        {
            addObject(pc, 800, 30); // default position
        }
        
        // Make all badGuy(s) valid upon first load of the map
        if(Player.firstEntry)
        {
            evilOne.makeValid();
            evilTwo.makeValid();
            evilThree.makeValid();
        }
        // draw/add object if valid
        if(evilOne.validity())
        {
            addObject(evilOne, 565, 140);
        }
        if(evilTwo.validity())
        {
            addObject(evilTwo, 360, 140);
        }
        if(evilThree.validity())
        {
            addObject(evilThree, 477, 300);
        }
    }
}
