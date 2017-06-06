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
    private String testName;
    private int testLevel;
    private int testGold;
    private float testHealth;
    
    public CharacterTest()
    {
	
    }

    public String getTestName()
    {
	return testName;
    }

    public void setTestName(String testName)
    {
	this.testName = testName;
    }

    public int getTestLevel()
    {
	return testLevel;
    }

    public void setTestLevel(int testLevel)
    {
	this.testLevel = testLevel;
    }

    public int getTestGold()
    {
	return testGold;
    }

    public void setTestGold(int testGold)
    {
	this.testGold = testGold;
    }

    public float getTestHealth()
    {
	return testHealth;
    }

    public void setTestHealth(float testHealth)
    {
	this.testHealth = testHealth;
    }
    
    @Test
    public void testSetName()
    {
	CharacterTest testChar = new CharacterTest();
	testChar.setTestName("Test");
	
	String expectedName = "Test";
	String actualName = testChar.getTestName();
	
	assertEquals(expectedName, actualName);
    }
    
    @Test
    public void testSetLevel()
    {
	CharacterTest testChar = new CharacterTest();
	testChar.setTestLevel(10);
	
	int expectedLevel = 10;
	int actualLevel = testChar.getTestLevel();
	
	assertEquals(expectedLevel, actualLevel);
    }
    
    @Test
    public void testSetGold()
    {
	CharacterTest testChar = new CharacterTest();
	testChar.setTestGold(123);
	
	int expectedGold = 123;
	int actualGold = testChar.getTestGold();
	
	assertEquals(expectedGold, actualGold);
    }
    
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
