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
public class Weapon extends Item
{
	private int damage;

	public Weapon(String name, int damage)
	{
		super(name, ItemType.Weapon);
		this.damage = damage;
	}
	
	public Weapon()
	{
		super("", ItemType.Weapon);
		this.damage = 0;
	}
	
	/**
	 * Gets the damage this weapon does
	 * @return The damage the weapon does
	 */
	public int getDamage()
	{
		return damage;
	}
	
	@Override
	public String toString()
	{
		return "Weapon	| " + this.getName() + " <" + this.getDamage() + ">";
	}
	
	@Override
	public void use(PlayerCharacter character)
	{
		character.equipItem(this);
	}

	@Override
	public HashMap<String, Object> getAttributes()
	{
		HashMap<String, Object> attributes = new HashMap<>();
		attributes.put("Damage", damage);
		
		return attributes;
	}

	@Override
	public void setAttributes(HashMap<String, Object> attributes)
	{
		attributes.forEach((String key, Object value) -> {
			if (key.equals("Name"))
			{
				this.name = value.toString();
			}
			else if (key.equals("Damage"))
			{
				this.damage = (Integer) value;
			}
		});
	}
	
}
