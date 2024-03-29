/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.game;

import java.util.Random;
import java.util.Scanner;
import rpg.cui.characters.Monster;
import rpg.cui.characters.MonsterType;
import rpg.cui.characters.PlayerCharacter;
import rpg.cui.characters.PlayerLocation;
import rpg.cui.items.Consumable;
import rpg.cui.items.ConsumableType;
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
		Random random = new Random();
		playerCharacter.setInCombat(true);
		
		while (enemy.isAlive() && !hasFled && playerCharacter.isAlive())
		{
			playerCharacter.printHealth();
			System.out.print("\t");
			enemy.printHealth();
			System.out.println();
			
			System.out.println(TextColor.Yellow + "System: What would you like to do?");
			
			System.out.println("	attack - attack the target with your " + playerCharacter.getEquippedWeapon().getName() + "");
			System.out.println("	flee - flee to town (you will lose health)");
			System.out.println("	inventory - access your inventory");
			
			System.out.print(TextColor.Red + "Combat> " + TextColor.Default);
			String command = scanner.next().toLowerCase();
			
			switch (command)
			{
				case "attack":
					
					playerCharacter.attack(enemy);
					enemy.attack(playerCharacter);
					
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
		
		playerCharacter.setInCombat(false);
		if (hasFled) 
		{
			playerCharacter.takeDamage(enemy.getMaxDamage());
			enemy = null;
			
			System.out.println(TextColor.Yellow + "System: You flee to town, but not without loss.");
			playerCharacter.setLocation(PlayerLocation.Town);
			playerCharacter.printHealth();
			Town.handleTown();
		}
		else if (!playerCharacter.isAlive())
		{
			System.out.println(TextColor.Red + "System: Unfortunately " + playerCharacter.getName() + " dies to " + enemy.getName() + ", a tragic death.");
			System.out.println(TextColor.Red + "** GAME OVER **");
		}
		else 
		{
			System.out.println(TextColor.Green + "System: You successfully defeated " + enemy.getName());
			playerCharacter.addXP(enemy.getLevel() * 10);
			
			int goldToAdd = random.nextInt(9) + 1;
			playerCharacter.setGold(playerCharacter.getGold() + goldToAdd);
			
			System.out.println(TextColor.Green + "System: You find " + goldToAdd + " from the enemy corpse.");
			
			enemy = null;
			Explore.handleExplore();
		}
	}
}
