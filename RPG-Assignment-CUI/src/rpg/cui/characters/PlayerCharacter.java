/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.characters;

/**
 *
 * @author Jonathan
 */
public class PlayerCharacter extends Character
{
    int xp = 0;
	String name;
    
	/**
	 * Creates a new Player Character
	 * @param name The name of the Player's Character
	 */
    public PlayerCharacter(String name)
    {
		this.setMaxHealth(100.f);
		this.setMaxStamina(100.f);
		this.setMaxMana(100.f);
		this.name = name;
    }
    
	/**
	 * Gets the amount of XP to the next level
	 * @return The amount of XP to the next level
	 */
    private int getLevelupXP()
    {
		return (this.getLevel() * 50);
    }
    
	/**
	 * Returns whether or not the user has leveled up
	 * @return True if the user has leveled up
	 */
    private boolean hasUserLevelledUp()
    {
		return xp >= getLevelupXP();
    }
	
	/**
	 * Handles leveling up
	 */
	private void levelupCheck()
	{
		if (hasUserLevelledUp())
		{
			xp -= getLevelupXP();
			this.setLevel(this.getLevel() + 1);
			System.out.println("** You are now level " + this.getLevel() + "! **");
			levelupCheck();
		}		
	}
    
	/**
	 * Adds XP to this character
	 * @param amount The amount of XP to add
	 */
    public void addXP(int amount)
    {
		xp += amount;
		levelupCheck();
    }

	/**
	 * Gets the name of this character
	 * @return The name of the character
	 */
	public String getName()
	{
		return name;
	}
}
