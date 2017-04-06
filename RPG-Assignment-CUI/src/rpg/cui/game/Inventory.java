/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.game;

import java.util.ArrayList;
import java.util.Scanner;
import rpg.cui.characters.PlayerCharacter;
import rpg.cui.items.Item;

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
		PlayerCharacter character = Game.getPlayerCharacter();
		ArrayList<Item> items = character.getItems();
		
		System.out.println("=== Inventory ===");
		for (int i = 0; i < items.size(); i++)
		{
			Item item = items.get(i);
			System.out.println((i + 1) + ". " + item.getName());
		}
		
		handleInventoryOptions();
	}
	
	/**
	 * 
	 */
	private static void handleInventoryOptions()
	{
		
	}
}
