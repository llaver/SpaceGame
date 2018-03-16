package com.practice.main.entities;

import java.io.File;
import java.util.LinkedList;

import com.practice.main.Assets;
import com.practice.main.util.Util;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.state.BasicGameState;

import com.practice.main.Game;
import com.practice.main.states.GameState;

public class Player extends GameObject {
	
	private float MAXHEALTH = 100;
	private int FIRE_DELAY = 200;
	
	private LinkedList<GameObject> objects;
	
	Handler handler = Game.handler;
	
	private ParticleSystem system;
	private Image image;
	private Image player;
	
	Input input = Game.input;

	public Player(float x, float y, float width, float height, float health, ObjectID id, int idNum, BasicGameState state) {
		super(x, y, width, height, health, id, idNum, state);
		
		velX = .5f;
		velY = .5f;
		
		init();
		
	}
	
	private void init() {
		player = Assets.PlayerShip.getScaledCopy((int) width, (int) height);
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
	public void update(int delta) {
		system.update(GameState.DELTA);

		y = Util.clamp(100, Game.HEIGHT - height, y);
		x = Util.clamp(0, Game.WIDTH - width, x);
		
		objects = handler.getObjects();
		
		for(int i = 0; i < objects.size(); i++) {
			if(objects.get(i).getID() != ObjectID.Player && objects.get(i).getID() != ObjectID.PlayerBullet) {
				if(this.getShape().intersects(objects.get(i).getShape())) {
					health = health - .3f;
					health = Util.clamp(0, MAXHEALTH, health);
				}
			}
		}
		
		FIRE_DELAY = FIRE_DELAY - delta;
		if(input.isKeyDown(Input.KEY_SPACE)) {
			if(FIRE_DELAY <= 0) {
				handler.addObject(new PlayerBullet((float) (x + width * .2 - 1), Util.clamp(80, Game.HEIGHT - 50, y - height), 5, 20, 0, ObjectID.PlayerBullet, -1, GameState.gameState));
				handler.addObject(new PlayerBullet((float) (x + width * .8 - 1), Util.clamp(80, Game.HEIGHT - 50, y - height), 5, 20, 0, ObjectID.PlayerBullet, -1, GameState.gameState));
				FIRE_DELAY = 200;
			}
		}
		if(input.isKeyDown(Input.KEY_UP)) {
			setPos(x, y - velY * delta);
		}
		if(input.isKeyDown(Input.KEY_DOWN)) {
			setPos(x, y + velY * delta);
		}
		if(input.isKeyDown(Input.KEY_LEFT)) {
			setPos(x - velX * delta, y);
		}
		if(input.isKeyDown(Input.KEY_RIGHT)) {
			setPos(x + velX * delta, y);
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawImage(player, x, y - height);
		system.render(x + width / 2, y);
	}
	
	@Override
	public void collision(GameObject object) {
	
	}
	
	public float getMaxHealth() {
		return MAXHEALTH;
	}

}
