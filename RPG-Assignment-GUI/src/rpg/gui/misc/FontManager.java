/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.misc;

import java.awt.FontFormatException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author Jonathan
 */
public class FontManager
{
    private static FontManager instance;
    private java.awt.Font javaFont;
    private TrueTypeFont balthazar;
    
    public TrueTypeFont getBalthazar()
    {
	return balthazar;
    }
    
    private FontManager() throws FontFormatException, IOException
    {
	javaFont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, ResourceLoader.getResourceAsStream("data/font/balthazar.ttf"));
	javaFont = javaFont.deriveFont(java.awt.Font.PLAIN, 18.f);
	balthazar = new TrueTypeFont(javaFont, true);	
    }
    
    public static FontManager getFontManager()
    {
	try
	{
	    if (instance == null)
		instance = new FontManager();
	    
	    return instance;
	}
	catch (FontFormatException | IOException ex)
	{
	    Logger.getLogger(FontManager.class.getName()).log(Level.SEVERE, null, ex);
	}
	
	return null;
    }
    
    @Override
    public FontManager clone() throws CloneNotSupportedException
    {
	throw new CloneNotSupportedException();
    }
}
