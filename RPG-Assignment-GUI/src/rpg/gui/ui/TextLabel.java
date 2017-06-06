/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.ui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.GUIContext;
import rpg.gui.misc.FontManager;
import rpg.gui.misc.Vector2;

/**
 *
 * @author Jonathan
 */
public class TextLabel extends GUIObject
{

    String text;
    private final TrueTypeFont textFont;

    public void setText(String text)
    {
	this.text = text;
    }

    public String getText()
    {
	return this.text;
    }

    public TextLabel(GUIContext context, String text, float size)
    {
	super(context);
	this.text = text;
	textFont = FontManager.getFontManager().getTrueTypeFont("balthazar", size);
	this.setSize(new Vector2(textFont.getWidth(text), textFont.getHeight(text)));
    }

    @Override
    protected void renderGUI(GUIContext container, Graphics graphics)
    {
	textFont.drawString(this.getPosition().getX(), this.getPosition().getY(), text);
    }

    @Override
    public void onGUIMousePressed()
    {

    }

}
