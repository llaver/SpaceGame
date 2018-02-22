package com.practice.main.entities;

import java.util.LinkedList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;

public class Handler {

	LinkedList<GameObject> objects = new LinkedList<GameObject>();
	
	public void update(int delta) {
		for(int i = 0; i < objects.size(); i++) {
			GameObject tempObject = objects.get(i);
			
			tempObject.update(delta);
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < objects.size(); i++) {
			GameObject tempObject = objects.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void updateByState(BasicGameState state, int delta) {
		for(int i = 0; i < objects.size(); i++) {
			GameObject tempObject = objects.get(i);
			if(tempObject.getState() == state) {
				tempObject.update(delta);
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
	
	public GameObject getObject(int index) {
		return objects.get(index);
	}
	
	public LinkedList<GameObject> getObjects() {
		return objects;
	}
}
