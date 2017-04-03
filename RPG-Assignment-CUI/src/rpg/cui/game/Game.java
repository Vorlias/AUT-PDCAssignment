/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.game;

import java.util.Scanner;
import rpg.cui.characters.PlayerCharacter;
import rpg.cui.items.ItemDatabase;

/**
 *
 * @author Nathan
 */
public class Game
{
	static Scanner scanner = new Scanner(System.in);
	private static PlayerCharacter pc;
	private static boolean isPlayerInTown = false;
	private static final int DAGGER_ID = 0;
	
	/**
	 * Start the game
	 * @param name Character name for creation
	 */
	public static void startGame(String name)
	{
		ItemDatabase.database = ItemDatabase.loadFromFile(ItemDatabase.DATABASE_FILE);
		pc = new PlayerCharacter(name);
		pc.addItem(ItemDatabase.database.getItemById(DAGGER_ID)); // Adding dagger to player inventory
		pc.equip(ItemDatabase.database.getItemById(DAGGER_ID)); // Equiping dagger for player
		System.out.println("After the encounter with the strange person you find yourself at the entrance to a forest.");
		System.out.println("The sign reads 'Kreahx Forest - Beware of Monsters! Enter at own risk!'");
		pc.printStats();
		chooseNextLocation();
	}
	
	/**
	 * Ask player where they would like to go
	 */
	public static void chooseNextLocation()
	{
		System.out.println("System: What would you like to do next?");
		System.out.print("\t1. Go to Town\n\t2. Explore\n\t3. Open Inventory\n\t4. Save Game\n\t5. Exit Game\n> ");
		handleNextLocationOption();
	}
	
	/**
	 * Handle player input for selecting next location
	 */
	private static void handleNextLocationOption()
	{
		String nextOption = scanner.nextLine();
		switch(nextOption)
		{
			case "1":
				setPlayerInTown(true);
				Town.handleTown();
				break;
			case "2":
				Explore.handleExplore();
				break;
			case "3":
				Inventory.handleInventory();
				break;
			case "4":
				handleSaveGame();
				chooseNextLocation();
				break;
			case "5":
				System.exit(0);
				break;
			default:
				System.out.print("System: Please only use the numbers displayed to select an option e.g. 1\nSelect an option > ");
				handleNextLocationOption();
				break;
		}
	}
	
	/**
	 * 
	 */
	public static PlayerCharacter getPlayerCharacter()
	{
		return pc;
	}
	
	/**
	 * 
	 */
	public static boolean getPlayerInTown()
	{
		return isPlayerInTown;
	}
	
	/**
	 * 
	 */
	public static boolean setPlayerInTown(boolean state)
	{
		return isPlayerInTown = state;
	}
	
	/**
	 * 
	 */
	public static void handleSaveGame()
	{
		pc.saveCharacter();
		System.out.println("Save successful!");
		if(!isPlayerInTown)
		{
			chooseNextLocation();
		}
		else
		{
			Town.handleTown();
		}
	}
}
