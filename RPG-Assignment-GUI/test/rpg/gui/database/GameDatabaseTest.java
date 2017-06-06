/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.database;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rpg.gui.items.Item;

/**
 *
 * @author Jonathan
 */
public class GameDatabaseTest
{
    
    public GameDatabaseTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of getItem method, of class GameDatabase.
     */
    @Test
    public void testGetItem()
    {
	//System.out.println("getDatabaseInstance");
	//GameDatabase expResult = null;
	//GameDatabase result = GameDatabase.getDatabaseInstance();
	//assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	//fail("The test case is a prototype.");
	GameDatabase db = GameDatabase.getDatabaseInstance();
	System.out.println("getItem");
	Item result = GameDatabase.getItem(1); // Should be dagger
	System.out.println("result is " + result);
	assertEquals(result.getName(), "Dagger");
    }
    
    /**
     * Tests the item list
     */
    @Test
    public void testItemList()
    {
	GameDatabase db = GameDatabase.getDatabaseInstance();	
	System.out.println("getting item list");
	
	int count = GameDatabase.getItemCount();
	assert(count > 0);
	
	for (int i = 1; i <= count; i++)
	{
	    System.out.println(GameDatabase.getItem(i));
	}
    }
    
}
