import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class instructions here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class instructions extends World
{
    Label instructionLabel = new Label("Instructions", 100);
    Label instructionLabel2 = new Label("Walk into enemy traniners to fight them", 40);
    Label instructionLabel3 = new Label("Use \u2190 or \u2192 and enter in battle", 40);
    Label instructionLabel4 = new Label("Use \u2191 \u2193 \u2190 \u2192 OR  W A S D to move ", 40);
    Label instructionLabel5 = new Label("Press <space> to continue", 40);
    /**
     * Constructor for objects of class instructions.
     * 
     */
    public instructions()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(960, 600, 1);
        addObject(instructionLabel, getWidth()/2, getHeight()/6);
        addObject(instructionLabel2, getWidth()/2, getHeight()/6*2);
        addObject(instructionLabel3, getWidth()/2, getHeight()/6*3);
        addObject(instructionLabel4, getWidth()/2, getHeight()/6*4);
        addObject(instructionLabel5, getWidth()/2, getHeight()/6*5);
        setBackground("backround.png");
    }
    public void act() {
        if ("space".equals(Greenfoot.getKey())){
            mapOne gameWorld = new mapOne();
            Greenfoot.setWorld(gameWorld);
        }
    }
}

