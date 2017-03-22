/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.characters;

/**
 *
 * @author Jonathan
 */
public class Character
{
	private float stamina;
	private float maxStamina;
	
    private float mana;
	private float maxMana;
	
    private float health;
	private float maxHealth;
	
    private int level = 1;
	
	protected void setLevel(int level)
	{
		this.level = level;
	}

	public float getMaxHealth()
	{
		return maxHealth;
	}

	public float getMaxMana()
	{
		return maxMana;
	}

	public float getMaxStamina()
	{
		return maxStamina;
	}
	
    public int getLevel()
    {
		return level;
    }
    
    public float getHealth()
    {
		return health;
    }
    
    public float getStamina()
    {
		return stamina;
    }
    
    public float getMana()
    {
		return mana;
    }
	
	public final void setMaxStamina(float value)
	{
		this.stamina = value;
		this.maxStamina = value;
	}
	
	public final void setMaxMana(float value)
	{
		this.mana = value;
		this.maxMana = value;
	}
	
	public final void setMaxHealth(float value)
	{
		this.health = value;
		this.maxHealth = value;
	}
	
	public void setMana(float value)
	{
		this.mana = value;
	}
	
	public void setStamina(float value)
	{
		this.stamina = value;
	}
	
	public void setHealth(float value)
	{
		this.health = value;
	}
}
