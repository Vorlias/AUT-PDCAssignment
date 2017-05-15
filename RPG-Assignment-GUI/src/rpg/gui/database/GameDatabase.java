/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jonathan
 */
public class GameDatabase
{

    private static Connection conn;
    private static final String DATABASE_URL = "jdbc:derby://localhost:1527/rpgdb;create=true";
    private static final String DATABASE_USERNAME = "rpg";
    private static final String DATABASE_PASSWORD = "rpg";

    private GameDatabase()
    {
	try
	{
	    conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
	    System.out.println(DATABASE_URL + " connected.");

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
	{
	    instance = new GameDatabase();
	}

	return instance;
    }
}
