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
    protected float stamina;
	protected float maxStamina;
	
    protected float mana;
	protected float maxMana;
	
    protected float health;
	protected float maxHealth;
	
    protected int level = 1;
    
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

	public void setHealth(float value)
	{
		this.health = value;
	}
}
