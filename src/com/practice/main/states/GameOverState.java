package com.practice.main.states;

import com.practice.main.Game;
import com.practice.main.entities.Handler;
import com.practice.main.entities.Point;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class GameOverState extends BasicGameState {
	
	Handler handler = Game.handler;
	Input input;
	
	boolean pos = false;
	Color col = new Color(0, 0, 0);
	int num = 0;
	int tracker = 0;
	int loops = 0;
	
	public int timesRan = 0;
	
	Circle c;
	Circle[] subCircles = new Circle[6];
	//g.setColor(col);
	
	ArrayList<java.awt.Point> javaAwtPoints = new ArrayList<>();
	
	private LinkedList<Point> allPoints = new LinkedList<>();
	private LinkedList<Point> smallListPoints = new LinkedList<>();
	
	Random r = new Random();
	
	private int width, height;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		input = container.getInput();
		width = container.getWidth();
		height = container.getHeight();
		
		c = new Circle(width / 2, height / 2, 250);
		java.awt.Point[] mainCirclePoints = new java.awt.Point[10];
		
		for(int i = 0; i < 6; i++) {
			int degs = 60 * (i + 1);
			mainCirclePoints[i] = PointOnCircle(250, degs, new java.awt.Point(width / 2, height / 2));
			subCircles[i] = new Circle(mainCirclePoints[i].x, mainCirclePoints[i].y, 75);
		}
		
		for(int i = 0; i < subCircles.length; i++) {
			for(int k = 0; k < 6; k++) {
				javaAwtPoints.add(PointOnCircle(75, 60 * (k + 1), new java.awt.Point((int) subCircles[i].getCenterX(), (int) subCircles[i].getCenterY())));
			}
		}
		
		
		for(int i = 0; i < javaAwtPoints.size(); i++) {
			for(int j = 0; j < javaAwtPoints.size(); j++) {
				if(col.getRed() >= 255 && col.getBlue() >= 255 && col.getGreen() >= 255) {
					pos = false;
				} else if(col.getRed() <= 0 && col.getBlue() <= 0 && col.getGreen() <= 0) {
					pos = true;
				}
				col = getColor(7, i + j);
				
				
				Point pi = new Point(new java.awt.Point((int) javaAwtPoints.get(i).getX(), (int) javaAwtPoints.get(i).getY()), col);
				Point pj = new Point(new java.awt.Point((int) javaAwtPoints.get(j).getX(), (int) javaAwtPoints.get(j).getY()), col);
				allPoints.add(pi);
				allPoints.add(pj);
			}
		}
		
		
		//smallList = shortenList(this.points, 5);
		smallListPoints = allPoints;
		System.out.println(smallListPoints.size());
		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		//g.setColor(Color.white);
		
		
		//System.out.println("circle number of points: " + c.getPointCount());
		
		//System.out.println("getPoints.length: " + points.length);
		
		
		//System.out.println("points size: " + this.points.size());
		//System.out.println("points color: " + p.getColor().toString());
		
		for(int i = 0; i < smallListPoints.size() - 1; i++) {
			Point p = smallListPoints.get(i);
			g.setColor(p.getColor());
			g.drawLine((float) p.getX(), (float) p.getY(), (float) smallListPoints.get(i + 1).getX(), (float) smallListPoints.get(i + 1).getY());
		}
		//g.setColor(Color.green);
		//g.draw(c);
		loops++;
		if(loops > smallListPoints.size()) {
			loops = 0;
		}
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		smallListPoints = progressList(smallListPoints);
		System.out.println("smallList.size: " + smallListPoints.size());
		System.out.println("tracker: " + tracker);
		
		if(tracker >= smallListPoints.size()) {
			tracker = 0;
		}
		
		for(int i = tracker; i > 0; i--) {
			Point toChange = smallListPoints.get(i);
			Color ctc = toChange.getColor();
			int alpha = (i * 255) / tracker;
			if(alpha > 10) {
				alpha -= 10;
			}
			ctc = new Color(ctc.getRed(), ctc.getGreen(), ctc.getBlue(), alpha);
			//System.out.println(ctc);
			toChange.setColor(ctc);
			
			tracker++;
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
		int divisionSize = size;
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
	
	public LinkedList<Point> progressList(LinkedList<Point> list) {
		LinkedList<Point> temp = new LinkedList<>();
		for(int i = 1; i < list.size(); i++) {
			Point tempPoint = list.get(i);
			temp.add(new Point(new java.awt.Point((int) tempPoint.getX(),(int) tempPoint.getY()), list.get(i - 1).getColor()));
		}
		Point listPoint = list.get(0);
		temp.add(new Point(new java.awt.Point((int) listPoint.getX(),(int) listPoint.getY()), list.get(list.size() - 1).getColor()));
		return temp;
	}
}
