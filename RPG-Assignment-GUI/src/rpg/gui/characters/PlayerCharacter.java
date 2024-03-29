/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.characters;

import java.util.ArrayList;
import rpg.gui.database.GameDatabase;
import rpg.gui.items.Consumable;
import rpg.gui.items.ConsumableType;
import rpg.gui.items.Item;
import rpg.gui.items.Weapon;
import rpg.gui.misc.Utility;

/**
 * PlayerCharacter class
 * @author Jonathan & Nathan
 */
public class PlayerCharacter extends Character
{

    static final int FIST_DAMAGE = 1; // No weapon equipped use fists

    private int xp = 0; // Player experience points
    private final ArrayList<Item> items = new ArrayList<>();
    private Weapon equippedWeapon; // Players equipped weapon
    private PlayerLocation location = PlayerLocation.Wilds; // Players inital location
    private boolean combat = false; // Is the player in combat

    /**
     * Returns whether or not the player is in combat
     * @return True if the player is in combat
     */
    public boolean inCombat()
    {
	return combat;
    }

    /**
     * Sets whether or not the player is in combat
     * @param combat True if the player is in combat
     */
    public void setInCombat(boolean combat)
    {
	this.combat = combat;
    }

    /**
     * Sets the location of the player
     * @param location The location of the player
     */
    public void setLocation(PlayerLocation location)
    {
	this.location = location;
    }

    /**
     * Gets the location of the player
     * @return The location of the player
     */
    public PlayerLocation getLocation()
    {
	return location;
    }

    /**
     * Removes the item from the player's inventory
     * @param item The item to remove
     * @param amount The amount to remove
     */
    public void removeItem(Item item, int amount)
    {
	int removedAmount = 0;
	for (int i = 0; i < items.size(); i++)
	{
	    Item currentItem = items.get(i);
	    if (currentItem == item)
	    {
		items.remove(i);
		removedAmount++;
	    }

	    if (amount == removedAmount)
	    {
		break;
	    }
	}
    }

    /**
     * Does the player have a consumable
     * @param type the type of consumable
     * @return true if the player has consumable otherwise false
     */
    public boolean hasConsumable(ConsumableType type)
    {
	for (Item i : items)
	{
	    if (i instanceof Consumable)
	    {
		Consumable c = (Consumable) i;
		if (c.getConsumableType() == type)
		{
		    return true;
		}
	    }
	}

	return false;
    }

    /**
     * Gets the equipped weapon
     * @return The equipped weapon
     */
    public Weapon getEquippedWeapon()
    {
	return equippedWeapon;
    }

    /**
     * ArrayList of items
     * @return items
     */
    public final ArrayList<Item> getItems()
    {
	return items;
    }

    /**
     * Gets the character's XP
     * @return The XP of this character
     */
    public int getXP()
    {
	return xp;
    }

    /**
     * PlayerCharacter constructor
     */
    public PlayerCharacter()
    {
	super("");
	this.setMaxHealth(100.f);
	this.setMaxStamina(100.f);
	this.setMaxMana(100.f);
    }

    /**
     * Creates a new Player Character
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
     * Adds the specified item to the player's inventory
     * @param item The item to add
     */
    public void addItem(Item item)
    {
	this.items.add(item);
    }

    /**
     * Adds the specified item to the player by id
     * @param id The id of the item to add
     */
    public void addItemById(int id)
    {
	this.items.add(GameDatabase.getItem(id));
    }

    /**
     * Equips an item by a specified id
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
     * @param target The target to attack
     * @return The damage done to the target
     */
    public float attack(Character target)
    {
	if (target == this)
	{
	    System.err.println("Player cannot attack themself.");
	    return 0;
	}
	else
	{
	    if (equippedWeapon != null)
	    {
		float max = equippedWeapon.getDamage();
		float min = max * 0.75f;
		float damage = Utility.randomFloat(min, max);

		target.takeDamage(damage);
		return damage;
	    }
	    else
	    {
		target.takeDamage(FIST_DAMAGE);
		return FIST_DAMAGE;
	    }
	}
    }
}
