import greenfoot.*;
/**
 * Write a description of class TextBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TextBox extends Actor 
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class TextBox
     */
    public TextBox()
    {
        GreenfootImage textBoxImage = new GreenfootImage("textBox.png");
        textBoxImage.scale(868, 154);
        setImage(textBoxImage);
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
}
