/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A weighted random generator
 * @author Jonathan
 * @param <T> The random object
 */
public class WeightedRandom<T>
{
	/**
	 * A weighted random item
	 */
	private class WeightedRandomItem
	{
		private T item;
		private float weight;
		
		public WeightedRandomItem(T item, float weight)
		{
			this.item = item;
			this.weight = weight;
		}
		
		public float getWeight()
		{
			return weight;
		}
		
		public T getItem()
		{
			return item;
		}
	}
	
	private final Random random = new Random();
	private static float maxWeight;
	private static float totalWeight;
	private ArrayList<WeightedRandomItem> weightedItems = new ArrayList<>();
	
	/**
	 * Adds a weighted item
	 * @param item The item to add
	 * @param weight The weight (chance) of the item
	 */
	public void addItem(T item, float weight)
	{
		weightedItems.add(new WeightedRandomItem(item, weight));
		totalWeight += weight;
	}
	
	/**
	 * Gets the next weighted random item
	 * @return The next item
	 */
	public T next()
	{
		float total = 0;
		
		// Calculate total weight
		for (WeightedRandomItem item : weightedItems)
		{
			total += item.weight;
		}
		
		float result = 0;
		
		if (total != 0)
		{
			result = Utility.randomFloat(0, total);
		}
		
		// Calculate the next item
		for (WeightedRandomItem item : weightedItems)
		{
			if (result < item.getWeight())
			{
				return item.getItem();
			}
			
			result -= item.getWeight();
		}
		
		throw new NullPointerException("No weighted random items set");
	}
}
