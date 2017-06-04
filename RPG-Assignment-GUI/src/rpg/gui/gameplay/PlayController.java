/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.gameplay;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import rpg.gui.characters.PlayerCharacter;
import rpg.gui.characters.PlayerLocation;
import rpg.gui.states.PlayView;
import rpg.gui.ui.Button;
import rpg.gui.ui.ButtonLayoutGroupItemPressed;
import rpg.gui.ui.GUILayoutGroup;
import rpg.gui.ui.GameUI;

/**
 *
 * @author Jonathan
 */
public class PlayController
{

    private PlayModel model;
    private PlayView view;
    public ButtonLayoutGroupItemPressed onActionButtonPressed;

    public static final String BUTTON_TOWN = "Go to Town",
	    BUTTON_FOREST = "Go to Forest",
	    BUTTON_BLACKSMITH = "Visit Blacksmith",
	    BUTTON_ALCHEMIST = "Visit Potion Store",
	    BUTTON_INVENTORY = "View Inventory",
	    BUTTON_ATTACK = "Attack",
	    BUTTON_FLEE = "Flee",
	    BUTTON_EXPLORE = "Explore Forest";

    public void onActionButtonPressed(Button button)
    {
	String action = button.getText();
	PlayerCharacter playerCharacter = model.getPlayerCharacter();

	System.out.println(action);
	
	switch (action)
	{
	    case BUTTON_TOWN:
		playerCharacter.setLocation(PlayerLocation.Town);
		updateOptions();
		break;
	}
    }

    public PlayModel getModel()
    {
	return model;
    }

    public void setModel(PlayModel model)
    {
	this.model = model;
    }

    public PlayView getView()
    {
	return view;
    }

    public void setView(PlayView view)
    {
	this.view = view;
    }

    /**
     * Called when the state is updated
     *
     * @param container
     * @param stateGame
     * @param delta
     */
    public void update(GameContainer container, StateBasedGame stateGame, int delta)
    {

    }

    public void updateOptions()
    {
	GameUI ui = view.getUI();
	GUILayoutGroup actionButtons = ui.getActionLayoutGroup();
	PlayerLocation location = model.getPlayerLocation();

		try
	{
	    Thread.sleep(1000);
	}
	catch (InterruptedException ex)
	{
	    Logger.getLogger(PlayController.class.getName()).log(Level.SEVERE, null, ex);
	}
	
	switch (location)
	{
	    case Town:
		actionButtons.clear();
		actionButtons.addButtons(Button.Size.Regular, BUTTON_BLACKSMITH, BUTTON_ALCHEMIST, BUTTON_FOREST, BUTTON_INVENTORY);
		break;
	    case Forest:
		actionButtons.clear();
		actionButtons.addButtons(Button.Size.Regular, BUTTON_EXPLORE, BUTTON_TOWN, BUTTON_INVENTORY);
		break;
	    case Wilds:
		actionButtons.clear();
		actionButtons.addButtons(Button.Size.Regular, BUTTON_FOREST, BUTTON_TOWN);
		break;
	}


    }

    public void startGame()
    {
	updateOptions();

    }
}
