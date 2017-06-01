package com.practice.main.entities;


import org.newdawn.slick.Color;

/**
 * Created by rbell on 5/4/2017.
 */
public class Point extends java.awt.Point {
	
	private int x;
	private int y;
	private Color color;
	private java.awt.Point point;
	
	public Point(int x, int y, Color c) {
		point = new java.awt.Point(x, y);
		this.x = x;
		this.y = y;
		color = c;
	}
	
	public void setPosition(java.awt.Point p) {
		point = p;
		x = p.x;
		y = p.y;
	}
	
	public void setColor(Color c) {
		color = c;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Point)) {
			return false;
		} else {
			return (x == ((Point) o).x && y == ((Point) o).y && color.equals(((Point) o).color));
		}
	}
	
	@Override
	public double getX() {
		return x;
	}
	
	@Override
	public double getY() {
		return y;
	}
	
	public Color getColor() {
		return color;
	}
	
	
}
