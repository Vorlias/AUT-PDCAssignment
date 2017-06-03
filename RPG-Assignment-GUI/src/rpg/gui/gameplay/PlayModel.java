/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.gameplay;

import rpg.gui.characters.Monster;
import rpg.gui.characters.PlayerCharacter;
import rpg.gui.states.PlayState;

/**
 * The game as a model
 *
 * @author Jonathan
 */
public class PlayModel
{
    private PlayState view;
    private PlayController controller;
    private PlayerCharacter playerCharacter;
    private Monster targetMonster;
    private Location playerLocation;
    private boolean inCombat;
    
    public PlayState getView()
    {
	return view;
    }

    public void setView(PlayState view)
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

    public Location getPlayerLocation()
    {
	return playerLocation;
    }

    /**
     * Sets the location of the player
     * @param playerLocation 
     */
    public void setPlayerLocation(Location playerLocation)
    {
	this.playerLocation = playerLocation;
    }

    public PlayModel(PlayerCharacter playerCharacter)
    {
	this.playerCharacter = playerCharacter;
    }
}
