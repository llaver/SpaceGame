package com.practice.main.entities.enemies;

import com.practice.main.entities.GameObject;
import com.practice.main.entities.ObjectID;
import org.newdawn.slick.state.BasicGameState;

/**
 * Created by rbell on 4/12/2017.
 */
public abstract class Enemy extends GameObject {
	public Enemy(float x, float y, float width, float height, float health, ObjectID id, int idNum, BasicGameState state) {
		super(x, y, width, height, health, id, idNum, state);
	}
}
