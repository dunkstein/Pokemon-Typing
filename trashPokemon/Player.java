import greenfoot.*;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor  
{
    private String curWorld;
    
    //Might need a constructor later to store the trash Pokemon the player owns
    public Player(String curWorld)
    {
        this.curWorld = curWorld;
        setImage("trainer(initial).png");
    }
    public void act()
    {
        int x = 0;
        int y = 0;
        if (Greenfoot.isKeyDown("right")||Greenfoot.isKeyDown("d")){
            x+=3;
        }
        if(Greenfoot.isKeyDown("left")||Greenfoot.isKeyDown("a")){
            x+=-3;
        }
        if(Greenfoot.isKeyDown("up")||Greenfoot.isKeyDown("w")){
            y+=-3;
        }
        if(Greenfoot.isKeyDown("down")||Greenfoot.isKeyDown("s")){
            y+=3;
        }
        if(x > 0){
            setImage("trainer(right).png");
        }else if(x < 0){
            setImage("trainer(left).png");
        }
        if(y > 0){
            SetAnimation("down");
        }else if(y < 0){
            SetAnimation("up");
        }
        Move(x,y);
        if(isTouching(ImpassableBox.class)){
            setLocation(getX()-x,getY()-y);
        }
        nextWorld(curWorld);
    }
    public void Move(int x, int y){
        setLocation(getX() + x, getY() + y);
    }
    
    private class Animation_Table{
        //           tot frame  cur frame
        int[][] table = {{2,       0}, //up
                         {2,       0}, //down
                         {2,       0}, //left
                         {2,       0}};//right
        public int[] get(String dir){
            if(dir == "up"){return table[0];}
            else if(dir == "down"){return table[1];}
            else if(dir == "left"){return table[2];}
            else if(dir == "right"){return table[3];}

            int[] tmp = {-1,-1};return tmp;
        }
        public void set(String dir, int[] t){
            if(dir == "up"){table[0] = t;}
            else if(dir == "down"){table[1] = t;}
            else if(dir == "left"){table[2] = t;}
            else if(dir == "right"){table[3] = t;}
        }
    }
    Animation_Table anim_tab = new Animation_Table();
    SimpleTimer timer = new SimpleTimer();
    int dt;
    int time = 0;
    public void SetAnimation(String direction){
        String cur_frame_name = "trainer(" + direction + ")";
        dt = timer.millisElapsed();
        time+=dt;
        int[] temp = anim_tab.get(direction);
        if(time > 500){
            time-=500;
            temp[1]+=1;
            if(temp[1]==temp[0]){
                temp[1]=0;
            }
        }
        cur_frame_name += temp[1]+".png";
        setImage(cur_frame_name);
        timer.mark();
    }
    
    public void nextWorld(String curWorld)
    {
        if (isTouching(NextLevelBox.class)){

            if (curWorld.equals("Level One")){
                mapTwo gameWorld = new mapTwo();
                Greenfoot.setWorld(gameWorld);
            } else if (curWorld.equals("Level Two")){
                mapThree gameWorld = new mapThree();
                Greenfoot.setWorld(gameWorld);
            }
        } 
        if (isTouching(badGuy.class)){
                battleWorld gameWorld = new battleWorld();
                Greenfoot.setWorld(gameWorld);
        }
    }
}

