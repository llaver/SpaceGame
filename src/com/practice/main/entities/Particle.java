package com.practice.main.entities;

import com.practice.main.Game;
import com.practice.main.states.ParticleCollisionState;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;

public class Particle extends GameObject {
	
	private float velX, velY, accelX, accelY;
	
	private boolean isAtom;
	
	private int idNum;
	private int parentIdNum;
	private float maxVel = 3;
	private float minVel = -3;
	
	private int tracker = 0;
	
	public Particle(float x, float y, float velX, float velY, float accelX, float accelY, boolean isAtom, int idNum, int parentIdNum, ObjectID objectID, BasicGameState state) {
		super(x, y, 6, 6, 100, objectID, , state, );
		
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
		this.accelX = accelX;
		this.accelY = accelY;
		this.isAtom = isAtom;
		this.idNum = idNum;
		this.parentIdNum = parentIdNum;
		this.id = objectID;
		
	}
	
	public Particle(float x, float y, float width, float height, float health, ObjectID id, BasicGameState state) {
		super(x, y, width, height, health, id, , state, );
	}
	
	public void updateValues(float x, float y, float velX, float velY, float accelX, float accelY, boolean isAtom) {
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
		this.accelX = accelX;
		this.accelY = accelY;
		this.isAtom = isAtom;
	}
	
	public void collision(GameObject object) {
		velX = -2 * velX;
		accelX = -accelX;
	
	}
	
	@Override
	public void update(int delta) {
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
		
		if(!isAtom) {
			tracker++;
			//update position
			if(tracker > 10) {
				for(GameObject object : ParticleCollisionState.objects) {
					if(object != this) {
						if(intersects(object) && object instanceof Atom) {
							collision(object);
						}
					}
				}
			}
		}
		
	}
	
	@Override
	public void render(Graphics g) {
		if(!isAtom) {
			g.setColor(Color.green);
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
	
	public boolean isAtom() {
		return isAtom;
	}
	
	public void setAtom(boolean atom) {
		isAtom = atom;
	}
}
