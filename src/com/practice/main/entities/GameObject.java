package com.practice.main.entities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;

public abstract class GameObject {

	protected float x, y;
	protected float width, height;
	protected float velX, velY;
	protected ObjectID id;
	protected BasicGameState state;
	
	public GameObject(float x, float y, float width, float height, ObjectID id, BasicGameState state) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
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
	
	public void setVelX(float velX) {
		this.velX = velX;
	}
	
	public void setVelY(float velY) {
		this.velY = velY;
	}
	
	public void setVel(float velX, float velY) {
		this.velX = velX;
		this.velY = velY;
	}
	
	public float getVelX() {
		return velX;
	}
	
	public float getVelY() {
		return velY;
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
