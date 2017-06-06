/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.ui;

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.GUIContext;
import rpg.gui.misc.FontManager;

/**
 *
 * @author Jonathan
 */
public class TextDisplay extends GUIObject
{

    public TextDisplay(GUIContext context)
    {
	super(context);
    }
    
    /**
     * A message
     */
    private class Message
    {
	String prefix;
	String message;
	Color color;
	
	public Message(String prefix, String message)
	{
	    this.prefix = prefix;
	    this.message = message;
	    this.color = Color.white;
	}
    }
    
    List<Message> messages = new ArrayList<>();
    
    /**
     * Adds a message to the text display
     * e.g. [Prefix] Message here
     * @param prefix The prefix
     * @param message The message
     */
    public void addMessage(String prefix, String message)
    {
	Message tp = new Message("[" + prefix + "]: ", message);
	messages.add(tp);
    }
    
    /**
     * Adds an empty message to the text display
     */
    public void addEmpty()
    {
	Message tp = new Message(" ", " ");
	messages.add(tp);
    }
    
    /**
     * Adds a system message to the display
     * @param message The message to display
     * @param color The color of the message
     */
    public void addSystemMessage(String message, Color color)
    {
	Message tp = new Message("", message);
	tp.color = color;
	messages.add(tp);	
    }
    
    /**
     * Adds a yellow system message to the text display
     * @param message The message
     */
    public void addSystemMessage(String message)
    {
	addSystemMessage(message, Color.yellow);
    }
    
    /**
     * Adds an error message to the text display
     * @param message The error message
     */
    public void addErrorMessage(String message)
    {
	Message tp = new Message("[ERROR] ", message.toUpperCase());
	tp.color = Color.red;
	messages.add(tp);	
    }
    
    @Override
    protected void renderGUI(GUIContext container, Graphics graphics)
    {
	Font oldFont = graphics.getFont();
	Font balthazar = FontManager.getFontManager().getTrueTypeFont("balthazar", 18.f);
	graphics.setFont(balthazar);
	
	graphics.setClip(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	
	int offsetY = this.getSize().getY();
	for (int i = messages.size() - 1; i >= 0; i--)
	{
	    Message m = messages.get(i);
	    
	    graphics.setColor(m.color);
	    
	    String prefix = m.prefix;
	    
	    int prefixWidth = balthazar.getWidth(prefix);
	    int messageHeight = balthazar.getLineHeight(); //font.getHeight(m.message);
	    // int messageWidth = font.getWidth(m.message);
	    
	    graphics.drawString(prefix, 5 + this.getPosition().getX(), offsetY - messageHeight);
	    graphics.drawString(m.message, this.getPosition().getX() + prefixWidth + 5, offsetY - messageHeight);
	    
	    offsetY -= messageHeight;
	}
	
	graphics.setColor(Color.white);
	graphics.clearClip();
	graphics.setFont(oldFont);
    }

    @Override
    public void onGUIMousePressed()
    {
    }
}
