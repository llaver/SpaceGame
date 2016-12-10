package com.practice.main.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;

import com.practice.main.Game;

public class PlayerBullet extends GameObject {
	
	Handler handler = Game.handler;
	Rectangle bullet;
	
	public PlayerBullet(float x, float y, float width, float height, ObjectID id, BasicGameState state) {
		super(x, y, width, height, id, state);
		// TODO Auto-generated constructor stub
		velX = 0;
		velY = 1f;
		
		bullet = new Rectangle(x, y, width, height);
	}
	
	
	
	@Override
	public void update() {
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
		//x = 
		//PlayerPosition = {x, y, x + width, y, x + width / 2, y - height};
		//g.fill(new Polygon(PlayerPosition));
		
	}
	
	public Rectangle getBullet() {
		return bullet;
	}

}
