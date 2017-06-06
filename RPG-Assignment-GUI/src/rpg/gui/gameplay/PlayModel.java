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
 *
 * @author Jonathan
 */
public class PlayModel
{
    private PlayView view;
    private PlayController controller;
    private PlayerCharacter playerCharacter;
    private Monster targetMonster;
    private boolean inCombat;
    
    public PlayView getView()
    {
	return view;
    }

    public void setView(PlayView view)
    {
	this.view = view;
    }

    public PlayController getController()
    {
	return controller;
    }

    public void setController(PlayController controller)
    {
	this.controller = controller;
    }

    public PlayerCharacter getPlayerCharacter()
    {
	return playerCharacter;
    }
    
    public Monster getTargetMonster()
    {
	return targetMonster;
    }
    
    public void setTargetMonster(Monster monster)
    {
	this.targetMonster = monster;
    }
    
    public void setInCombat(boolean inCombat)
    {
	this.inCombat = inCombat;
    }
    
    public boolean isInCombat()
    {
	return inCombat;
    }

    public void setPlayerCharacter(PlayerCharacter playerCharacter)
    {
	this.playerCharacter = playerCharacter;
    }

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

    public PlayModel(PlayerCharacter playerCharacter)
    {
	this.playerCharacter = playerCharacter;
    }
}
