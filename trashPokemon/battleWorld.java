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
    private ArrayList<String> hard = new ArrayList<String>();
    private ArrayList<String> easy = new ArrayList<String>();
    
    private boolean typing = false;
    private String curAction = "choosing";
    private String curUIAction = "fight";
    
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
        if (curAction.equals("choosing"))
        {
            addObject(ui, 724, 482);
            addObject(select, 532, 482);
            addObject(fight, 614, 482);
            addObject(bag, 781, 482);
            String key = Greenfoot.getKey();
            
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
                if(curUIAction.equals("fight"))
                {
                    curAction = "fighting";
                }
                else
                {
                    curAction = "inventory";
                }
            }
        }
        if (curAction.equals("fighting"))
        {
            
        }
        if (curAction.equals("type"))
        {
            if(typing)
            {
                typing = false;
            }
            else
            {
                typing = true;
            }
        }
        if(typing)
        {
            addObject(box, 480, 465);
        }
        else if (!typing)
        {
            removeObject(box);
        }
    }
}
