/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.items;

import java.io.Serializable;
import rpg.cui.characters.PlayerCharacter;

/**
 *
 * @author Jonathan
 */
public abstract class Item implements Serializable
{
	static final long serialVersionUID = 0xD100;
	
	public enum ItemType 
	{
		Weapon,
		Consumable,
	}
	
	private String name;
	private final ItemType type;
	
	/**
	 * Creates a new item
	 * @param name The name of the item
	 * @param type The type of the item
	 */
	public Item(String name, ItemType type)
	{
		this.name = name;
		this.type = type;
	}

	/**
	 * Gets the name of the item
	 * @return The name of the item
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Get the type of the item
	 * @return The type of the item
	 */
	public ItemType getType()
	{
		return type;
	}
	
	@Override
	public String toString()
	{
		return getName();
	}
	
	/**
	 * Use the item (equip, consume etc.)
	 * @param character The character to use the item on
	 */
	public abstract void use(PlayerCharacter character);
}
