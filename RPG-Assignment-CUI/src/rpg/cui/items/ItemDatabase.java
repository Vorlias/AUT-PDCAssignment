/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.items;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A database full of items that is loaded from a file
 *
 * @author Jonathan
 */
public class ItemDatabase implements Serializable
{

	public static final String DATABASE_FILE = "data/ItemDatabase.data";

	private LinkedHashMap<Integer, Item> items = new LinkedHashMap<>();

	/**
	 * Adds an item to the database
	 *
	 * @param item The item to add
	 */
	public void insert(Item item)
	{
		items.put(items.size(), item);
	}

	/**
	 * Updates the item at the index
	 *
	 * @param itemId The item id
	 * @param item The item
	 */
	public void update(int itemId, Item item)
	{
		items.put(itemId, item);
	}

	/**
	 * Get the item by it's ID
	 *
	 * @param id The item ID
	 * @return The Item with the specified id
	 */
	public Item getItemById(int id)
	{
		return items.get(id);
	}

	String returnString; // because java is stupid

	@Override
	public String toString()
	{
		returnString = "";
		items.forEach((key, value) ->
		{
			returnString += "" + key + "	| " + value + "\n";
		});

		return returnString;
	}
	
	static final String REGEX_ATTRIBUTE_STRING = "([A-Za-z]+)\\s*=\\s*\"(.*)\"";
	static final String REGEX_ATTRIBUTE_NUMBER = "([A-Za-z]+)\\s*=\\s*([0-9.]+)";
	static final String REGEX_ATTRIBUTE = " ([A-Za-z]+)\\s*=\\s*(.*);";

	/** 
	 * Gets an attribute pair from the text
	 * Used for parsing item database attributes
	 * @param text The text to parse and get the attributes from
	 * @param pattern The pattern to match
	 * @return The key and value
	 */
	private static String[] getAttributePair(String text, String pattern)
	{
		if (text.matches(pattern))
		{
			Pattern attributePattern = Pattern.compile(pattern);
			Matcher valueMatcher = attributePattern.matcher(text);
			if (valueMatcher.find())
			{
				String name = valueMatcher.group(1);
				String value = valueMatcher.group(2);
				return new String[] { name, value };
			}
		}

		return null;
	}
	
	/**
	 * Parses the next entry in the item database file
	 * @param scanner The scanner to use to parse it
	 * @param db The database to insert results into
	 */
	private static void parseEntry(Scanner scanner, ItemDatabase db)
	{
		scanner.next(); // remove 'item'
		String type = scanner.next(); // get declared type
		Item item = null; // Item definition
		
		switch (type)
		{
			case "Weapon":
				item = new Weapon();
				break;
			case "Consumable":
				item = new Consumable();
				break;
		}
		
		if (item != null)
		{
			HashMap<String, Object> attributeMap = new HashMap<>();
			
			String nextLine = scanner.nextLine().trim();

			String[] attributes = nextLine.split(";");

			for (String attribute : attributes)
			{		
				attribute = attribute.trim();

				String[] stringAttributePair = getAttributePair(attribute, REGEX_ATTRIBUTE_STRING);
				String[] numberAttributePair = getAttributePair(attribute, REGEX_ATTRIBUTE_NUMBER);
				if (stringAttributePair != null)
				{
					attributeMap.put(stringAttributePair[0], stringAttributePair[1]);
				}
				else if (numberAttributePair != null)
				{
					attributeMap.put(numberAttributePair[0], Integer.parseInt(numberAttributePair[1]) );
				}
				else 
				{
					System.err.println("Invalid syntax [0x2]: '" + nextLine +"'");
				}				
			}
			
			item.setAttributes(attributeMap);
			db.insert(item);
		}
		else 
		{
			System.err.println("Invalid type [0x3] '" + type + "'");
		}
	}
	
	/**
	 * Load the ItemDatabase from a file
	 *
	 * @param file The file to load the database from
	 * @return The ItemDatabase
	 */
	public static ItemDatabase loadFromFile(String file)
	{
		ItemDatabase db = new ItemDatabase();
		Scanner input;
		try
		{
			input = new Scanner(new File(file));
			while (input.hasNextLine())
			{
				if (input.hasNext("item"))
				{
					parseEntry(input, db);
				}
				else
				{
					System.err.println("Syntax error [0x1]: " + input.nextLine());
				}
			}
		}
		catch (FileNotFoundException ex)
		{
			Logger.getLogger(ItemDatabase.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return db;
	}

	/**
	 * Saves the ItemDatabase to a file
	 *
	 * @param file The file to save the database to
	 */
	public void saveToFile(String file)
	{

		try (PrintWriter pw = new PrintWriter(file))
		{
			for (int i = 0; i < items.size(); i++)
			{
				pw.println(items.get(i).encode());
			}

			pw.flush();
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
