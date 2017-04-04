package rpg.cui.game;

import rpg.cui.items.ItemDatabase;

/**
 *
 * @author Jonathan Holmes & Nathan Cross
 */
public class RPGAssignmentCUI
{
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) 
	{
		ItemDatabase.database = ItemDatabase.loadFromFile(ItemDatabase.DATABASE_FILE);
		MainMenu.handleMainMenu(); // Load main menu
	}
}