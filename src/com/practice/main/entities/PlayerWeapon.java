package com.practice.main.entities;

import org.newdawn.slick.state.BasicGameState;

public abstract class PlayerWeapon extends GameObject {
	
	public int weaponLevel = 1;
	

	public PlayerWeapon(float x, float y, float width, float height, float health, ObjectID id, int idNum, BasicGameState state) {
		super(x, y, width, height, health, id, idNum, state);
	}
	public int getWeaponLevel() {
		return weaponLevel;
	}
	public void setWeaponLevel(int level) {
		weaponLevel = level;
	}
}
