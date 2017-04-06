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
public class Consumable extends Item
{
	public enum ConsumableType
	{
		Health,
		Mana,
		Stamina,
	}
	
	private ConsumableType type;
	private int modifier;
	
	/**
	 * Creates a new Consumable Item
	 * @param name The name of the consumable
	 * @param type The type of the consumable
	 * @param modifier How much this item modifies it's type by
	 */
	public Consumable(String name, ConsumableType type, int modifier)
	{
		super(name, ItemType.Consumable);
		this.type = type;
		this.modifier = modifier;
	}
	
	public Consumable()
	{
		super("", ItemType.Consumable);
	}	
	
	@Override
	public String toString()
	{
		return "Consumable	| " + this.getName() + ", type: " + this.type.toString() + ", modifier: " + this.modifier;
	}
	
	@Override
	public HashMap<String, Object> getAttributes()
	{
		HashMap<String, Object> attributes = new HashMap<>();
		attributes.put("Modifier", modifier);
		attributes.put("Type", type.toString());
		
		return attributes;
	}

	@Override
	public void setAttributes(HashMap<String, Object> attributes)
	{
		attributes.forEach((String key, Object value) -> {
			switch (key)
			{
				case "Name":
					this.name = value.toString();
					break;
				case "Modifier":
					this.modifier = (Integer) value;
					break;
				case "Type":
					this.type = ConsumableType.valueOf((String) value);
					break;
				default:
					break;
			}
		});
	}

	@Override
	public void use(PlayerCharacter character)
	{
		
	}
}
