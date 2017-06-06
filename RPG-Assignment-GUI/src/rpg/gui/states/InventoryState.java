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
import rpg.gui.characters.PlayerCharacter;
import rpg.gui.gameplay.PlayModel;
import rpg.gui.items.Item;
import rpg.gui.misc.Vector2;
import rpg.gui.ui.Button;
import rpg.gui.ui.GUILayoutGroup;

/**
 *
 * @author Jonathan
 */
public class InventoryState extends BasicGameState
{

    GUILayoutGroup layoutGroup;

    @Override
    public int getID()
    {
	return RPGGame.STATE_INVENTORY;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
	layoutGroup = new GUILayoutGroup(gc, GUILayoutGroup.LayoutType.Grid);
	layoutGroup.setGridExtents(new Vector2(2, 1000));
	layoutGroup.setItemPadding(new Vector2(5, 5));
	layoutGroup.onButtonPress((Button button) ->
	{
	    System.out.println(button);

	    PlayModel model = RPGGame.playView.getModel();
	    PlayerCharacter character = model.getPlayerCharacter();

	    if (button.getText().equals("Back"))
	    {
		RPGGame.game.enterState(RPGGame.STATE_MENU);
		System.out.println("Go back!");
	    }
	    else
	    {
		int index = button.getIndex();
		character.equipItem((character.getItems().get(index)));
		System.out.println("Use item " + index);
	    }

	});
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException
    {
	layoutGroup.setEnabled(true);

	layoutGroup.clear();

	PlayModel model = RPGGame.playView.getModel();
	PlayerCharacter character = model.getPlayerCharacter();
	for (Item i : character.getItems())
	{
	    layoutGroup.addLabels(22.f, i.getName());
	    layoutGroup.addButtons(Button.Size.Large, "Use");
	}

	layoutGroup.addLabels(22.f, " ");
	layoutGroup.addButtons(Button.Size.Regular, "Back");
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException
    {
	layoutGroup.setEnabled(false);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException
    {
	layoutGroup.render(gc, grphcs);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException
    {
	layoutGroup.update();
    }

}
