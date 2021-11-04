import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class battleWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class battleWorld extends World
{
    private Images box = new Images("textBox.png", 868, 154);
    private Images ui = new Images("battleUI.png", 472, 188);
    private Images select = new Images("selector.png", 28, 44);
    private Images fight = new Images("fightLabel.png", 120, 40);
    private Images bag = new Images("bagLabel.png", 72, 40);
    private Images moveOne = new Images("qAttack.png", 158, 38);
    private Images moveTwo = new Images("qAttack.png", 158, 38);
    private Stack<Character> userInput = new Stack<Character>();
    private ArrayList<String> hard = new ArrayList<String>();
    private ArrayList<String> easy = new ArrayList<String>();
    
    private boolean typing = false;
    private String curAction = "choosing";
    private String curUIAction = "fight";
    private String moveSelection = "one";
    private String key = null;
    
    /**
     * Constructor for objects of class battleWorld.
     * 
     */
    public battleWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(960, 576, 1);
        setBackground("TestBackground.png");
        
        try
        {
            TextReader.readInto(hard, easy);
        } 
        catch(Exception e)
        {
            System.out.println("error");
        }
        
    }
    
    public void act()
    {
        // Testing addition + removal of text box
        key = Greenfoot.getKey();
        if (curAction.equals("choosing"))
        {
            addObject(ui, 724, 482);
            addObject(select, 532, 482);
            addObject(fight, 614, 482);
            addObject(bag, 781, 482);
            
            if("right".equals(key) || "left".equals(key))
            {
                if(curUIAction.equals("fight"))
                {
                    select.setLocation(724, 482);
                    curUIAction = "bag";
                }
                else
                {
                    select.setLocation(532, 482);
                    curUIAction = "fight";
                }
            }
            if("enter".equals(key))
            {
                removeObject(select);
                removeObject(fight);
                removeObject(bag);
                key = null;
                if(curUIAction.equals("fight"))
                {
                    curAction = "fighting";
                }
                else
                {
                    curUIAction = "fight";
                    curAction = "inventory";
                }
            }
        }
        
        if (curAction.equals("fighting"))
        {
            addObject(moveOne, 632, 482);
            addObject(moveTwo, 827, 482);
            addObject(select, 532, 482);
            if("right".equals(key) || "left".equals(key))
            {
                if(moveSelection.equals("one"))
                {
                    select.setLocation(727, 482);
                    moveSelection = "two";
                }
                else
                {
                    select.setLocation(532, 482);
                    moveSelection = "one";
                }
            }
            else if("enter".equals(key) || "backspace".equals(key))
            {
                removeObject(moveOne);
                removeObject(moveTwo);
                removeObject(select);
                if("enter".equals(key))
                {
                    removeObject(ui);
                    while (!userInput.isEmpty())
                    {
                        userInput.pop();
                    }
                    if(moveSelection.equals("one"))
                    {
                        //Generate words from the easy array
                    }
                    else
                    {
                        //Generate words from the hard array
                    }
                    curAction = "typing";
                }
                else
                {
                    curAction = "choosing";
                }
            }
        }
        
        /**
         * Does nothing right now
         */
        if (curAction.equals("typing"))
        {
            addObject(box, 480, 465);
            
            //Main typing part
        }
        
        key = null;
    }
}
