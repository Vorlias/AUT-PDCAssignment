/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.game;

import java.util.Scanner;
import rpg.cui.characters.Monster;
import rpg.cui.characters.MonsterType;

/**
 *
 * @author Nathan
 */
public class Combat
{
	static Scanner scanner = new Scanner(System.in);
	private static Monster enemy;
	/**
	 * 
	 */
	public static void handleCombat()
	{
		//Monster goblin = new Monster("Goblin", 50.f, 10, 5);
		//System.out.println(goblin.isAlive()); // Goblins alive!
		enemy = new Monster(MonsterType.random());
		
		switch (enemy.getType())
		{
			case Dragon:
				System.out.println("System: You come across an opening in the forest, of which a dragon swoops down and lands in front of you... prepare for a firey battle!");
				break;
			case Snake:
				System.out.println("System: You come across a snake, remembering your fear of snakes - you decide to attack it... prepare for combat!");
				break;
			default:
				System.out.println("System: As you explore the forest, a foe appears... get ready for combat!");
		}
		
		handleCombatOptions();
	}
	
	/**
	 * 
	 */
	private static void handleCombatOptions()
	{
		do
		{
			Game.getPlayerCharacter().printHealth();
			System.out.print("\t");
			enemy.printHealth();
			System.out.println();
			
			System.out.println("System: What would you like to do?");
			
		}
		while (enemy.isAlive());
	}
}
