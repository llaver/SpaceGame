package com.practice.main.entities;

import com.practice.main.Game;
import com.practice.main.states.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by rbell on 5/31/2017.
 */
public class RandomPath extends GameObject {
	
	Input input;
	
	Random r = new Random();
	
	ArrayList<Point> points = new ArrayList();
	
	int[] tracker = new int[10];
	float posTrack = 0;
	
	private int width, height;
	private float x, y, oldX, oldY;
	
	private int maxStepLength;
	
	Handler handler = Game.handler;
	
	
	public RandomPath() {
		super(0, 0, 0, 0, 0, ObjectID.RandomPath, null);
		
		this.width = GravityMovementState.width;
		this.height = GravityMovementState.height;
		
		maxStepLength = ThreadLocalRandom.current().nextInt( 20 + 1);
		
		x = r.nextInt(1600);
		y = r.nextInt(900);
		
		points.add(new Point((int) this.x, (int) this.y, Color.white));
	}
	
	public RandomPath(float x, float y, float width, float height, float health, ObjectID id, BasicGameState state) {
		super(x, y, width, height, health, id, state);
		this.width = GravityMovementState.width;
		this.height = GravityMovementState.height;
		
		maxStepLength = ThreadLocalRandom.current().nextInt( 40 + 1);;
		
		x = r.nextInt(this.width);
		y = r.nextInt(this.height);
		
		points.add(new Point((int) this.x, (int) this.y, Color.white));
	}
	
	@Override
	public void update() {
		
		int xStepSize = ThreadLocalRandom.current().nextInt(-maxStepLength, maxStepLength + 1);
		int yStepSize = ThreadLocalRandom.current().nextInt(-maxStepLength, maxStepLength + 1);
		
		posTrack += (xStepSize > 0) ? 1 : -1;
		posTrack += (yStepSize > 0) ? 1 : -1;
		
		oldX = x;
		oldY = y;
		x += xStepSize;
		y += yStepSize;
		
		if(x > width) x = width;
		if(x < 0) x = 0;
		if(y > height) y = height;
		if(y < 0) y = 0;
		
		points.add(new Point((int) x, (int) y, Color.white));
		
		while(points.size() > 500) {
			points.remove(0);
		}
	}
	
	@Override
	public void render(Graphics g) {
		for(int i = 1; i < points.size(); i++) {
			points.get(i).setColor(getColor(points.size(), i));
			g.setColor(points.get(i).getColor());
			g.drawLine((float) points.get(i - 1).getX(), (float) points.get(i - 1).getY(), (float) points.get(i).getX(), (float) points.get(i).getY());
			
			for(int j = 1; j < points.size(); j *= 30) {
				g.setColor(points.get(i).getColor());
				g.drawLine((float) points.get(i).getX(), (float) points.get(i).getY(), (float) points.get(j).getX(), (float) points.get(j).getY());
				
			}
			
			
		}
		
		if(points.size() > 6) {
			for(int i = 5; i > 0; i--) {
				g.setColor(points.get(points.size() - 1).getColor());
				g.drawLine((float) points.get(points.size() - 1).getX(), (float) points.get(points.size() - 1).getY(), (float) points.get(points.size() - 1 - i).getX(), (float) points.get(points.size() - 1 - i).getY());
				
			}
		}
		
	}
	
	@Override
	public void collision(GameObject object) {
	
	}
	
	@Override
	public float getX() {
		return this.x;
	}
	
	@Override
	public float getY() {
		return this.y;
	}
	
	public Color getColor(int size, int index) {
		int divisionSize = 1400 / size;
		int value = divisionSize * index;
		int red = 0;
		int green = 0;
		int blue = 0;
		switch(value / 255) {
			case 0:
				red = 255;
				blue = value;
				break;
			case 1:
				red = 255 - value % 255;
				blue = 255;
				break;
			case 2:
				green = value % 255;
				blue = 255;
				break;
			case 3:
				green = 255;
				blue = 255 - value % 255;
				break;
			case 4:
				red = value % 255;
				green = 255;
				break;
			default:
				red = 255;
				green = 255;
		}
		return new Color(red, green, blue);
	}
}
