/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jonathan
 */
public class GameDatabase
{
    private Connection connection;
    private static final String DATABASE_URL = 
	    "jdbc:derby://localhost:1527/ GameDB; create=true";
    
    private static final String DATABASE_USERNAME = "RPGGame";
    private static final String DATABASE_PASSWORD = "x09348GJpR5oeiLLn";
    
    private GameDatabase()
    {
	try
	{
	    connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
	    Statement statement = connection.createStatement();
	    
	}
	catch (SQLException ex)
	{
	    Logger.getLogger(GameDatabase.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
    private static GameDatabase instance;
    public static GameDatabase getDatabaseInstance()
    {
	if (instance == null)
	    instance = new GameDatabase();
	
	return instance;
    }
}
