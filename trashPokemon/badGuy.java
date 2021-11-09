import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * The enemies the player will encounter in the overworld
 * 
 * @author Isaac Chan, Kenneth Li
 * @version 1.0
 */
public class badGuy extends Actor
{
    // ArrayList valid stores the valid badGuy spawns
    private static ArrayList<String> valid = new ArrayList<String>();
    // This is the value stored in valid to determine validity
    private String name; // the spawn location of the badGuy
    private String image; // The sprite
    /**
     * Constructor for the badGuy class. Takes two arguments
     * @param name The position/location of the badGuy, essentially an identifier
     * @param image The sprite of the badGuy
     */
    public badGuy(String name, String image)
    {
        this.name = name;
        this.image = image;
        setImage(image);
    }
    // Remove the badGuy from the valid spawns list
    public static void defeat(badGuy guy)
    {
        valid.remove(guy.getName());
    }
    // Check if the badGuy is valid (i.e. badGuy.name is in the valid ArrayList)
    public boolean validity()
    {
        return valid.contains(name);
    }
    // Return the badGuy's name/position
    public String getName()
    {
        return name;
    }
    // Make the badGuy of this specific name/position valid
    public void makeValid()
    {
        valid.add(name);
    }
    // Clear the entire valid ArrayList
    public static void clearValid()
    {
        valid.clear();
    }
    // Not necessary, but maybe if you want to make the badGuys able to walk
    // or something in the future, it would go here
    public void act()
    {
    }
}
