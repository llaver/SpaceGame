package com.practice.main.states;

import com.practice.main.Game;
import com.practice.main.OpenSimplexNoise;
import com.practice.main.Pathing;
import com.practice.main.Position;
import com.practice.main.astar.Map;
import com.practice.main.astar.RoadNode;
import com.practice.main.astar.RoadNodeFactory;
import com.practice.main.entities.*;
import com.practice.main.util.IDTracker;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GravityMovementState extends BasicGameState implements KeyListener {
	
	Handler handler = Game.handler;
	Input input;
	
	Random r = new Random();
	
	ArrayList<Point> points = new ArrayList();
	StellarBody s3;
	
	int[] tracker = new int[10];
	int posTrack = 0;
	
	public static int width, height;
	private int x, y, oldX, oldY;
	private boolean runOnce = false;
	
	private int maxStepLength;
	
	public static List<GameObject> objects = new ArrayList<>();
	public static List<Atom> atoms = new ArrayList<>();
	public static List<Particle> particles = new ArrayList<>();
	
	private boolean reset = false;
	
	Pathing p = new Pathing();
	ArrayList<Position> line;
	
	Position current = new Position();
	Position previous = new Position();
	private OpenSimplexNoise noise = new OpenSimplexNoise(r.nextLong());
	public static double[][] map = new double[32][32];
	
	private Map<RoadNode> roadMap = new Map<RoadNode>(32, 32, new RoadNodeFactory());
	private List<RoadNode> path;
	
	Ellipse e;
	Shape s;
	float deg = 0;
	int spiroTracker = 0;
	int waitTracker = 0;
	boolean pos = true;
	
	ArrayList<Shape> al = new ArrayList<>();
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		width = container.getWidth();
		height = container.getHeight();
		System.out.println(width + " " + height);
		
		RandomPath rp = new RandomPath();
		RandomPath rp2 = new RandomPath();
		RandomPath rp3 = new RandomPath();
		
		LineObj lo = new LineObj();
		
		//GravityObj go = new GravityObj(100, 500, 50, ObjectID.GravityObj, this);
		
		//Sun
		StellarBody s1 = new StellarBody(300, 400,(float) 1.98892 * (10*30), 10, 0, ObjectID.StellarBody, this);
		s1.setVelY((float) -.000003);
		s1.setVelX((float) -.000003);
		//Earth
		StellarBody s2 = new StellarBody(350, 70,(float) 5.9742 * (10*24), 5, 0, ObjectID.StellarBody, this);
		s2.setVelY((float) .000001);
		s2.setVelX((float) .000005);
		//Black Hole!!!!!!
		s3 = new StellarBody(350, 300,(float) 5.9742 * (10*200), 30, 0, ObjectID.StellarBody, this);
		
		
		//Sun
		StellarBody s4 = new StellarBody(2, 678,(float) 1.98892 * (10*12), 10, 0, ObjectID.StellarBody, this);
		s4.setVelY((float) -.000003);
		s4.setVelX((float) -.000003);
		//Earth
		StellarBody s5 = new StellarBody(350, 34,(float) 5.9742 * (10*30), 5, 0, ObjectID.StellarBody, this);
		s5.setVelY((float) .000001);
		s5.setVelX((float) .000005);
		//Sun
		StellarBody s6 = new StellarBody(300, 400,(float) 1.98892 * (10*35), 10, 0, ObjectID.StellarBody, this);
		s6.setVelY((float) -.000003);
		s6.setVelX((float) -.000003);
		//Earth
		StellarBody s7 = new StellarBody(0, 500,(float) 5.9742 * (10*40), 5, 0, ObjectID.StellarBody, this);
		s7.setVelY((float) .000001);
		s7.setVelX((float) .000005);
		//Sun
		StellarBody s8 = new StellarBody(100, 700,(float) 1.98892 * (10*25), 10, 0, ObjectID.StellarBody, this);
		s8.setVelY((float) -.000003);
		s8.setVelX((float) -.000003);
		//Earth
		StellarBody s9 = new StellarBody(500, 500,(float) 5.9742 * (10*29), 5, 0, ObjectID.StellarBody, this);
		s9.setVelY((float) .000001);
		s9.setVelX((float) .000005);
		
		handler.addObject(rp);
		handler.addObject(rp2);
		handler.addObject(rp3);
		handler.addObject(lo);
		//handler.addObject(go);
		handler.addObject(s1);
		handler.addObject(s2);
		handler.addObject(s3);
		handler.addObject(s4);
		handler.addObject(s5);
		handler.addObject(s6);
		handler.addObject(s7);
		handler.addObject(s8);
		handler.addObject(s9);
		
		for(int i = 0; i < map.length; i++) {
			//System.out.print("\n*");
			for(int k = 0; k < map[i].length; k++) {
				map[i][k] = noise.eval(i * .25, k * .15);
				//System.out.println("i10: " + i * 10 + " k10: " + k * 10 + " 1i10: " + (i + 1) * 10 + " 1k10: " + (k + 1) * 10);
				//System.out.println(map[i][k]);
			}
		}
		path = roadMap.findPath(0, 31, 31, 0);
		roadMap.drawMap(path);
		
		atoms.add(new Atom(10, Game.HEIGHT / 2, 1, 0, 1, 0, 5, 5, 5, IDTracker.claimNextID(), this));
		atoms.add(new Atom(Game.WIDTH - 10, Game.HEIGHT / 2, -1, 0, -1, 0, 5, 5, 5, IDTracker.claimNextID(), this));
		
		objects.addAll(atoms);
		
		e = new Ellipse(1400, 300, 40, 150);
		s = e;
		
	}
	
	@Override
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_SPACE) {
			reset = true;
		}
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		handler.update(delta);
		s3.setVel(0, 0);
		
		if(reset) {
			atoms.clear();
			
			atoms.add(new Atom(10, Game.HEIGHT / 2, 1, 0, 1, 0, 5, 5, 5, IDTracker.claimNextID(), this));
			atoms.add(new Atom(Game.WIDTH - 10, Game.HEIGHT / 2, -1, 0, -1, 0, 5, 5, 5, IDTracker.claimNextID(), this));
			
			reset = false;
		}
		objects.clear();
		particles.clear();
		
		for(Atom atom : atoms) {
			particles.addAll(atom.getParticles());
		}
		
		objects.addAll(atoms);
		objects.addAll(particles);
		
		for(Atom atom : atoms) {
			atom.update(delta);
		}
		
		if(pos) {
			if(spiroTracker > 10) {
				s = e.transform(Transform.createRotateTransform(deg, 1400, 300));
				if(al.size() > 170) {
					if(waitTracker < 300) {
						waitTracker++;
					} else {
						pos = false;
						waitTracker = 0;
					}
				} else {
					al.add(s);
					deg += 2;
					spiroTracker = 0;
				}
			}
		} else {
			if(spiroTracker > 20) {
				s = e.transform(Transform.createRotateTransform(deg, 1400, 300));
				if(al.size() <= 0) {
					if(waitTracker < 50) {
						waitTracker++;
					} else {
						pos = true;
						waitTracker = 0;
					}
				} else {
					al.remove(al.size() - 1);
				}
				spiroTracker = 0;
			}
		}
		spiroTracker++;
		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		for(int i = 0; i < map.length; i++) {
			for(int k = 0; k < map[i].length; k++) {
				float current = (float) map[i][k];
				//System.out.println(current / 100);
				byte col = (byte) (256 * map[i][k]);
				g.setColor(new Color(col, col, col));
				//g.fillRect(i * 10, k * 10, (i + 1) * 10, (k + 1) * 10);
				
				if(current >= -.2 && current <= .2 && !path.contains(new RoadNode(i, k))) {
					g.setColor(Color.darkGray);
					//g.fillRect(i, k, i, k);
					g.fillRect(i * 10 + 1300, k * 10 + 600, (i + 1) * 10, (k + 1) * 10);
				} else if(path.contains(new RoadNode(i, k))) {
					g.setColor(Color.green);
					//g.fillRect(i, k, i,k);
					g.fillRect(i * 10 + 1300, k * 10 + 600, (i + 1) * 10, (k + 1) * 10);
				} else {
					g.setColor(Color.black);
					//g.fillRect(i, k, i, k);
					g.fillRect(i * 10 + 1300, k * 10 + 600, (i + 1) * 10, (k + 1) * 10);
				}
			}
		}
		
		g.setColor(Color.white);
		g.drawString("Fuck your Lol", 1350, 100);
		for(int i = 0; i < al.size(); i++) {
			
			g.setColor(getColor(170, i));
			g.draw(al.get(i));
		}
		
		handler.render(g);
		
		for(GameObject object : objects) {
			object.render(g);
		}
		
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 3;
	}
	
	public Color loopColor(Color color, boolean positive) {
		int r = color.getRed();
		int g = color.getGreen();
		int b = color.getBlue();
		
		if(positive) {
			if(r >= 255 && g >= 255) {
				b += 5;
			} else if(r >= 255) {
				g += 5;
			} else {
				r += 5;
			}
		} else if(!positive) {
			if(r <= 0 && g <= 0) {
				b -= 5;
			} else if(r <= 0) {
				g -= 5;
			} else {
				r -= 5;
			}
		}
		System.out.println("r: " + r + " g: " + g + " b: " + b);
		return new Color(r, g, b);
	}
	
	public Color getColor(int size, int index) {
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
	
	public int getRoundedIndex(int length, int toAdd, int index) {
		if(index + toAdd >= length) {
			return toAdd - (length - index) + 1;
		}
		return index + toAdd;
	}
	
	public static java.awt.Point PointOnCircle(float radius, float angleInDegrees, java.awt.Point origin) {
		// Convert from degrees to radians via multiplication by PI/180
		float x = (float) (radius * Math.cos(angleInDegrees * Math.PI / 180F)) + origin.x;
		float y = (float) (radius * Math.sin(angleInDegrees * Math.PI / 180F)) + origin.y;
		
		return new java.awt.Point((int) x, (int) y);
	}
	
	public LinkedList<Point> shortenList(LinkedList<Point> list, int num) {
		LinkedList<Point> temp = new LinkedList<>();
		int track = 0;
		for(int i = 0; i < list.size(); i++) {
			if(track == num) {
				temp.add(list.get(i));
				track = 0;
			}
			track++;
		}
		return temp;
	}
}
