/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.characters;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nate
 */
public class CharacterTest
{
    private String testName; // Character name
    private int testLevel; // Character level
    private int testGold; // Character gold
    private float testHealth; // Character health
    
    /**
     * Character constructor
     */
    public CharacterTest()
    {
	
    }

    /**
     * Gets the name of the character
     * @return the character test name
     */
    public String getTestName()
    {
	return testName;
    }

    /**
     * Sets the name of the character
     * @param testName the name to set to the character
     */
    public void setTestName(String testName)
    {
	this.testName = testName;
    }

    /**
     * Gets the level of the character
     * @return the character level
     */
    public int getTestLevel()
    {
	return testLevel;
    }

    /**
     * Sets the level of the character
     * @param testLevel the level to set the character to
     */
    public void setTestLevel(int testLevel)
    {
	this.testLevel = testLevel;
    }

    /**
     * Gets the gold the character has
     * @return the amount of gold
     */
    public int getTestGold()
    {
	return testGold;
    }

    /**
     * Set the gold the character has
     * @param testGold the gold to set to the character
     */
    public void setTestGold(int testGold)
    {
	this.testGold = testGold;
    }

    /**
     * Get the health the character has
     * @return the amount of health
     */
    public float getTestHealth()
    {
	return testHealth;
    }

    /**
     * Sets the health the character has
     * @param testHealth the amount of health to set
     */
    public void setTestHealth(float testHealth)
    {
	this.testHealth = testHealth;
    }
    
    /**
     * Test setting character name
     */
    @Test
    public void testSetName()
    {
	CharacterTest testChar = new CharacterTest();
	testChar.setTestName("Test");
	
	String expectedName = "Test";
	String actualName = testChar.getTestName();
	
	assertEquals(expectedName, actualName);
    }
    
    /**
     * Test setting character level
     */
    @Test
    public void testSetLevel()
    {
	CharacterTest testChar = new CharacterTest();
	testChar.setTestLevel(10);
	
	int expectedLevel = 10;
	int actualLevel = testChar.getTestLevel();
	
	assertEquals(expectedLevel, actualLevel);
    }
    
    /**
     * Test setting character gold
     */
    @Test
    public void testSetGold()
    {
	CharacterTest testChar = new CharacterTest();
	testChar.setTestGold(123);
	
	int expectedGold = 123;
	int actualGold = testChar.getTestGold();
	
	assertEquals(expectedGold, actualGold);
    }
    
    /**
     * Test setting character health
     */
    @Test
    public void testSetHealth()
    {
	CharacterTest testChar = new CharacterTest();
	testChar.setTestHealth(321.0f);
	
	float expectedHealth = 321.0f;
	float actualHealth = testChar.getTestHealth();
	
	assertEquals(expectedHealth, actualHealth, 0.0);
    }
}
