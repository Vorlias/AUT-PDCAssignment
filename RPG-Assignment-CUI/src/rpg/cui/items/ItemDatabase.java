/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.items;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * A database full of items that is loaded from a file
 * @author Jonathan
 */
public class ItemDatabase implements Serializable
{
	static final String DATABASE_FILE = "data/ItemDatabase.ser";
	
	private LinkedHashMap<Integer, Item> items = new LinkedHashMap<>();

	/**
	 * Adds an item to the database
	 * @param item The item to add
	 */
	public void insert(Item item)
	{
		items.put(items.size(), item);
	}
	
	public void update(int itemId, Item item)
	{
		items.put(itemId, item);
	}
	
	public Item getItemById(int id)
	{
		return items.get(id);
	}
	
	String returnString; // because java is stupid
	@Override
	public String toString()
	{
		returnString = "";
		items.forEach((key, value) -> {
			returnString += "" + key + "	| " + value + "\n";
		});
		
		return returnString;
	}
	
	@Deprecated
	public void fillWithTestData()
	{
		insert(new Weapon("Piss Water", 100));
		insert(new Weapon("Holy hand grenade", 100));
	}
	
	public static ItemDatabase loadFromFile(String file)
	{
		ItemDatabase db = null;
		
		try
		{
			try (FileInputStream fileIn = new FileInputStream(file); ObjectInputStream in = new ObjectInputStream(fileIn))
			{
				db = (ItemDatabase) in.readObject();
			}
		}
		catch (IOException | ClassNotFoundException i)
		{
			System.err.println(i.getMessage());
		}
		
		return db;
	}
	
	public void saveToFile(String file)
	{
		try 
		{
			try (FileOutputStream fileOut = new FileOutputStream(file); ObjectOutputStream out = new ObjectOutputStream(fileOut))
			{
				out.writeObject(this);
			}
		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	private ItemDatabase()
	{
	}
	
	public static ItemDatabase database = new ItemDatabase();
}