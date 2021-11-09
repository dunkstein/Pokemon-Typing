import greenfoot.*;
/**
 * Similar to ImpassableBox, this class does nothing on its own
 * Only purpose is to do transport the Player on collision to another world 
 * (above is handled in the Player class)
 * 
 * @author Isaac Chan, Kenneth Li, Vincent Hsieh 
 * @version 1.0
 */
public class NextLevelBox extends Actor 
{   
    /**
     * Constructor for the NextLevelBox class
     * Take two int arguments
     * @param width The width of the box
     * @param height The height of the box
     */
    public NextLevelBox(int width, int height)
    {
        GreenfootImage boundingBox = new GreenfootImage(width, height);
        setImage(boundingBox);
        boundingBox.setColor(new Color(255,0,0));
        
        //code to see the NextLevelBox
        //boundingBox.fill();
    }
}
