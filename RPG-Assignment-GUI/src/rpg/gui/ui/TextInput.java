/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.ui;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.GUIContext;
import rpg.gui.misc.FontManager;
import rpg.gui.misc.Vector2;

/**
 * Handle text input
 * @author Jonathan
 */
public class TextInput extends GUIObject
{

    String input;
    Image textInputBackground;
    private TrueTypeFont textInputFont;

    // Text input constructor
    public TextInput(GUIContext context) throws SlickException
    {
	super(context);
	this.input = "";
	textInputBackground = new Image("data/images/text_input.png");
	textInputFont = FontManager.getFontManager().getTrueTypeFont("balthazar", 22.f);
	this.setSize(new Vector2(200, 30));
    }

    // Gui focus lost
    @Override
    public void onGUIFocusLost()
    {
	if (focused)
	    focused = false;
    }

    // Render gui
    @Override
    protected void renderGUI(GUIContext container, Graphics graphics)
    {
	graphics.drawImage(textInputBackground, this.getPosition().getX(), this.getPosition().getY());
	textInputFont.drawString(this.getPosition().getX() + 5, this.getPosition().getY() + 5, input, Color.black);
	
	if (focused)
	{
	    int pos = textInputFont.getWidth(input);
	    graphics.setColor(Color.black);
	    graphics.fillRect(this.getPosition().getX() + 5 + pos, this.getPosition().getY() + 4, 2, 22.f);
	    
	}
    }

    // Set focused
    public void setFocused(boolean focused)
    {
	this.focused = focused;
    }

    // Is focused
    public boolean isFocused()
    {
	return focused;
    }

    // Key pressed
    @Override
    public void keyPressed(int key, char c)
    {
	//System.out.println(c);
	if (focused && isEnabled())
	{
	    
	    if (key == Keyboard.KEY_RETURN)
	    {
		focused = false;
	    }
	    else if (key == Keyboard.KEY_BACK || key == Keyboard.KEY_DELETE)
	    {
		if (input.length() > 0)
		{
		    input = input.substring(0, input.length() - 1);
		}
	    }
	    else if (c >= 32 && c <= 126)
	    {
		input += c;
		System.out.println(input);
	    }
	}
    }

    // Mouse press gui
    @Override
    public void onGUIMousePressed()
    {
	if (!focused)
	{
	    //this.setFocus(true);
	    focused = true;
	    System.out.println("pressed");
	}

    }

    // Get text
    public String getText()
    {
	return input;
    }

}
