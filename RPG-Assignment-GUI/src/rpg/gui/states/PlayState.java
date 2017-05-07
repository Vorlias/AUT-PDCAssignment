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
import rpg.gui.ui.GameUI;

/**
 *
 * @author Jonathan
 */
public class PlayState extends BasicGameState
{
    private final GameUI ui = new GameUI();

    @Override
    public int getID()
    {
	return RPGGame.STATE_GAME;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics renderer) throws SlickException
    {
	renderer.setColor(Color.white);
	
	// Draw outlines
	ui.renderBackground(gc, game, renderer);
	ui.renderPlayerInfo(gc, game, renderer);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException
    {
	ui.setHealthPercentage(100);
    }
    
}
