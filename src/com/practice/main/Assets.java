package com.practice.main;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;

public class Assets {

	//Sprite Sheets
	public static SpriteSheet EnemyShips;
	public static SpriteSheet PlayerShips;

	//Sprites
	public static Image Player;
	public static Image PlayerUpgraded;

	public static Image Recon1;
	public static Image Recon2;
	public static Image Recon3;
	
	public static Image Interceptor1;
	public static Image Interceptor2;
	public static Image Interceptor3;
	
	public static Image Assault1;
	public static Image Assault2;
	public static Image Assault3;

	public static Image ButtonStart;
	public static Image ButtonHelp;
	public static Image ButtonCredits;
	public static Image ButtonQuit;

	public static Image TitleImage;

	//Audio
	public static Audio Theme;

	public static void load() {
		
		try {
			EnemyShips = new SpriteSheet("res/img/enemies/EnemyShips01.png", 32, 32);
			PlayerShips = new SpriteSheet("res/img/player/Playership.png", 32, 32);

			PlayerShips = new SpriteSheet("res/img/player/Playership.png", 32, 32);

			ButtonStart = new Image("res/img/GUI/Buttons/Start.png");
			ButtonHelp = new Image("res/img/GUI/Buttons/Help.png");
			ButtonCredits = new Image("res/img/GUI/Buttons/Credits.png");
			ButtonQuit = new Image("res/img/GUI/Buttons/Quit.png");

			TitleImage = new Image("res/img/GUI/Title/StellaViatoremTitle.png");

			Player = PlayerShips.getSprite(0, 1);
			PlayerUpgraded = PlayerShips.getSprite(0, 0);

			Recon1 = EnemyShips.getSprite(0, 0);
			Recon2 = EnemyShips.getSprite(1, 0);
			Recon3 = EnemyShips.getSprite(2, 0);
			
			Interceptor1 = EnemyShips.getSprite(0, 1);
			Interceptor2 = EnemyShips.getSprite(1, 1);
			Interceptor3 = EnemyShips.getSprite(2, 1);
			
			Assault1 = EnemyShips.getSprite(0, 2);
			Assault2 = EnemyShips.getSprite(1, 2);
			Assault3 = EnemyShips.getSprite(2, 2);

			Theme = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/sound/StellaViatoremTheme.wav"));
		} catch(Exception e) {
			System.out.println("Shit.. " + e.toString());
			//e.printStackTrace();
		}
	}
}
