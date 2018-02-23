package com.practice.main.levels;

import com.practice.main.Game;
import com.practice.main.entities.Handler;
import com.practice.main.entities.ObjectID;
import com.practice.main.entities.enemies.BasicEnemy;
import com.practice.main.states.GameState;
import org.newdawn.slick.Graphics;

import java.util.Random;

public class LevelOne extends Level {
	
	Handler handler = Game.handler;
	
	Random r = new Random();
	
	@Override
	public void init() {
		isStarted = true;
		isRunning = true;
		
		handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 30), -r.nextInt(30), 50, 50, 100, ObjectID.BasicEnemy, -1, GameState.gameState));
		handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 30), -r.nextInt(30), 50, 50, 100, ObjectID.BasicEnemy, -1, GameState.gameState));
		handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 30), -r.nextInt(30), 50, 50, 100, ObjectID.BasicEnemy, -1, GameState.gameState));
		
		
	}
	
	@Override
	public void exit() {
		isRunning = false;
		isComplete = true;
		
	}
	
	@Override
	public void update(int delta) {
	
	}
	
	@Override
	public void render(Graphics g) {
	
	}
}
