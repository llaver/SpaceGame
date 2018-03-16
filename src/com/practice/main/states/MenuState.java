package com.practice.main.states;

import com.practice.main.Assets;
import org.newdawn.slick.*;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.practice.main.Game;
import com.practice.main.entities.Handler;

public class MenuState extends BasicGameState {

	Handler handler = Game.handler;
	Input input;
	
	private boolean isHelp = false;
	private boolean isCredits = false;

	private Image ButtonStart;
	private Image ButtonHelp;
	private Image ButtonCredits;
	private Image ButtonQuit;

	private Image TitleImage;

	private Audio Theme;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		input = container.getInput();
		ButtonStart = Assets.ButtonStart;
		ButtonHelp = Assets.ButtonHelp;
		ButtonCredits = Assets.ButtonCredits;
		ButtonQuit = Assets.ButtonQuit;
		TitleImage= Assets.TitleImage;

		Theme = Assets.Theme;

		Theme.playAsMusic(1.0f, 1.0f, true);

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		handler.renderByState(g, this);
		
		g.setBackground(new Color(51, 1, 101));
		
		if(!isHelp) {

			//Title
			g.drawImage(TitleImage.getScaledCopy(480, 160), container.getWidth() / 2 - 240, 100);
			
			//Play Game
			g.drawImage(ButtonStart.getScaledCopy(200, 75), container.getWidth() / 2 - 100, 300);
			//Help
			g.drawImage(ButtonHelp.getScaledCopy(200, 75), container.getWidth() / 2 - 100, 400);
			//Credits
			g.drawImage(ButtonCredits.getScaledCopy(200, 75), container.getWidth() / 2 - 100, 500);
			//Quit Game
			g.drawImage(ButtonQuit.getScaledCopy(200, 75), container.getWidth() / 2 - 100, 600);
			
		} else {
			g.drawString("Help Menu", container.getWidth() / 2 - 70, 100);
			
			//Back Button
			g.drawRect(300, 400, 200, 75);
			g.drawString("Back", 380, 425);
		}
		
		
		
		
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// TODO Auto-generated method stub\
		
		if(!isHelp) {
			
			if(input.isMousePressed(0)) {
				System.out.println("Mouse pressed - MouseX: " + input.getMouseX() + " MouseY: " + input.getMouseY());
				if(input.getMouseX() <= container.getWidth() / 2 + 100 && input.getMouseX() >= container.getWidth() / 2 - 100) {


					if(input.getMouseY() <= 375 && input.getMouseY() >= 300) {
						//play game
						game.enterState(State.GAME);
					} else if(input.getMouseY() <= 475 && input.getMouseY() >= 400) {
						//help
						isHelp = true;
					} else if(input.getMouseY() <= 575 && input.getMouseY() >= 500) {
						//Credits
						isCredits = true;
					} else if(input.getMouseY() <= 675 && input.getMouseY() >= 600) {
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

		SoundStore.get().poll(0);
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return State.MENU;
	}

}
