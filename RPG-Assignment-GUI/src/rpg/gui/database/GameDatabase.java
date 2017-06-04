/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
	    checkDatabaseTables();
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

    public static void checkDatabaseTables()
    {
	try
	{
	    DatabaseMetaData metadata = conn.getMetaData();
	    ResultSet rs = metadata.getTables(null, null, "CONSUMABLES", null);
	    if(rs.next())
	    {
		System.out.println("Tables already exist.");
	    }
	    else
	    {
		System.out.println("Tables do not exist, creating now.");
		createTables();
		System.out.println("Inserting data into created tables.");
		insertTableData();
	    }
	}
	catch(SQLException ex)
	{
	    Logger.getLogger(GameDatabase.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
    public static void createTables()
    {
	try
	{
	    Statement statement = conn.createStatement();
	    
	    String consumableTable = "Consumables";
	    String createConsumableTable = "CREATE TABLE " + consumableTable
		    + " (ITEMID INTEGER DEFAULT 1  NOT NULL,"
		    + " ITEMNAME VARCHAR(50) NOT NULL,"
		    + " ITEMMODIFIER INTEGER DEFAULT 0  NOT NULL,"
		    + " ITEMTYPE VARCHAR(16) DEFAULT 'Consumable'  NOT NULL,"
		    + " ITEMMODIFIERTYPE VARCHAR(16) DEFAULT 'Health'  NOT NULL,"
		    + " PRIMARY KEY (ITEMID))";
	    statement.executeUpdate(createConsumableTable);

	    String weaponTable = "Weapons";
	    String createWeaponTable = "CREATE TABLE " + weaponTable
		    + " (ITEMID INTEGER DEFAULT 1  NOT NULL,"
		    + " ITEMNAME VARCHAR(50) NOT NULL,"
		    + " ITEMDAMAGE INTEGER DEFAULT 0  NOT NULL,"
		    + " ITEMTYPE VARCHAR(16) DEFAULT 'Weapon'  NOT NULL,"
		    + " PRIMARY KEY (ITEMID))";
	    statement.executeUpdate(createWeaponTable);

	    statement.close();
	    System.out.println("Consumables and Weapons Tables Created.");
	}
	catch (SQLException ex)
	{
	    Logger.getLogger(GameDatabase.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
    public static void insertTableData()
    {
	try
	{
	    Statement statement = conn.createStatement();
	    String firstTableName = "Consumables";
	    String secondTableName = "Weapons";
	    
	    String firstTableFirstInsert = "INSERT INTO " + firstTableName
		    + " (ITEMID, ITEMNAME, ITEMMODIFIER, ITEMTYPE, ITEMMODIFIERTYPE) VALUES (1, 'Health Potion', 50, 'Consumable', 'Health')";
	    String firstTableSecondInsert = "INSERT INTO " + firstTableName
		    + " (ITEMID, ITEMNAME, ITEMMODIFIER, ITEMTYPE, ITEMMODIFIERTYPE) VALUES (2, 'Mana Potion', 50, 'Consumable', 'Mana')";
	    
	    statement.executeUpdate(firstTableFirstInsert);
	    statement.executeUpdate(firstTableSecondInsert);
	    
	    String secondTableFirstInsert = "INSERT INTO " + secondTableName
		    + " (ITEMID, ITEMNAME, ITEMDAMAGE, ITEMTYPE) VALUES (1, 'Dagger', 10, 'Weapon')";
	    String secondTableSecondInsert = "INSERT INTO " + secondTableName
		    + " (ITEMID, ITEMNAME, ITEMDAMAGE, ITEMTYPE) VALUES (2, 'Shortsword', 20, 'Weapon')";
	    String secondTableThirdInsert = "INSERT INTO " + secondTableName
		    + " (ITEMID, ITEMNAME, ITEMDAMAGE, ITEMTYPE) VALUES (3, 'Longsword', 25, 'Weapon')";
	    String secondTableFourthInsert = "INSERT INTO " + secondTableName
		    + " (ITEMID, ITEMNAME, ITEMDAMAGE, ITEMTYPE) VALUES (4, 'Broadsword', 30, 'Weapon')";
	    String secondTableFifthInsert = "INSERT INTO " + secondTableName
		    + " (ITEMID, ITEMNAME, ITEMDAMAGE, ITEMTYPE) VALUES (5, 'Mace', 20, 'Weapon')";
	    String secondTableSixthInsert = "INSERT INTO " + secondTableName
		    + " (ITEMID, ITEMNAME, ITEMDAMAGE, ITEMTYPE) VALUES (6, 'Bow', 15, 'Weapon')";
	    String secondTableSeventhInsert = "INSERT INTO " + secondTableName
		    + " (ITEMID, ITEMNAME, ITEMDAMAGE, ITEMTYPE) VALUES (7, 'Crossbow', 20, 'Weapon')";
	    String secondTableEighthInsert = "INSERT INTO " + secondTableName
		    + " (ITEMID, ITEMNAME, ITEMDAMAGE, ITEMTYPE) VALUES (8, 'Staff', 20, 'Weapon')";
	
	    statement.executeUpdate(secondTableFirstInsert);
	    statement.executeUpdate(secondTableSecondInsert);
	    statement.executeUpdate(secondTableThirdInsert);
	    statement.executeUpdate(secondTableFourthInsert);
	    statement.executeUpdate(secondTableFifthInsert);
	    statement.executeUpdate(secondTableSixthInsert);
	    statement.executeUpdate(secondTableSeventhInsert);
	    statement.executeUpdate(secondTableEighthInsert);
	    
	    System.out.println("Consumables and Weapons Data Inserted.");
	    statement.close();
	}
	catch(SQLException ex)
	{
	    Logger.getLogger(GameDatabase.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
    public static int getWeaponDamage(int weaponId)
    {
	ResultSet rs = null;
	int weaponDamage = 0;
	
	System.out.println("Querying Database for Weapon Damage...");
	
	try
	{
	    Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	    String weapon = "SELECT ITEMDAMAGE FROM WEAPONS WHERE ITEMID = " + weaponId;
	    
	    rs = statement.executeQuery(weapon);
	    rs.beforeFirst();
            while(rs.next())
            {
                weaponDamage = rs.getInt(1);           
            }
	}
	catch(SQLException ex)
	{
	    Logger.getLogger(GameDatabase.class.getName()).log(Level.SEVERE, null, ex);
	}
	
	return weaponDamage;
    }
    
    public static int getConsumableModifier(int consumableId)
    {
	ResultSet rs = null;
	int consumableModifier = 0;
	
	System.out.println("Querying Database for Consumable Modifier...");
	
	try
	{
	    Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	    String consumable = "SELECT ITEMMODIFIER FROM CONSUMABLES WHERE ITEMID = " + consumableId;
	    
	    rs = statement.executeQuery(consumable);
	    rs.beforeFirst();
            while(rs.next())
            {
                consumableModifier = rs.getInt(1);           
            }
	}
	catch(SQLException ex)
	{
	    Logger.getLogger(GameDatabase.class.getName()).log(Level.SEVERE, null, ex);
	}
	
	return consumableModifier;
    }
}
