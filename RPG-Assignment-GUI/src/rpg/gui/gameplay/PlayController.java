/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.gameplay;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import rpg.gui.RPGGame;
import rpg.gui.characters.Monster;
import rpg.gui.characters.MonsterType;
import rpg.gui.characters.PlayerCharacter;
import rpg.gui.characters.PlayerLocation;
import rpg.gui.characters.PlayerSave;
import rpg.gui.items.Weapon;
import rpg.gui.misc.Vector2;
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
    StateBasedGame stateBasedGame;
    
    public void setStateBasedGame(StateBasedGame stateBasedGame)
    {
	this.stateBasedGame = stateBasedGame;
    }

    public static final String BUTTON_TOWN = "Go to Town",
	    BUTTON_FOREST = "Go to Forest",
	    BUTTON_BLACKSMITH = "Visit Blacksmith",
	    BUTTON_ALCHEMIST = "Visit Potion Store",
	    BUTTON_INVENTORY = "View Inventory",
	    BUTTON_ATTACK = "Attack",
	    BUTTON_FLEE = "Flee",
	    BUTTON_EXPLORE_FOREST = "Explore Forest",
	    BUTTON_CLERIC = "Visit Healer";
    
    public static final String BUTTON_SAVE = "Save",
	    BUTTON_MENU = "Menu";

    public void menuInit()
    {
	GameUI ui = view.getUI();
	GUILayoutGroup menuGroup = ui.getMenuButtonGroup();
	menuGroup.setLocation(15, 3);
	menuGroup.setItemPadding(new Vector2(5, 5));
	menuGroup.addButtons(Button.Size.Regular, BUTTON_MENU, BUTTON_SAVE);
    }
    
    public void onMenuButtonPressed(Button button)
    {
	String action = button.getText();
	GameUI ui = view.getUI();
	TextDisplay textDisplay = ui.getTextDisplay();
	
	switch (action)
	{
	    case BUTTON_MENU:
		stateBasedGame.enterState(RPGGame.STATE_MENU);
		break;
	    case BUTTON_SAVE:
		PlayerSave.save(model.getPlayerCharacter());
		textDisplay.addSystemMessage("Saved " + model.getPlayerCharacter().getName() + " successfully.");
		break;
	}
    }
    
    public void onActionButtonPressed(Button button)
    {
	String action = button.getText();
	PlayerCharacter playerCharacter = model.getPlayerCharacter();
	Monster targetMonster = model.getTargetMonster();
	GameUI ui = view.getUI();
	TextDisplay textDisplay = ui.getTextDisplay();
	
	switch (action)
	{
	    case BUTTON_ATTACK:
		float playerDamage = playerCharacter.attack(targetMonster);
		int monsterDamage = targetMonster.attack(playerCharacter);
		Weapon weapon = playerCharacter.getEquippedWeapon();
		
		textDisplay.addSystemMessage("[" + playerCharacter.getName() + "] attacks [" + targetMonster.getName() + "] with " + (weapon != null ? weapon.getName() : "fists") + " for " + (int)playerDamage + " damage", Color.green);
		textDisplay.addSystemMessage("[" + targetMonster.getName() + "] attacks [" + playerCharacter.getName() + "] for " + monsterDamage + " damage", Color.red);
		
		if (!targetMonster.isAlive())
		{
		    textDisplay.addSystemMessage("You successfully defeated the " + targetMonster.getName() + "!");
		    model.setTargetMonster(null);
		    playerCharacter.addXP(targetMonster.getLevel() * 10);

		    int goldToAdd = random.nextInt(9) + 1;
		    playerCharacter.setGold(playerCharacter.getGold() + goldToAdd);
		    textDisplay.addSystemMessage("You picked up " + goldToAdd + " gold.");
		}
		else if (!playerCharacter.isAlive())
		{
		    playerCharacter.setLocation(PlayerLocation.Dead);
		    model.setTargetMonster(null);
		    textDisplay.addEmpty();
		    textDisplay.addEmpty();
		    textDisplay.addEmpty();
		    textDisplay.addSystemMessage("You died. How unfortunate.", Color.red);
		}
		
		updateOptions();
		
		break;
		
	    case BUTTON_FLEE:
		playerCharacter.setLocation(PlayerLocation.Town);
		textDisplay.addSystemMessage("You escape to town... but not unscathed.");
		playerCharacter.takeDamage(25.f);
		model.setTargetMonster(null);
		updateOptions();
		break; 
		
	    case BUTTON_TOWN:
		playerCharacter.setLocation(PlayerLocation.Town);
		textDisplay.addSystemMessage("You have entered the town of Tarrin.");
		updateOptions();
		break;
		
	    case BUTTON_CLERIC:
		if (playerCharacter.getGold() >= 10)
		{
		    playerCharacter.setHealth(playerCharacter.getMaxHealth());
		    playerCharacter.setGold(playerCharacter.getGold() - 10);
		    textDisplay.addMessage("Healer", "There you go, all healed up " + playerCharacter.getName() + ".");
		}
		else
		{
		    textDisplay.addMessage("Healer", "Sorry, you need 10 gold to heal yourself. You have " + playerCharacter.getGold());
		}
		break;
		
	    case BUTTON_FOREST:
		playerCharacter.setLocation(PlayerLocation.Forest);
		textDisplay.addSystemMessage("You have entered the forest of Kreahx.");
		updateOptions();
		break;
		
	    case BUTTON_EXPLORE_FOREST:
		textDisplay.addSystemMessage("You proceed to explore the forest...");

		int randomNum = random.nextInt(2) + 1;
		switch (randomNum)
		{
		    case 1:
			textDisplay.addEmpty();
			textDisplay.addSystemMessage("The first few days pass by quickly as you explore the forest.");
			textDisplay.addSystemMessage("You find yourself at the entrance to the forest having done nothing but walk a big loop.");
			break;
			
		    case 2:
			textDisplay.addEmpty();
			Monster newMonster = new Monster(MonsterType.random());
			model.setTargetMonster(newMonster);

			textDisplay.addMessage(newMonster.getName(), newMonster.getType().getGreeting());
			textDisplay.addSystemMessage("PREPARE FOR COMBAT!", Color.red);
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
		actionButtons.addButtons(Button.Size.Large, BUTTON_ATTACK, BUTTON_FLEE);
	    }
		
	    
	}
	else
	{
	    if (location != lastLocation)
	    {
		switch (location)
		{
		    case Dead:
			actionButtons.clear();
			break;
		    
		    case Town:
			actionButtons.clear();
			actionButtons.addButtons(Button.Size.Large, BUTTON_CLERIC, BUTTON_FOREST);
			break;
		    case Forest:
			actionButtons.clear();
			actionButtons.addButtons(Button.Size.Large, BUTTON_EXPLORE_FOREST, BUTTON_TOWN);
			
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
