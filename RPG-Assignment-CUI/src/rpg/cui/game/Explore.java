/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.game;

import java.util.Scanner;
import java.util.Random;
import rpg.cui.characters.PlayerLocation;
import rpg.cui.misc.Utility;

/**
 *
 * @author Nathan
 */
public class Explore
{
	static Scanner scanner = new Scanner(System.in);
	static Random random = new Random();
	
	/**
	 * 
	 */
	public static void handleExplore()
	{
		Utility.printBreak(41, '/');
		System.out.println("System: You have entered the forest of Kreahx.");
		Game.getPlayerCharacter().printStats();
		handleExploreOptions();
	}
	
	/**
	 * 
	 * @param locationValue 
	 */
	private static void handleExploreLocations(int locationValue)
	{
		switch(locationValue)
		{
			case 1:
				Combat.handleCombat();
				break;
			case 2:
				System.out.println("System: Hours pass by as you explore the forest, without any luck of finding any monsters or loot you begin to give up.");
				handleExploreOptions();
				break;
			case 3:
				System.out.println("System: While exploring the forest you stumble over a branch placed by a monster... get ready for combat!");
				Combat.handleCombat();
				break;
			case 4:
				System.out.println("System: The first few days pass by quickly as you explore the forest. You find yourself at the entrance to the forest having done nothing but walk a big loop.");
				handleExploreOptions();
				break;
			case 5:
				int checkLuck = random.nextInt(100);
				if(checkLuck < 15)
				{
					System.out.println("System: It's an ambush! You reach for your weapon and prepare for combat.");
					Combat.handleCombat();
				}
				System.out.println("System: You find 5 gold from a dead goblin corpse.");
				Game.getPlayerCharacter().setGold(5);
				handleExploreOptions();
				break;
		}
	}
	
	/**
	 * 
	 */
	private static void handleExploreOptions()
	{
		System.out.println("System: What would you like to do next?");
		System.out.print("\t1. Explore the Forest\n\t2. Open Inventory\n\t3. Leave Forest\n\t4. Save Game\n\t5. Exit Game\n> ");
		String nextExploreOption = scanner.nextLine();
		
		switch(nextExploreOption)
		{
			case "1":
				handleExploreLocations(random.nextInt(5));
				break;
			case "2":
				Inventory.handleInventory();
				break;
			case "3":
				System.out.println("System: You have left the forest of Kreahx.");
				Utility.printBreak(41, '/');
				Game.getPlayerCharacter().setLocation(PlayerLocation.Wilds);
				Game.chooseNextLocation();
				break;
			case "4":
				Game.handleSaveGame();
				handleExploreOptions();
				break;
			case "5":
				System.exit(0);
				break;
			default:
				System.out.print("System: Please only use the numbers displayed to select an option e.g. 1\nSelect an option > ");
				handleExploreOptions();
				break;
		}
	}
}
