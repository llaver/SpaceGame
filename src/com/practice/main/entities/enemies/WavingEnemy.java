package com.practice.main.entities.enemies;

import java.util.LinkedList;
import java.util.Random;

import com.practice.main.Assets;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;

import com.practice.main.Game;
import com.practice.main.entities.GameObject;
import com.practice.main.entities.Handler;
import com.practice.main.entities.ObjectID;
import com.practice.main.states.GameState;

public class WavingEnemy extends Enemy {

	Handler handler = Game.handler;
	Rectangle enemy;
	Random r = new Random();
	int archSize = 1;
	
	float delta = 300;
	float deltaVelX = 0;
	boolean negative = false;
	
	Image wavingSprite;
	
	public WavingEnemy(float x, float y, float width, float height, float health, ObjectID id, BasicGameState state) {
		super(x, y, width, height, health, id, state);
		
		enemy = new Rectangle(x, y, width, height);
		archSize = r.nextInt(3) + 1;
		delta += r.nextInt(200);
		
		velY = .075f;
		
		wavingSprite = Assets.Interceptor1;
	}

	@Override
	public void update(int delta) {
		if(Math.abs(deltaVelX) <= delta) {
			if(negative) {
				velX = velX - .003f * archSize;
				deltaVelX--;
			} else {
				velX = velX + .003f * archSize;
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
					GameState.SCORE += 100 * GameState.MULTIPLIER;
					
					//Add particle explosion
					
					//Remove this enemy
					handler.removeObject(this);
				}
			}
		}
		
		if(y >= Game.HEIGHT) {
			handler.removeObject(this);
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, width, height);
		g.drawImage(wavingSprite, x, y);
	}
	
	@Override
	public void collision(GameObject object) {
	
	}
	
}
