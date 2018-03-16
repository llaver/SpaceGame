package com.practice.main.levels;

import com.practice.main.Game;
import com.practice.main.entities.Handler;
import com.practice.main.entities.ObjectID;
import com.practice.main.entities.enemies.BasicEnemy;
import com.practice.main.states.GameState;
import org.lwjgl.util.Timer;
import org.newdawn.slick.Graphics;

import java.util.Random;

public class LevelOne extends Level {
	
	Handler handler = Game.handler;
	
	Random r = new Random();
	
	private Timer levelTimer = new Timer();
	private Timer spawnTimer = new Timer();
	
	private boolean initialSpawn = false;
	
	@Override
	public void init() {
		isStarted = true;
		isRunning = true;
		
		levelTimer.reset();
		spawnTimer.reset();
		
	}
	
	@Override
	public void exit() {
		isRunning = false;
		isComplete = true;
		
	}
	
	@Override
	public void update(int delta) {
		if(!initialSpawn) {
			if(spawnTimer.getTime() > 3) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 30), -r.nextInt(30), 50, 50, 100, ObjectID.BasicEnemy, -1, GameState.gameState));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 30), -r.nextInt(30), 50, 50, 100, ObjectID.BasicEnemy, -1, GameState.gameState));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 30), -r.nextInt(30), 50, 50, 100, ObjectID.BasicEnemy, -1, GameState.gameState));
				initialSpawn = true;
			}
		}
	
	}
	
	@Override
	public void render(Graphics g) {
	
	}
}
