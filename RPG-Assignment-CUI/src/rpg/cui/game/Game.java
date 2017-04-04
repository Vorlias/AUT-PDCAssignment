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
	static boolean isPlayerInTown = false;
	static boolean isPlayerInForest = false;
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
		pc.equipItemById(DAGGER_ID); // Equiping dagger for player
		System.out.println("After the encounter with the strange person you find yourself at the entrance to a forest.");
		System.out.println("The sign reads 'Kreahx Forest - Beware of Monsters! Enter at own risk!'");
		pc.printStats();
		setIsPlayerInTown(false);
		setIsPlayerInForest(false);
		chooseNextLocation();
	}
	
	/**
	 * Ask player where they would like to go
	 */
	public static void chooseNextLocation()
	{
		System.out.println("System: What would you like to do next?");
		System.out.print("\t1. Go to Town\n\t2. Enter the Forest\n\t3. Open Inventory\n\t4. Save Game\n\t5. Exit Game\n> ");
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
				setIsPlayerInTown(true);
				setIsPlayerInForest(false);
				Town.handleTown();
				break;
			case "2":
				setIsPlayerInTown(false);
				setIsPlayerInForest(true);
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
	 * @return 
	 */
	public static PlayerCharacter getPlayerCharacter()
	{
		return pc;
	}

	/**
	 * 
	 * @return 
	 */
	public static boolean getIsPlayerInTown()
	{
		return isPlayerInTown;
	}
	
	/**
	 * 
	 * @param isPlayerInTown 
	 */
	public static void setIsPlayerInTown(boolean isPlayerInTown)
	{
		Game.isPlayerInTown = isPlayerInTown;
	}

	/**
	 * 
	 * @return 
	 */
	public static boolean getIsPlayerInForest()
	{
		return isPlayerInForest;
	}

	/**
	 * 
	 * @param isPlayerInForest 
	 */
	public static void setIsPlayerInForest(boolean isPlayerInForest)
	{
		Game.isPlayerInForest = isPlayerInForest;
	}
	
	/**
	 * 
	 */
	public static void handleSaveGame()
	{
		pc.saveCharacter();
		System.out.println("Save successful!");
	}
}
