package com.practice.main.levels;

import org.newdawn.slick.Graphics;

public abstract class Level {
	
	protected boolean isStarted = false;
	protected boolean isRunning = false;
	protected boolean isComplete = false;
	
	public void start() {
		init();
		isStarted = true;
		isRunning = true;
	}
	
	public abstract void init();
	
	public void exit() {
		isRunning = false;
		isComplete = true;
	}
	
	public abstract void update(int delta);
	
	public abstract void render(Graphics g);
	
	public boolean isStarted() {
		return isStarted;
	}
	
	public boolean isComplete() {
		return isComplete;
	}
	
	public boolean isRunning() {
		return isRunning;
	}
	
	
}
