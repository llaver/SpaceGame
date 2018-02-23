package com.practice.main.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;

import com.practice.main.Game;

public class PlayerBullet extends PlayerWeapon {
	
	Handler handler = Game.handler;
	Rectangle bullet;
	
	public PlayerBullet(float x, float y, float width, float height, float health, ObjectID id, int idNum, BasicGameState state) {
		super(x, y, width, height, health, id, idNum, state);
		velX = 0;
		velY = 1f;
		
		bullet = new Rectangle(x, y, width, height);
	}
	
	
	
	@Override
	public void update(int delta) {
		x += velX;
		y -= velY;
		
		bullet = new Rectangle(x, y, width, height);
		
		if(y <= -10) {
			handler.removeObject(this);
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fill(bullet);
	}
	
	@Override
	public void collision(GameObject object) {
	
	}
	
	public Rectangle getBullet() {
		return bullet;
	}

}
