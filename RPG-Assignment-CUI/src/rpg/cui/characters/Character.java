/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.characters;

import rpg.cui.misc.TextColor;

/**
 *
 * @author Jonathan
 */
public abstract class Character
{
	private float stamina;
	private float maxStamina;
	
    private float mana;
	private float maxMana;
	
    private float health;
	private float maxHealth;
	
    private int level = 1;
	private int gold = 0;
	
	private String name;
	
	/**
	 * Gets the name of the character
	 * @return The name of the character
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Sets the name of this character
	 * @param name The name of this character
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Creates a character
	 * @param name The name of the character
	 */
	protected Character(String name)
	{
		this.name = name;
	}
	
	/**
	 * Takes away health from this character
	 * @param amount The amount to take away
	 */
	public void takeDamage(float amount)
	{
		health -= Math.min(health, amount);
	}
	
	/**
	 * Returns whether or not the character is alive
	 * @return True if the character is alive
	 */
	public boolean isAlive()
	{
		return health > 0;
	}
	
	/**
	 * Sets the character's level
	 * @param level The level of the character
	 */
	public void setLevel(int level)
	{
		this.level = level;
	}
	
	/**
	 * Sets the character's amount of gold
	 * @param gold The amount of  gold the character has
	 */
	public void setGold(int gold)
	{
		this.gold = gold;
	}
	
	/**
	 * Gets the character's max health
	 * @return The max health of this character
	 */
	public float getMaxHealth()
	{
		return maxHealth;
	}

	/**
	 * Gets the character's max mana
	 * @return The max mana of this character
	 */
	public float getMaxMana()
	{
		return maxMana;
	}

	/**
	 * Gets the character's max stamina
	 * @return The max stamina of this character
	 */
	public float getMaxStamina()
	{
		return maxStamina;
	}
	
	/**
	 * Gets the level of this character
	 * @return The level of this character
	 */
    public int getLevel()
    {
		return level;
    }
    
	/**
	 * Gets the amount of gold the character has
	 * @return The gold amount of the character
	 */
    public int getGold()
    {
		return gold;
    }
	
	/**
	 * Gets the current health of this character
	 * @return The current health of the character
	 */
    public float getHealth()
    {
		return health;
    }
    
	/**
	 * Gets the current stamina of this character
	 * @return The current stamina of this character
	 */
    public float getStamina()
    {
		return stamina;
    }
    
	/**
	 * Gets the mana of this character
	 * @return The mana of this character
	 */
    public float getMana()
    {
		return mana;
    }
	
	/**
	 * Sets the max stamina of this character
	 * @param value The max stamina of this character
	 */
	public final void setMaxStamina(float value)
	{
		this.stamina = value;
		this.maxStamina = value;
	}
	
	/**
	 * Sets the max mana of this character
	 * @param value The max mana of this character
	 */
	public final void setMaxMana(float value)
	{
		this.mana = value;
		this.maxMana = value;
	}
	
	/**
	 * Sets the max health of this character
	 * @param value The max health of the character
 	 */
	public final void setMaxHealth(float value)
	{
		this.health = value;
		this.maxHealth = value;
	}
	
	/**
	 * Sets the current mana of this character
	 * @param value The current mana of this character
	 */
	public void setMana(float value)
	{
		this.mana = value;
	}
	
	/**
	 * Sets the current stamina of this character
	 * @param value The current stamina of this character
	 */
	public void setStamina(float value)
	{
		this.stamina = value;
	}
	
	/**
	 * Sets the current health of this character
	 * @param value The current health of this character
	 */
	public void setHealth(float value)
	{
		this.health = value;
	}
	
	/**
	 * Writes the health to the console
	 */
	public void printHealth()
	{
		System.out.print(TextColor.Default + this.getName() + " (Lv. " + this.getLevel() +") [");
		
		float percentage = this.health / this.maxHealth;
		
		int first = (int) (percentage * 10);
		int second = 10 - first;
		
		for (int i = 0; i < first; i++)
		{
			System.out.print(TextColor.Green + "|");
		}
		
		for (int i = 0; i < second; i++)
		{
			System.out.print(TextColor.Red + "|");
		}
		
		System.out.println(TextColor.Default + "] (" + this.getHealth() + "/" + this.getMaxHealth() + ")");
	}
}
