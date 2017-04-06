/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.characters;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Jonathan
 */
public enum MonsterType
{
	Goblin(50.f, 10, 5),
	Snake(10.f, 3, 2),
	Elf(75.f, 15, 15),
	Bandit(75.f, 15, 10),
	Dragon(200.f, 50, 50);

	final String name;
	final float maxHealth;
	final int maxDamage;
	final int maxGold;

	MonsterType(float maxHealth, int maxDamage, int maxGold)
	{
		this.name = this.toString();
		this.maxHealth = maxHealth;
		this.maxDamage = maxDamage;
		this.maxGold = maxGold;
	}

	private static final List<MonsterType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static MonsterType random()
	{
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
}
