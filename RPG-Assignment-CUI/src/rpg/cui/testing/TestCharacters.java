/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.testing;

import java.util.HashSet;
import java.util.Scanner;
import rpg.cui.characters.PlayerCharacter;
import rpg.cui.characters.PlayerSave;
import rpg.cui.items.Item;

/**
 * Class for testing the Player Character stuff
 * @author Jonathan
 */
public class TestCharacters
{
	static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args)
	{
		System.out.print("Enter your name: ");
		String name = scanner.nextLine();
		
		System.out.print("Enter your level: ");
		int level = scanner.nextInt();	
		
		PlayerCharacter pc = new PlayerCharacter(name);
		for (int i = 0; i < level - 1; i++)
		{
			pc.addXP(pc.getLevel() * 50);
		}
		pc.setMaxHealth(level * 100.f);
		pc.setMaxMana(level * 100.f);
		pc.setMaxStamina(level * 100.f);
		
		System.out.println("Character '" + pc.getName() + "'");
		pc.takeDamage(50.f);
		
		PlayerSave.load(pc, "Vorlias");
		
		HashSet<Item> items = pc.getItems();
		
		for (Item item : items)
		{
			System.out.println(item.getName());
		}
		
	}
}
