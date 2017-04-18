package com.practice.main.entities.menus;

import com.practice.main.entities.ObjectID;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;

import java.awt.*;

/**
 * Created by rbell on 3/29/2017.
 */
public class TextMenu extends MenuObject {
	
	private String text;
	private Alignment horizontalAlignment = Alignment.CENTER;
	private Alignment verticalAlignment = Alignment.CENTER;
	private Object[] position;
	Graphics graphics;
	
	
	public TextMenu(float x, float y, float width, float height, Color backgroundColor, ObjectID id, BasicGameState state) {
		super(x, y, width, height, backgroundColor, id, state);
	}
	
	public enum Alignment {
		//Horizontal
		LEFT, RIGHT,
		
		//Vertical
		TOP, BOTTOM,
		
		//BOTH
		CENTER, CUSTOM, DEFAULT
		
		
		
	}
	
	/**
	 * Sets the text of the TextMenu object
	 * alignment is an enum to quickly and easily set the alignment of the text
	 * position is an optional variable(s) that allows users to customize the position
	 *
	 * If custom positioning is going to be applied then both alignment params must use RANDOM.
	 * The user must then specify either ONE or TWO float values or int values for the position param.
	 * For position the values are X position and Y position of the text.
	 * For custom positioning the values can either be a percentage or an exact position in the object.
	 * For example: An object is 100x100. If the float values .1f and .31f, the bottom left most pixel will be at coords
	 * 10 and 31.
	 * The user can instead choose to provide exact positions (relative to the object position) instead of a percentage.
	 * To do this simply pass two ints instead of two floats.
	 *
	 * The user can also pass DEFAULT in any field (other than String text) and the alignment will be set to CENTER.
	 *
	 * @param text The text to be assigned to the TextMenu
	 * @param horizontalAlignment The horizontal alignment of the text. Must use LEFT, RIGHT, or CUSTOM.
	 * @param verticalAlignment The vertical alignment of the text. Must use TOP, BOTTOM, or CUSTOM
	 * @param position An optional param that allows users to set a custom value for text positioning.
	 *
	 */
	public void setText(String text, Alignment horizontalAlignment, Alignment verticalAlignment, Object... position) {
		this.text = text;
		this.horizontalAlignment = horizontalAlignment;
		this.verticalAlignment = verticalAlignment;
		this.position = position;
		
		if(graphics != null) {
			//FontMetrics metrics = Graphics.getFontMetrics(graphics.getFont());
		}
		
		if(horizontalAlignment == Alignment.DEFAULT) {
			this.horizontalAlignment = Alignment.CENTER;
			
			
		} else if(horizontalAlignment == Alignment.LEFT) {
		
		} else if(horizontalAlignment == Alignment.RIGHT) {
		
		}
	
	}
	
	public void setFont(Font font) {
		//graphics.setFont(font);
	}
	
	@Override
	public void update() {
	
	}
	
	@Override
	public void render(Graphics g) {
		if(graphics != null) {
			g.setFont(graphics.getFont());
		}
		graphics = g;
		
		
	}
}
