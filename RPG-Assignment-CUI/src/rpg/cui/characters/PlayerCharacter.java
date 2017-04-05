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
import rpg.cui.items.ItemDatabase;
import rpg.cui.items.Weapon;

/**
 *
 * @author Jonathan
 */
public class PlayerCharacter extends Character
{

	static final int FIST_DAMAGE = 1;

	private int xp = 0;
	private final HashSet<Item> items = new HashSet<>();
	private Weapon equippedWeapon;
	
	@Deprecated
	public void saveCharacter()
	{
		PlayerSave.save(this);
	}

	/**
	 * Gets the equipped weapon
	 * @return The equipped weapon
	 */
	public Weapon getEquippedWeapon()
	{
		return equippedWeapon;
	}

	public final HashSet<Item> getItems()
	{
		return items;
	}

	/**
	 * Gets the character's XP
	 *
	 * @return The XP of this character
	 */
	public int getXP()
	{
		return xp;
	}

	/**
	 * Creates a new Player Character
	 *
	 * @param name The name of the Player's Character
	 */
	public PlayerCharacter(String name)
	{
		super(name);
		this.setMaxHealth(100.f);
		this.setMaxStamina(100.f);
		this.setMaxMana(100.f);
	}
	
	/**
	 * Gets the amount of XP to the next level
	 *
	 * @return The amount of XP to the next level
	 */
	private int getLevelupXP()
	{
		return (this.getLevel() * 50);
	}

	/**
	 * Returns whether or not the user has leveled up
	 *
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
	 *
	 * @param amount The amount of XP to add
	 */
	public void addXP(int amount)
	{
		xp += amount;
		levelupCheck();
	}

	/**
	 *
	 */
	public void printStats()
	{
		System.out.println("Current Stats -");
		System.out.println("\tLevel: " + this.getLevel());
		System.out.println("\tHealth: " + this.getHealth() + "/" + this.getMaxHealth());
		System.out.println("\tMana: " + this.getMana() + "/" + this.getMaxMana());
		System.out.println("\tStamina: " + this.getStamina() + "/" + this.getMaxStamina());
		System.out.println("\tGold: " + this.getGold());
		System.out.println("\tXP: " + this.xp);
	}

	/**
	 * Adds the specified item to the player's inventory
	 *
	 * @param item The item to add
	 */
	public void addItem(Item item)
	{
		this.items.add(item);
	}

	/**
	 * Adds the specified item to the player by id
	 *
	 * @param id The id of the item to add
	 */
	public void addItemById(int id)
	{
		this.items.add(ItemDatabase.database.getItemById(id));
	}

	/**
	 * Equips an item by a specified id
	 *
	 * @param id
	 */
	public void equipItemById(int id)
	{
		for (Item i : items)
		{
			if (i.getId() == id)
			{
				equipItem(i);
			}
		}
	}

	/**
	 * Attempts to equip the item to the player
	 *
	 * @param item The item to equip
	 */
	public void equipItem(Item item)
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

	/**
	 * Attacks the target
	 *
	 * @param target The target to attack
	 */
	public void attack(Character target)
	{
		if (target == this)
		{
			System.err.println("Player cannot attack themself.");
		}
		else
		{
			if (equippedWeapon != null)
			{
				target.takeDamage(equippedWeapon.getDamage());
			}
			else
			{
				target.takeDamage(FIST_DAMAGE);
			}
		}
	}
}
