package com.practice.main.entities.menus;

import com.practice.main.entities.ObjectID;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;

import java.awt.*;

public abstract class MenuObject {
	
	protected float x, y;
	protected float width, height;
	protected ObjectID id;
	protected BasicGameState state;
	private Color backgroundColor = Color.BLACK;
	
	MenuObject(float x, float y, float width, float height, Color backgroundColor, ObjectID id, BasicGameState state) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.backgroundColor = backgroundColor;
		this.id = id;
		this.state = state;
	}
	
	public abstract void update();
	public abstract void render(Graphics g);
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void setPos(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setID(ObjectID id) {
		this.id = id;
	}
	
	public ObjectID getID() {
		return id;
	}
	
	public void setState(BasicGameState state) {
		this.state = state;
	}
	
	public BasicGameState getState() {
		return state;
	}
	
	public Shape getShape() {
		return new Rectangle(x, y, width, height);
	}
	
}
