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
import rpg.gui.characters.PlayerSave;
import rpg.gui.misc.Vector2;
import rpg.gui.ui.Button;
import rpg.gui.ui.GUILayoutGroup;

/**
 * Handle game save loading
 * @author Jonathan
 */
public class LoadSaveState extends BasicGameState
{

    GUILayoutGroup loadSaveLayout;

    // Get load state id
    @Override
    public int getID()
    {
	return RPGGame.STATE_LOAD;
    }

    // Inital load state
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
	loadSaveLayout = new GUILayoutGroup(gc, GUILayoutGroup.LayoutType.Vertical);
	loadSaveLayout.setGridExtents(new Vector2(2, 0));
	loadSaveLayout.setItemPadding(new Vector2(5, 5));

	loadSaveLayout.onButtonPress((Button button) ->
	{
	    String buttonText = button.getText();
	    if ("Menu".equals(buttonText))
	    {
		sbg.enterState(RPGGame.STATE_MENU);
		loadSaveLayout.setEnabled(false);
	    }
	    else
	    {
		/*
		    PlayerCharacter character = new PlayerCharacter(characterName.getText());
		    character.addItem(GameDatabase.getItem(ITEM_DAGGER)); // Add dagger
		    character.equipItemById(1);
		    
		    RPGGame.playView.setup(character);
		    sbg.enterState(RPGGame.STATE_GAME);
		*/
		
		PlayerCharacter character = new PlayerCharacter();
		PlayerSave.load(character, buttonText);
		System.out.println("origin: " + character.getLocation());
		RPGGame.playView.setup(character);
		loadSaveLayout.setEnabled(false);
		
		sbg.enterState(RPGGame.STATE_GAME);
	    }
	});
    }

    // Enter state
    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException
    {
	String[] saves = PlayerSave.getPlayerSaveList();

	loadSaveLayout.setEnabled(true);
	loadSaveLayout.clear();

	loadSaveLayout.addLabels(18.f, "Characters");

	for (String save : saves)
	{
	    loadSaveLayout.addButtons(Button.Size.Large, save);
	}

	loadSaveLayout.addLabels(18.f, "Menu");
	loadSaveLayout.addButtons(Button.Size.Large, "Menu");
    }

    // Render state
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException
    {
	loadSaveLayout.render(gc, grphcs);
    }

    // Update state
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException
    {
	loadSaveLayout.update();
    }

}
