package com.practice.main.entities;

import java.io.File;
import java.util.LinkedList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.state.BasicGameState;

import com.practice.main.Game;
import com.practice.main.Util;
import com.practice.main.states.GameState;

public class Player extends GameObject {
	
	private float MAXHEALTH = 100;
	
	private LinkedList<GameObject> objects;
	
	Handler handler = Game.handler;
	
	private ParticleSystem system;
	private Image image;

	public Player(float x, float y, float width, float height, float health, ObjectID id, BasicGameState state) {
		super(x, y, width, height, health, id, state);
		
		velX = .5f;
		velY = .5f;
		
		init();
		
	}
	
	private void init() { 
		try {			
			image = new Image("res/img/particles/particle.png", false);
			system = new ParticleSystem(image,1500);
			File xmlFile = new File("res/particles/rocket.xml");
			ConfigurableEmitter emitter = ParticleIO.loadEmitter(xmlFile);
			system.addEmitter(emitter);
		} catch (Exception e) {
			System.out.println("Exception: " +e.getMessage());
			e.printStackTrace();
			System.exit(0);
		}
 
		system.setBlendingMode(ParticleSystem.BLEND_ADDITIVE);
	}

	@Override
	public void update() {
		system.update(GameState.DELTA);
		
		objects = handler.getObjects();
		
		for(int i = 0; i < objects.size(); i++) {
			if(objects.get(i).getID() != ObjectID.Player && objects.get(i).getID() != ObjectID.PlayerBullet) {
				if(this.getShape().intersects(objects.get(i).getShape())) {
					health = health - .3f;
					health = Util.clamp(0, MAXHEALTH, health);
				}
			}
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		//g.fillRect(x, y, width, height);
		y = Util.clamp(100, 550, y);
		x = Util.clamp(0, 770, x);
		float[] triangle = {x, y, x + width, y, x + width / 2, y - height};
		g.fill(new Polygon(triangle));
		
		system.render(x + width / 2, y);
		
	}
	
	public float getMaxHealth() {
		return MAXHEALTH;
	}

}
