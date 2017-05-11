/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.database;

import java.sql.Connection;

/**
 *
 * @author Jonathan
 */
public class GameDatabase
{
    private Connection connection;
    private static final String DATABASE_URL = 
	    "jdbc:derby://localhost:1527/ GameDB; create=true";
    
    private GameDatabase()
    {
	
    }
    
    private static GameDatabase instance;
    public static GameDatabase getDatabaseInstance()
    {
	if (instance == null)
	    instance = new GameDatabase();
	
	return instance;
    }
}
