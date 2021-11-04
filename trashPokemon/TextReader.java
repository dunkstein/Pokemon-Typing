import java.util.*;
import java.net.*;
import java.io.*;

/**
 * Write a description of class TextReader here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TextReader  
{
    static String url = "https://raw.githubusercontent.com/first20hours/google-10000-english/master/google-10000-english-no-swears.txt";
    
    public static void readInto(ArrayList<String> list1, ArrayList<String> list2) throws Exception
    {
        URL wordsURL = new URL(url);
        BufferedReader in = new BufferedReader(
        new InputStreamReader(wordsURL.openStream()));
        
        String word;
        
        while ((word = in.readLine()) != null)
        {
            if (word.length() > 7)
            {
                list1.add(word);
            }
            else
            {
                list2.add(word);
            }
        }
        in.close();
    }
}
