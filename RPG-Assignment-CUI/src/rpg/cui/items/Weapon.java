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

	public Weapon(String name)
	{
		super(name, ItemType.Weapon);
	}
	
	@Override
	public void use(PlayerCharacter character)
	{
		character.equip(this);
	}
	
}
