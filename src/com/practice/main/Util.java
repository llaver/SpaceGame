package com.practice.main;

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
	
}
