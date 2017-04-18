package com.practice.main.entities.enemies;

import java.util.LinkedList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;

import com.practice.main.Game;
import com.practice.main.entities.GameObject;
import com.practice.main.entities.Handler;
import com.practice.main.entities.ObjectID;
import com.practice.main.states.GameState;

public class ArchingEnemy extends Enemy {

	Handler handler = Game.handler;
	Rectangle enemy;
	Random r = new Random();
	int archSize = 1;
	
	float deltaVelX = 0;
	boolean negative = false;
	
	public ArchingEnemy(float x, float y, float width, float height, float health, ObjectID id, BasicGameState state) {
		super(x, y, width, height, health, id, state);
		
		enemy = new Rectangle(x, y, width, height);
		archSize = r.nextInt(9) + 1;
		
		velY = .05f * (r.nextInt(5) + 1);
	}

	@Override
	public void update() {
		if(Math.abs(deltaVelX) <= 1000) {
			if(negative) {
				velX = velX - .00007f * archSize;
				deltaVelX--;
			} else {
				velX = velX + .00007f * archSize;
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
					//increase score
					GameState.SCORE += 150 * GameState.MULTIPLIER;
					
					//Add particle explosion
					
					//Remove this enemy
					handler.removeObject(this);
				}
			}
		}
		
		if(y >= 650) {
			handler.removeObject(this);
		}
		
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, width, height);
		
	}

}
