/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.ui;

import java.awt.FontFormatException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;
import rpg.gui.misc.Vector2;

/**
 * @deprecated Use GUILayoutGroup instead. :c
 * @author Jonathan
 */
@Deprecated
public class ButtonLayoutGroup extends GUILayoutGroup
{

    private final GUIContext context;
    private Button.Size buttonSize;

    
    /**
     * Changes the enabled states of the buttons
     * @param enabled Whether or not the buttons are enabled
     */
    public void setButtonsEnabled(boolean enabled)
    {
	for (GUIObject b : guiObjects)
	    b.setEnabled(enabled);
    }

    // Set buttons
    public void setButtons(String... buttons)
    {
	this.clear();
	this.addButtons(buttonSize, buttons);
    }

    // Button layout group
    public ButtonLayoutGroup(GUIContext context, LayoutType layoutType, Button.Size buttonSize)
    {
	super(context, layoutType);
	this.context = context;
	this.layoutType = layoutType;
	this.buttonSize = buttonSize;	
    }

    // Button layout group
    public ButtonLayoutGroup(GUIContext context, LayoutType layoutType)
    {
	super(context, layoutType);
	this.context = context;
	this.layoutType = layoutType;
	this.buttonSize = Button.Size.Regular;
    }
}
