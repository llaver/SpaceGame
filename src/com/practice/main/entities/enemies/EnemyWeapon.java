package com.practice.main.entities.enemies;

import org.newdawn.slick.state.BasicGameState;

import com.practice.main.entities.GameObject;
import com.practice.main.entities.ObjectID;

public abstract class EnemyWeapon extends Enemy {
	
	public int weaponLevel = 1;
	

	public EnemyWeapon(float x, float y, float width, float height, float health, ObjectID id, int idNum, BasicGameState state) {
		super(x, y, width, height, health, id, idNum, state);
	}
	public int getWeaponLevel() {
		return weaponLevel;
	}
	public void setWeaponLevel(int level) {
		weaponLevel = level;
	}
}
