package com.practice.main;

import java.awt.*;
import java.util.List;

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
	
}
