package com.practice.main;

import com.practice.main.states.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.practice.main.entities.Handler;

public class Game extends StateBasedGame {

	public static Handler handler = new Handler();
	public static int WIDTH = 1600;
	public static int HEIGHT = 900;
	
	private Game(String name) {
		super(name);
	}
	
	public static Input input;

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Game("Practice Game"));
		
		app.setDisplayMode(WIDTH, HEIGHT, false);
		app.setAlwaysRender(true);
		
		app.start();
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		Assets.load();
		
		input = container.getInput();
		
		this.addState(new MenuState());
		this.addState(new GameState());
		this.addState(new PauseState());
		//this.addState(new LinesState());
		//this.addState(new RandomMovementState());
		//this.addState(new TestState());
		//this.addState(new SpirographState());
		//this.addState(new GravityMovementState());
		//this.addState(new RandomMovementState());
		//this.addState(new ParticleCollisionState());
	}

}
