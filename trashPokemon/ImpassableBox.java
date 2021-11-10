import greenfoot.*;
/**
 * A very primitive class that just stores an actor that the player can collide with
 * Upon collision, the player is prevented from moving, these bounding boxes 
 * act as impassable walls. This is handled in the Player class. 
 * 
 * @author Kenneth Li, Isaac Chan, Vincent Hsieh
 * @version 1.0
 */
public class ImpassableBox extends Actor 
{
    /**
     * Constructor for objects of class ImpassableBox
     * Takes two specific int arguments
     * @param width The width of the box
     * @param height The height of the box
     */
    public ImpassableBox(int width, int height)
    {
        // Creates an invisible box
        GreenfootImage boundingBox = new GreenfootImage(width, height);
        setImage(boundingBox);
        
        //code to see the bounding boxes
        //boundingBox.setColor(new Color(0, 0, 255)); 
        //boundingBox.fill();
    }
}
