import greenfoot.*;
/**
 * Write a description of class Images here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Images extends Actor
{
    private GreenfootImage img;
    private int orgHeight;
    private int orgWidth;
    private int curHeight;
    private int curWidth;
    /**
     * Constructor for objects of class Images
     */
    public Images(String name, int width, int height)
    {
        orgHeight = height;
        orgWidth = width;
        curHeight = orgHeight;
        curWidth = orgWidth;
        img = new GreenfootImage(name);
        img.scale(width, height);
        setImage(img);
    }
    // Resize based on original dimensions; note percent is expressed as actual %
    public void resizePercent(int widthPercent, int heightPercent)
    {
        img.scale(orgWidth*widthPercent/100, orgHeight*heightPercent/100);
        curHeight = img.getHeight();
        curWidth = img.getWidth();
        setImage(img);
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
