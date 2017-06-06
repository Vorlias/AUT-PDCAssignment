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
 * Handle GameUI
 * @author Jonathan
 */
public class GameUI
{

    private final int LEFT_CONTAINER_WIDTH = 600;
    private final int LEFT_CONTAINER_HEIGHT = 580;

    private float healthPercentage;
    private float enemyHealthPercentage;
    private int enemyLevel;
    private int playerLevel;
    private final ButtonLayoutGroup actionLayoutGroup;
    private final GUILayoutGroup menuGroup;
    private final TextDisplay textDisplay;

    private Image backgroundImage, foregroundImage;
    private String playerName, enemyName = "";

    /**
     * Gets the menu button group
     *
     * @return The menu button group
     */
    public GUILayoutGroup getMenuButtonGroup()
    {
	return menuGroup;
    }

    // Set enemy level
    public void setEnemyLevel(int enemyLevel)
    {
	this.enemyLevel = enemyLevel;
    }

    // Set player level
    public void setPlayerLevel(int playerLevel)
    {
	this.playerLevel = playerLevel;
    }

    /**
     * Gets the button layout group
     *
     * @return The button layout group
     */
    public ButtonLayoutGroup getActionLayoutGroup()
    {
	return actionLayoutGroup;
    }

    /**
     * Gets the text display
     *
     * @return The text display
     */
    public TextDisplay getTextDisplay()
    {
	return textDisplay;
    }

    // Game UI Constructor
    public GameUI(GUIContext context)
    {
	try
	{
	    backgroundImage = new Image("data/images/background.png");
	    foregroundImage = new Image("data/images/foreground.png");

	}
	catch (SlickException e)
	{
	    System.err.println(e.getMessage());
	}

	actionLayoutGroup = new ButtonLayoutGroup(context, GUILayoutGroup.LayoutType.Grid);
	textDisplay = new TextDisplay(context);

	actionLayoutGroup.setItemPadding(new Vector2(5, 5));
	actionLayoutGroup.setGridExtents(new Vector2(2, 6));

	textDisplay.setSize(new Vector2(776 + 12, 407 + 33));
	textDisplay.setPosition(new Vector2(12, 33));

	menuGroup = new GUILayoutGroup(context, GUILayoutGroup.LayoutType.Horizontal);
    }

    // Render
    public void render(GameContainer container, StateBasedGame game, Graphics renderer) throws SlickException
    {
	this.renderBottomLayer(container, game, renderer);
	this.renderBackground(container, game, renderer);
	this.renderTopLayer(container, game, renderer);
    }

    // Update
    public void update()
    {
	actionLayoutGroup.update();
	menuGroup.update();
    }

    /**
     * Renders the background of the UI
     *
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

	actionLayoutGroup.setLocation(196, 444);
	actionLayoutGroup.render(container, renderer);

	textDisplay.render(container, renderer);

	menuGroup.render(container, renderer);
    }

    // Render top layer
    private void renderTopLayer(GameContainer container, StateBasedGame game, Graphics renderer)
    {
	renderer.setColor(Color.white);
	renderer.drawString(playerName, 16, 447);

	renderer.drawString("Lv. " + playerLevel, 136, 447);
	
	if (!"".equals(enemyName))
	    renderer.drawString("Lv. " + enemyLevel, 733, 447);

	renderer.setColor(Color.white);
	renderer.drawString(enemyName, 613, 447);

	renderer.setColor(new Color(50, 50, 50));
	renderer.fillRect(17, 468, 170, 20);

	renderer.setColor(new Color(50, 50, 50));
	renderer.fillRect(613, 468, 170, 20);

	renderer.setColor(Color.green);
	renderer.fillRect(17, 468, healthPercentage * 170, 20);

	renderer.setColor(Color.red);
	renderer.fillRect(613, 468, enemyHealthPercentage * 170, 20);
	
	

	renderer.drawImage(foregroundImage, 0, 0);
    }

    // Render bottom layer
    private void renderBottomLayer(GameContainer container, StateBasedGame game, Graphics renderer)
    {

    }

    /**
     * Sets the health bar percentage in the UI
     *
     * @param percentage The percentage
     */
    public void setHealthPercentage(float percentage)
    {
	this.healthPercentage = percentage / 100.0f;
    }

    // Set enemy health
    public void setEnemyHealthPercentage(float percentage)
    {
	this.enemyHealthPercentage = percentage / 100.0f;
    }

    // Set enemy name
    public void setEnemyName(String name)
    {
	this.enemyName = name;
    }

    // Set player name
    public void setPlayerName(String name)
    {
	this.playerName = name;
    }
}
