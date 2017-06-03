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
import rpg.gui.characters.PlayerCharacter;
import rpg.gui.gameplay.PlayModel;
import rpg.gui.ui.GameUI;

/**
 *
 * @author Jonathan
 */
public class PlayState extends BasicGameState
{

    private GameUI ui;
    private PlayModel model;
    
    public PlayModel getModel()
    {
	return model;
    }

    public void setup(PlayerCharacter character)
    {
	model = new PlayModel(character);
	model.setView(this);
    }
    
    @Override
    public int getID()
    {
	return RPGGame.STATE_GAME;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame sbg)
    {
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
	gc.setShowFPS(false);
	ui = new GameUI(gc);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics renderer) throws SlickException
    {
	renderer.setColor(Color.white);

	ui.render(gc, game, renderer);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException
    {
	PlayerCharacter character = model.getPlayerCharacter();
	ui.setHealthPercentage(character.getHealth());
	ui.setPlayerName(character.getName());
    }
}
