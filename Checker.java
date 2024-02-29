import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BlackChecker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Checker extends Actor
{
    /**
     * Act - do whatever the BlackChecker wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int col;
    private int numMoves;
    
    private double scaleFactor;
 
    
    public Checker(int col, GreenfootImage image, double scaleFactor){
        //scales image
        image.scale((int)(41/scaleFactor), (int)(41/scaleFactor));
        //image.scale((int)(40, (int)(image.getHeight()*0.07));
        setImage(image);
        
        //turns checker so it moves down
        turn(270);
        
        
        this.col = col;
        this.numMoves=-1;
        this.scaleFactor = scaleFactor;
        
    }
    
    public void act() 
    {
        // determines how far to drop
        if(numMoves==-1){
            this.numMoves+=2;
            for(int r=0; r < ((MyWorld)(getWorld())).getGrid().length; r++){
                
                if(((MyWorld)(getWorld())).getGrid()[r][col]==null)
                    this.numMoves++;
                
            }

        }
        
        
    
        
    if(numMoves > 0){
        //drops the checker the required amount
         move((int)(-38/scaleFactor));
         numMoves--;
    }
        
        
    }    
}
