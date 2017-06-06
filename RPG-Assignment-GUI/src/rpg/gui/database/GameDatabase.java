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
import rpg.gui.items.Item;
import rpg.gui.items.Consumable;
import rpg.gui.items.ConsumableType;
import rpg.gui.items.Weapon;

/**
 * Handle database setup and fetching information
 * @author Jonathan & Nathan
 */
public class GameDatabase
{

    private static Connection conn; // New connection
    private static final String DATABASE_URL = "jdbc:derby:rpgdb;create=true"; // The database url
    private static final String DATABASE_USERNAME = "rpg"; // The database username
    private static final String DATABASE_PASSWORD = "rpg"; // The database password

    /**
     * Database constructor
     */
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

    // Database instance
    private static GameDatabase instance;

    // Create a new database instance if there isnt one
    public static GameDatabase getDatabaseInstance()
    {
	if (instance == null)
	{
	    instance = new GameDatabase();
	}

	return instance;
    }

    // Check if database tables exist
    public static void checkDatabaseTables()
    {
	try
	{
	    DatabaseMetaData metadata = conn.getMetaData();
	    ResultSet rs = metadata.getTables(null, null, "ITEMS", null);
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
    
    private static int currentItemIndex = 1;
    
    /**
     * Returns the Item count in the database
     * @return The item count
     */
    public static int getItemCount()
    {
	ResultSet rs;	
	try
	{
	    Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	    String weapon = "SELECT COUNT(*) FROM ITEMS";
	    
	    rs = statement.executeQuery(weapon);
	    rs.beforeFirst();
            while(rs.next())
            {
		return rs.getInt(1);
            }
	}
	catch(SQLException ex)
	{
	    Logger.getLogger(GameDatabase.class.getName()).log(Level.SEVERE, null, ex);
	}
	
	return 0;
    }
    
    /**
     * Gets the item from the database with the specified id
     * @param id The id
     * @return The item
     */
    public static Item getItem(int id)
    {
	ResultSet rs;
	
	try
	{
	    Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	    String weapon = "SELECT * FROM ITEMS WHERE ITEMID = " + id;
	    
	    rs = statement.executeQuery(weapon);
	    rs.beforeFirst();
            while(rs.next())
            {
		Item item;
		String type = rs.getString("ITEMTYPE");
		String name = rs.getString("ITEMNAME");
		int itemId = rs.getInt("ITEMID");
		
		if ("Weapon".equals(type))
		{
		    ResultSet weaponInfo = statement.executeQuery("SELECT WEAPONDAMAGE FROM itemsweapon WHERE ItemId = " + id);
		    weaponInfo.beforeFirst();
		   
		    if (weaponInfo.next())
		    {
			int damage = weaponInfo.getInt("WEAPONDAMAGE");
		    
			item = new Weapon(name, damage);
			item.setId(itemId);
			return item;		
		    }
		    else
		    {
			throw new SQLException("ItemType is weapon, but no weapon result found for item " + id);
		    }
		}
		else if ("Consumable".equals(type))
		{
		    ResultSet consumableInfo = statement.executeQuery("SELECT CONSUMABLETYPE,CONSUMABLEMODIFIER FROM itemsconsumable WHERE ItemId = " + id);
		    consumableInfo.beforeFirst();
		   
		    if (consumableInfo.next())
		    {
			int modifier = consumableInfo.getInt("CONSUMABLEMODIFIER");
		    
			item = new Consumable(name, ConsumableType.valueOf(consumableInfo.getString("CONSUMABLETYPE")), modifier);
			item.setId(itemId);
			return item;		
		    }
		    else
		    {
			throw new SQLException("ItemType is cpmsumable, but no consumable result found for item " + id);
		    }		    
		}
            }
	}
	catch(SQLException ex)
	{
	    Logger.getLogger(GameDatabase.class.getName()).log(Level.SEVERE, null, ex);
	}
	
	return null;
    }
    
    // Insert weapon into database
    private static void insertWeapon(Statement statement, String name, int damage) throws SQLException
    {
	statement.executeUpdate("INSERT INTO items (ItemId, ItemName, ItemType) VALUES(" + currentItemIndex + ", '" + name + "', 'Weapon')");
	statement.executeUpdate("INSERT INTO itemsweapon (ItemId, WeaponDamage) VALUES(" + currentItemIndex + ", " + damage + ")");
	
	currentItemIndex++;
    }
    
    // Insert consumable into database
    private static void insertConsumable(Statement statement, String name, ConsumableType consumableType, int modifier) throws SQLException
    {
	statement.executeUpdate("INSERT INTO items (ItemId, ItemName, ItemType) VALUES(" + currentItemIndex + ", '" + name + "', 'Consumable')");
	statement.executeUpdate("INSERT INTO itemsconsumable (ItemId, ConsumableType, ConsumableModifier) VALUES(" + currentItemIndex + ", '" + consumableType.toString() + "', " + modifier +")");

	currentItemIndex++;
    }
    
    // Create database tables
    public static void createTables()
    {
	try
	{
	    Statement statement = conn.createStatement();
	    
	    String itemTableStatement = "CREATE TABLE items ("
		    + "ItemId INTEGER DEFAULT 1 NOT NULL,"
		    + "ItemName VARCHAR(50) NOT NULL,"
		    + "ItemType VARCHAR(16) DEFAULT 'Weapon' NOT NULL,"
		    + "PRIMARY KEY (ItemId))";
	    String consumableTableStatement = "CREATE TABLE itemsconsumable ("
		    + "ItemId INTEGER NOT NULL,"
		    + "ConsumableModifier INTEGER DEFAULT 0 NOT NULL,"
		    + "ConsumableType VARCHAR(16) DEFAULT 'Health' NOT NULL,"
		    + "FOREIGN KEY (ItemId) REFERENCES items(ItemId))";
	    String weaponTableStatement = "CREATE TABLE itemsweapon ("
		    + "ItemId INTEGER NOT NULL,"
		    + "WeaponDamage INTEGER DEFAULT 0 NOT NULL,"
		    + "FOREIGN KEY (ItemId) REFERENCES items(ItemId))";
	    
	    statement.executeUpdate(itemTableStatement);
	    statement.executeUpdate(consumableTableStatement);
	    statement.executeUpdate(weaponTableStatement);

	    statement.close();
	    System.out.println("Consumables and Weapons Tables Created.");
	}
	catch (SQLException ex)
	{
	    Logger.getLogger(GameDatabase.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
    // Insert database table data
    public static void insertTableData()
    {
	try
	{
	    Statement statement = conn.createStatement();
	    
	    insertWeapon(statement, "Dagger", 10);
	    insertWeapon(statement, "Shortsword", 20);
	    insertWeapon(statement, "Longsword", 25);
	    insertWeapon(statement, "Broadsword", 30);
	    insertWeapon(statement, "Mace", 20);
	    insertWeapon(statement, "Bow", 15);
	    insertWeapon(statement, "Crossbow", 20);
	    insertWeapon(statement, "Staff", 20);
	    insertConsumable(statement, "Health Potion", ConsumableType.Health, 50);
	    insertConsumable(statement, "Mana Potion", ConsumableType.Mana, 50);
	    
	    System.out.println("Consumables and Weapons Data Inserted.");
	    statement.close();
	}
	catch(SQLException ex)
	{
	    Logger.getLogger(GameDatabase.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
}
