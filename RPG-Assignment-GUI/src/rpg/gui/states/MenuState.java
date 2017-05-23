/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import rpg.gui.RPGGame;
import rpg.gui.misc.Vector2;
import rpg.gui.ui.Button;
import rpg.gui.ui.ButtonLayoutGroup;

/**
 *
 * @author Jonathan
 */
public class MenuState extends BasicGameState
{
    ButtonLayoutGroup menuButtons;
    
    public static final String PLAY_BUTTON_TEXT = "PLAY";
    
    @Override
    public int getID()
    {
	return RPGGame.STATE_MENU;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
	if (menuButtons == null)
	{
	    menuButtons = new ButtonLayoutGroup(gc, ButtonLayoutGroup.LayoutType.Vertical);
	    menuButtons.setItemPadding(new Vector2(5, 5));
	    menuButtons.setButtons(PLAY_BUTTON_TEXT);
	    menuButtons.setLocation(10, 10);
	    menuButtons.onItemPressed((Button button) -> {
		if (PLAY_BUTTON_TEXT.equals(button.getText()))
		{
		    sbg.enterState(RPGGame.STATE_GAME);
		}
	    });
	}
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException
    {
	grphcs.clear();
	menuButtons.render(gc, grphcs);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException
    {
	
    }

    
}
