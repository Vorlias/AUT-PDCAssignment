/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.game;

import rpg.cui.characters.Monster;

/**
 *
 * @author Nathan
 */
public class Combat
{
	/**
	 * 
	 */
	public static void handleCombat()
	{
		Monster goblin = new Monster("Goblin", 50.f, 10, 5);
		System.out.println(goblin.isAlive()); // Goblins alive!
	}
	
	/**
	 * 
	 */
	private static void handleCombatOptions()
	{
		
	}
}
