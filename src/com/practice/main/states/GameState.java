package com.practice.main.states;

import java.util.LinkedList;
import java.util.Random;

import com.practice.main.entities.*;
import com.practice.main.entities.enemies.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.practice.main.Game;
import com.practice.main.util.Util;

import static com.practice.main.entities.ObjectID.PathEnemy;

public class GameState extends BasicGameState {

	Handler handler = Game.handler;
	public static Input input;

	public static int LIVES = 3;
	public static int LEVEL = 0;

	public static int DELTA;

	public static int SCORE = 0;
	public static int MULTIPLIER = 0;
	public static int LEVELTICKER = 0;
	public static int CURRENCY = 0;

	Player player = new Player(50, 200, 64, 64, 100, ObjectID.Player, -1, this);

	private LinkedList<GameObject> objects;

	private int lastLevel = 0;

	Random r = new Random();

	private int width, height;
	
	public static GameState gameState;


	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		gameState = this;
		
		input = container.getInput();
		width = container.getWidth();
		height = container.getHeight();
		handler.addObject(player);

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.drawRect(49, 49, player.getMaxHealth() + 1, 26);
		g.setColor(Color.red);
		g.fillRect(50, 50, player.getMaxHealth(), 25);
		g.setColor(Color.green);
		g.fillRect(50, 50, player.getHealth(), 25);

		g.setColor(Color.white);
		g.drawString("Score: " + SCORE, 50, 100);
		g.drawString("Lives: " + LIVES, 50, 125);
		g.drawString("Level: " + LEVEL, 50, 150);
		g.drawString("Ticks: " + LEVELTICKER, 50, 175);
		g.drawString("Multiplier: " + MULTIPLIER, 50, 200);

		handler.renderByState(g, this);

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		DELTA = delta;

		handler = Game.handler;
		handler.updateByState(this, delta);
		//player.setPos(input.getMouseX() - 15, input.getMouseY() + 15);
		LEVELTICKER++;
		CURRENCY++;

		if(LEVELTICKER >= lastLevel) {
			LEVEL++;
			MULTIPLIER++;
			lastLevel += 5000;
			nextLevel(LEVEL);
		}

		if(input.isKeyPressed(Input.KEY_ESCAPE)) {
			game.enterState(2);
		}
		if(input.isKeyPressed(Input.KEY_SPACE)) {
		}
		if(input.isKeyDown(Input.KEY_UP)) {
		}
		if(input.isKeyDown(Input.KEY_DOWN)) {
		}
		if(input.isKeyDown(Input.KEY_LEFT)) {
		}
		if(input.isKeyDown(Input.KEY_RIGHT)) {
		}



	}

	public void nextLevel(int level) {





		//LEVELS
		if(level == 1) {
			handler.addObject(new BasicEnemy(r.nextInt(width - 30), -r.nextInt(30), 50, 50, 100, ObjectID.BasicEnemy, -1, this));
			handler.addObject(new BasicEnemy(r.nextInt(width - 30), -r.nextInt(30), 50, 50, 100, ObjectID.BasicEnemy, -1, this));
			handler.addObject(new BasicEnemy(r.nextInt(width - 30), -r.nextInt(30), 50, 50, 100, ObjectID.BasicEnemy, -1, this));

//			handler.addObject(new PathEnemy(r.nextInt(width - 30), -r.nextInt(30), 30, 30, 100, PathEnemy, this));
//			handler.addObject(new PathEnemy(r.nextInt(width - 30), -r.nextInt(30), 30, 30, 100, PathEnemy, this));
//			handler.addObject(new PathEnemy(r.nextInt(width - 30), -r.nextInt(30), 30, 30, 100, PathEnemy, this));
		}
		if(level == 2) {
			handler.addObject(new BasicEnemy(r.nextInt(width - 30), -r.nextInt(30), 70, 70, 100, ObjectID.BasicEnemy, -1, this));
			handler.addObject(new BasicEnemy(r.nextInt(width - 30), -r.nextInt(30), 70, 70, 100, ObjectID.BasicEnemy, -1, this));
			handler.addObject(new BasicEnemy(r.nextInt(width - 30), -r.nextInt(30), 70, 70, 100, ObjectID.BasicEnemy, -1, this));
		}
		if(level == 3) {
			handler.addObject(new BasicEnemy(r.nextInt(width - 30), -r.nextInt(30), 90, 90, 100, ObjectID.BasicEnemy, -1, this));
			handler.addObject(new BasicEnemy(r.nextInt(width - 30), -r.nextInt(30), 90, 90, 100,  ObjectID.BasicEnemy, -1, this));
			handler.addObject(new BasicEnemy(r.nextInt(width - 30), -r.nextInt(30), 90, 90, 100, ObjectID.BasicEnemy, -1, this));
		}
		if(level == 4) {
			//handler.clearEnemies();
			handler.addObject(new WavingEnemy(r.nextInt(width - 100), -r.nextInt(30), 20, 20, 100, ObjectID.WavingEnemy, -1, this));
			handler.addObject(new WavingEnemy(r.nextInt(width - 100), -r.nextInt(30), 20, 20, 100, ObjectID.WavingEnemy, -1, this));
			handler.addObject(new WavingEnemy(r.nextInt(width - 100), -r.nextInt(30), 20, 20, 100,  ObjectID.WavingEnemy, -1, this));
		}
		if(level == 5) {
			//handler.clearEnemies();
			handler.addObject(new ArchingEnemy(r.nextInt(200), -r.nextInt(30), 20, 20, 100, ObjectID.ArchingEnemy, -1, this));
			handler.addObject(new ArchingEnemy(r.nextInt(200), -r.nextInt(30), 20, 20, 100, ObjectID.ArchingEnemy, -1, this));
			handler.addObject(new ArchingEnemy(r.nextInt(200), -r.nextInt(30), 20, 20, 100, ObjectID.ArchingEnemy, -1, this));
		}
		if(level == 6) {
			//handler.clearEnemies();
			handler.addObject(new ArchingEnemy(r.nextInt(200), -r.nextInt(30), 20, 20, 100, ObjectID.ArchingEnemy, -1, this));
			handler.addObject(new ArchingEnemy(r.nextInt(200), -r.nextInt(30), 20, 20, 100, ObjectID.ArchingEnemy, -1, this));
			handler.addObject(new ArchingEnemy(r.nextInt(200), -r.nextInt(30), 20, 20, 100, ObjectID.ArchingEnemy, -1, this));
			handler.addObject(new BasicEnemy(r.nextInt(width - 30), -r.nextInt(30), 30, 30, 100, ObjectID.BasicEnemy, -1, this));
			handler.addObject(new BasicEnemy(r.nextInt(width - 30), -r.nextInt(30), 30, 30, 100, ObjectID.BasicEnemy, -1, this));
			handler.addObject(new BasicEnemy(r.nextInt(width - 30), -r.nextInt(30), 30, 30, 100, ObjectID.BasicEnemy, -1, this));
		}
	}

	public void spawn(int level, int difficulty) {
		int enemiesToSpawn = 0;
		Enemy e1;
		Enemy e2;
		Enemy e3;
		if(level < 5) {
			enemiesToSpawn = 3 + (difficulty - 1);
		} else if(level >= 5 && level <= 10) {
			enemiesToSpawn = level + 2 + difficulty;
		} else if(level > 10) {
			enemiesToSpawn = 20;
		}

		switch(level) {
			case 1:
				break;

		}

		for(int i = 0; i < enemiesToSpawn; i++) {
			//handler.addObject();
		}



	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}

}
