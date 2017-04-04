/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.game;

import java.util.Scanner;
import rpg.cui.characters.PlayerCharacter;
import rpg.cui.characters.PlayerSave;

/**
 *
 * @author Nathan
 */
public class Game
{
	static Scanner scanner = new Scanner(System.in);
	private static PlayerCharacter pc;
	static boolean playerInTown = false;
	static boolean playerInForest = false;
	private static final int DAGGER_ID = 0;
	
	/**
	 * Start the game
	 * @param name Character name for creation
	 */
	public static void startGame(String name)
	{
		pc = new PlayerCharacter(name);
		pc.addItemById(DAGGER_ID); // Adding dagger to player inventory
		pc.equipItemById(DAGGER_ID); // Equiping dagger for player
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
				setPlayerInTown(true);
				Town.handleTown();
				break;
			case "2":
				setPlayerInForest(true);
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
	 * @param pc 
	 */
	public static void setPlayerCharacter(PlayerCharacter pc)
	{
		Game.pc = pc;
	}
	
	/**
	 * 
	 * @return 
	 */
	public static boolean isPlayerInTown()
	{
		return playerInTown;
	}
	
	/**
	 * 
	 * @param playerInTown 
	 */
	public static void setPlayerInTown(boolean playerInTown)
	{
		Game.playerInTown = playerInTown;
	}

	/**
	 * 
	 * @return 
	 */
	public static boolean isPlayerInForest()
	{
		return playerInForest;
	}

	/**
	 * 
	 * @param playerInForest 
	 */
	public static void setPlayerInForest(boolean playerInForest)
	{
		Game.playerInForest = playerInForest;
	}
	
	/**
	 * 
	 */
	public static void handleSaveGame()
	{
		PlayerSave.save(pc);
		System.out.println("Save successful!");
	}
}
