import java.util.*;
import java.net.*;
import java.io.*;

/**
 * Reads in words in a pastebin from a URL to be used in the battle world
 * 
 * @author Isaac Chan
 * @version 1.0
 */
public class TextReader  
{
    // url to read from
    static String url = "https://raw.githubusercontent.com/first20hours/google-10000-english/master/google-10000-english-no-swears.txt";
    
    public static void readInto(ArrayList<String> list1, ArrayList<String> list2) throws Exception
    {
        URL wordsURL = new URL(url);
        BufferedReader in = new BufferedReader(
        new InputStreamReader(wordsURL.openStream()));
        
        String word;
        
        while ((word = in.readLine()) != null)
        {
            // Words longer than 7 characters
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
