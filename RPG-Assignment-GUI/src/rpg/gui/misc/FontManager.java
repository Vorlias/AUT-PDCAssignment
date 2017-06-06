/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.gui.misc;

import java.awt.FontFormatException;
import java.io.IOException;
import java.util.HashMap;
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
    
    private final HashMap<String, java.awt.Font> fonts = new HashMap<>();
    
    /**
     * Gets the font as a TrueTypeFont
     * @param fontId The font's id
     * @param size The size of the font
     * @return 
     */
    public TrueTypeFont getTrueTypeFont(String fontId, float size)
    {
	java.awt.Font font = fonts.get(fontId).deriveFont(java.awt.Font.PLAIN, size);
	return new TrueTypeFont(font, true);
    }
    
    /**
     * Gets the specified font at the id
     * @param fontId The font's id
     * @return The font
     */
    public java.awt.Font getFont(String fontId)
    {
	return fonts.get(fontId);
    }
    
    /**
     * Loads a font to the specified id
     * @param id The id of the font
     * @param fontFile The file location of the font
     */
    public void loadFontToId(String id, String fontFile)
    {
	try
	{
	    java.awt.Font newFont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, ResourceLoader.getResourceAsStream(fontFile));
	    fonts.put(id, newFont);
	}
	catch (FontFormatException | IOException ex)
	{
	    Logger.getLogger(FontManager.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
    private FontManager() throws FontFormatException, IOException
    {
	//javaFont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, ResourceLoader.getResourceAsStream("data/font/balthazar.ttf"));
	//javaFont = javaFont.deriveFont(java.awt.Font.PLAIN, 18.f);
	//balthazar = new TrueTypeFont(javaFont, true);	
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
