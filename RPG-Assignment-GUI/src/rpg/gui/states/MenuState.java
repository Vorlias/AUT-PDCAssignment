/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import rpg.gui.RPGGame;
import rpg.gui.characters.PlayerCharacter;
import rpg.gui.misc.Vector2;
import rpg.gui.ui.Button;
import rpg.gui.ui.ButtonLayoutGroup;
import rpg.gui.ui.GUILayoutGroup;

/**
 *
 * @author Jonathan
 */
public class MenuState extends BasicGameState
{
    ButtonLayoutGroup menuButtons;
    
    Image menuBackground;
    Image rpgLogo;
    
    public static final String NEW_GAME_TEXT = "NEW GAME";
    public static final String LOAD_GAME_TEXT = "LOAD GAME";
    
    @Override
    public int getID()
    {
	return RPGGame.STATE_MENU;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
	menuBackground = new Image("data/images/background_menu.png");
	rpgLogo = new Image("data/images/title.png");
	
	if (menuButtons == null)
	{
	    menuButtons = new ButtonLayoutGroup(gc, GUILayoutGroup.LayoutType.Vertical, Button.Size.Large);
	    menuButtons.setItemPadding(new Vector2(5, 5));
	    menuButtons.setButtons(NEW_GAME_TEXT, LOAD_GAME_TEXT);
	    menuButtons.setLocation(300, 70);
	    menuButtons.onButtonPress((Button button) -> {
		if (NEW_GAME_TEXT.equals(button.getText()))
		{
		    sbg.enterState(RPGGame.STATE_CREATION);
		}
		else if (LOAD_GAME_TEXT.equals(button.getText()))
		{
		    RPGGame.playState.setup(new PlayerCharacter("Paul"));
		    sbg.enterState(RPGGame.STATE_GAME);
		}
	    });
	}
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException
    {
	menuButtons.setButtonsEnabled(false);
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException
    {
	menuButtons.setButtonsEnabled(true);
    }
    
    

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException
    {
	grphcs.clear();
	
	grphcs.drawImage(menuBackground, 0, 0);
	grphcs.drawImage(rpgLogo, 300 - 0, 20);
	
	menuButtons.render(gc, grphcs);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException
    {
	
    }

    
}
