package com.practice.main.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.state.BasicGameState;

import com.practice.main.Util;

public class Player extends GameObject {

	public Player(float x, float y, float width, float height, ObjectID id, BasicGameState state) {
		super(x, y, width, height, id, state);
		
		velX = .5f;
		velY = .5f;
		
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		//g.fillRect(x, y, width, height);
		y = Util.clamp(100, 550, y);
		x = Util.clamp(0, 770, x);
		float[] triangle = {x, y, x + width, y, x + width / 2, y - height};
		g.fill(new Polygon(triangle));
		
	}

}
