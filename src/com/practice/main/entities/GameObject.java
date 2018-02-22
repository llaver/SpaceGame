package com.practice.main.entities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;

public abstract class GameObject {
	
	protected float x, y;
	
	protected float width, height;
	protected float velX, velY;
	protected float health;
	protected ObjectID id;
	protected BasicGameState state;
	
	protected GameObject(float x, float y, float width, float height, float health, ObjectID id, int idNum, BasicGameState state) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.health = health;
		this.id = id;
		this.state = state;
	}
	
	public abstract void update(int delta);
	
	public abstract void render(Graphics g);
	
	public abstract void collision(GameObject object);
	
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
	
	public float getWidth() {
		return width;
	}
	
	public void setWidth(float width) {
		this.width = width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public void setHeight(float height) {
		this.height = height;
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
	
	public void setHealth(float health) {
		this.health = health;
	}
	
	public float getHealth() {
		return health;
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
	
	public boolean intersects(GameObject object) {
		return object != null && this.getShape().intersects(object.getShape());
	}
	
	public boolean isEqual(Object obj) {
		return obj instanceof GameObject &&
			x == ((GameObject) obj).getX() &&
			y == ((GameObject) obj).getY() &&
			width == ((GameObject) obj).getWidth() &&
			height == ((GameObject) obj).getY() &&
			velX == ((GameObject) obj).getY() &&
			velY == ((GameObject) obj).getY() &&
			health == ((GameObject) obj).getY() &&
			id == ((GameObject) obj).getID() &&
			state == ((GameObject) obj).getState();
	}
}
