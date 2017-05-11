/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.ui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;
import rpg.gui.misc.Vector2;

/**
 *
 * @author Jonathan
 */
public class ButtonLayoutGroup extends AbstractComponent
{

    private final GUIContext context;

    public enum LayoutType
    {
	Grid,
	Horizontal,
	Vertical
    }

    private LayoutType layoutType;

    private Button[] buttons;
    private Vector2 position = Vector2.ZERO;
    private Vector2 gridExtents = Vector2.ZERO;
    private Vector2 padding = Vector2.ZERO;
    private ButtonLayoutGroupItemPressed itemPressedListener;

    public void setItemPadding(Vector2 padding)
    {
	this.padding = padding;
    }

    public void setGridExtents(Vector2 extents)
    {
	this.gridExtents = extents;
    }

    public void onItemPressed(ButtonLayoutGroupItemPressed listener)
    {
	this.itemPressedListener = listener;
    }

    public ButtonLayoutGroup(GUIContext context, LayoutType layoutType)
    {
	super(context);
	this.context = context;
	this.layoutType = layoutType;
    }

    public void setButtons(String... buttonNames)
    {
	buttons = new Button[buttonNames.length];
	for (int i = 0; i < buttonNames.length; i++)
	{
	    String buttonName = buttonNames[i];
	    Button btn = new Button(this.context, buttonName, Vector2.ZERO);
	    btn.onButtonPressed(() ->
	    {
		if (itemPressedListener != null)
		{
		    itemPressedListener.onItemPressed(btn);
		}
	    });
	    buttons[i] = btn;
	}
    }

    @Override
    public void render(GUIContext container, Graphics g) throws SlickException
    {
	if (null != layoutType)
	{
	    switch (layoutType)
	    {
		case Grid:
		{
		    int pX = 0, pY = 0;
		    int columnCount = 1;
		    for (Button button : buttons)
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
			button.render(container, g);
		    }
		    break;
		}
		case Horizontal:
		{
		    int pX = 0, pY = 0;
		    for (Button button : buttons)
		    {
			button.setPosition(new Vector2(position.getX() + pX, position.getY() + pY));
			pX += button.getWidth() + padding.getX();
		    }
		    break;
		}
		case Vertical:
		{
		    int pX = 0, pY = 0;
		    for (Button button : buttons)
		    {
			button.setPosition(new Vector2(position.getX() + pX, position.getY() + pY));
			pY += button.getHeight() + padding.getY();
		    }
		    break;
		}
		default:
		    break;
	    }
	}
    }

    @Override
    public void setLocation(int x, int y)
    {
	this.position = new Vector2(x, y);
    }

    @Override
    public int getX()
    {
	return 0;
    }

    @Override
    public int getY()
    {
	return 0;
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
