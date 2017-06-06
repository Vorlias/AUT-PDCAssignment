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
import rpg.gui.characters.Monster;
import rpg.gui.characters.PlayerCharacter;
import rpg.gui.characters.PlayerLocation;
import rpg.gui.gameplay.PlayController;
import rpg.gui.gameplay.PlayModel;
import rpg.gui.ui.GameUI;

/**
 *
 * @author Jonathan
 */
public class PlayView extends BasicGameState
{

    private GameUI ui;
    private PlayModel model;
    private PlayController controller;
    
    public GameUI getUI()
    {
	return ui;
    }
    
    public PlayModel getModel()
    {
	return model;
    }

    public void setup(PlayerCharacter character)
    {
	model = new PlayModel(character);
	model.setView(this);
	controller.setModel(model);
    }
    
    @Override
    public int getID()
    {
	return RPGGame.STATE_GAME;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame sbg)
    {
	controller.startGame();
    }
    
    public PlayView(GameContainer gc)
    {
	ui = new GameUI(gc);
	controller = new PlayController();
	controller.setView(this);
	
	
	
	ui.getActionLayoutGroup().onButtonPress(controller::onActionButtonPressed);	
	ui.getMenuButtonGroup().onButtonPress(controller::onMenuButtonPressed);
	
	controller.menuInit();
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
	controller.setStateBasedGame(sbg);
	gc.setShowFPS(false);	
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
	ui.update();
	
	Monster enemy = model.getTargetMonster();

	if (enemy != null)
	{
	    ui.setEnemyHealthPercentage(enemy.getHealth() / enemy.getMaxHealth() * 100.f);
	    ui.setEnemyName(enemy.getName());
	}
	else
	{
	    ui.setEnemyHealthPercentage(0);
	    ui.setEnemyName("");	    
	}
    }
}
