import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class gameOver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class gameOver extends World
{
    Label gameOver = new Label("Game Over", 50);
    Label gameOverReason = new Label("Your pokemon fainted", 50);
    Label tip = new Label ("Try using a potion next time", 50);
    Label score = new Label("Final Score: " + Player.score,50);
    /**
     * Constructor for objects of class gameOver.
     * 
     */
    public gameOver()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(531, 448, 1); 
        setBackground("fainted.jpg");
        addObject(gameOver, getWidth()/2, getHeight()/6);
        addObject(gameOverReason, getWidth()/2, getHeight()/6*2);
        addObject(tip, getWidth()/2, getHeight()/6*3);
        addObject(score, getWidth()/2, getHeight()/6*4);
    }
}
