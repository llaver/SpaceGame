package com.practice.main.states;

import com.practice.main.Pathing;
import com.practice.main.Position;
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
public class TestState extends BasicGameState {
	
	
	Pathing p = new Pathing();
	ArrayList<Position> line;
	
	Position current = new Position();
	Position previous = new Position();
	
	
	@Override
	public int getID() {
		return 0;
	}
	
	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		
		line = p.generatePath(false);
		
		System.out.println("Size: " + line.size());
		
		for(Position point : line) {
			//System.out.println("X: " + point.getX() + " Y: " + point.getY());
		}
		
	}
	
	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
		g.setColor(Color.white);
		previous.setX(0);
		previous.setY(0);
		current.setX(0);
		current.setY(0);
		
		for(int i = 0; i < line.size(); i++) {
			previous = current;
			current = line.get(i);
			//System.out.println("X: " + current.getX() + " Y: " + current.getY());
			if(!((previous.getX() == 0 && previous.getY() == 0) || (current.getX() == 0 && current.getY() == 0))) {
				g.drawLine(previous.getX() / 5, previous.getY() / 5, current.getX() / 5, current.getY() / 5);
			}
		}
	}
	
	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
		line.add(p.update(line.get(line.size() - 1)));
		line.remove(0);
	}
	
}
