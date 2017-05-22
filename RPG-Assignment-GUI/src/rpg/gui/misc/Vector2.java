/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.misc;

/**
 *
 * @author Jonathan
 */
public class Vector2
{
    public static final Vector2 ZERO = new Vector2(0, 0);

    public int getX()
    {
	return x;
    }

    public void setX(int x)
    {
	this.x = x;
    }

    public int getY()
    {
	return y;
    }

    public void setY(int y)
    {
	this.y = y;
    }
    private int x;
    private int y;
    
    public Vector2(int x, int y)
    {
	this.x = x;
	this.y = y;
    }
}
