/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.gameplay;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import rpg.gui.characters.Monster;
import rpg.gui.characters.MonsterType;
import rpg.gui.characters.PlayerCharacter;
import rpg.gui.characters.PlayerLocation;
import rpg.gui.states.PlayView;
import rpg.gui.ui.Button;
import rpg.gui.ui.ButtonLayoutGroupItemPressed;
import rpg.gui.ui.GUILayoutGroup;
import rpg.gui.ui.GameUI;
import rpg.gui.ui.TextDisplay;

/**
 *
 * @author Jonathan
 */
public class PlayController
{

    private PlayModel model;
    private PlayView view;
    public ButtonLayoutGroupItemPressed onActionButtonPressed;
    Random random = new Random();

    public static final String BUTTON_TOWN = "Go to Town",
	    BUTTON_FOREST = "Go to Forest",
	    BUTTON_BLACKSMITH = "Visit Blacksmith",
	    BUTTON_ALCHEMIST = "Visit Potion Store",
	    BUTTON_INVENTORY = "View Inventory",
	    BUTTON_ATTACK = "Attack",
	    BUTTON_FLEE = "Flee",
	    BUTTON_EXPLORE_FOREST = "Explore Forest";

    public void onActionButtonPressed(Button button)
    {
	String action = button.getText();
	PlayerCharacter playerCharacter = model.getPlayerCharacter();
	GameUI ui = view.getUI();
	TextDisplay textDisplay = ui.getTextDisplay();

	System.out.println(action);

	switch (action)
	{
	    case BUTTON_TOWN:
		playerCharacter.setLocation(PlayerLocation.Town);
		ui.getTextDisplay().addSystemMessage("You have entered the town of Tarrin.");
		updateOptions();
		break;
	    case BUTTON_FOREST:
		playerCharacter.setLocation(PlayerLocation.Forest);
		ui.getTextDisplay().addSystemMessage("You have entered the forest of Kreahx.");
		updateOptions();
		break;
	    case BUTTON_EXPLORE_FOREST:
		ui.getTextDisplay().addSystemMessage("You proceed to explore the forest...");
		// TODO: Random events ;-)
		int randomNum = random.nextInt(2) + 1;
		switch (randomNum)
		{
		    case 1:
			textDisplay.addSystemMessage("The first few days pass by quickly as you explore the forest.");
			textDisplay.addSystemMessage("You find yourself at the entrance to the forest having done nothing but walk a big loop.");
			break;
		    case 2:
			Monster newMonster = new Monster(MonsterType.random());
			model.setTargetMonster(newMonster);

			textDisplay.addMessage(newMonster.getName(), newMonster.getType().getGreeting());
			textDisplay.addRedMessage("PREPARE FOR COMBAT!");

			break;
		    case 3:
			break;
		}

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

    PlayerLocation lastLocation;
    boolean inCombat;

    public void updateOptions()
    {
	PlayerCharacter PlayerCharacter = model.getPlayerCharacter();
	Monster target = model.getTargetMonster();
	GameUI ui = view.getUI();
	GUILayoutGroup actionButtons = ui.getActionLayoutGroup();
	PlayerLocation location = model.getPlayerLocation();

	if (target != null)
	{
	    if (!inCombat)
	    {
		actionButtons.clear();
		lastLocation = PlayerLocation.Combat;
		actionButtons.addButtons(Button.Size.Large, BUTTON_ATTACK, BUTTON_FLEE, BUTTON_INVENTORY);
	    }
		
	    
	}
	else
	{
	    if (location != lastLocation)
	    {
		switch (location)
		{
		    case Town:
			actionButtons.clear();
			actionButtons.addButtons(Button.Size.Large, BUTTON_BLACKSMITH, BUTTON_ALCHEMIST, BUTTON_FOREST, BUTTON_INVENTORY);
			break;
		    case Forest:
			actionButtons.clear();
			actionButtons.addButtons(Button.Size.Large, BUTTON_EXPLORE_FOREST, BUTTON_TOWN, BUTTON_INVENTORY);
			break;
		    case Wilds:
			actionButtons.clear();
			actionButtons.addButtons(Button.Size.Large, BUTTON_FOREST, BUTTON_TOWN);
			break;
		}
	    }

	    lastLocation = location;
	}
    }

    public void startGame()
    {
	updateOptions();

    }
}
