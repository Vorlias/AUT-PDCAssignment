/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.misc;

/**
 * Vector2 class
 * @author Jonathan
 */
public class Vector2
{
    public static final Vector2 ZERO = new Vector2(0, 0);

    // Get x co-ordinate
    public int getX()
    {
	return x;
    }

    // Set x co-ordinate
    public void setX(int x)
    {
	this.x = x;
    }

    // Get y co-ordinate
    public int getY()
    {
	return y;
    }

    // Set y co-ordinate
    public void setY(int y)
    {
	this.y = y;
    }
    
    private int x;
    private int y;
    
    // Vector2 constructor
    public Vector2(int x, int y)
    {
	this.x = x;
	this.y = y;
    }
}
