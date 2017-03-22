/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.characters;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import rpg.cui.items.Item;
import rpg.cui.items.Weapon;

/**
 *
 * @author Jonathan
 */
public class PlayerCharacter extends Character
{
    private int xp = 0;
	private String name;
	private final HashSet<Item> items = new HashSet<>();
	private Weapon equippedWeapon;
    
	public void saveCharacter()
	{
		try (PrintWriter out = new PrintWriter(name + ".save"))
		{
			out.println("Name " + name);
			this.writeStats(out);
			out.println("XP " + xp);
			out.flush();
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Failed to write to file " + name + ".save - " + e.getMessage());
		}
	}
	
	public final HashSet<Item> getItems()
	{
		return items;
	}
	
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
	
	/**
	 * Adds the specified item to the player's inventory
	 * @param item The item to add
	 */
	public void addItem(Item item)
	{
		this.items.add(item);
	}
	
	/**
	 * Attempts to equip the item to the player
	 * @param item The item to equip
	 */
	public void equip(Item item)
	{
		if (!items.contains(item))
		{
			System.err.println("Cannot equip weapon the user does not have");
			return;
		}
		
		if (item instanceof Weapon)
		{
			this.equippedWeapon = (Weapon) item;
		}
	}
}
