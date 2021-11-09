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
        
        ImpassableBox leftWall = new ImpassableBox (70,586);
        addObject(leftWall, 35, getHeight()/2);
        
        ImpassableBox leftTrees = new ImpassableBox (25, 100);
        addObject(leftTrees, getWidth()/12, getHeight()/2);
        
        ImpassableBox topTrees = new ImpassableBox (325,100);
        addObject(topTrees, getWidth()/2,getHeight()/5-20);
        
        ImpassableBox leftLedge = new ImpassableBox (250,1);
        addObject(leftLedge, getWidth()/5,getHeight()/5+20);
        
        ImpassableBox rightTrees = new ImpassableBox (200,586);
        addObject(rightTrees, getWidth(), getHeight()/2);
        
        ImpassableBox topRightTrees = new ImpassableBox (50,350);
        addObject(topRightTrees, getWidth()/8*7, getHeight()/4);
        
        ImpassableBox middleLedge = new ImpassableBox (500,1);
        addObject(middleLedge, getWidth()/5*2+50, getHeight()/2-40);
        
        ImpassableBox middleTree = new ImpassableBox (150,100);
        addObject(middleTree, getWidth()/2-30, getHeight()/2-100);
        
        ImpassableBox rightLedge = new ImpassableBox (50,1);
        addObject(rightLedge, getWidth()/10*8+30, getHeight()/2-40);
        
        ImpassableBox bottomLeftTree = new ImpassableBox (225,250);
        addObject(bottomLeftTree, getWidth()/4-60, getHeight()/4*3+25);
        
        ImpassableBox bottomMiddleTree = new ImpassableBox (100,225);
        addObject(bottomMiddleTree, getWidth()/5*2-50, getHeight()/4*3+50);
        
        ImpassableBox bottomRightTree = new ImpassableBox (200,125);
        addObject(bottomRightTree, getWidth()/5*2+100, getHeight()/4*3);
        
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
}
