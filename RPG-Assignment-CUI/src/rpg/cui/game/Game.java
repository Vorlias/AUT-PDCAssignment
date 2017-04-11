/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.game;

import java.util.Scanner;
import rpg.cui.characters.PlayerCharacter;
import rpg.cui.characters.PlayerLocation;
import rpg.cui.characters.PlayerSave;

/**
 *
 * @author Nathan
 */
public class Game
{
	static Scanner scanner = new Scanner(System.in);
	private static PlayerCharacter playerCharacter;
	private static final int DAGGER_ID = 0;
	
	/**
	 * Start the game
	 * @param name Character name for creation
	 */
	public static void startGame(String name)
	{
		playerCharacter = new PlayerCharacter(name);
		playerCharacter.addItemById(DAGGER_ID); // Adding dagger to player inventory
		playerCharacter.equipItemById(DAGGER_ID); // Equiping dagger for player
		System.out.println("After the encounter with the strange person you find yourself at the entrance to a forest.");
		System.out.println("The sign reads 'Kreahx Forest - Beware of Monsters! Enter at own risk!'");
		playerCharacter.printStats();
		playerCharacter.setLocation(PlayerLocation.Forest);
		chooseNextLocation();
	}
	
	/**
	 * Ask player where they would like to go
	 */
	public static void chooseNextLocation()
	{
		System.out.println("System: What would you like to do next?");
		System.out.print("\t1. Go to Town\n\t2. Enter the Forest\n\t3. Open Inventory\n\t4. Save Game\n\t5. Exit Game\nYou: > ");
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
				playerCharacter.setLocation(PlayerLocation.Town);
				Town.handleTown();
				break;
			case "2":
				playerCharacter.setLocation(PlayerLocation.Forest);
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
		}
	}
	
	/**
	 * Returns the playerCharacter
	 * @return playerChracter
	 */
	public static PlayerCharacter getPlayerCharacter()
	{
		return playerCharacter;
	}
	
	/**
	 * Sets the playerCharacter object
	 * @param playerCharacter the playerCharacter object
	 */
	public static void setPlayerCharacter(PlayerCharacter playerCharacter)
	{
		Game.playerCharacter = playerCharacter;
	}
	
	/**
	 * Check if player is in town
	 * @return true if player in town
	 */
	public static boolean isPlayerInTown()
	{
		return playerCharacter.getLocation() == PlayerLocation.Town;
	}

	/**
	 * Check if player is in forest
	 * @return true if player in forest
	 */
	public static boolean isPlayerInForest()
	{
		return playerCharacter.getLocation() == PlayerLocation.Forest;
	}
	
	/**
	 * Saves the game
	 */
	public static void handleSaveGame()
	{
		PlayerSave.save(playerCharacter);
		System.out.println("Save successful!");
	}
}
