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
	//Player
	public static Image Player;
	public static Image PlayerUpgraded;
	
	public static Image PlayerShip;
	public static Image PlayerShipSuper;

	public static Image Recon1;
	public static Image Recon2;
	public static Image Recon3;
	
	public static Image Interceptor1;
	public static Image Interceptor2;
	public static Image Interceptor3;
	
	public static Image Assault1;
	public static Image Assault2;
	public static Image Assault3;
	
	//Galugor Faction
	//Large Sprites
	public static Image GalugorFrigate;
	
	//Small Sprites
	//Drones
	public static Image GalugorDroneCombat;
	public static Image GalugorDroneScout;
	
	//Mk1
	public static Image GalugorBomber;
	public static Image GalugorFigter;
	public static Image GalugorInterceptor;
	public static Image GalugorScout;
	
	//Mk2
	public static Image GalugorBomberMk2;
	public static Image GalugorFigterMk2;
	public static Image GalugorInterceptorMk2;
	public static Image GalugorScoutMk2;

	//GUI
	//Title Buttons
	public static Image ButtonStart;
	public static Image ButtonHelp;
	public static Image ButtonCredits;
	public static Image ButtonQuit;
	
	//Lives
	public static Image Lives;
	
	//Title
	public static Image TitleImage;

	//Audio
	public static Audio Theme;

	public static void load() {
		
		try {
			
			//SpriteSheets
			
			EnemyShips = new SpriteSheet("res/img/enemies/EnemyShips.png", 32, 32);
			PlayerShips = new SpriteSheet("res/img/player/Playerships.png", 32, 32);
			
			PlayerShip = new Image("res/img/player/PlayerShip.png");
			PlayerShipSuper = new Image("res/img/player/PlayerShipSuper.png");
			
			
			//Individual Sprites
			
			
			//Galugor Faction
			//Large Sprites
			GalugorFrigate = new Image("res/img/enemies/galugorfaction/largeships/GalugorFrigate.png");
			
			//Small Sprites
			//Drones
			GalugorDroneCombat = new Image("res/img/enemies/galugorfaction/smallships/drones/GalugorDroneCombat.png");
			GalugorDroneScout = new Image("res/img/enemies/galugorfaction/smallships/drones/GalugorDroneScout.png");
			
			//Mk1
			GalugorBomber = new Image("res/img/enemies/galugorfaction/smallships/mk1/GalugorBomber.png");
			GalugorFigter = new Image("res/img/enemies/galugorfaction/smallships/mk1/GalugorFighter.png");
			GalugorInterceptor = new Image("res/img/enemies/galugorfaction/smallships/mk1/GalugorInterceptor.png");
			GalugorScout = new Image("res/img/enemies/galugorfaction/smallships/mk1/GalugorScout.png");
			
			//Mk2
			GalugorBomberMk2 = new Image("res/img/enemies/galugorfaction/smallships/mk2/GalugorBomberMk2.png");
			GalugorFigterMk2 = new Image("res/img/enemies/galugorfaction/smallships/mk2/GalugorFighterMk2.png");
			GalugorInterceptorMk2 = new Image("res/img/enemies/galugorfaction/smallships/mk2/GalugorInterceptorMk2.png");
			GalugorScoutMk2 = new Image("res/img/enemies/galugorfaction/smallships/mk2/GalugorScoutMk2.png");
			
			//GUI
			//Title Buttons
			ButtonStart = new Image("res/img/GUI/buttons/Start.png");
			ButtonHelp = new Image("res/img/GUI/buttons/Help.png");
			ButtonCredits = new Image("res/img/GUI/buttons/Credits.png");
			ButtonQuit = new Image("res/img/GUI/buttons/Quit.png");

			//Lives
			TitleImage = new Image("res/img/GUI/lives/Lives.png");
			
			//Title Image
			TitleImage = new Image("res/img/GUI/title/StellaViatoremTitle.png");

			//Sprites from Sprite Sheets
			//Player Ships
			Player = PlayerShips.getSprite(0, 1);
			PlayerUpgraded = PlayerShips.getSprite(0, 0);

			//Enemy Ships
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
