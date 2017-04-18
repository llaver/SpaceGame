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

public class BasicEnemy extends Enemy {

	Handler handler = Game.handler;
	Rectangle enemy;
	
	Random r = new Random();
	
	public BasicEnemy(float x, float y, float width, float height,float health, ObjectID id, BasicGameState state) {
		super(x, y, width, height, health, id, state);
		
		enemy = new Rectangle(x, y, width, height);
		
		velX = .1f * (r.nextInt(10) - 5);
		velY = .1f * (r.nextInt(4) + 1);
	}

	@Override
	public void update() {
		x += velX;
		y += velY;
		
		enemy = new Rectangle(x, y, width, height);
		
		LinkedList<GameObject> objects = handler.getObjects();
		
		for(int i = objects.size() - 1; i >= 0; i--) {
			GameObject currentObject = objects.get(i);
			if(currentObject.getID() == ObjectID.PlayerBullet) {
				if(currentObject.getShape().intersects(enemy)) {
					//increase score
					GameState.SCORE += 50 * GameState.MULTIPLIER;
					
					//Add particle explosion
					
					//Remove this enemy
					handler.removeObject(this);
				}
			}
		}
		
		if(x >= 775 || x <= 0) {
			velX = velX * -1;
		}
		if(y <= 0) {
			velY = Math.abs(velY);
		}
		if(y >= 575) {
			velY = Math.abs(velY) * -1;
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
		
	}

}
