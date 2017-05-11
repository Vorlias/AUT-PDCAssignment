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
import rpg.gui.ui.ButtonLayoutGroup;
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
    ButtonLayoutGroup actionButtons;

    @Override
    public int getID()
    {
	return RPGGame.STATE_GAME;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
	gc.setShowFPS(false);

	actionButtons = new ButtonLayoutGroup(gc, ButtonLayoutGroup.LayoutType.Horizontal);
	actionButtons.setGridExtents(new Vector2(2, 2));
	actionButtons.setButtons("Ayy", "Lmao", "This", "Workededededededed");
	actionButtons.setItemPadding(new Vector2(5, 5));
	actionButtons.onItemPressed((Button button) ->
	{
	    System.out.println(button.getText());
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
	actionButtons.render(gc, renderer);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException
    {

    }
}
