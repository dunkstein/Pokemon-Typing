import greenfoot.*;
/**
 * A class that extends the Actor class. Used to create images that will be
 * drawn onto the screen, but acts similar to an actor (can move)
 * @author Isaac Chan 
 * @version 1.0
 */
public class Images extends Actor
{
    // Some private variable to store the original (org) and current (cur) dimensions
    private GreenfootImage img; // variable to store the actual image
    private int orgHeight;
    private int orgWidth;
    private int curHeight;
    private int curWidth;
    /**
     * Constructor for objects of class Images
     * @param name The name of the file used
     * @param width The width of the image
     * @param height The height of the image
     * 
     */
    public Images(String name, int width, int height)
    {
        orgHeight = height;
        orgWidth = width;
        curHeight = orgHeight;
        curWidth = orgWidth;
        img = new GreenfootImage(name);
        img.scale(width, height); // scale the image to specified parameters
        setImage(img);
    }
    // Resize based on original dimensions; note percent is expressed as actual %
    public void resizePercent(int widthPercent, int heightPercent)
    {
        img.scale(orgWidth*widthPercent/100, orgHeight*heightPercent/100);
        curHeight = img.getHeight(); // Stores new height
        curWidth = img.getWidth(); // Stores new width
        setImage(img); // re-sets the image
    }
    // Get original width of image
    public int getOrgWidth()
    {
        return orgWidth;
    }
    // Get original height of image
    public int getOrgHeight()
    {
        return orgHeight;
    }
    // Get current width
    public int getCurWidth()
    {
        return curWidth;
    }
    // Get current height
    public int getCurHeight()
    {
        return curHeight;
    }
}
