package com.practice.main.entities;

import com.practice.main.Game;
import com.practice.main.states.GravityMovementState;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by rbell on 5/31/2017.
 */
public class LineObj extends GameObject {
	
	Input input;
	
	Random r = new Random();
	
	ArrayList<Point> points = new ArrayList();
	
	int[] tracker = new int[10];
	int posTrack = 0;
	
	private int width, height;
	private float posx, posy;
	
	private int maxStepLength;
	
	Handler handler = Game.handler;
	
	public LineObj() {
		super(0, 0, 0, 0, 0, null, null);
		
		this.posx = 0;
		this.posy = 100;
		
	}
	
	
	public LineObj(float x, float y, float width, float height, float health, ObjectID id, BasicGameState state) {
		super(x, y, width, height, health, id, state);
		
		this.posx = 0;
		this.posy = 100;
		
	}
	
	@Override
	public void update() {
		
		this.posx += 1;
		
		if(this.posx > 1800) {
			points.clear();
			this.posx = 0;
		}
		
		points.add(new Point((int) this.posx, (int) this.posy, Color.white));
		
		while(points.size() > 500) {
			points.remove(0);
		}
		
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		for(int i = 1; i < points.size(); i++) {
			g.drawLine((float) points.get(i - 1).getX(), (float) points.get(i - 1).getY(), (float) points.get(i).getX(), (float) points.get(i).getY());
			//System.out.println(points.get(i).getX() + " " + points.get(i).getY());
			//System.out.println(points.get(points.size() - 1).getX());
		}
	}
	
	@Override
	public float getX() {
		return this.posx;
	}
	
	@Override
	public float getY() {
		return this.posy;
	}
	
	@Override
	public void setX(float num) {
		this.posx = num;
	}
	
	@Override
	public void setY(float num) {
		this.posy = num;
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
