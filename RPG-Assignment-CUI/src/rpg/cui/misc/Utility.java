/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.misc;

import java.util.Random;

/**
 *
 * @author Nathan
 */
public class Utility
{
	static Random random = new Random();
	
	/**
	 * Print a specified amount of a character to console
	 * @param amount The amount to print
	 * @param character The character to print
	 */
	public static void printBreak(int amount, char character)
	{
		for(int i = 0; i < amount; i++)
		{
			System.out.print(character);
		}
		System.out.println();
	}
	
	/**
	 * Returns a random float between min and max
	 * @param min The minimum value
	 * @param max The maximum value
	 * @return The random number
	 */
	public static float randomFloat(float min, float max)
	{
		return random.nextFloat() * (max - min) + min;
	}	
	
	/**
	 * Returns a random integer between min and max
	 * @param min The minimum value
	 * @param max The maximum value
	 * @return The random number
	 */
	public static int randomInt(int min, int max)
	{
		return random.nextInt((max - min) + 1) + min;
	}
}
