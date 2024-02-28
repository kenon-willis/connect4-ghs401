import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RedSymbol here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RedSymbol extends Actor
{
    /**
     * Act - do whatever the RedSymbol wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    public RedSymbol(int scaleFactor) {
        getImage().scale(60/scaleFactor,60/scaleFactor);
    }
}
