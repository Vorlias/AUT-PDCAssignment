package rpg.gui;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Jonathan
 */
public class RPGGame extends StateBasedGame
{

    public static final int STATE_MENU = 0;
    public static final int STATE_GAME = 1;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int FPS = 60;
    public static final double VERSION = 0.1;
    
    public static final String TITLE = "RPG Game";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
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
	
    }

}