/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.items;

import java.util.HashMap;
import rpg.cui.characters.PlayerCharacter;

/**
 *
 * @author Jonathan
 */
public abstract class Item
{
	public static final int INVALID_ID = -1;
	
	public enum ItemType 
	{
		Weapon,
		Consumable,
	}
	
	protected String name;
	private final ItemType type;
	
	protected int id = INVALID_ID;
	
	/**
	 * Gets the item's ID
	 * @return The item id
	 */
	public int getId()
	{
		return id;
	}
	
	/**
	 * Set the item Id
	 * @param id The id of the item
	 */
	public void setId(int id)
	{
		this.id = id;
	}
	
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
	
	public abstract HashMap<String, Object> getAttributes();
	public abstract void setAttributes(HashMap<String, Object> attributes);
	
	public String encode()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("item ");
		builder.append(type.toString()).append(" ");
		
		builder.append("Name = \"").append(this.getName()).append("\"; ");
		
		this.getAttributes().forEach((String key, Object value) -> {
			if (value instanceof String)
				builder.append(key).append(" = \"").append(value).append("\"; ");
			else if (value instanceof Integer || value instanceof Double)
				builder.append(key).append(" = ").append(value).append("; ");
		});
		
		return builder.toString();
	}
}
