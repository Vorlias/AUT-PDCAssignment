/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import rpg.gui.RPGGame;
import rpg.gui.misc.Vector2;
import rpg.gui.ui.Button;
import rpg.gui.ui.ButtonPressedListener;
import rpg.gui.ui.GameUI;

/**
 *
 * @author Jonathan
 */
public class PlayState extends BasicGameState
{
    private final GameUI ui = new GameUI();
    
    int testAmount = 100;
    Button testButton;

    @Override
    public int getID()
    {
	return RPGGame.STATE_GAME;
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
	gc.setShowFPS(false);
	
	testButton = new Button(gc, "Attack", new Vector2(10, 10));
	//testButton.setTextPadding(new Vector2(5, 2));
	testButton.onButtonPressed(() -> {
	    System.out.println(testAmount);
	    testAmount -= 5;
	    ui.setHealthPercentage(testAmount);
	});
	
	ui.setHealthPercentage(100);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics renderer) throws SlickException
    {
	renderer.setColor(Color.white);
	
	// Draw outlines
	ui.renderBackground(gc, game, renderer);
	ui.renderPlayerInfo(gc, game, renderer);
	testButton.render(gc, renderer);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException
    {
	
    }
}
