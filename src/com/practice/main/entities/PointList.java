package com.practice.main.entities;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by rbell on 5/4/2017.
 */
public class PointList extends LinkedList {
	
	LinkedList<Point> list = new LinkedList<>();

	public PointList(LinkedList<Point> l) {
		list = l;
	}
	public void add(Point p) {
		list.add(p);
	}
	public void add(Point p, int i) {
		list.add(i, p);
	}
	public void remove(Point p) {
		for(int i = list.size(); i >= 0; i--) {
			if (list.get(i).equals(p)) {
				list.remove(i);
			}
		}
	}
	public Point getPoint(int index) {
		return list.get(index);
	}
}
