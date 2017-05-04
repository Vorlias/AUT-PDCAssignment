/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import rpg.gui.RPGGame;

/**
 *
 * @author Jonathan
 */
public class MenuState extends BasicGameState
{

    @Override
    public int getID()
    {
	return RPGGame.STATE_MENU;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException
    {
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException
    {
    }

    
}
