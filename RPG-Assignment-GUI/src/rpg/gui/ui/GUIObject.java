/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.ui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;
import rpg.gui.misc.Vector2;

/**
 *
 * @author Jonathan
 */
public abstract class GUIObject extends AbstractComponent
{
    private Vector2 size = Vector2.ZERO;
    private Vector2 position = Vector2.ZERO;
    private boolean clickState;
    private boolean mouseOverState;
    private boolean enabled = true;
    private GUILayoutGroup layoutGroup;
    
    public GUILayoutGroup getLayoutGroup()
    {
	return layoutGroup;
    }
    
    public void setLayoutGroup(GUILayoutGroup layoutGroup)
    {
	this.layoutGroup = layoutGroup;
    }
    
    public boolean isEnabled()
    {
	return this.enabled;
    }
    
    public void setEnabled(boolean enabled)
    {
	this.enabled = enabled;
    }

    public Vector2 getSize()
    {
	return size;
    }

    public void setSize(Vector2 size)
    {
	this.size = size;
    }

    public Vector2 getPosition()
    {
	return position;
    }

    public void setPosition(Vector2 position)
    {
	this.position = position;
    }



    protected abstract void renderGUI(GUIContext container, Graphics graphics);

    public GUIObject(GUIContext context)
    {
	super(context);
    }

    @Override
    public void render(GUIContext container, Graphics graphics) throws SlickException
    {
	renderGUI(container, graphics);
    }

    @Override
    public void setLocation(int x, int y)
    {
	this.position = new Vector2(x, y);
    }

    @Override
    public int getX()
    {
	return this.position.getX();
    }

    @Override
    public int getY()
    {
	return this.position.getY();
    }

    @Override
    public int getWidth()
    {
	return this.size.getX();
    }

    @Override
    public int getHeight()
    {
	return this.size.getY();
    }

    @Override
    public void mouseMoved(int oldX, int oldY, int x, int y)
    {
	Rectangle boundsRectangle = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	Rectangle mouseRectangle = new Rectangle(x, y, 2, 2);

	mouseOverState = boundsRectangle.intersects(mouseRectangle) && enabled;
    }

    public void setClickState(boolean value)
    {
	this.clickState = value;
    }

    public boolean isMouseOver()
    {
	return this.mouseOverState;
    }

    /**
     * Returns whether or not this GUI component is active Used for UI state
     * handling
     *
     * @return True if the component is active
     */
    public boolean isActive()
    {
	return this.clickState;
    }

    public boolean isPressed()
    {
	return this.clickState;
    }
    
    public void onGUIMousePressedOutside()
    {
	
    }

    @Override
    public void mouseReleased(int button, int x, int y)
    {
	if (mouseOverState)
	{
	    setClickState(false);
	    onGUIMousePressed();
	    System.out.println("Press");
	}
	else
	{
	    onGUIMousePressedOutside();
	}
    }

    public abstract void onGUIMousePressed();

    @Override
    public void mousePressed(int button, int x, int y)
    {
	setClickState(true);
    }
}
