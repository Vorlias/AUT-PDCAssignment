/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.characters;

import java.util.Random;
import rpg.cui.misc.TextColor;

/**
 *
 * @author Nate
 */
public class Monster extends Character
{
	static Random random = new Random();
	private final int maxDamage;
	private final MonsterType type;
	
	/**
	 * Gets the type of monster this is
	 * @return The type of monster
	 */
	public MonsterType getType()
	{
		return type;
	}
	
	/**
	 * Creates a new monster of the specified type
	 * @param type The type of monster
	 */
	public Monster(MonsterType type)
	{
		super(type.name);
		this.setMaxHealth(type.maxHealth);
		this.maxDamage = type.maxDamage;
		this.setGold(random.nextInt(type.maxGold));
		this.type = type;
	}
	
	/**
	 * Gets the max damage this monster does
	 * @return The maximum damage the monster does
	 */
	public int getMaxDamage()
	{
		return maxDamage;
	}
	
	/**
	 * Attack the player's character
	 * @param character The player character
	 */
	public void attack(PlayerCharacter character)
	{
		int damage = random.nextInt(maxDamage);
		character.takeDamage(damage);
		
		System.out.println(TextColor.Red + "** " + this.getName() + " attacked " + character.getName() + " for " + damage + " damage **");
	}
}
