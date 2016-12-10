package com.practice.main.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PauseState extends BasicGameState {

	Input input;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		input = container.getInput();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.drawString("Game Paused", container.getWidth() / 2 - 70, 100);
		
		//Resume
		g.drawRect(300, 300, 200, 75);
		//Quit Game
		g.drawRect(300, 400, 200, 75);
		
		g.drawString("Resume", 380, 325);
		g.drawString("Quit", 380, 425);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		if(input.isMousePressed(0)) {
			System.out.println("Mouse pressed - MouseX: " + input.getMouseX() + " MouseY: " + input.getMouseY());
			if(input.getMouseX() <= 500 && input.getMouseX() >= 300) {
				if(input.getMouseY() <= 375 && input.getMouseY() >= 300) {
					//Resume
					game.enterState(1);
				} else if(input.getMouseY() <= 475 && input.getMouseY() >= 400) {
					//quit
					game.enterState(0);
					
				}
			}
		}
		
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}

}
