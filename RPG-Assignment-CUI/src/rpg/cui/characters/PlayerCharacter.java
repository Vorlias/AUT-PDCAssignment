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
public class PlayerCharacter extends Character
{
    int xp = 0;
	String name;
    
    public PlayerCharacter(String name)
    {
		this.setMaxHealth(100.f);
		this.setMaxStamina(100.f);
		this.setMaxMana(100.f);
		this.name = name;
    }
    
    private int getLevelupXP()
    {
		return (this.getLevel() * 50);
    }
    
    private boolean hasUserLevelledUp()
    {
		return xp >= getLevelupXP();
    }
    
    public void addXP(int amount)
    {
		xp += amount;

		if (hasUserLevelledUp())
		{
			xp -= getLevelupXP();
			this.setLevel(this.getLevel() + 1);
			System.out.println("** You are now level " + this.getLevel() + "! **");
		}
    }
}
