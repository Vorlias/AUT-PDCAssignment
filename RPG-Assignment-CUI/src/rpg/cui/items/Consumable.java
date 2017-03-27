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
public class Consumable extends Item implements Serializable
{
	static final long serialVersionUID = 0xD102;
	
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

	@Override
	public void use(PlayerCharacter character)
	{
		
	}
}
