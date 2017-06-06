/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.characters;

import java.util.Random;

/**
 * Monster class that contains all the monster information
 * @author Jonathan & Nathan
 */
public class Monster extends Character
{

    static Random random = new Random(); // New random
    private final int maxDamage; // Monster max damage
    private final MonsterType type; // Monster type

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
     * @return The damage dealt to the player
     */
    public int attack(PlayerCharacter character)
    {
	int damage = random.nextInt(maxDamage);
	character.takeDamage(damage);

	return damage;
    }
}
