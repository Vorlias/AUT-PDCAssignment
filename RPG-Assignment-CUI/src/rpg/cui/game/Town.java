/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.game;

import java.util.Scanner;
import rpg.cui.characters.PlayerCharacter;
import rpg.cui.characters.PlayerLocation;
import rpg.cui.misc.Utility;

/**
 *
 * @author Nathan
 */
public class Town
{
	private static final int HEALTH_POTION_ID =  8;
	static Scanner scanner = new Scanner(System.in);
	static PlayerCharacter player = Game.getPlayerCharacter();
	
	/**
	 * Handles entering the town
	 */
	public static void handleTown()
	{
		Utility.printBreak(41, '/');
		System.out.println("System: You have entered the town of Tarrin.");
		Game.getPlayerCharacter().printStats();
		handleTownOptions();
	}
	
	/**
	 * Handles the player selecting an option
	 */
	private static void handleTownOptions()
	{
		System.out.println("System: You see store signs along the street, what would you like to do?");
		System.out.print("\t1. Visit Blacksmith\n\t2. Visit Potion Store\n\t3. Open Inventory\n\t4. Leave Town\n\t5. Save Game\n\t6. Exit Game\nYou: > ");
		
		String townOption = scanner.nextLine();
		
		switch(townOption)
		{
			case "1":
				System.out.println("Lorhamri: Welcome to Lorhamri Orcbuster's smithery!");
				handleBlacksmith();
				break;
			case "2":
				System.out.println("Hocru: Welcome to Hocru's magical potion store!");
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
		}
	}
	
	/**
	 * Handles entering the blacksmith shop
	 */
	private static void handleBlacksmith()
	{
		System.out.println("Lorhamri: What would you like to do player?");
		System.out.print("\t1. Purchase a Weapon\n\t2. Leave Blacksmith\n> ");
		handleBlacksmithOptions();
	}
	
	/**
	 * Handles the player selecting a blacksmith shop option
	 */
	private static void handleBlacksmithOptions()
	{
		int blacksmithOption = scanner.nextInt();
		
		switch(blacksmithOption)
		{
			case 1:
				System.out.println("Lorhamri: What weapon would you like to purchase player?");
				System.out.println("System: \n\t1. Dagger (5 Gold)\n\t2. Shortsword (15 Gold)\n\t3. Longsword (30 Gold)\n\t4. Broadsword (50 Gold)\n\t5. Mace (15 Gold)\n\t6. Bow (10 Gold)\n\t7. Crossbow (15 Gold)\n\t8. Staff (15 Gold)\n\t9. Exit Weapon Purchasing");
				handleWeaponPurchase();
				break;
			case 2:
				handleTown();
				break;
			default:
				System.out.print("System: Please only use the numbers displayed to select an option e.g. 1\nSelect an option > ");
				handleBlacksmithOptions();
		}
	}
	
	/**
	 * Handles the player purchasing a weapon
	 */
	private static void handleWeaponPurchase()
	{
		System.out.print("You: > ");
		int purchaseWeapon = scanner.nextInt();
		
		switch(purchaseWeapon)
		{
			case 1:
				if(player.getGold() >= 5)
					handleSuccessfulWeaponPurchase(purchaseWeapon);
				else
					handleFailedWeaponPurchase();
				break;
			case 2:
				if(player.getGold() >= 15)
					handleSuccessfulWeaponPurchase(purchaseWeapon);
				else
					handleFailedWeaponPurchase();
				break;
			case 3:
				if(player.getGold() >= 30)
					handleSuccessfulWeaponPurchase(purchaseWeapon);
				else
					handleFailedWeaponPurchase();
				break;
			case 4:
				if(player.getGold() >= 50)
					handleSuccessfulWeaponPurchase(purchaseWeapon);
				else
					handleFailedWeaponPurchase();
				break;
			case 5:
				if(player.getGold() >= 15)
					handleSuccessfulWeaponPurchase(purchaseWeapon);
				else
					handleFailedWeaponPurchase();
				break;
			case 6:
				if(player.getGold() >= 10)
					handleSuccessfulWeaponPurchase(purchaseWeapon);
				else
					handleFailedWeaponPurchase();
				break;
			case 7:
				if(player.getGold() >= 15)
					handleSuccessfulWeaponPurchase(purchaseWeapon);
				else
					handleFailedWeaponPurchase();
				break;
			case 8:
				if(player.getGold() >= 15)
					handleSuccessfulWeaponPurchase(purchaseWeapon);
				else
					handleFailedWeaponPurchase();
				break;
			case 9:
				System.out.println("Lorhamri: Maybe you'll buy something next time eh?");
				handleBlacksmith();
				break;
			default:
				System.out.print("System: Please only use the numbers displayed to select an option e.g. 1");
				handleWeaponPurchase();
		}
	}
	
	/**
	 * Handle player successfully purchasing selected weapon
	 * @param weaponPurchased weapon chosen for purchase
	 */
	private static void handleSuccessfulWeaponPurchase(int weaponPurchased)
	{
		System.out.println("System: Successfully purchased the weapon, item has been added to player inventory.");
		player.addItemById(weaponPurchased - 1);
		handleBlacksmith();
	}
	
	/**
	 * Handle player not having enough gold to purchase selected weapon
	 */
	private static void handleFailedWeaponPurchase()
	{
		System.out.println("System: You do not have enough gold to purchase that weapon.");
		handleBlacksmith();
	}
	
	/**
	 * Handles the player entering the potion store
	 */
	private static void handlePotionStore()
	{
		System.out.println("Hocru: What would you like to do player?");
		System.out.print("\t1. Purchase Potions\n\t2. Leave Potion Store\n> ");
		handlePotionStoreOptions();
	}
	
	/**
	 * Handles the player selecting a potion store option
	 */
	private static void handlePotionStoreOptions()
	{
		String potionStoreOption = scanner.nextLine();
		
		switch(potionStoreOption)
		{
			case "1":
				System.out.println("Hocru: We're currently out of mana potions but would you be interested in health potions?");
				handlePotionPurchase();
				break;
			case "2":
				handleTown();
				break;
			default:
				System.out.print("System: Please only use the numbers displayed to select an option e.g. 1\nSelect an option > ");
				handlePotionStoreOptions();
		}
	}
	
	/**
	 * Handles the player purchasing a potion
	 */
	private static void handlePotionPurchase()
	{
		System.out.println("System: Purchase a health potion for 10 gold, 'yes' or 'no'?");
		System.out.print("You: > ");
		String purchasePotion = scanner.nextLine();
		
		switch(purchasePotion)
		{
			case "yes":
				if(player.getGold() >= 10)
				{
					System.out.println("System: Successfully purchased a health potion, item has been added to player inventory.");
					player.addItemById(HEALTH_POTION_ID);
					player.setGold(player.getGold() - 10);
				}
				else
				{
					System.out.println("System: You do not have enough gold to purchase a health potion.");
				}
				handlePotionStore();
				break;
			case "no":
				handlePotionStore();
				break;
			default:
				System.out.println("Please only type 'yes' or 'no'.");
				handlePotionPurchase();
		}
	}
}
