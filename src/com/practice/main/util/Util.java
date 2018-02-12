package com.practice.main.util;

import org.newdawn.slick.Color;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class Util {

	public static float clamp(float min, float max, float var) {
		if(var >= max) {
			return max;
		} else if (var <= min) {
			return min;
		}
		else {
			return var;
		}
	}
	
	public static boolean listContainsPoint(List<Point> list, Point p) {
		for(Point point : list) {
			if(p.equals(point)) {
				return true;
			}
		}
		return false;
	}
	
	public static int randomInt(int digits) {
		int minimum = (int) Math.pow(10, digits - 1); // minimum value with 2 digits is 10 (10^1)
		int maximum = (int) Math.pow(10, digits) - 1; // maximum value with 2 digits is 99 (10^2 - 1)
		Random random = new Random();
		return minimum + random.nextInt((maximum - minimum) + 1);
	}
	
	public static int randomInt(int min, int max) {
		Random random = new Random();
		return random.nextInt(max + 1 - min) + min;
	}
	
	public static Color getGradientColor(int size, int index) {
		int divisionSize = 1400 / size;
		int value = divisionSize * index;
		int red = 0;
		int green = 0;
		int blue = 0;
		switch(value / 255) {
			case 0:
				red = 255;
				blue = value;
				break;
			case 1:
				red = 255 - value % 255;
				blue = 255;
				break;
			case 2:
				green = value % 255;
				blue = 255;
				break;
			case 3:
				green = 255;
				blue = 255 - value % 255;
				break;
			case 4:
				red = value % 255;
				green = 255;
				break;
			default:
				red = 255;
				green = 255;
		}
		return new Color(red, green, blue);
	}
	
}
