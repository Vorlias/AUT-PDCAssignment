package rpg.gui;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import rpg.gui.misc.FontManager;
import rpg.gui.states.CharacterCreationState;
import rpg.gui.states.MenuState;
import rpg.gui.states.PlayView;
import rpg.gui.database.GameDatabase;
import rpg.gui.states.LoadSaveState;

/**
 *
 * @author Jonathan
 */
public class RPGGame extends StateBasedGame
{

    public static final int STATE_MENU = 0;
    public static final int STATE_CREATION = 1;
    public static final int STATE_GAME = 2;
    public static final int STATE_LOAD = 3;
    public static final int STATE_INVENTORY = 4;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int FPS = 60;
    public static final double VERSION = 0.1;

    public static final String TITLE = "RPG Game";
    public static PlayView playView;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
	FontManager.getFontManager().loadFontToId("balthazar", "data/font/balthazar.ttf");
	GameDatabase.getDatabaseInstance(); //Establish game database connection
	
	try
	{
	    AppGameContainer app;
	    app = new AppGameContainer(new RPGGame());

	    app.setDisplayMode(WIDTH, HEIGHT, false);
	    app.start();
	}
	catch (SlickException ex)
	{
	    Logger.getLogger(RPGGame.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    public RPGGame()
    {
	super(TITLE);
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException
    {
	playView = new PlayView(gc);
	
	this.addState(new MenuState());
	this.addState(playView);
	this.addState(new CharacterCreationState());
	this.addState(new LoadSaveState());

	this.enterState(STATE_MENU);
    }

}
