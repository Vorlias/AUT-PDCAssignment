/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.characters;

import java.util.Random;

/**
 *
 * @author Nate
 */
public class Monster extends Character
{
	static Random random = new Random();
	private String name;
	private int maxDamage;
	
	public Monster(String name, float maxHealth, int maxDamage, int maxGold)
	{
		this.setMaxHealth(maxHealth);
		this.maxDamage = maxDamage;
		this.setGold(random.nextInt(maxGold));
		this.name = name;
	}
	
	public int getMaxDamage()
	{
		return maxDamage;
	}
	
	public String getName()
	{
		return name;
	}
}
