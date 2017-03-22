/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.testing;

import java.util.Scanner;
import rpg.cui.characters.PlayerCharacter;

/**
 * Class for testing the Player Character stuff
 * @author Jonathan
 */
public class TestCharacters
{
	static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args)
	{
		System.out.println("Enter your name: ");
		String name = scanner.nextLine();
		
		PlayerCharacter pc = new PlayerCharacter("Jonathan");
		
		System.out.println("Character '" + pc.getName() + "'");
		
		pc.addXP(1000);
		
		System.out.println("Level: " + pc.getLevel());
		System.out.println("Health: " + pc.getHealth() + "/" + pc.getMaxHealth());
		System.out.println("Mana: " + pc.getMana() + "/" + pc.getMaxMana());
		System.out.println("Stamina: " + pc.getStamina() + "/" + pc.getMaxStamina());
		
	}
}
