import greenfoot.*;
import java.util.List;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor  
{
    private String curWorld;
    
    int moveX;
    int moveY;
    //Might need a constructor later to store the trash Pokemon the player owns
    public Player(String curWorld){
        
        this.curWorld = curWorld;
        setImage("trainer(initial).png");
        
    }
    public void act(){

        MovementControl();
        nextWorld(curWorld);
        
    }
    
    public void Move(int x, int y){
        setLocation(getX()+x, getY()+y);
    }
    // handles the movements of the player object
    public void MovementControl(){
        // per tick
        // initialize player wanting movement
        boolean up=false,down=false,left=false,right=false;
        // initialize player real movement
        int x=0,y=0;
        // initialize player colide states
        boolean upper_hit=false,lower_hit=false,left_hit=false,right_hit=false;
        
        // define image used for "collision box"
        GreenfootImage img=new GreenfootImage("trainer(initial).png");
        // get image bounds
        int img_x=img.getWidth(),img_y=img.getHeight();
        
        // where does the player trying to move to?
        if(Greenfoot.isKeyDown("up")||Greenfoot.isKeyDown("w")){up=true;}
        if(Greenfoot.isKeyDown("down")||Greenfoot.isKeyDown("s")){down=true;}
        if(Greenfoot.isKeyDown("left")||Greenfoot.isKeyDown("a")){left=true;}
        if(Greenfoot.isKeyDown("right")||Greenfoot.isKeyDown("d")){right=true;}
        // move 3 steps, each step do a collision detection, hell
        for(int junk=0;junk<3;junk++){
            // try to move
            if(up){y-=1;}
            if(down){y+=1;}
            if(left){x-=1;}
            if(right){x+=1;}
            
            // initialize object list
            List<ImpassableBox>object;
            // pixel based collision detection, made from scratch
            // check upper/lower collide state first            
            for(int i=-img_x/2;i<img_x/2;i++){
                // start from the pixel half the width left relative to the center of image
                if(!upper_hit){
                    // try find any impassableboxes pixel at (i,top_of_image)
                    object=getObjectsAtOffset(x+i,y-img_y/2-1,ImpassableBox.class);
                    if(object.size()!=0){upper_hit=true;up=false;}
                }
                if(!lower_hit){
                    object=getObjectsAtOffset(x+i,y+img_y/2,ImpassableBox.class);
                    if(object.size()!=0){lower_hit=true;down=false;}
                }
            }
            for(int i=-img_y/2;i<img_y/2;i++){
                if(!left_hit){
                    object=getObjectsAtOffset(x-img_x/2-1,y+i,ImpassableBox.class);
                    if(object.size()!=0){left_hit=true;left=false;}
                }
                if(!right_hit){
                    object=getObjectsAtOffset(x+img_x/2,y+i,ImpassableBox.class);
                    if(object.size()!=0){right_hit=true;right=false;}
                }
            }
            
            if(upper_hit){y=Math.max(0,y);}
            if(lower_hit){y=Math.min(0,y);}
            if(left_hit){x=Math.max(0,x);}
            if(right_hit){x=Math.min(0,x);}
        }
        if(x==0&y==0){
            setImage("trainer(initial).png");
        }else{
            if(x>0){setImage("trainer(right).png");}
            else if(x<0){setImage("trainer(left).png");}
            if(y>0){SetAnimation("down");}
            else if(y<0){SetAnimation("up");}
        }
        Move(x,y);
    }
    
    //clamp value between min and max, useful for limiting thing inside a bound
    public int clamp(int val,int min,int max){return Math.max(min,Math.min(max,val));}
    private class Animation_Table{
        //         tot_frame  cur_frame
        int[][]table={{2,       0}, //up
                      {2,       0}, //down
                      {2,       0}, //left
                      {2,       0}};//right
        public int[]get(String dir){
            if(dir=="up"){return table[0];}
            else if(dir=="down"){return table[1];}
            else if(dir=="left"){return table[2];}
            else if(dir=="right"){return table[3];}
            return null;//STFU about the error
        }
        public void set(String dir,int[]tab){
            if(dir=="up"){table[0]=tab;}
            else if(dir=="down"){table[1]=tab;}
            else if(dir=="left"){table[2]=tab;}
            else if(dir=="right"){table[3]=tab;}
        }
    }
    private Animation_Table anim_tab=new Animation_Table();
    private SimpleTimer timer=new SimpleTimer();
    int dt;
    int time=0;
    public void SetAnimation(String direction){
        String cur_frame_name="trainer("+direction+")";
        dt=timer.millisElapsed();
        time+=dt;
        int[]temp=anim_tab.get(direction);
        if(time>500){
            time-=500;
            temp[1]+=1;
            if(temp[1]==temp[0]){
                temp[1]=0;
            }
        }
        cur_frame_name+=temp[1]+".png";
        setImage(cur_frame_name);
        timer.mark();
    }
    
    public void nextWorld(String curWorld){
        if(isTouching(NextLevelBox.class)){
            if(curWorld.equals("Level One")){
                mapTwo gameWorld = new mapTwo();
                Greenfoot.setWorld(gameWorld);
            }else if (curWorld.equals("Level Two")){
                mapThree gameWorld = new mapThree();
                Greenfoot.setWorld(gameWorld);
            }
        } 
        if(isTouching(badGuy.class)){
            battleWorld gameWorld = new battleWorld();
            Greenfoot.setWorld(gameWorld);
        }
    }
    
}
