package com.practice.main.entities;

import com.practice.main.Game;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.BasicGameState;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by rbell on 5/31/2017.
 */
public class GravityObj extends GameObject {
	
	Random r = new Random();
	private int x, y, rad;
	
	Color[] cols = new Color[5];
	
	Handler handler = Game.handler;
	
	public GravityObj(float x, float y, float radius, ObjectID id, BasicGameState state) {
		super(x, y, 2 * radius, 2 * radius, 0, id, state);
		this.x = (int) x;
		this.y = (int) y;
		this.rad = (int) radius;
		
		cols[0] = new Color(26, 0, 26);
		cols[1] = new Color(77, 0, 77);
		cols[2] = new Color(0, 0, 51);
		cols[3] = new Color(26, 26, 26);
		cols[4] = new Color(77, 0, 0);
		
		for(int i = 0; i < cols.length; i++) {
			cols[i] = cols[i].brighter(1.2f);
		}
		
	}
	
	@Override
	public void update() {
		LinkedList<GameObject> objects = handler.getObjects();
		
		for(GameObject obj : objects) {
			if(obj.getID() != ObjectID.GravityObj) {
				int forceX = getForce(x - (int) obj.getX());
				int forceY = getForce((int) obj.getY() - y);
				
				System.out.println(forceX + " " + forceY);
				//obj.setPos(obj.getX() + forceX, obj.getY() + forceY);
				obj.setX(obj.getX() + forceX);
				obj.setY(obj.getY() - forceY);
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
		
		g.setColor(new Color(cols[0]));
		
		g.setLineWidth(3);
		
		g.draw(new Circle(x, y, rad));
		
		for(int i = 0; i < 5; i++) {
			int dx = r.nextInt(4) - 2;
			int dy = r.nextInt(4) - 2;
			int dr = r.nextInt(4) - 2;
			g.setColor(cols[i]);
			g.draw(new Circle(x + dx, y + dy, rad + dr));
		}
	}
	
	@Override
	public void collision(GameObject object) {
	
	}
	
	public int getForce(int distance) {
		if(distance != 0) {
			return distance / rad;
		} else {
			return 0;
		}
	}
}
