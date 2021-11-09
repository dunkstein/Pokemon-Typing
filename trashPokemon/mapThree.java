import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class mapThree here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(960, 397, 1); 
        setBackground("map3.png");
        
        NextLevelBox nextLevel = new NextLevelBox(150, 50);
        addObject(nextLevel, 475, 586);
        
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
        
        mapOne.music.playLoop();
        Player pc = new Player("Level Three");
        
        if (Player.returning)
        {
            addObject(pc, Player.curPos[0], Player.curPos[1]);
            badGuy.defeat(Player.nameOfbadGuy);
            Player.firstEntry = false;
            Player.returning = false;
        }
        else
        {
            addObject(pc, 800, 30);
        }
        
        // Make all badGuy(s) valid upon first load of the map
        if(Player.firstEntry)
        {
            evilOne.makeValid();
            evilTwo.makeValid();
            evilThree.makeValid();
        }
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
