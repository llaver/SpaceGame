package com.practice.main.states;

import com.practice.main.OpenSimplexNoise;
import com.practice.main.Pathing;
import com.practice.main.Position;
import com.practice.main.astar.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by rbell on 6/14/2017.
 */
public class TestState extends BasicGameState {
	
	
	Pathing p = new Pathing();
	ArrayList<Position> line;
	
	Position current = new Position();
	Position previous = new Position();
	private Random r = new Random();
	private OpenSimplexNoise noise = new OpenSimplexNoise(r.nextLong());
	public static double[][] map = new double[32][32];
	
	private Map<RoadNode> roadMap = new Map<RoadNode>(32, 32, new RoadNodeFactory());
	private List<RoadNode> path;
	
	
	@Override
	public int getID() {
		return 0;
	}
	
	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		for(int i = 0; i < map.length; i++) {
			//System.out.print("\n*");
			for(int k = 0; k < map[i].length; k++) {
				map[i][k] = noise.eval(i * .25, k * .15);
				//System.out.println("i10: " + i * 10 + " k10: " + k * 10 + " 1i10: " + (i + 1) * 10 + " 1k10: " + (k + 1) * 10);
				//System.out.println(map[i][k]);
			}
		}
		path = roadMap.findPath(0, 31, 31, 0);
		roadMap.drawMap(path);
		
	}
	
	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
//		g.setColor(Color.white);
//		previous.setX(0);
//		previous.setY(0);
//		current.setX(0);
//		current.setY(0);
//
//		for(int i = 0; i < line.size(); i++) {
//			previous = current;
//			current = line.get(i);
//			//System.out.println("X: " + current.getX() + " Y: " + current.getY());
//			if(!((previous.getX() == 0 && previous.getY() == 0) || (current.getX() == 0 && current.getY() == 0))) {
//				g.drawLine(previous.getX() / 5, previous.getY() / 5, current.getX() / 5, current.getY() / 5);
//			}
//		}
		for(int i = 0; i < map.length; i++) {
			for(int k = 0; k < map[i].length; k++) {
				float current = (float) map[i][k];
				//System.out.println(current / 100);
				byte col = (byte) (256 * map[i][k]);
				g.setColor(new Color(col, col, col));
				//g.fillRect(i * 10, k * 10, (i + 1) * 10, (k + 1) * 10);
				
				if(current >= -.2 && current <= .2 && !path.contains(new RoadNode(i, k))) {
					g.setColor(Color.darkGray);
					//g.fillRect(i, k, i, k);
					g.fillRect(i * 10, k * 10, (i + 1) * 10, (k + 1) * 10);
				} else if(path.contains(new RoadNode(i, k))) {
					g.setColor(Color.green);
					//g.fillRect(i, k, i,k);
					g.fillRect(i * 10, k * 10, (i + 1) * 10, (k + 1) * 10);
				} else {
					g.setColor(Color.black);
					//g.fillRect(i, k, i, k);
					g.fillRect(i * 10, k * 10, (i + 1) * 10, (k + 1) * 10);
				}
			}
		}
	}
	
	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
//		line.add(p.update(line.get(line.size() - 1)));
//		line.remove(0);
	
	
	}
	
}

