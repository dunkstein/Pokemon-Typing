import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class battleWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class battleWorld extends World
{
    private Random rand = new Random();
    
    // Generate a random monster for the enemy from a list of possible monsters
    private String[] possibleMons = {"mon1.png", "mon2.png", "mon3.png"};
    private String chosenMon = possibleMons[rand.nextInt(3)];
    
    private Images box = new Images("textBox.png", 868, 154);
    private Images ui = new Images("battleUI.png", 472, 188);
    private Images select = new Images("selector.png", 28, 44);
    private Images fight = new Images("fightLabel.png", 120, 40);
    private Images bag = new Images("bagLabel.png", 72, 40);
    private Label potionQuantity = new Label(Player.potions + " x ", 60);
    private Label Notification = new Label("", 60);
    private Images potion = new Images("potion.png", 153, 39);
    private Images moveOne = new Images("pound.png", 30*3, 10*3);
    private Images moveTwo = new Images("razorleaf.png", 57*3, 11*3);
    private Images playerHealthBar = new Images("playerHPBar.png", 108*3, 42*3);
    private Images enemyHealthBar = new Images("enemyHPBar.png", 110*3, 36*3);
    private Images playerHPCount = new Images("hp.png", 143, 3*3);
    private Images enemyHPCount = new Images("hp.png", 143, 3*3);
    private Images pcMon = new Images("playerMon.png", 96*6, 96*6);
    private Images enemyMon = new Images(chosenMon, 96*4, 96*4);
    
    private Stack<String> userInput = new Stack<String>();
    private ArrayList<String> hard = new ArrayList<String>();
    private ArrayList<String> easy = new ArrayList<String>();
    private ArrayList<String> query = new ArrayList<String>();
    private Label typedText;
    private Label generatedWords;
    private int movesTaken = 0;
    private int timer = 0; // Around 50-60 act() cycles per second
    private int damageDealt = 0;
    private int enemyHP = 100;
    private String typed;
    private String generated;
    
    private boolean drawHealth = true;
    private String curAction = "choosing";
    private String curUIAction = "fight";
    private String moveSelection = "one";
    private String key = null;
    
    private GreenfootSound battleMusic = new GreenfootSound("battleMusic.mp3");
    private GreenfootSound bAttack = new GreenfootSound("Quick Attack.mp3");
    private GreenfootSound razorLeafAttack = new GreenfootSound("Razor Leaf.mp3");
    private GreenfootSound winMusic = new GreenfootSound("victoryMusic.mp3");
    
    /**
     * Constructor for objects of class battleWorld.
     * 
     */
    public battleWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(960, 576, 1);
        mapOne.music.stop();
        setBackground("TestBackground.png");
        battleMusic.setVolume(15);
        winMusic.setVolume(15);
        bAttack.setVolume(20);
        razorLeafAttack.setVolume(20);
        battleMusic.playLoop();
        try
        {
            TextReader.readInto(hard, easy);
        } 
        catch(Exception e)
        {
            System.out.println("Probably not connected to the internet");
        }
        
    }
    
    public void act()
    {
        key = Greenfoot.getKey();
        addObject(pcMon, 160, 510);
        addObject(enemyMon, 640, 272);
        if (curAction.equals("choosing"))
        {
            // Show player's current health
            addObject(playerHealthBar, 800, 330);
            addObject(playerHPCount, 863, 334);
            if (Player.HP < 100)
            {
                // Adjust image
                if (Player.HP <= 0)
                {
                    removeObject(playerHPCount);
                }
                else
                {
                    playerHPCount.resizePercent(Player.HP, 100);
                    int sizeChange = playerHPCount.getCurWidth()-playerHPCount.getOrgWidth();
                    int newXPos = 863 + sizeChange/2;
                    playerHPCount.setLocation(newXPos,334);
                }
            }
            
            // Show enemy's current health
            addObject(enemyHealthBar, 360, 160);
            addObject(enemyHPCount, 390, 164);
            if (enemyHP < 100)
            {
                // Adjust image
                if (enemyHP <= 0)
                {
                    removeObject(enemyHPCount);
                }
                else
                {
                    enemyHPCount.resizePercent(enemyHP, 100);
                    int sizeChange = enemyHPCount.getCurWidth()-enemyHPCount.getOrgWidth();
                    int newXPos = 390 + sizeChange/2;
                    enemyHPCount.setLocation(newXPos,164);
                }
            }
            
            // Draw UI elements onto the screen
            addObject(ui, 724, 482);
            addObject(select, 532, 482);
            addObject(fight, 614, 482);
            addObject(bag, 781, 482);
            
            if (enemyHP == 0)
            {
                curAction = "win";
            }
            else if (Player.HP == 0)
            {
                curAction = "lose";
            }
            
            if("right".equals(key) || "left".equals(key))
            {
                if(curUIAction.equals("fight"))
                {
                    // Moves selector cursor for UI
                    select.setLocation(724, 482);
                    curUIAction = "bag";
                }
                else
                {
                    select.setLocation(532, 482);
                    curUIAction = "fight";
                }
            }
            if("enter".equals(key))
            {
                removeObject(select);
                removeObject(fight);
                removeObject(bag);
                if(curUIAction.equals("fight"))
                {
                    key = null;
                    curAction = "fighting";
                }
                else
                {
                    key = null;
                    curUIAction = "fight";
                    curAction = "inventory";
                }
            }
        }
        
        if (curAction.equals("fighting"))
        {
            addObject(moveOne, 602, 482);
            addObject(moveTwo, 815, 482);
            addObject(select, 532, 482);
            if("right".equals(key) || "left".equals(key))
            {
                if(moveSelection.equals("one"))
                {
                    select.setLocation(707, 482);
                    moveSelection = "two";
                }
                else
                {
                    select.setLocation(532, 482);
                    moveSelection = "one";
                }
            }
            else if("enter".equals(key) || "backspace".equals(key))
            {
                removeObject(moveOne);
                removeObject(moveTwo);
                removeObject(select);
                query.clear();
                
                if("enter".equals(key))
                {
                    removeObject(ui);
                    key = null;
                    while (!userInput.isEmpty())
                    {
                        userInput.pop();
                    }
                    if(moveSelection.equals("one"))
                    {
                        typedText = new Label ("",40);
                        typedText.setFillColor(Color.GREEN);
                        generatedWords = new Label ("",40);
                        generatedWords.setFillColor(Color.BLACK);
                        generatedWords.setLineColor(Color.WHITE);
                        // Generate words from the easy array
                        // 6 words
                        for (int i = 0; i < 6; i++)
                        {
                            String wordToAdd = easy.get(rand.nextInt(6492));
                            query.add(wordToAdd);
                            if (i != 5)
                            {
                                query.add(" ");
                            }
                        }
                    }
                    else
                    {
                        typedText = new Label ("",30);
                        typedText.setFillColor(Color.GREEN);
                        generatedWords = new Label ("",30);
                        generatedWords.setFillColor(Color.BLACK);
                        generatedWords.setLineColor(Color.WHITE);
                        //Generate words from the hard array
                        for (int i = 0; i < 6; i++)
                        {
                            String wordToAdd = hard.get(rand.nextInt(3404));
                            query.add(wordToAdd);
                            if (i != 5)
                            {
                                query.add(" ");
                            }
                        }
                    }
                    curAction = "typing";
                }
                else
                {
                    curAction = "choosing";
                }
            }
        }
        if (curAction.equals("inventory"))
        {
            potionQuantity.setFillColor(Color.GRAY);
            potionQuantity.setValue(Player.potions + " x ");
            addObject(potionQuantity, 660, 480);
            addObject(potion, 783, 480);
            addObject(select, 600, 480);
            if("enter".equals(key) || "backspace".equals(key))
            {
                // Get rid of potion text
                removeObject(potionQuantity);
                removeObject(potion);
                removeObject(select);
                if("enter".equals(key))
                {
                    // Use potion
                    key = null;
                    if (Player.potions > 0)
                    {
                        Player.usePotion();
                        curAction = "enemy";
                    }
                    else
                    {
                        curAction = "choosing";
                    }
                }
                else
                {
                    curAction = "choosing";
                }
            }
        }
        if (curAction.equals("typing"))
        {
            
            addObject(box, 480, 465);
            addObject (generatedWords, getWidth()/2, getHeight()/4*3);
            generated = String.join("", query);
            generatedWords.setValue(generated);
            addObject (typedText, getWidth()/2, getHeight()/6*5);
            timer += 1;
            
            if (key != null){
                if ("backspace".equals(key)){
                    if (!userInput.isEmpty()){
                        userInput.pop();
                    }
                } else if(key != "space"){
                    userInput.push(Character.toString(key.charAt(0)));
                } else {
                    userInput.push(Character.toString(' '));
                }
                typed = String.join("", userInput);
                typedText.setValue(typed);
                if(generated.equals(typed))
                {
                    removeObject(box);
                    curAction = "done";
                }
            }
        }
        if (curAction.equals("done")){
                typedText.setValue("");
                generatedWords.setValue("");
                movesTaken += 1;
                // Calculate damage
                if (moveSelection.equals("one"))
                {
                    damageDealt = 40;
                }
                else
                {
                    damageDealt = 90;
                }
                // Reward player for typing quickly
                if (timer/50 < 15)
                {
                    damageDealt += 10;
                }
                // Penalize player for typing slowly
                if (timer/50 > 25)
                {
                    damageDealt -= 10;
                }
                
                enemyHP -= damageDealt;
                
                // Reset timer
                timer = 0;
                
                if (moveSelection.equals("one"))
                {
                    bAttack.play();
                }else
                {
                    razorLeafAttack.play();
                }
                // Reset move selection
                moveSelection = "one";
                
                // Play sound
                curAction = "damageOfPlayer";
        }
        if(curAction.equals("damageOfPlayer"))
        {
            addObject(box, 480, 465);
            Notification.setValue("You dealt " + damageDealt + " damage!");
            Notification.setFillColor(Color.BLACK);
            Notification.setLineColor(Color.WHITE);
            addObject(Notification, 480, 465);
            addObject(select, 890, 500);
            if ("enter".equals(key))
            {
                removeObject(select);
                removeObject(box);
                removeObject(Notification);
                key = null;
                // Play sound
                if (enemyHP <= 0)
                {
                    enemyHP = 0;
                    // Player attacks first, if enemy dies, they should not attack
                    curAction = "choosing";
                }
                else
                {
                    curAction = "enemy";
                }
            }
        }
        if (curAction.equals("enemy"))
        {
            Player.takeDamage();
            // Play sound
            bAttack.play();
            curAction = "damageOfEnemy";
        }
        if (curAction.equals("damageOfEnemy"))
        {
            addObject(box, 480, 465);
            Notification.setValue("You took 10 damage!");
            Notification.setFillColor(Color.BLACK);
            Notification.setLineColor(Color.WHITE);
            addObject(Notification, 480, 465);
            addObject(select, 890, 500);
            if ("enter".equals(key))
            {
                key = null;
                removeObject(select);
                removeObject(Notification);
                removeObject(box);
                curAction = "choosing";
            }
        }
        if (curAction.equals("win"))
        {
            int ScoreIncrease = 0;
            if (movesTaken < 2)
            {
                ScoreIncrease = 300;
            }
            else
            {
                ScoreIncrease = 100;
            }
            
            Player.adjustScore(ScoreIncrease);
            curAction = "scoreNotification";
        }
        if (curAction.equals("scoreNotification"))
        {
            addObject(box, 480, 465);
            removeObject(ui);
            Notification.setValue("Your score is now " + Player.score);
            Notification.setFillColor(Color.BLACK);
            Notification.setLineColor(Color.WHITE);
            addObject(Notification, 480, 465);
            battleMusic.stop();
            winMusic.playLoop();
            if("enter".equals(key))
            {
                removeObject(select);
                removeObject(Notification);
                removeObject(box);
                winMusic.stop();
                if(Player.curWorld.equals("Level One"))
                {
                    mapOne retWorld = new mapOne();
                    Greenfoot.setWorld(retWorld);
                }
                else if(Player.curWorld.equals("Level Two"))
                {
                    mapTwo retWorld = new mapTwo();
                    Greenfoot.setWorld(retWorld);
                }
                else
                {
                    mapThree retWorld = new mapThree();
                    Greenfoot.setWorld(retWorld);
                }
            }
        }
        if (curAction.equals("lose"))
        {
            gameOver screen = new gameOver();
            Greenfoot.setWorld(screen);
        }
    }
}
