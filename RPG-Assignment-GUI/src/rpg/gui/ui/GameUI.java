/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.state.StateBasedGame;
import rpg.gui.misc.Vector2;

/**
 *
 * @author Jonathan
 */
public class GameUI
{
    private final int LEFT_CONTAINER_WIDTH = 600;
    private final int LEFT_CONTAINER_HEIGHT = 580;
    
    private float healthPercentage = 0.2f;
    private final ButtonLayoutGroup actionLayoutGroup;
    private final TextDisplay textDisplay;
    
    private Image backgroundImage;
    private String playerName;
    
    /**
     * Gets the button layout group
     * @return The button layout group
     */
    public ButtonLayoutGroup getActionLayoutGroup()
    {
	return actionLayoutGroup;
    }
    
    /**
     * Gets the text display
     * @return The text display
     */
    public TextDisplay getTextDisplay()
    {
	return textDisplay;
    }
    
    public GameUI(GUIContext context)
    {
	try
	{
	   backgroundImage = new Image("data/images/background.png"); 
	}
	catch (SlickException e)
	{
	    System.err.println(e.getMessage());
	}
	
	
	actionLayoutGroup = new ButtonLayoutGroup(context, GUILayoutGroup.LayoutType.Grid);
	textDisplay = new TextDisplay(context);	
	
	actionLayoutGroup.setItemPadding(new Vector2(5, 5));
	actionLayoutGroup.setGridExtents(new Vector2(3, 4));
	
	textDisplay.setSize(new Vector2(LEFT_CONTAINER_WIDTH, LEFT_CONTAINER_HEIGHT - 80 - 20));
	textDisplay.setPosition(new Vector2(10, 10));
    }
    
    public void render(GameContainer container, StateBasedGame game, Graphics renderer) throws SlickException
    {
	this.renderBottomLayer(container, game, renderer);
	this.renderBackground(container, game, renderer);
	this.renderTopLayer(container, game, renderer);
    }
    
    /**
     * Renders the background of the UI
     * @param container The container
     * @param game The game
     * @param renderer The renderer
     * @throws org.newdawn.slick.SlickException
     */
    private void renderBackground(GameContainer container, StateBasedGame game, Graphics renderer) throws SlickException
    {
	renderer.setColor(Color.white);
	//renderer.drawRect(10, 10, LEFT_CONTAINER_WIDTH, LEFT_CONTAINER_HEIGHT);
	//renderer.fillRect(10, LEFT_CONTAINER_HEIGHT - 90, LEFT_CONTAINER_WIDTH, 200);
	backgroundImage.draw(0, 0);
	
	actionLayoutGroup.setLocation(20, LEFT_CONTAINER_HEIGHT - 80);
	actionLayoutGroup.render(container, renderer);
	
	textDisplay.render(container, renderer);
    }
    
    private void renderTopLayer(GameContainer container, StateBasedGame game, Graphics renderer)
    {
	renderer.setColor(Color.white);
	renderer.drawString(playerName, 20 + LEFT_CONTAINER_WIDTH, 10);
    }
    
    private void renderBottomLayer(GameContainer container, StateBasedGame game, Graphics renderer)
    {

	
	renderer.setColor(new Color(50, 50, 50));
	renderer.fillRect(20 + LEFT_CONTAINER_WIDTH, 30, 170, 20);
	
	renderer.setColor(Color.green);
	renderer.fillRect(20 + LEFT_CONTAINER_WIDTH, 30, healthPercentage * 170, 20);
    }
    
    /**
     * Sets the health bar percentage in the UI
     * @param percentage The percentage
     */
    public void setHealthPercentage(float percentage)
    {
	this.healthPercentage = percentage / 100.0f;
    }

    public void setPlayerName(String name)
    {
	this.playerName = name;
    }
}
