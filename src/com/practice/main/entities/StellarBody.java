package com.practice.main.entities;

import com.practice.main.Game;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.BasicGameState;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by rbell on 6/1/2017.
 */
public class StellarBody extends GameObject {
	
	private float mass;
	private float radius;
	
	double G = 6.67428e-11;
	
	//Assumed scale: 100 pixels = 1AU
	double AU = (149.6e6 * 1000); //149.6 million km, in meters.
	double SCALE = 250 / AU;
	double timestep = 24*3600;
	
	Color[] cols = new Color[5];
	
	Handler handler = Game.handler;
	Random r = new Random();
	
	public StellarBody(float px, float py, float m, float r, float health, ObjectID id, BasicGameState state) {
		super(px , py, 2*r, 2*r, health, id, state);
		mass = m;
		radius = m / 100;
		
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
		System.out.println(x + " " + y + " " + velX + " " + velY);
		
		LinkedList<GameObject> objects = handler.getObjects();
		
		float totalFX = 0;
		float totalFY = 0;
		
		for(GameObject obj : objects) {
			if(obj.getID() == ObjectID.StellarBody && !this.isEqual(obj)) {
				float[] attr = getAttraction((StellarBody) obj);
				
				System.out.println("attr: " + attr[0] + " " + attr[1]);
				totalFX += attr[0];
				totalFY += attr[1];
			}
		}
		velX += totalFX / mass;
		velY += totalFY / mass;
		x += velX * timestep;
		y += velY * timestep;
	
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(new Color(cols[0]));
		g.setLineWidth(3);
		g.draw(new Circle(x, y, radius));
		for(int i = 0; i < 5; i++) {
			int dx = r.nextInt(4) - 2;
			int dy = r.nextInt(4) - 2;
			int dr = r.nextInt(4) - 2;
			g.setColor(cols[i]);
			g.draw(new Circle(x + dx, y + dy, radius + dr));
		}
		
	}
	
	
	public float[] getAttraction(StellarBody body) {
		if(isEqual(body)) {
			//System.out.println("obj's were the same");
			return new float[]{0, 0};
		}
		//Self x and y
		float sX = x;
		float sY = y;
		System.out.println("Self x and y:" + sX + " " + sY);
		
		//Other x and y
		float oX = body.getX();
		float oY = body.getY();
		//System.out.println("Other x and y:" + oX + " " + oY);
		
		
		//Calculate distance
		float dX = oX - sX;
		float dY = oY - sY;
		float d = (float) Math.sqrt(dX*dX + dY*dY);
		System.out.println("Distance x and y:" + dX + " " + dY + " " + d);
		
		
		//Make sure distance is not zero
		if(d == 0) {
			//System.out.println("d was zero");
			return new float[]{0, 0};
		}
		
		//Calculate force
		double force = G * mass * body.getMass() / d*2;
		//System.out.println("Distance x and y:" );
		
		//Calculate direction of force
		double theta = Math.atan2(dY, dX);
		double fX = Math.cos(theta) * force;
		double fY = Math.sin(theta) * force;
		return new float[]{(float) fX, (float) fY};
	}
	
	public float getRadius() {
		return radius;
	}
	
	public void setRadius(float radius) {
		this.radius = radius;
	}
	
	public float getMass() {
		return mass;
	}
	
	public void setMass(float mass) {
		this.mass = mass;
	}
	
	@Override
	public boolean isEqual(Object obj) {
		if(obj instanceof StellarBody) {
			return x   == ((StellarBody) obj).getX()      &&
				y      == ((StellarBody) obj).getY()      &&
				mass   == ((StellarBody) obj).getMass()   &&
				radius == ((StellarBody) obj).getRadius() &&
				velX   == ((StellarBody) obj).getY()      &&
				velY   == ((StellarBody) obj).getY()      &&
				health == ((StellarBody) obj).getY()      &&
				id     == ((StellarBody) obj).getID()     &&
				state  == ((StellarBody) obj).getState();
		}
		return false;
	}
}
