package com.practice.main.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by rbell on 6/14/2017.
 */
public class SpirographState extends BasicGameState {
	
	Ellipse e;
	Shape s;
	float deg = 0;
	int tracker = 0;
	int waitTracker = 0;
	boolean pos = true;
	
	ArrayList<Shape> al = new ArrayList<>();
	Random r = new Random();
	
	@Override
	public int getID() {
		return 0;
	}
	
	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		e = new Ellipse(400, 300, 40, 150);
		s = e;
	}
	
	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.drawString("Fuck your Lol", 350, 100);
		for(int i = 0; i < al.size(); i++) {
			
			g.setColor(getColor(170, i));
			g.draw(al.get(i));
		}
	}
	
	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
			if(pos) {
				if(tracker > 10) {
					s = e.transform(Transform.createRotateTransform(deg, 400, 300));
					if(al.size() > 170) {
						if(waitTracker < 300) {
							waitTracker++;
						} else {
							pos = false;
							waitTracker = 0;
						}
					} else {
						al.add(s);
						deg += 2;
						tracker = 0;
					}
				}
			} else {
				if(tracker > 20) {
					s = e.transform(Transform.createRotateTransform(deg, 400, 300));
					if(al.size() <= 0) {
						if(waitTracker < 50) {
							waitTracker++;
						} else {
							pos = true;
							waitTracker = 0;
						}
					} else {
						al.remove(al.size() - 1);
					}
					tracker = 0;
				}
			}
		tracker++;
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
