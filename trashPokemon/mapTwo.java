import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class mapTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class mapTwo extends World
{       
    // Create all the bad guys
    badGuy evilOne = new badGuy("topLeft2", "enemyFour.png");
    badGuy evilTwo = new badGuy("middle2", "enemyTwo.png");
    badGuy evilThree = new badGuy("bottom2", "enemyThree.png");
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
        if (Player.returning)
        {
            addObject(pc, Player.curPos[0], Player.curPos[1]);
            badGuy.defeat(Player.nameOfbadGuy);
            Player.firstEntry = false;
            Player.returning = false;
        }
        else
        {
            addObject(pc, 750, 40);
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
            addObject(evilOne, 650, 160);
        }
        if(evilTwo.validity())
        {
            addObject(evilTwo, 458, 366);
        }
        if(evilThree.validity())
        {
            addObject(evilThree, 610, 500);
        }
    }
    public void act()
    {
        //Start game if space bar is pressed
    }
}
