package com.practice.main.states;

import com.practice.main.Game;
import com.practice.main.entities.Handler;
import com.practice.main.entities.Point;
import com.practice.main.entities.RandomPath;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomMovementState extends BasicGameState {
	
	Handler handler = Game.handler;
	Input input;
	
	Random r = new Random();
	
	ArrayList<Point> points = new ArrayList();
	
	RandomPath randomPath;
	
	int[] tracker = new int[10];
	int posTrack = 0;
	
	public static int width, height;
	private int x, y, oldX, oldY;
	private boolean runOnce = false;
	
	private int maxStepLength;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		input = container.getInput();
		width = container.getWidth();
		height = container.getHeight();
		
		randomPath = new RandomPath();
		
		maxStepLength = ThreadLocalRandom.current().nextInt( 20 + 1);;
		
		x = r.nextInt(width);
		y = r.nextInt(height);
		
		points.add(new Point(x, y, Color.white));
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
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
		
		System.out.println("x: " + x + " y: " + y + " xStepSize: " + xStepSize + " yStepSize: " + yStepSize + " posTrack: + " + posTrack);
		points.add(new Point(x, y, Color.white));
		
		while(points.size() > 500) {
			points.remove(0);
		}
		
		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
		for(int i = 1; i < points.size(); i++) {
			points.get(i).setColor(getColor(points.size(), i));
			g.setColor(points.get(i).getColor());
			g.drawLine((float) points.get(i - 1).getX(), (float) points.get(i - 1).getY(), (float) points.get(i).getX(), (float) points.get(i).getY());
		}
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 3;
	}
	
	public Color loopColor(Color color, boolean positive) {
		int r = color.getRed();
		int g = color.getGreen();
		int b = color.getBlue();
		
		if(positive) {
			if(r >= 255 && g >= 255) {
				b += 5;
			} else if(r >= 255) {
				g += 5;
			} else {
				r += 5;
			}
		} else if(!positive) {
			if(r <= 0 && g <= 0) {
				b -= 5;
			} else if(r <= 0) {
				g -= 5;
			} else {
				r -= 5;
			}
		}
		System.out.println("r: " + r + " g: " + g + " b: " + b);
		return new Color(r, g, b);
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
	
	public int getRoundedIndex(int length, int toAdd, int index) {
		if(index + toAdd >= length) {
			return toAdd - (length - index) + 1;
		}
		return index + toAdd;
	}
	
	public static java.awt.Point PointOnCircle(float radius, float angleInDegrees, java.awt.Point origin) {
		// Convert from degrees to radians via multiplication by PI/180
		float x = (float) (radius * Math.cos(angleInDegrees * Math.PI / 180F)) + origin.x;
		float y = (float) (radius * Math.sin(angleInDegrees * Math.PI / 180F)) + origin.y;
		
		return new java.awt.Point((int) x, (int) y);
	}
	
	public LinkedList<Point> shortenList(LinkedList<Point> list, int num) {
		LinkedList<Point> temp = new LinkedList<>();
		int track = 0;
		for(int i = 0; i < list.size(); i++) {
			if(track == num) {
				temp.add(list.get(i));
				track = 0;
			}
			track++;
		}
		return temp;
	}
}
