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
 *
 * @author Jonathan
 */
public enum MonsterType
{
    Goblin(2, 10, 5, 25),
    Snake(1, 3, 2, 25),
    Elf(3, 15, 15, 15),
    Bandit(3, 15, 15, 15),
    Dragon(10, 50, 50, 5);

    final String name;
    final float maxHealth;
    final int maxDamage;
    final int maxGold;
    final float weight;
    final int level;

    MonsterType(int level, int maxDamage, int maxGold, float weight)
    {
	this.name = this.toString();
	this.maxHealth = level * 25.f;
	this.maxDamage = maxDamage;
	this.maxGold = maxGold;
	this.weight = weight;
	this.level = level;
    }

    // TODO: Weighted random instead of regular random
    private static final List<MonsterType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    // To cache the weighted random object
    private static WeightedRandom<MonsterType> weightedMonsters;

    /**
     * Returns a monster through weighted random
     *
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

    /*public static MonsterType random()
	{
		return VALUES.get(RANDOM.nextInt(SIZE));
	}*/
}
