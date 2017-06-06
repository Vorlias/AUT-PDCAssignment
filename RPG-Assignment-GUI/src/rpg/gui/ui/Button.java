/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.ui;

import java.awt.FontFormatException;
import java.io.IOException;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.GUIContext;
import rpg.gui.misc.FontManager;
import rpg.gui.misc.Vector2;

/**
 *
 * @author Jonathan
 */
public class Button extends GUIObject
{
        /**
     * The scale of the buttons
     */
    public enum Size
    {
	Regular,
	Large,
    }
    
    private Size buttonSize;
    
    private String text;
    private Vector2 textPadding = Vector2.ZERO;
    private ButtonPressedListener pressListener;
    private Image buttonImage, buttonHoverImage;
    private int index = -1;
    
    private TrueTypeFont buttonFont;

    // Get text padding
    public Vector2 getTextPadding()
    {
	return textPadding;
    }
    
    public void setIndex(int index)
    {
	this.index = index;
    }
    
    public int getIndex()
    {
	return this.index;
    }

    // Set text padding
    public void setTextPadding(Vector2 textPadding)
    {
	this.textPadding = textPadding;
    }
    
    // On button press
    public void onButtonPressed(ButtonPressedListener listener)
    {
	this.pressListener = listener;
    }
    
    // Get text
    public String getText()
    {
	return text;
    }

    // Set text
    public void setText(String text)
    {
	this.text = text;
    }
    
    public Button(GUIContext context, String text, Vector2 position, Size buttonSize) throws FontFormatException, IOException, SlickException
    {
	super(context);
	this.text = text;
	this.setPosition(position);
	this.textPadding = new Vector2(10, 3);
	
	switch (buttonSize)
	{
	    case Regular:
		buttonImage = new Image("data/images/button.png");
		buttonHoverImage = new Image("data/images/button_hover.png");
		this.setSize(new Vector2(150, 25));
		
		buttonFont = FontManager.getFontManager().getTrueTypeFont("balthazar", 18.f);
		break;
	    case Large:
		buttonImage = new Image("data/images/button_large.png");
		buttonHoverImage = new Image("data/images/button_large_hover.png");
		this.setSize(new Vector2(200, 30));
			
		buttonFont = FontManager.getFontManager().getTrueTypeFont("balthazar", 22.f);
		break;
	}


    }
    
    public Button(GUIContext context, String text, Vector2 position) throws FontFormatException, IOException, SlickException
    {
	super(context);
	this.text = text;
	this.setSize(new Vector2(150, 25));
	this.setPosition(position);
	this.textPadding = new Vector2(10, 3);
	
	buttonImage = new Image("data/images/button.png");
	buttonHoverImage = new Image("data/images/button_hover.png");
	
	if (buttonFont == null)
	{
	    buttonFont = FontManager.getFontManager().getTrueTypeFont("balthazar", 18.f);
	}
	
    }
    
    public Button(GUIContext context, String text, Vector2 position, Vector2 size)
    {
	super(context);
	
	this.text = text;
	this.setSize(size);
	this.setPosition(position);
	this.textPadding = new Vector2(this.getSize().getX() / 10, this.getSize().getY() / 10);
    }

    // Render GUI
    @Override
    protected void renderGUI(GUIContext container, Graphics graphics)
    {
	Font oldFont = graphics.getFont();
	Font balthazar = buttonFont;
	graphics.setFont(balthazar);
	graphics.setColor(this.isMouseOver() ? new Color(100, 100, 100) : new Color(50, 50, 50));
	graphics.fillRect(this.getPosition().getX(), this.getPosition().getY(), this.getSize().getX(), this.getSize().getY());
	graphics.setColor(Color.white);
	
	if (this.isMouseOver())
	    graphics.drawImage(buttonHoverImage, this.getPosition().getX(), this.getPosition().getY());
	else
	    graphics.drawImage(buttonImage, this.getPosition().getX(), this.getPosition().getY());
	
	graphics.drawString(text, this.getPosition().getX() + textPadding.getX(), this.getPosition().getY() + textPadding.getY());
	
	graphics.setFont(oldFont);
    }

    // On GUI press
    @Override
    public void onGUIMousePressed()
    {
	if (this.pressListener != null)
	    this.pressListener.pressed();
    }
}
