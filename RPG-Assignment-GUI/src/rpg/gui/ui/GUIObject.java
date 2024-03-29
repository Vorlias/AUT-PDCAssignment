/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.ui;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;
import rpg.gui.misc.Vector2;

/**
 * Handle Game Object
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

    private static boolean globalClickState;

    public GUILayoutGroup getLayoutGroup()
    {
	return layoutGroup;
    }

    // Set group layout
    public void setLayoutGroup(GUILayoutGroup layoutGroup)
    {
	this.layoutGroup = layoutGroup;
    }

    // Check group enabled
    public boolean isEnabled()
    {
	return this.enabled;
    }

    // Set group enabled
    public void setEnabled(boolean enabled)
    {
	this.enabled = enabled;
    }

    // Get size
    public Vector2 getSize()
    {
	return size;
    }

    // Set size
    public void setSize(Vector2 size)
    {
	this.size = size;
    }

    // Get position
    public Vector2 getPosition()
    {
	return position;
    }

    // Set position
    public void setPosition(Vector2 position)
    {
	this.position = position;
    }

    protected abstract void renderGUI(GUIContext container, Graphics graphics);

    // GUI Object constructor
    public GUIObject(GUIContext context)
    {
	super(context);
    }

    // Render
    @Override
    public void render(GUIContext container, Graphics graphics) throws SlickException
    {
	renderGUI(container, graphics);
    }

    // Set location
    @Override
    public void setLocation(int x, int y)
    {
	this.position = new Vector2(x, y);
    }

    // Get x co-ordinate
    @Override
    public int getX()
    {
	return this.position.getX();
    }

    // Get y co-ordinate
    @Override
    public int getY()
    {
	return this.position.getY();
    }

    // Get width
    @Override
    public int getWidth()
    {
	return this.size.getX();
    }

    // Get height
    @Override
    public int getHeight()
    {
	return this.size.getY();
    }

    // Mouse moved
    @Override
    public void mouseMoved(int oldX, int oldY, int x, int y)
    {

    }

    // Click state
    public void setClickState(boolean value)
    {
	this.clickState = value;
    }

    // Check if mouse over
    public boolean isMouseOver()
    {
	return this.mouseOverState;
    }

    /**
     * Returns whether or not this GUI component is active Used for UI state
     * handling
     * @return True if the component is active
     */
    public boolean isActive()
    {
	return this.clickState;
    }

    // Is pressed
    public boolean isPressed()
    {
	return this.clickState;
    }

    // Gui focus lost
    public void onGUIFocusLost()
    {

    }

    public abstract void onGUIMousePressed();

    protected boolean focused;
    
    // Mouse clicked
    @Override
    public void mouseClicked(int button, int x, int y, int clickCount)
    {
	if (enabled)
	{
	    if (clickState)
	    {
		onGUIMousePressed();
	    }
	    else if (focused)
	    {
		onGUIFocusLost();
	    }
	}
    }
 
    // Update
    public void update()
    {
	int x = Mouse.getX();
	int y = Display.getHeight() - Mouse.getY();

	Rectangle boundsRectangle = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	Rectangle mouseRectangle = new Rectangle(x, y, 2, 2);

	mouseOverState = boundsRectangle.intersects(mouseRectangle) && enabled;

	boolean leftButtonDown = Mouse.getEventButton() == Input.MOUSE_LEFT_BUTTON && Mouse.getEventButtonState();

	if (mouseOverState && leftButtonDown)
	{
	    if (!clickState)
	    {
		setClickState(true);
	    }
	}
	else
	{
	    if (clickState)
	    {
		setClickState(false);
	    }

	    if (leftButtonDown && !clickState && this.hasFocus())

	    {
		
	    }
	}
    }
}
