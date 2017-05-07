/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.ui;

import org.newdawn.slick.Color;
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
public class Button extends AbstractComponent
{
    private String text;
    private Vector2 position;
    private Vector2 size;
    private Vector2 textPadding = Vector2.ZERO;
    private boolean clickState;

    public Vector2 getTextPadding()
    {
	return textPadding;
    }

    public void setTextPadding(Vector2 textPadding)
    {
	this.textPadding = textPadding;
    }
    private ButtonPressedListener pressListener;
    
    public void onButtonPressed(ButtonPressedListener listener)
    {
	this.pressListener = listener;
    }
    
    public String getText()
    {
	return text;
    }

    public void setText(String text)
    {
	this.text = text;
    }

    public Vector2 getPosition()
    {
	return position;
    }

    public void setPosition(Vector2 position)
    {
	this.position = position;
    }
    
    public Button(GUIContext context, String text, Vector2 position)
    {
	super(context);
	this.text = text;
	this.size = new Vector2(150, 25);
	this.position = position;
	this.textPadding = new Vector2(10, 3);
    }
    
    public Button(GUIContext context, String text, Vector2 position, Vector2 size)
    {
	super(context);
	
	this.text = text;
	this.size = size;
	this.position = position;
	
	this.textPadding = new Vector2(this.size.getX() / 10, this.size.getY() / 10);
    }
    
    public void setClickState(boolean value)
    {
	this.clickState = value;
    }
    
    public void draw(Graphics graphics)
    {
	
    }

    public Vector2 getSize()
    {
	return size;
    }

    public void setSize(Vector2 size)
    {
	this.size = size;
	
    }
    
    public boolean isPressed()
    {
	return this.clickState;
    }
    
    @Override 
    public void mousePressed(int button, int x, int y)
    {
	Rectangle boundsRectangle = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	Rectangle mouseRectangle = new Rectangle(x, y, 2, 2);
	if (boundsRectangle.intersects(mouseRectangle))
	{
	    setClickState(true);
	    if (this.pressListener != null)
		this.pressListener.pressed();
	}
    }
    
    @Override
    public void mouseReleased(int button, int x, int y)
    {
	setClickState(false);
    }

    @Override
    public void render(GUIContext container, Graphics graphics) throws SlickException
    {
	//TrueTypeFont ttf = new TrueTypeFont(new Font("Impact", 0, 15), true);
	//graphics.setFont(ttf);
	
	graphics.setColor(clickState ? new Color(100, 100, 100) : new Color(50, 50, 50));
	graphics.fillRect(position.getX(), position.getY(), size.getX(), size.getY());
	graphics.setColor(Color.white);
	graphics.drawString(text, position.getX() + textPadding.getX(), position.getY() + textPadding.getY());
    }

    @Override
    public void setLocation(int x, int y)
    {
	this.setPosition(new Vector2(x, y));
    }

    @Override
    public int getX()
    {
	return position.getX();
    }

    @Override
    public int getY()
    {
	return position.getY();
    }

    @Override
    public int getWidth()
    {
	return size.getX();
    }

    @Override
    public int getHeight()
    {
	return size.getY();
    }
}
