import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class badGuy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class badGuy extends Actor
{
    private static ArrayList<String> valid = new ArrayList<String>();
    private String name;
    private String image;
    /**
     * Act - do whatever the badGuy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public badGuy(String name, String image)
    {
        this.name = name;
        this.image = image;
    }
    public static void defeat(badGuy guy)
    {
        valid.remove(guy.getName());
    }
    public boolean validity()
    {
        return valid.contains(name);
    }
    public String getName()
    {
        return name;
    }
    public void makeValid()
    {
        valid.add(name);
    }
    public void act()
    {
        // Add your action code here.
    }
}
