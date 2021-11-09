import greenfoot.*;
/**
 * Write a description of class ImpassableBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ImpassableBox extends Actor 
{
    /**
     * Constructor for objects of class ImpassableBox
     */
    public ImpassableBox(int width, int height)
    {
        GreenfootImage boundingBox = new GreenfootImage(width, height);
        setImage(boundingBox);
        boundingBox.setColor(new Color(0, 0, 255));
        
        //code to see the boundaries
        //boundingBox.fill();
    }
}
