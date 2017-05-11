/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
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
    private ButtonLayoutGroup actionLayoutGroup;
    
    public GameUI(GUIContext context)
    {
	actionLayoutGroup = new ButtonLayoutGroup(context, ButtonLayoutGroup.LayoutType.Grid);
	actionLayoutGroup.setButtons("North", "South", "East", "West");
	actionLayoutGroup.setItemPadding(new Vector2(10, 10));
	actionLayoutGroup.setGridExtents(new Vector2(4, 4));
    }
    
    public void setButtons(String... actions)
    {
	actionLayoutGroup.setButtons(actions);
    }
    
    /**
     * Renders the background of the UI
     * @param container The container
     * @param game The game
     * @param renderer The renderer
     * @throws org.newdawn.slick.SlickException
     */
    public void renderBackground(GameContainer container, StateBasedGame game, Graphics renderer) throws SlickException
    {
	renderer.setColor(Color.white);
	renderer.drawRect(10, 10, LEFT_CONTAINER_WIDTH, LEFT_CONTAINER_HEIGHT);
	renderer.fillRect(10, LEFT_CONTAINER_HEIGHT - 190, LEFT_CONTAINER_WIDTH, 200);
	
	actionLayoutGroup.setLocation(20, LEFT_CONTAINER_HEIGHT - 180);
	actionLayoutGroup.render(container, renderer);
    }
    
    public void renderPlayerInfo(GameContainer container, StateBasedGame game, Graphics renderer)
    {
	renderer.setColor(Color.white);
	renderer.drawString("PlayerName", 20 + LEFT_CONTAINER_WIDTH, 10);
	
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
}
