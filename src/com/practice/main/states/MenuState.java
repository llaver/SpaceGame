package com.practice.main.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.practice.main.Game;
import com.practice.main.entities.Handler;

public class MenuState extends BasicGameState {

	Handler handler = Game.handler;
	Input input;
	
	private boolean isHelp = false;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		input = container.getInput();
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		handler.renderByState(g, this);
		
		g.setColor(Color.white);
		
		if(!isHelp) {
			g.drawString("Practice Game", container.getWidth() / 2 - 70, 100);
			
			//Play Game
			g.drawRect(300, 200, 200, 75);
			//Help
			g.drawRect(300, 300, 200, 75);
			//Quit Game
			g.drawRect(300, 400, 200, 75);
					
			g.drawString("Play Game", 360, 225);
			g.drawString("Help", 380, 325);
			g.drawString("Quit", 380, 425);
			
		} else {
			g.drawString("Help Menu", container.getWidth() / 2 - 70, 100);
			
			//Back Button
			g.drawRect(300, 400, 200, 75);
			g.drawString("Back", 380, 425);
		}
		
		
		
		
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// TODO Auto-generated method stub
		
		
		if(!isHelp) {
			
			if(input.isMousePressed(0)) {
				System.out.println("Mouse pressed - MouseX: " + input.getMouseX() + " MouseY: " + input.getMouseY());
				if(input.getMouseX() <= 500 && input.getMouseX() >= 300) {
					if(input.getMouseY() <= 275 && input.getMouseY() >= 200) {
						//play game
						game.enterState(1);
						
					} else if(input.getMouseY() <= 375 && input.getMouseY() >= 300) {
						//help
						isHelp = true;
					} else if(input.getMouseY() <= 475 && input.getMouseY() >= 400) {
						//quit
						container.exit();
						
					}
				}
			}
			
		} else {
			
			if(input.isMousePressed(0)) {
				if(input.getMouseX() <= 500 && input.getMouseX() >= 300) {
					if(input.getMouseY() <= 475 && input.getMouseY() >= 400) {
						//back
						isHelp = false;
						
					}
				}
			}
			
		}
		
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
