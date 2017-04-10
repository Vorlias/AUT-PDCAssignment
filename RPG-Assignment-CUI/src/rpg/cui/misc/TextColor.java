/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.cui.misc;

/**
 * For handling console foreground colouring (of text)
 * @author Jonathan
 */
public enum TextColor
{
	Default(0),
	Black(30),
	Red(31),
	Green(32),
	Yellow(33),
	Blue(34),
	Purple(35),
	Cyan(36),
	White(37);

	@Override
	public String toString()
	{
		return "\u001B["+index+"m";
	}

	int index;

	TextColor(int index)
	{
		this.index = index;
	}
}
