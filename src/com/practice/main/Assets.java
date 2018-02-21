package com.practice.main;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Assets {
	
	public static SpriteSheet EnemyShips;
	
	public static Image Player;
	
	public static Image Recon1;
	public static Image Recon2;
	public static Image Recon3;
	
	public static Image Interceptor1;
	public static Image Interceptor2;
	public static Image Interceptor3;
	
	public static Image Assault1;
	public static Image Assault2;
	public static Image Assault3;
	
	
	public static void load() {
		
		try {
			EnemyShips = new SpriteSheet("res/img/enemies/EnemyShips01.png", 32, 32);
			
			//TODO Replace temp Player with updated.
			Player = EnemyShips.getSprite(2, 2);
		
			Recon1 = EnemyShips.getSprite(0, 0);
			Recon2 = EnemyShips.getSprite(1, 0);
			Recon3 = EnemyShips.getSprite(2, 0);
			
			Interceptor1 = EnemyShips.getSprite(0, 1);
			Interceptor2 = EnemyShips.getSprite(1, 1);
			Interceptor3 = EnemyShips.getSprite(2, 1);
			
			Assault1 = EnemyShips.getSprite(0, 2);
			Assault2 = EnemyShips.getSprite(1, 2);
			Assault3 = EnemyShips.getSprite(2, 2);
		
		
		} catch(SlickException e) {
			System.out.println("Shit.. " + e.toString());
			//e.printStackTrace();
		}
		
		
	}
	
}
