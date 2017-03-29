package com.practice.main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.practice.main.entities.Handler;
import com.practice.main.states.GameOverState;
import com.practice.main.states.GameState;
import com.practice.main.states.MenuState;
import com.practice.main.states.PauseState;

public class Game extends StateBasedGame {

	public static Handler handler = new Handler();
	
	private Game(String name) {
		super(name);
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Game("Practice Game"));
		
		app.setDisplayMode(800, 600, false);
		app.setAlwaysRender(true);
		
		app.start();		
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.addState(new MenuState());
		this.addState(new GameState());
		this.addState(new PauseState());
		this.addState(new GameOverState());
	}

}
