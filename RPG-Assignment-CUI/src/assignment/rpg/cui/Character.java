/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.rpg.cui;

/**
 *
 * @author Jonathan
 */
public class Character
{
    float health;
    float stamina;
    float mana;
    
    public Character(float health, float stamina, float mana)
    {
	this.health = health;
	this.stamina = stamina;
	this.mana = mana;
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
}
