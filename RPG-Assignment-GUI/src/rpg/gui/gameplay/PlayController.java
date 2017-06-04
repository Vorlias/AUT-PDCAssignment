/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.gameplay;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import rpg.gui.states.PlayState;

/**
 *
 * @author Jonathan
 */
public class PlayController
{
    private PlayModel model;
    private PlayState view;

    public PlayModel getModel()
    {
	return model;
    }

    public void setModel(PlayModel model)
    {
	this.model = model;
    }

    public PlayState getView()
    {
	return view;
    }

    public void setView(PlayState view)
    {
	this.view = view;
    }

    /**
     * Called when the state is updated
     * @param container
     * @param stateGame
     * @param delta 
     */
    public void update(GameContainer container, StateBasedGame stateGame, int delta)
    {
	
    }
}
