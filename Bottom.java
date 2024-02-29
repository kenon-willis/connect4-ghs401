import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bottom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bottom extends Actor
{
    /**
     * Act - do whatever the Bottom wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public Bottom(double scaleFactor) {
        getImage().scale((int)(364/scaleFactor), (int)(24/scaleFactor));
    }
}
