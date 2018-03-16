package com.practice.main.states;

import com.practice.main.Assets;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PauseState extends BasicGameState {
	
	Input input;
	
	private Image ButtonStart;
	private Image ButtonQuit;
	private Image TitleImage;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		input = container.getInput();
		
		ButtonStart = Assets.ButtonStart;
		ButtonQuit = Assets.ButtonQuit;
		
		TitleImage = Assets.TitleImage;
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.drawString("Game Paused", container.getWidth() / 2 - 50, 50);
		
		
		//Title
		g.drawImage(TitleImage.getScaledCopy(480, 160), container.getWidth() / 2 - 240, 100);
		
		//Play Game
		g.drawImage(ButtonStart.getScaledCopy(200, 75), container.getWidth() / 2 - 100, 300);
		//Quit Game
		g.drawImage(ButtonQuit.getScaledCopy(200, 75), container.getWidth() / 2 - 100, 400);
		
		
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if(input.isMousePressed(0)) {
			System.out.println("Mouse pressed - MouseX: " + input.getMouseX() + " MouseY: " + input.getMouseY());
			if(input.getMouseX() <= container.getWidth() / 2 + 100 && input.getMouseX() >= container.getWidth() / 2 - 100) {
				if(input.getMouseY() <= 375 && input.getMouseY() >= 300) {
					//Resume game
					game.enterState(State.GAME);
				} else if(input.getMouseY() <= 475 && input.getMouseY() >= 400) {
					//quit
					game.enterState(State.MENU);
				}
			}
		}
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return State.PAUSE;
	}
	
}
