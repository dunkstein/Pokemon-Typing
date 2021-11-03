import greenfoot.*;
/**
 * Write a description of class Images here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Images extends Actor
{
    /**
     * Constructor for objects of class Images
     */
    public Images(String name, int height, int width)
    {
        GreenfootImage img = new GreenfootImage(name);
        img.scale(height, width);
        setImage(img);
    }
}
