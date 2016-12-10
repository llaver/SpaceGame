package com.practice.main.entities;

import java.util.LinkedList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;

public class Handler {

	LinkedList<GameObject> objects = new LinkedList<GameObject>();
	
	public void update() {
		for(int i = 0; i < objects.size(); i++) {
			GameObject tempObject = objects.get(i);
			
			tempObject.update();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < objects.size(); i++) {
			GameObject tempObject = objects.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void updateByState(BasicGameState state) {
		for(int i = 0; i < objects.size(); i++) {
			GameObject tempObject = objects.get(i);
			if(tempObject.getState() == state) {
				tempObject.update();
			}
		}
	}
	
	public void renderByState(Graphics g, BasicGameState state) {
		for(int i = 0; i < objects.size(); i++) {
			GameObject tempObject = objects.get(i);
			if(tempObject.getState() == state) {
				tempObject.render(g);
			}
		}
	}
	
	public void addObject(GameObject object) {
		objects.add(object);
	}
	
	public void removeObject(GameObject object) {
		objects.remove(object);
	}
	
	public void clearAllObjects() {
		objects.clear();
	}
	
	public void clearEnemies() {
		for(int i = objects.size(); i > 0; i--) {
			GameObject tempObject = objects.get(i - 1);
			if(tempObject.getID() != ObjectID.Player) {
				objects.remove(tempObject);
			}
		}
	}
	
	public GameObject getObject(int index) {
		return objects.get(index);
	}
	
	public LinkedList<GameObject> getObjects() {
		return objects;
	}
}
