/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.game;

import java.util.Scanner;
import rpg.cui.characters.PlayerLocation;
import rpg.cui.misc.Utility;

/**
 *
 * @author Nathan
 */
public class Town
{
	static Scanner scanner = new Scanner(System.in);
	
	/**
	 * 
	 */
	public static void handleTown()
	{
		Utility.printBreak(41, '/');
		System.out.println("System: You have entered the town of Tarrin.");
		Game.getPlayerCharacter().printStats();
		System.out.println("System: You see store signs along the street, what would you like to do?");
		System.out.print("\t1. Visit Blacksmith\n\t2. Visit Potion Store\n\t3. Open Inventory\n\t4. Leave Town\n\t5. Save Game\n\t6. Exit Game\n> ");
		handleTownOptions();
	}
	
	/**
	 * 
	 */
	private static void handleTownOptions()
	{
		String townOption = scanner.nextLine();
		
		switch(townOption)
		{
			case "1":
				handleBlacksmith();
				break;
			case "2":
				handlePotionStore();
				break;
			case "3":
				Inventory.handleInventory();
				break;
			case "4":
				System.out.println("System: You have left the town of Tarrin.");
				Utility.printBreak(41, '/');
				Game.getPlayerCharacter().setLocation(PlayerLocation.Wilds);
				Game.chooseNextLocation();
				break;
			case "5":
				Game.handleSaveGame();
				handleTownOptions();
				break;
			case "6":
				System.exit(0);
				break;
			default:
				System.out.print("System: Please only use the numbers displayed to select an option e.g. 1\nSelect an option > ");
				handleTownOptions();
				break;
		}
	}
	
	/**
	 * 
	 */
	private static void handleBlacksmith()
	{
		System.out.println("Lorhamri: Welcome to Lorhamri Orcbuster's smithery!");
		System.out.println("Lorhamri: What would you like to do player?");
		System.out.print("\t1. Purchase a Weapon\n\t2. Leave Blacksmith\n> ");
		handleBlacksmithOptions();
	}
	
	/**
	 * 
	 */
	private static void handleBlacksmithOptions()
	{
		String blacksmithOption = scanner.nextLine();
		
		switch(blacksmithOption)
		{
			case "1":
				handleWeaponPurchase();
				break;
			case "2":
				handleTown();
				break;
			default:
				System.out.print("System: Please only use the numbers displayed to select an option e.g. 1\nSelect an option > ");
				handleBlacksmithOptions();
				break;
		}
	}
	
	/**
	 * 
	 */
	private static void handleWeaponPurchase()
	{
		
	}
	
	/**
	 * 
	 */
	private static void handlePotionStore()
	{
		System.out.println("Hocru: Welcome to Hocru's magical potion store!");
		System.out.println("Hocru: What would you like to do player?");
		System.out.print("\t1. Purchase Potions\n\t2. Leave Potion Store\n> ");
		handlePotionStoreOptions();
	}
	
	/**
	 * 
	 */
	private static void handlePotionStoreOptions()
	{
		String potionStoreOption = scanner.nextLine();
		
		switch(potionStoreOption)
		{
			case "1":
				handlePotionPurchase();
				break;
			case "2":
				handleTown();
				break;
			default:
				System.out.print("System: Please only use the numbers displayed to select an option e.g. 1\nSelect an option > ");
				handlePotionStoreOptions();
				break;
		}
	}
	
	/**
	 * 
	 */
	private static void handlePotionPurchase()
	{
		
	}
}
