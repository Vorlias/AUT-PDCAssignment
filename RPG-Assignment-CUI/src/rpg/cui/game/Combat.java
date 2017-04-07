/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.game;

import java.util.Scanner;
import rpg.cui.characters.Monster;
import rpg.cui.characters.MonsterType;
import rpg.cui.characters.PlayerCharacter;
import rpg.cui.characters.PlayerLocation;
import rpg.cui.misc.TextColor;

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
				System.out.println(TextColor.Green + "System: You come across an opening in the forest, of which a dragon swoops down and lands in front of you... prepare for a firey battle!");
				break;
			case Snake:
				System.out.println(TextColor.Green + "System: You come across a snake, remembering your fear of snakes - you decide to attack it... prepare for combat!");
				break;
			default:
				System.out.println(TextColor.Green + "System: As you explore the forest, a foe appears... get ready for combat!");
		}
		
		handleCombatOptions();
	}
	
	/**
	 * 
	 */
	private static void handleCombatOptions()
	{
		boolean hasFled = false;
		PlayerCharacter playerCharacter = Game.getPlayerCharacter();
		playerCharacter.setInCombat(true);
		
		while (enemy.isAlive() && !hasFled)
		{
			playerCharacter.printHealth();
			System.out.print("\t");
			enemy.printHealth();
			System.out.println();
			
			System.out.println(TextColor.Yellow + "System: What would you like to do?");
			
			System.out.println("	attack - deal damage");
			System.out.println("	flee - flee to town (you will lose health)");
			System.out.println("	inventory - access your inventory");
			
			System.out.print(TextColor.Red + "Combat> " + TextColor.Default);
			String command = scanner.next().toLowerCase();
			
			switch (command)
			{
				case "attack":
					System.out.println("attack");
					break;
				case "flee":
					hasFled = true;
					playerCharacter.setInCombat(false);
					break;
				case "inventory":
					Inventory.handleInventory();
					break;
			}
			
		}
		
		if (hasFled) 
		{
			playerCharacter.takeDamage(enemy.getMaxDamage());
			enemy = null;
			
			System.out.println(TextColor.Yellow + "System: You flee to town, but not without loss.");
			playerCharacter.setLocation(PlayerLocation.Town);
			playerCharacter.printHealth();
			Town.handleTown();
		}
		else 
		{
			enemy = null;
		}
	}
}
