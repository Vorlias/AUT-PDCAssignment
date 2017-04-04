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
	
	/**
	 * 
	 * @param name
	 * @param maxHealth
	 * @param maxDamage
	 * @param maxGold 
	 */
	public Monster(String name, float maxHealth, int maxDamage, int maxGold)
	{
		this.setMaxHealth(maxHealth);
		this.setMaxDamage(maxDamage);
		this.setGold(random.nextInt(maxGold));
		this.name = name;
	}
	
	/**
	 * 
	 * @return 
	 */
	public int getMaxDamage()
	{
		return maxDamage;
	}

	/**
	 * 
	 * @param maxDamage 
	 */
	public void setMaxDamage(int maxDamage)
	{
		this.maxDamage = maxDamage;
	}
	
	/**
	 * 
	 * @return 
	 */
	public String getName()
	{
		return name;
	}
}
