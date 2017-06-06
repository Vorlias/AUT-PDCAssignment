/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.gameplay;

import rpg.gui.characters.Monster;
import rpg.gui.characters.PlayerCharacter;
import rpg.gui.characters.PlayerLocation;
import rpg.gui.states.PlayView;

/**
 * The game as a model
 * @author Jonathan
 */
public class PlayModel
{
    private PlayView view;
    private PlayController controller;
    private PlayerCharacter playerCharacter;
    private Monster targetMonster;
    private boolean inCombat;
    
    // Get the view
    public PlayView getView()
    {
	return view;
    }

    // Set the view
    public void setView(PlayView view)
    {
	this.view = view;
    }

    // Get controller
    public PlayController getController()
    {
	return controller;
    }

    // Set controller
    public void setController(PlayController controller)
    {
	this.controller = controller;
    }

    // Get playercharacter
    public PlayerCharacter getPlayerCharacter()
    {
	return playerCharacter;
    }
    
    // Get target monster
    public Monster getTargetMonster()
    {
	return targetMonster;
    }
    
    // Set target monster
    public void setTargetMonster(Monster monster)
    {
	this.targetMonster = monster;
    }
    
    // Set player in combat
    public void setInCombat(boolean inCombat)
    {
	this.inCombat = inCombat;
    }
    
    // Check if player in combat
    public boolean isInCombat()
    {
	return inCombat;
    }

    // Set playercharacter
    public void setPlayerCharacter(PlayerCharacter playerCharacter)
    {
	this.playerCharacter = playerCharacter;
    }

    // Get player location
    public PlayerLocation getPlayerLocation()
    {
	return playerCharacter.getLocation();
    }

    /**
     * Sets the location of the player
     * @param playerLocation 
     */
    public void setPlayerLocation(PlayerLocation playerLocation)
    {
	playerCharacter.setLocation(playerLocation);
    }

    // PlayModel constructor
    public PlayModel(PlayerCharacter playerCharacter)
    {
	this.playerCharacter = playerCharacter;
    }
}
