/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.game;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import rpg.cui.characters.PlayerCharacter;
import rpg.cui.items.Item;
import rpg.cui.items.Consumable;
import rpg.cui.items.Weapon;

/**
 *
 * @author Nathan
 */
public class Inventory
{

	static Scanner scanner = new Scanner(System.in);

	/**
	 * 
	 */
	public static void handleInventory()
	{

		handleInventoryOptions();
	}

	/**
	 *
	 */
	private static void handleInventoryOptions()
	{
		PlayerCharacter character = Game.getPlayerCharacter();
		ArrayList<Item> items = character.getItems();

		System.out.println("System: You open your inventory, these are the items you have:");

		System.out.println("\t-- Your Inventory --");
		for (int i = 0; i < items.size(); i++)
		{
			Item item = items.get(i);
			System.out.println("\t   " + (i + 1) + ". " + item.getName());
		}

		System.out.println("System: What would you like to do?");
		System.out.println("	use <index> - equip the item");
		System.out.println("	close - close your inventory");

		String action = "";
		do
		{
			try
			{
				System.out.print("Inventory> ");
				action = scanner.next().toLowerCase();
				switch (action)
				{
					case "use":
						int id = scanner.nextInt() - 1;
						if (id >= 0 && id < items.size())
						{
							Item item = items.get(id);
							if (item != null)
							{
								if (item instanceof Weapon)
								{
									character.equipItem(item);
									System.out.println("Equipped " + item.getName());
								}
								else if (item instanceof Consumable)
								{
									item.use(character);
									System.out.println("Used " + item.getName());
								}
							}
						}

						break;
					case "close":
						break;
					default:
						System.out.println("Unknown command '" + action + "'");
				}
			}
			catch (InputMismatchException e)
			{
				System.out.println("Invalid input entered, try again.");
			}
		}
		while (!action.equals("close"));

		System.out.println("System: You close your inventory.");

		if (!Game.getPlayerCharacter().inCombat())
		{
			if (Game.isPlayerInForest())
			{
				Explore.handleExplore();
			}
			else if (Game.isPlayerInTown())
			{
				Town.handleTown();
			}
			else
			{
				Game.chooseNextLocation();
			}
		}
	}
}
