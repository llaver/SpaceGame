package com.practice.main.states;

import com.practice.main.Game;
import com.practice.main.entities.*;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GravityMovementState extends BasicGameState {
	
	Handler handler = Game.handler;
	Input input;
	
	Random r = new Random();
	
	ArrayList<Point> points = new ArrayList();
	StellarBody s3;
	
	int[] tracker = new int[10];
	int posTrack = 0;
	
	public static int width, height;
	private int x, y, oldX, oldY;
	private boolean runOnce = false;
	
	private int maxStepLength;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		width = container.getWidth();
		height = container.getHeight();
		System.out.println(width + " " + height);
		
		RandomPath rp = new RandomPath();
		RandomPath rp2 = new RandomPath();
		RandomPath rp3 = new RandomPath();
		
		//LineObj lo = new LineObj();
		
		//GravityObj go = new GravityObj(100, 500, 50, ObjectID.GravityObj, this);
		
		//Sun
		StellarBody s1 = new StellarBody(300, 400,(float) 1.98892 * (10*30), 10, 0, ObjectID.StellarBody, this);
		s1.setVelY((float) -.000003);
		s1.setVelX((float) -.000003);
		//Earth
		StellarBody s2 = new StellarBody(500, 300,(float) 5.9742 * (10*24), 5, 0, ObjectID.StellarBody, this);
		s2.setVelY((float) .000003);
		s2.setVelX((float) .000003);
		//Black Hole!!!!!!
		s3 = new StellarBody(300, 250,(float) 5.9742 * (10*24*10), 30, 0, ObjectID.StellarBody, this);
		
		
		
		
		//handler.addObject(rp);
		//handler.addObject(rp2);
		//handler.addObject(rp3);
		//handler.addObject(lo);
		//handler.addObject(go);
		handler.addObject(s1);
		handler.addObject(s2);
		handler.addObject(s3);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		handler.update();
		s3.setVel(0, 0);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		handler.render(g);

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
