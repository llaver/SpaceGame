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
		g.draw(s);
		for(Shape s : al) {
			//g.setColor(new Color(r.nextInt(250), r.nextInt(250), r.nextInt(250), r.nextInt(250)));
			g.draw(s);
		}
		
	}
	
	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
		s = e.transform(Transform.createRotateTransform(deg, 400, 300));
		if(al.size() > 50) {
			al.remove(0);
		}
		al.add(s);
		deg += 20;
	}
}
