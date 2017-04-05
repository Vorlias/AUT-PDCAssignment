/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.game;

import java.util.Scanner;
import rpg.cui.characters.PlayerCharacter;
import rpg.cui.characters.PlayerSave;
import rpg.cui.misc.Miscellaneous;

/**
 * 
 * @author Nathan
 */
public class MainMenu
{
	static Scanner scanner = new Scanner(System.in);
	
	/**
	 * Ask the user what they would like to do
	 */
	public static void handleMainMenu()
	{
		System.out.print("1. Start new game.\n2. Load save game.\n3. Exit game.\nSelect an option > ");
		handleMenuOption();
	}
	
	/**
	 * Handle user input for selecting option
	 */
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
				System.out.print("System: Please only use the numbers displayed to select an option e.g. 1\nSelect an option > ");
				handleMenuOption();
				break;
		}
	}
	
	/**
	 * New game introduction
	 */
	private static void handleNewGame()
	{
		Miscellaneous.printBreak(41, '/');
		System.out.print("Stranger: Woah there! You over there, who are you?\nYou: ");
		String name = scanner.nextLine();
		System.out.println("Stranger: Ah I see, " + name + " is it? You shouldn't be walking around here without a weapon to protect you, here take this!");
		System.out.println("System: " + name + " received a dagger!");
		System.out.println("Stranger: There are powerful monsters lurking in this area so use that to protect yourself. I must go now, good luck out there " + name + "! *poof*");
		Miscellaneous.printBreak(41, '/');
		Game.setPlayerInTown(false);
		Game.setPlayerInForest(false);
		Game.startGame(name);
	}
	
	/**
	 * Load a previous game save
	 */
	private static void handleLoadGame()
	{
		System.out.print("System: Enter the name of the save file you want to load > ");
		String saveName = scanner.nextLine();
		PlayerSave.loadCharacter(saveName);
		Game.setPlayerInTown(false);
		Game.setPlayerInForest(false);
		Game.chooseNextLocation();
	}
}
