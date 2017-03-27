/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.items;

import java.io.File;
import java.util.Scanner;

/**
 * Program for adding/removing items from the database.
 * (For developers only)
 * @author Jonathan
 */
public class ItemDatabaseEditor
{
	static ItemDatabase db;
	static Scanner scanner;
	
	public static void userAddItem()
	{
		System.out.println("=== ADD ITEM ===");
		System.out.println("Options: weapon | consumable | cancel");
		System.out.print("o==[ItemDatabase> Create># ");
		
		String option = scanner.next();
		
		if ("weapon".equals(option.toLowerCase()))
		{
			scanner.nextLine();
			
			System.out.print("	Name: ");
			String name = scanner.nextLine();
			System.out.print("	Damage: ");
			int damage = scanner.nextInt();
			
			db.insert(new Weapon(name, damage));
			
			System.out.println("Added item '" + name + "' to database successfully.");
		}
		else if ("consumable".equals(option.toLowerCase()))
		{
			scanner.nextLine();
			
			System.out.print("	Name: ");		
			String name = scanner.nextLine();
			
			System.out.print("	Type (health | mana | stamina): ");
			String type = scanner.next();
			
			System.out.print("	Modifier: ");
			int modifier = scanner.nextInt();

			if (type.toLowerCase().equals("health"))
			{
				db.insert(new Consumable(name, Consumable.ConsumableType.Health, modifier));
				System.out.println("Added item '" + name + "' to database successfully.");
			}
			else if (type.toLowerCase().equals("mana"))
			{
				db.insert(new Consumable(name, Consumable.ConsumableType.Mana, modifier));
				System.out.println("Added item '" + name + "' to database successfully.");
			}		
			else if (type.toLowerCase().equals("stamina"))
			{
				db.insert(new Consumable(name, Consumable.ConsumableType.Stamina, modifier));
				System.out.println("Added item '" + name + "' to database successfully.");
			}
			else
			{
				System.err.println("Failed to add item '" + name + "' - Invalid type.");
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (new File(ItemDatabase.DATABASE_FILE).exists())
		{
			db = ItemDatabase.loadFromFile(ItemDatabase.DATABASE_FILE);
		}
		else 
		{
			db = ItemDatabase.database;
		}
		
		scanner = new Scanner(System.in);
		String action;
		
		do
		{
			System.out.println("Options: add | list | save | exit");
			System.out.print("o==[ItemDatabase># ");
			action = scanner.next();
			if (action.toLowerCase().equals("add"))
			{
				userAddItem();
			}
			else if (action.toLowerCase().equals("list"))
			{
				System.out.println("=== ITEMS ===================================================================");
				System.out.println("Name	| Type		| Item");
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println(db);
				System.out.println("=============================================================================");
			}
			else if (action.toLowerCase().equals("save"))
			{
				db.saveToFile(ItemDatabase.DATABASE_FILE);
				System.out.println("Saved to file.");
			}
		}
		while (!action.toLowerCase().equals("exit"));
	}
}
