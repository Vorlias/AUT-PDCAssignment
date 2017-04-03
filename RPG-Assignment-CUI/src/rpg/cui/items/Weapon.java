/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.items;

import rpg.cui.characters.PlayerCharacter;

/**
 *
 * @author Jonathan
 */
public class Weapon extends Item
{
	static final long serialVersionUID = 0xD101;
	
	private int damage;
	private String fiddle;

	public Weapon(String name, int damage)
	{
		super(name, ItemType.Weapon);
		this.damage = damage;
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
		character.equip(this);
	}
	
}
