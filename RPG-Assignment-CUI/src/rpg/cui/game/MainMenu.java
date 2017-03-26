/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.game;

import java.util.Scanner;
import rpg.cui.characters.PlayerCharacter;

/**
 * @author Nathan
 */
public class MainMenu
{
	static Scanner scanner = new Scanner(System.in);
	
	public static void handleMainMenu()
	{
		System.out.print("1. Start new game.\n2. Load save game.\n3. Exit game.\nSelect an option >");
		handleMenuOption();
	}
	
	private static void handleMenuOption()
	{
		String menuItem = scanner.nextLine();
		switch(menuItem)
		{
			case "1":
				handleNewGame();
				break;
			case "2":
				handleLoadGame();
				break;
			case "3":
				System.exit(0);
				break;
			default:
				System.out.print("Please only use the numbers displayed to select an option e.g. 1\n>");
				handleMenuOption();
				break;
		}
	}
	
	private static void handleNewGame()
	{
		printBreak(41, '/');
		System.out.print("Stranger: Woah there! You over there, who are you?\nYou: ");
		String name = scanner.nextLine();
		PlayerCharacter pc = new PlayerCharacter(name);
		System.out.println("Stranger: Ah I see, " + name + " is it? You shouldn't be walking around here without a weapon to protect you, here take this!");
		//Handle player receiving dagger
		System.out.println("Stranger: There are powerful monsters lurking in this area so use that to protect yourself. I must go now, go luck out there " + name + "! *poof*");
		//Handle start game
	}
	
	private static void printBreak(int amount, char character)
	{
		for(int i = 0; i < amount; i++)
		{
			System.out.print(character);
		}
		System.out.println();
	}
	
	private static void handleLoadGame()
	{
		
	}
}
