package com.practice.main;

import com.practice.main.levels.Level;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.List;

public class LevelManager {
	
	private List<Level> levels;
	
	Level tempLevel;
	
	public LevelManager() {
		levels = new ArrayList<>();
	}
	
	public LevelManager(ArrayList<Level> levels) {
		this.levels = levels;
	}
	
	public void addLevel(Level level) {
		levels.add(level);
	}
	
	public void addLevel(Level level, int position) {
		levels.add(position, level);
	}
	
	public void pushLevel(Level level) {
		levels.add(0, level);
	}
	
	public void update(int delta) {
		tempLevel = levels.get(0);
		if(tempLevel.isStarted()) {
			if(!tempLevel.isComplete()) {
				tempLevel.update(delta);
			}
			if(tempLevel.isComplete()) {
				levels.remove(tempLevel);
				levels.get(0).start();
			}
		}
	}
	
	public void render(Graphics g) {
		tempLevel = levels.get(0);
		if(tempLevel.isRunning()) {
			tempLevel.render(g);
		}
	}
}
