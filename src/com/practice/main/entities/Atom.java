package com.practice.main.entities;

import com.practice.main.Game;
import com.practice.main.states.GravityMovementState;
import com.practice.main.states.ParticleCollisionState;
import com.practice.main.util.IDTracker;
import com.practice.main.util.Util;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;

import java.util.ArrayList;
import java.util.List;

public class Atom extends GameObject {
	
	private int protonCount, electronCount, neutronCount, particleCount;
	
	private List<Particle> particles = new ArrayList<Particle>();
	
	private float maxVel = 3;
	private float minVel = -3;
	
	private float velX, velY, accelX, accelY;
	
	private int idNum;
	
	private boolean isAtom = true;
	
	public Atom(float x, float y, float velX, float velY, float accelX, float accelY, int protonCount, int electronCount, int neutronCount, int idNum, BasicGameState state) {
		super(x, y, 10, 10, 100, ObjectID.Atom, state);
		
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
		this.accelX = accelX;
		this.accelY = accelY;
		this.protonCount = protonCount;
		this.electronCount = electronCount;
		this.neutronCount = neutronCount;
		this.idNum = idNum;
		particleCount = protonCount + electronCount + neutronCount;
		
		for(int i = 0; i < particleCount; i++) {
			ObjectID particleID = ObjectID.Particle;
			if(i < protonCount) {
				particleID = ObjectID.Proton;
			} else if( i < protonCount + electronCount) {
				particleID = ObjectID.Electron;
			} else if(i < protonCount + electronCount + neutronCount) {
				particleID = ObjectID.Neutron;
			}
			
			particles.add(new Particle(x, y, velX, velY, accelX, accelY, true, IDTracker.claimNextID(), idNum, particleID, state));
		}
		
	}
	
	public Atom(float x, float y, float width, float height, float health, ObjectID id, BasicGameState state) {
		super(x, y, width, height, health, id, state);
	}
	
	public List<Particle> getParticles() {
		return particles;
	}
	
	public void collision(GameObject object) {
		if(object instanceof Atom) {
			float velXTemp;
			float velYTemp;
			float accelYTemp;
			int count = 0;
			for(Particle p : particles) {
				count++;
				velXTemp = (-1 * getVelX() * Util.randomInt(1, 100) / 300);
				velYTemp = (Util.randomInt(-1, 1) * ((count % 2) + 1) + (float) (Util.randomInt(-9, 9) * .03));
				accelYTemp = velYTemp > 0 ? Math.abs(getAccelX() * 2) : Math.abs(getAccelX() * 2) * -1;
				System.out.println(" velXTemp: " + velXTemp + " velYTemp: " + velYTemp + " accelYTemp: " + accelYTemp);
				
				p.updateValues(p.x, p.y, velXTemp, velYTemp, 0, 0, false);
			}
			isAtom = false;
		} else {
		
		}
	}
	
	@Override
	public void update() {
		velX += accelX;
		velY += accelY;
		
		velX = velX > maxVel ? maxVel : velX;
		velX = velX < minVel ? minVel : velX;
		velY = velY > maxVel ? maxVel : velY;
		velY = velY < minVel ? minVel : velY;
		if(x < 0) {
			velX = Math.abs(velX);
			accelX = Math.abs(accelX);
		} else if (x > Game.WIDTH) {
			velX = -Math.abs(velX);
			accelX = -Math.abs(accelX);
		}
		if(y < 0) {
			velY = Math.abs(velY);
			accelY = Math.abs(accelY);
		} else if (y > Game.HEIGHT) {
			velY = -Math.abs(velY);
			accelY = -Math.abs(accelY);
		}
		
		x += velX;
		y += velY;
		
		for(Particle particle : particles) {
			particle.update();
		}
		
		if(isAtom) {
			for(GameObject object : GravityMovementState.objects) {
				if(object != this) {
					if(intersects(object) && object instanceof Atom || object instanceof Particle) {
						object.collision(this);
						collision(object);
					}
				}
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
		if(isAtom) {
			g.setColor(Color.blue);
			g.draw(getShape());
			g.fill(getShape());
		}
	}
	
	
	@Override
	public float getVelX() {
		return velX;
	}
	
	@Override
	public void setVelX(float velX) {
		this.velX = velX;
	}
	
	@Override
	public float getVelY() {
		return velY;
	}
	
	@Override
	public void setVelY(float velY) {
		this.velY = velY;
	}
	
	public float getAccelX() {
		return accelX;
	}
	
	public void setAccelX(float accelX) {
		this.accelX = accelX;
	}
	
	public float getAccelY() {
		return accelY;
	}
	
	public void setAccelY(float accelY) {
		this.accelY = accelY;
	}
}
