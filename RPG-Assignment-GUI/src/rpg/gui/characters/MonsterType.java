/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.characters;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import rpg.gui.misc.WeightedRandom;

/**
 * Monster type class that contains all the monster types and stats
 * @author Jonathan & Nathan
 */
public enum MonsterType
{
    Goblin(2, 10, 5, 25, "Gaargla blarrg flarg"), // Goblin stats
    Snake(1, 3, 2, 25, "Sssssssss...."), // Snake stats
    Elf(3, 15, 15, 15, "You don't belong here, Human."), // Elf stats
    Bandit(3, 15, 15, 15, "Hand over all your money!"), // Bandit stats
    Dragon(10, 50, 50, 5, "ROOOOOOOOOAAAAAAAAAAR!!"); // Dragon stats

    final String name; // Monster name
    final float maxHealth; // Monster max health
    final int maxDamage; // Monster max damage
    final int maxGold; // Monster max gold
    final float weight; // Random weight
    final int level; // Monster level
    final String greeting; // Monster greeting
    
    /**
     * Get monster greeting
     * @return the monster greeting
     */
    public String getGreeting()
    {
	return greeting;
    }
    
    /**
     * Get monster name
     * @return the monster name
     */
    public String getName()
    {
	return name;
    }

    /**
     * The type of monster
     * @param level monsters level
     * @param maxDamage monsters max damage
     * @param maxGold monsters max gold
     * @param weight monsters random weight
     * @param greeting monsters greeting
     */
    MonsterType(int level, int maxDamage, int maxGold, float weight, String greeting)
    {
	this.name = this.toString();
	this.maxHealth = level * 25.f;
	this.maxDamage = maxDamage;
	this.maxGold = maxGold;
	this.weight = weight;
	this.level = level;
	this.greeting = greeting;
    }

    private static final List<MonsterType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    // To cache the weighted random object
    private static WeightedRandom<MonsterType> weightedMonsters;

    /**
     * Returns a monster through weighted random
     * @return A monster
     */
    public static MonsterType random()
    {
	if (weightedMonsters == null)
	{
	    weightedMonsters = new WeightedRandom<>();
	    VALUES.forEach((type) ->
	    {
		weightedMonsters.addItem(type, type.weight);
	    });
	}

	return weightedMonsters.next();
    }
}
