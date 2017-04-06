/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.characters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import rpg.cui.game.Game;
import rpg.cui.items.Item;
import rpg.cui.items.Weapon;

/**
 *
 * @author Jonathan
 */
public class PlayerSave
{
	PlayerCharacter character;
	Scanner inputScanner;
	
	private PlayerSave(PlayerCharacter character)
	{
		this.character = character;
	}
	
	/**
	 * Parses a string attribute
	 * E.g. 'Name Test'
	 * @param attribute The attribute's name
	 * @param scanner The scanner parsing the line
	 */
	private void parseStringAttribute(String attribute, Scanner scanner)
	{
		String value = scanner.nextLine().trim();
		if (attribute.equals("Name"))
		{
			character.setName(value);
		}
	}
	
	/**
	 * Parses an integer array attribute
	 * E.g. 'Inventory 0 1 2 3'
	 * @param attribute The attribute's name
	 * @param scanner The scanner parsing the line
	 */
	private void parseIntArray(String attribute, Scanner scanner)
	{
		ArrayList<Integer> theArray = new ArrayList<>();
		while (scanner.hasNextInt())
		{
			theArray.add(scanner.nextInt());
		}
		
		if (attribute.equals("Inventory"))
		{
			for (int i : theArray)
			{
				character.addItemById(i);
			}
		}
	}
	
	/**
	 * Parses a constrained float attribute
	 * E.g. 'Health 50.0 100.0'
	 * @param attribute The attribute's name
	 * @param scanner The scanner parsing the line
	 */
	private void parseConstrainedFloatAttribute(String attribute, Scanner scanner)
	{
		float value = scanner.nextFloat();
		float maxValue = scanner.nextFloat();
		
		switch (attribute)
		{
			case "Health":
				character.setMaxHealth(maxValue);
				character.setHealth(value);
				break;
			case "Mana":
				character.setMaxMana(maxValue);
				character.setMana(value);
				break;
			case "Stamina":
				character.setMaxStamina(maxValue);
				character.setStamina(value);
				break;
			default:
				break;
		}
	}
	
	/**
	 * Parses an integer attribute
	 * E.g. 'Gold 100'
	 * @param attribute The attribute's name
	 * @param scanner The scanner parsing the line
	 */
	private void parseIntAttribute(String attribute, Scanner scanner)
	{
		int value = scanner.nextInt();
		
		switch (attribute)
		{
			case "Gold":
				character.setGold(value);
				break;
			case "XP":
				character.addXP(value);
				break;
			case "EquippedWeapon":
				character.equipItemById(value);
				break;
			case "Level":
				character.setLevel(value);
				break;
			default:
				break;
		}
	}
	
	/**
	 * Parses a save file line
	 * @param line The line to parse
	 */
	private void parseLine(String line)
	{
		Scanner lineScanner = new Scanner(line);
		String attribName = lineScanner.next();
		
		switch (attribName)
		{
			case "Name":
				parseStringAttribute(attribName, lineScanner);
				break;
			case "Gold":
			case "XP":
			case "Level":
			case "EquippedWeapon":
				parseIntAttribute(attribName, lineScanner);
				break;
			case "Health":
			case "Mana":
			case "Stamina":
				parseConstrainedFloatAttribute(attribName, lineScanner);
				break;
			case "Inventory":
				parseIntArray(attribName, lineScanner);
				break;
		}
	}
	
	/**
	 * Loads the character from a file
	 * @param file The file path
	 */
	public void loadFromFile(String file)
	{
		try
		{
			inputScanner = new Scanner(new File(file));
			while (inputScanner.hasNextLine())
			{
				parseLine(inputScanner.nextLine());
			}
			
			inputScanner.close();
		}
		catch (FileNotFoundException ex)
		{
			Logger.getLogger(PlayerSave.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	/**
	 * Write the character to a file
	 * @param file The file path
	 */
	public void writeToFile(String file)
	{
		try
		{
			PrintWriter writer = new PrintWriter(file);
			writer.println("Name " + character.getName());
			writer.println("Health " + character.getHealth() + " " + character.getMaxHealth());
			writer.println("Mana " + character.getMana() + " " + character.getMaxMana());
			writer.println("Stamina " + character.getStamina() + " " + character.getMaxStamina());
			writer.println("Gold " + character.getGold());
			writer.println("XP " + character.getXP());
			writer.println("Level " + character.getLevel());
			
			// Now we print the inventory
			writer.print("Inventory ");
			
			for (Item item : character.getItems())
			{
				if (item.getId() != Item.INVALID_ID)
				{
					writer.print(item.getId() + " ");
				}
			}
			writer.println();
			
			Weapon equippedWeapon = character.getEquippedWeapon();
			if (equippedWeapon != null && equippedWeapon.getId() != Item.INVALID_ID)
				writer.println("EquippedWeapon " + equippedWeapon.getId());
			
			writer.flush();
			writer.close();
		}
		catch (FileNotFoundException ex)
		{
			Logger.getLogger(PlayerSave.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	/**
	 * Get all the available player saves
	 * @return A list of the player saves
	 */
	public static String[] getPlayerSaveList()
	{
		ArrayList<String> saveFiles = new ArrayList<>();
		
		File folder = new File("playersaves");
		File[] fileList = folder.listFiles();
		for (File file : fileList)
		{
			if (file.isFile() && file.getName().endsWith(".save"))
			{
				saveFiles.add(file.getName().replace(".save", ""));
			}
		}
		
		return saveFiles.toArray(new String[0]);
	}
	
	/**
	 * Saves the character
	 * @param character The character to save
	 */
	public static void save(PlayerCharacter character)
	{
		PlayerSave playerSave = new PlayerSave(character);
		playerSave.writeToFile("playersaves/" + character.getName().toLowerCase() + ".save");
	}
	
	/**
	 * Loads a character into the game
	 * @param saveName The name of the save to load
	 */
	public static void loadCharacter(String saveName)
	{
		PlayerCharacter character = new PlayerCharacter();
		PlayerSave.load(character, saveName);
		Game.setPlayerCharacter(character);
	}
	
	/**
	 * Loads the character
	 * @param character The character to load to
	 * @param saveName The name of the character to load
	 */
	public static void load(PlayerCharacter character, String saveName)
	{
		PlayerSave playerSave = new PlayerSave(character);
		playerSave.loadFromFile("playersaves/" + saveName.toLowerCase() + ".save");
	}
}
