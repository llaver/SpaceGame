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

public class BasicEnemy extends GameObject {

	Handler handler = Game.handler;
	Rectangle enemy;
	
	public BasicEnemy(float x, float y, float width, float height, ObjectID id, BasicGameState state) {
		super(x, y, width, height, id, state);
		
		enemy = new Rectangle(x, y, width, height);
		
		velX = .5f;
		velY =.5f;
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
					//Add particle explosion
					
					//Remove this enemy
					handler.removeObject(this);
				}
			}
		}
		
		if(x >= 775 || x <= 0) {
			velX = velX * -1;
		} 
		if(y >= 575 || y <= 0) {
			velY = velY * -1;
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
		
	}

}
