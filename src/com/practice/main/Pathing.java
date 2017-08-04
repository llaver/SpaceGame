package com.practice.main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rbell on 8/3/2017.
 */
public class Pathing {
	
	private double stepSize; //Distance between points
	private Position position; //Position of path
	private double angle; //Angle of turn
	private double deltaRange; //Change in range
	private double deltaAngle; //Change in angle
	private int updateCount = 0;
	
	public Pathing() {
		position = new Position(0,0);
		angle  = Math.random() * (Math.PI * 2);
		deltaRange = 0.05f;
		deltaAngle = 0;
		stepSize = 5f;
	}
	
	public Pathing(float posStep, float angle) {
		this.stepSize = posStep;
		this.angle = angle;
	}
	
	public <T> ArrayList<Position> generatePath(boolean firstRun) {
		ArrayList<Position> line = new ArrayList<>();
		
		if(firstRun) {
			//TODO Add Straight Section
		}
		int count = 0;
		while(count < 750) {
			double cor = deltaRange * Math.atan(15 * deltaAngle) / Math.PI;
			double randNum = ((Math.random() * 2) - 1) * deltaRange - cor;  //Random number from (-deltaRange, deltaRange)
			
			
			deltaAngle += randNum;                       //We don't change the angle directly
			//but its differential - source of the smoothness!
			
			boolean watev = true;
			if(count % 500 == 0) {
				watev = !watev;
			}
			if(watev) {
				angle += deltaAngle;                         //new angle is angle+deltaAngle
			} else {
				//angle -= deltaAngle;                         //new angle is angle+deltaAngle
			}
			
			
			
			float x = (float) (position.getX() + (stepSize * Math.cos(angle)));
			float y = (float) (position.getY() + (stepSize * Math.sin(angle)));
			
			if(x < 0){ x *= -1; }
			if(y < 0){ y *= -1; }
			
			//System.out.println("1: " + x + " 2: " + y);
			
			position = new Position(x, y);
			
			line.add(position);
			count++;
		}
		return line;
	}
	
	public Position update(Position previous) {
		
		double cor = deltaRange * Math.atan(15 * deltaAngle) / Math.PI;
		double randNum = ((Math.random() * 2) - 1) * deltaRange - cor;  //Random number from (-deltaRange, deltaRange)
		
		
		deltaAngle += randNum;                       //We don't change the angle directly
		//but its differential - source of the smoothness!
		
		boolean watev = true;
		if(updateCount % 500 == 0) { watev = !watev; }
		if(watev) {
			angle += deltaAngle;                         //new angle is angle+deltaAngle
		} else {
			angle -= deltaAngle;                         //new angle is angle+deltaAngle
		}
		
		
		
		float x = (float) (previous.getX() + (stepSize * Math.cos(angle)));
		float y = (float) (previous.getY() + (stepSize * Math.sin(angle)));
		
		if(x < 0){ x *= -1; }
		if(y < 0){ y *= -1; }
		
		
		position.setX(x);
		position.setY(y);
		
		updateCount++;
		
		return position;
	}

//	void update() {
//		float cor = dRange*atan(15*dAngle)/PI;
//		float randNum = (random(2)-1)*dRange-cor;  //Random number from (-dRange, dRange)
//		dAngle+=randNum;                       //We don't change the angle directly
//		//but its differential - source of the smoothness!
//
//		angle+=dAngle;                         //new angle is angle+dAngle
//
//		pos.x+=posStep*cos(angle);            //just move on step
//		pos.y+=posStep*sin(angle);
//	}

}
