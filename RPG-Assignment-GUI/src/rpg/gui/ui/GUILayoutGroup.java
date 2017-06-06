/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.ui;

import java.awt.FontFormatException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;
import rpg.gui.misc.Vector2;

/**
 *
 * @author Jonathan
 */
public class GUILayoutGroup extends AbstractComponent
{

    public enum LayoutType
    {
	Grid,
	Horizontal,
	Vertical
    }
    
    private ButtonLayoutGroupItemPressed buttonPressedListener;

    public void onButtonPress(ButtonLayoutGroupItemPressed listener)
    {
	this.buttonPressedListener = listener;
    }
    
    public void setItemPadding(Vector2 padding)
    {
	this.padding = padding;
    }

    public void setGridExtents(Vector2 extents)
    {
	this.gridExtents = extents;
    }

    List<GUIObject> guiObjects = new ArrayList<>();
    LayoutType layoutType;
    protected Vector2 position = Vector2.ZERO;
    protected Vector2 gridExtents = Vector2.ZERO;
    protected Vector2 padding = Vector2.ZERO;
    protected GUIContext context;
    
    //public void addButton(String text, )
    
    public GUILayoutGroup(GUIContext container, LayoutType layoutType)
    {
	super(container);
	context = container;
	this.layoutType = layoutType;
    }
    
    public void addInputs(TextInput... inputs)
    {
	guiObjects.addAll(Arrays.asList(inputs));
    }
    
    public void addLabels(float size, String... labels)
    {
	for (String label : labels)
	    guiObjects.add(new TextLabel(this.context, label, size));
    }
    
    public void setEnabled(boolean value)
    {
	for (GUIObject object : guiObjects)
	{
	    object.setEnabled(value);
	}
    }
    
    public void addButtons(Button.Size size, String... buttonNames)
    {

	for (int i = 0; i < buttonNames.length; i++)
	{
	    try
	    {
		String buttonName = buttonNames[i];
		Button btn = new Button(this.context, buttonName, Vector2.ZERO, size);
		
		
		btn.onButtonPressed(() ->
		{
		    if (buttonPressedListener != null)
		    {
			buttonPressedListener.onItemPressed(btn);
		    }
		});

		btn.setLayoutGroup(this);
		guiObjects.add(btn);
	    }
	    catch (FontFormatException | IOException | SlickException ex)
	    {
		Logger.getLogger(GUILayoutGroup.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
    }
    
    public void update()
    {
	for (GUIObject object : guiObjects)
	{
	    object.update();
	}
    }
    
    public void clear()
    {
	for (GUIObject object : guiObjects)
	{
	    object.setEnabled(false);
	}
	guiObjects = new ArrayList<>();
    }
    
    public void add(GUIObject object)
    {
	guiObjects.add(object);
    }

    @Override
    public void render(GUIContext guic, Graphics graphics) throws SlickException
    {
	if (null != layoutType)
	{
	    switch (layoutType)
	    {
		case Grid:
		{
		    int pX = 0, pY = 0;
		    int columnCount = 1;
		    for (GUIObject button : guiObjects)
		    {
			button.setPosition(new Vector2(position.getX() + pX, position.getY() + pY));
			if (columnCount >= gridExtents.getX())
			{
			    pX = 0;
			    pY += button.getHeight() + padding.getY();
			    columnCount = 0;
			}
			else
			{
			    columnCount ++;
			    pX += button.getWidth() + padding.getX();
			}
			button.render(container, graphics);
		    }
		    break;
		}
		case Horizontal:
		{
		    int pX = 0, pY = 0;
		    for (GUIObject button : guiObjects)
		    {
			button.setPosition(new Vector2(position.getX() + pX, position.getY() + pY));
			pX += button.getWidth() + padding.getX();
			button.render(container, graphics);
		    }
		    break;
		}
		case Vertical:
		{
		    int pX = 0, pY = 0;
		    for (GUIObject button : guiObjects)
		    {
			button.setPosition(new Vector2(position.getX() + pX, position.getY() + pY));
			pY += button.getHeight() + padding.getY();
			button.render(container, graphics);
		    }
		    break;
		}
		default:
		    break;
	    }
	}
	else
	    System.err.println("fuck");
    }

    @Override
    public void setLocation(int x, int y)
    {
	position = new Vector2(x, y);
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
	return 0;
    }

    @Override
    public int getHeight()
    {
	return 0;
    }

}
