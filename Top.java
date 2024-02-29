import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Top here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Top extends Actor
{
    /**
     * Act - do whatever the Top wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    public Top(double scaleFactor) {
        getImage().scale((int)(263/scaleFactor),(int)(60/scaleFactor));
    }
}
