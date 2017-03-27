/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.misc;

/**
 *
 * @author Nate
 */
public class Miscellaneous
{
	/**
	 * Print a specified amount of a character to console
	 */
	public static void printBreak(int amount, char character)
	{
		for(int i = 0; i < amount; i++)
		{
			System.out.print(character);
		}
		System.out.println();
	}
}
