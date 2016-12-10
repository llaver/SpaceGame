package com.practice.main.entities.enemies;

import java.util.LinkedList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;

import com.practice.main.Game;
import com.practice.main.entities.GameObject;
import com.practice.main.entities.Handler;
import com.practice.main.entities.ObjectID;

public class WavingEnemy extends GameObject {

	Handler handler = Game.handler;
	Rectangle enemy;
	
	float deltaVelX = 0;
	boolean negative = false;
	
	public WavingEnemy(float x, float y, float width, float height, ObjectID id, BasicGameState state) {
		super(x, y, width, height, id, state);
		
		enemy = new Rectangle(x, y, width, height);
		
		velY = .1f;
	}

	@Override
	public void update() {
		System.out.println("Velx: " + velX + " deltaVelX: " + deltaVelX);
		if(Math.abs(deltaVelX) <= 400) {
			if(negative) {
				velX = velX - .001f;
				deltaVelX--;
			} else {
				velX = velX + .001f;
				deltaVelX++;
			}
		} else {
			negative = !negative;
			if(deltaVelX < 0) {
				deltaVelX++;
			}
			if(deltaVelX > 0) {
				deltaVelX--;
			}
		}
		
		x += velX;
		y += velY;
		
		enemy = new Rectangle(x, y, width, height);
		
		LinkedList<GameObject> objects = handler.getObjects();
		
		for(int i = objects.size() - 1; i >= 0; i--) {
			GameObject currentObject = objects.get(i);
			if(currentObject.getID() == ObjectID.PlayerBullet) {
				if(currentObject.getShape().intersects(enemy)) {
					//Add particle explosion
					
					//Remove this enemy
					handler.removeObject(this);
				}
			}
		}
		
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, width, height);
		
	}

}
