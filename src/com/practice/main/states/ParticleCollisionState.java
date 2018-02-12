package com.practice.main.states;

import com.practice.main.Game;
import com.practice.main.entities.Atom;
import com.practice.main.entities.GameObject;
import com.practice.main.entities.Particle;
import com.practice.main.util.IDTracker;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rbell on 6/14/2017.
 */
public class ParticleCollisionState extends BasicGameState implements KeyListener {
	
	public static List<GameObject> objects = new ArrayList<>();
	public static List<Atom> atoms = new ArrayList<>();
	public static List<Particle> particles = new ArrayList<>();
	
	private boolean reset = false;
	
	@Override
	public int getID() {
		return 0;
	}
	
	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		atoms.add(new Atom(10, Game.HEIGHT / 2, 1, 0, 1, 0, 5, 5, 5, IDTracker.claimNextID(), this));
		atoms.add(new Atom(Game.WIDTH - 10, Game.HEIGHT / 2, -1, 0, -1, 0, 5, 5, 5, IDTracker.claimNextID(), this));
		
		
		objects.addAll(atoms);
	}
	
	@Override
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_SPACE) {
			reset = true;
		}
	}
	
	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
		for(GameObject object : objects) {
			object.render(g);
		}
	}
	
	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
		if(reset) {
			atoms.clear();
			
			atoms.add(new Atom(10, Game.HEIGHT / 2, 1, 0, 1, 0, 5, 5, 5, IDTracker.claimNextID(), this));
			atoms.add(new Atom(Game.WIDTH - 10, Game.HEIGHT / 2, -1, 0, -1, 0, 5, 5, 5, IDTracker.claimNextID(), this));
			
			reset = false;
		}
		objects.clear();
		particles.clear();
		
		for(Atom atom : atoms) {
			particles.addAll(atom.getParticles());
		}
		
		objects.addAll(atoms);
		objects.addAll(particles);

		for(Atom atom : atoms) {
			atom.update();
		}
		
	}
}
