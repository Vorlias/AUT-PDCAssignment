/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.states;

import java.awt.FontFormatException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import rpg.gui.RPGGame;
import rpg.gui.characters.PlayerCharacter;
import rpg.gui.database.GameDatabase;
import rpg.gui.misc.Vector2;
import rpg.gui.ui.Button;
import rpg.gui.ui.GUILayoutGroup;
import rpg.gui.ui.TextInput;

/**
 * Handle character creation state
 * @author Jonathan
 */
public class CharacterCreationState extends BasicGameState
{

    private Image backgroundImage;
    private GUILayoutGroup characterCreationLayout;
    private TextInput characterName;
    //private Button createButton;

    // Get state id
    @Override
    public int getID()
    {
	return RPGGame.STATE_CREATION;
    }

    public static final String CREATE_BUTTON = "Create";
    public static final String MENU_BUTTON = "Menu";
    
    public static final int ITEM_DAGGER = 1;

    // Inital state
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
	backgroundImage = new Image("data/images/background_menu.png");
	characterCreationLayout = new GUILayoutGroup(gc, GUILayoutGroup.LayoutType.Grid);
	characterCreationLayout.setItemPadding(new Vector2(0, 5));

	characterCreationLayout.addLabels(18.f, "Character Name");
	characterCreationLayout.addLabels(12.f, "It can only contain letter A-Z and spaces.");
	characterName = new TextInput(gc);
	characterCreationLayout.addInputs(characterName);

	characterCreationLayout.addButtons(Button.Size.Large, "Create", "Menu");
	characterCreationLayout.setLocation(300, 70);

	characterCreationLayout.onButtonPress((Button button) ->
	{
	    if (MENU_BUTTON.equals(button.getText()))
	    {
		sbg.enterState(RPGGame.STATE_MENU);
	    }
	    else if (CREATE_BUTTON.equals(button.getText()))
	    {
		if (characterName.getText().matches("([A-z]+|[A-z]+ [A-z\\-]+)"))
		{
		    PlayerCharacter character = new PlayerCharacter(characterName.getText());
		    character.addItem(GameDatabase.getItem(ITEM_DAGGER)); // Add dagger
		    character.equipItemById(1);
		    
		    RPGGame.playView.setup(character);
		    sbg.enterState(RPGGame.STATE_GAME);
		}

	    }
	});
    }

    // Leave state
    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException
    {
	characterCreationLayout.setEnabled(false);
    }

    // Enter state
    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException
    {
	characterCreationLayout.setEnabled(true);
    }

    // Render state
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException
    {
	grphcs.drawImage(backgroundImage, 0, 0);
	characterCreationLayout.render(gc, grphcs);
    }

    // Update state
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException
    {
	characterCreationLayout.update();
    }

}
