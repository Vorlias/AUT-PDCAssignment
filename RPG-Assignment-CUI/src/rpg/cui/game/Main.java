/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.game;

import java.util.Scanner;
import rpg.cui.characters.PlayerCharacter;

/**
 *
 * @author Nathan
 */
public class Main
{
	static Scanner scanner = new Scanner(System.in);
	private static PlayerCharacter pc;
	
	/**
	 * Start the game
	 * @param name Character name for creation
	 */
	public static void startGame(String name)
	{
		pc = new PlayerCharacter(name);
		//Add basic dagger
		System.out.println("After the encounter with the strange person you find yourself at the entrance to a forest.");
		System.out.println("The sign reads 'Kreahx Forest - Beware of Monsters! Enter at own risk!'");
		pc.printStats();
		chooseNextLocation();
	}
	
	/**
	 * Ask player where they would like to go
	 */
	private static void chooseNextLocation()
	{
		System.out.println("System: What would you like to do next?");
		System.out.print("\t1. Go to Town\n\t2. Explore\n\t3. Save Game\n\t4. Exit Game\n> ");
		handleNextLocationOption();
	}
	
	/**
	 * Handle player input for selecting next location
	 */
	private static void handleNextLocationOption()
	{
		String nextLocation = scanner.nextLine();
		switch(nextLocation)
		{
			case "1":
				handleTown();
				break;
			case "2":
				handleExplore();
				break;
			case "3":
				pc.saveCharacter();
				System.out.println("Save successful!");
				chooseNextLocation();
				break;
			case "4":
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
	private static void handleTown()
	{
		System.out.println("System: You have entered the town of Tarrin.");
	}
	
	/**
	 * 
	 */
	private static void handleTownOptions()
	{
		
	}
	
	/**
	 * 
	 */
	private static void handleExplore()
	{
		System.out.println("System: You have entered the forest of Kreahx.");
	}
	
	/**
	 * 
	 */
	private static void handleExploreOptions()
	{
		
	}
}
