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
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.util.ResourceLoader;
import rpg.gui.misc.FontManager;
import rpg.gui.misc.Vector2;

/**
 *
 * @author Jonathan
 */
public class Button extends GUIObject
{
    private String text;
    private Vector2 textPadding = Vector2.ZERO;
    private ButtonPressedListener pressListener;
    private Image buttonImage, buttonHoverImage;

   

    public Vector2 getTextPadding()
    {
	return textPadding;
    }

    public void setTextPadding(Vector2 textPadding)
    {
	this.textPadding = textPadding;
    }
    
    
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
    
    public Button(GUIContext context, String text, Vector2 position) throws FontFormatException, IOException, SlickException
    {
	super(context);
	this.text = text;
	this.setSize(new Vector2(150, 25));
	this.setPosition(position);
	this.textPadding = new Vector2(10, 3);
	
	buttonImage = new Image("data/images/button.png");
	buttonHoverImage = new Image("data/images/button_hover.png");
	
    }
    
    public Button(GUIContext context, String text, Vector2 position, Vector2 size)
    {
	super(context);
	
	this.text = text;
	this.setSize(size);
	this.setPosition(position);
	this.textPadding = new Vector2(this.getSize().getX() / 10, this.getSize().getY() / 10);
    }



    @Override
    protected void renderGUI(GUIContext container, Graphics graphics)
    {
	Font oldFont = graphics.getFont();
	Font balthazar = FontManager.getFontManager().getBalthazar();
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

    @Override
    public void onGUIMousePressed()
    {
	if (this.pressListener != null)
	    this.pressListener.pressed();
    }
}
