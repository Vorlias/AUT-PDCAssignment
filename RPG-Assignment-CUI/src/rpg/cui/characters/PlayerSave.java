/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.characters;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import rpg.cui.items.Item;

/**
 *
 * @author Jonathan
 */
public class PlayerSave
{
	PlayerCharacter character;
	
	private PlayerSave(PlayerCharacter character)
	{
		this.character = character;
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
			
			writer.flush();
		}
		catch (FileNotFoundException ex)
		{
			Logger.getLogger(PlayerSave.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	/**
	 * Saves the character
	 * @param character The character to save
	 */
	public static void save(PlayerCharacter character)
	{
		PlayerSave playerSave = new PlayerSave(character);
		playerSave.writeToFile(character.getName() + ".save");
	}
	
	/**
	 * Loads the character
	 * @param character The character to load to
	 * @param saveName The name of the character to load
	 */
	public static void load(PlayerCharacter character, String saveName)
	{
		
	}
}
