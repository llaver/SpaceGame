package com.practice.main.states;

import com.practice.main.Game;
import com.practice.main.Util;
import com.practice.main.entities.GameObject;
import com.practice.main.entities.Handler;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class GameOverState extends BasicGameState {
	
	Handler handler = Game.handler;
	Input input;
	
	boolean pos = false;
	Color col = Color.white;
	int num = 0;
	
	public int timesRan = 0;
	
	private LinkedList<Point> points = new LinkedList<>();
	private boolean runOnce = false;
	
	private String pi = "3" +
		"141592653589793238462643383279502884197169399375105" +
		"82097494459230781640628620899862803482534211706798" +
		"21480865132823066470938446095505822317253594081284" +
		"81117450284102701938521105559644622948954930381964" +
		"42881097566593344612847564823378678316527120190914" +
		"56485669234603486104543266482133936072602491412737" +
		"24587006606315588174881520920962829254091715364367" +
		"89259036001133053054882046652138414695194151160943" +
		"30572703657595919530921861173819326117931051185480" +
		"74462379962749567351885752724891227938183011949129" +
		"83367336244065664308602139494639522473719070217986" +
		"09437027705392171762931767523846748184676694051320" +
		"00568127145263560827785771342757789609173637178721" +
		"46844090122495343014654958537105079227968925892354" +
		"20199561121290219608640344181598136297747713099605" +
		"18707211349999998372978049951059731732816096318595" +
		"02445945534690830264252230825334468503526193118817" +
		"10100031378387528865875332083814206171776691473035" +
		"98253490428755468731159562863882353787593751957781" +
		"85778053217122680661300192787661119590921642019893" +
		"80952572010654858632788659361533818279682303019520" +
		"35301852968995773622599413891249721775283479131515" +
		"57485724245415069595082953311686172785588907509838" +
		"17546374649393192550604009277016711390098488240128" +
		"58361603563707660104710181942955596198946767837449" +
		"44825537977472684710404753464620804668425906949129" +
		"33136770289891521047521620569660240580381501935112" +
		"53382430035587640247496473263914199272604269922796" +
		"78235478163600934172164121992458631503028618297455" +
		"57067498385054945885869269956909272107975093029553" +
		"21165344987202755960236480665499119881834797753566" +
		"36980742654252786255181841757467289097777279380008" +
		"16470600161452491921732172147723501414419735685481" +
		"61361157352552133475741849468438523323907394143334" +
		"54776241686251898356948556209921922218427255025425" +
		"68876717904946016534668049886272327917860857843838" +
		"27967976681454100953883786360950680064225125205117" +
		"39298489608412848862694560424196528502221066118630" +
		"67442786220391949450471237137869609563643719172874" +
		"67764657573962413890865832645995813390478027590099" +
		"46576407895126946839835259570982582262052248940772" +
		"67194782684826014769909026401363944374553050682034" +
		"96252451749399651431429809190659250937221696461515" +
		"70985838741059788595977297549893016175392846813826" +
		"86838689427741559918559252459539594310499725246808" +
		"45987273644695848653836736222626099124608051243884" +
		"39045124413654976278079771569143599770012961608944" +
		"16948685558484063534220722258284886481584560285060" +
		"16842739452267467678895252138522549954666727823986" +
		"45659611635488623057745649803559363456817432411251" +
		"50760694794510965960940252288797108931456691368672" +
		"28748940560101503308617928680920874760917824938589" +
		"00971490967598526136554978189312978482168299894872" +
		"26588048575640142704775551323796414515237462343645" +
		"42858444795265867821051141354735739523113427166102" +
		"13596953623144295248493718711014576540359027993440" +
		"37420073105785390621983874478084784896833214457138" +
		"68751943506430218453191048481005370614680674919278" +
		"19119793995206141966342875444064374512371819217999" +
		"8391015919561814675142691239748940907186494231961";
	
	Random r = new Random();
	
	private int width, height;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		input = container.getInput();
		width = container.getWidth();
		height = container.getHeight();
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		//g.setColor(Color.white);
		Circle c = new Circle(width / 2, height / 2, 250);
		Point[] points = new Point[10];
		Circle[] subs = new Circle[10];
		
		ArrayList<Point> ap = new ArrayList<>();
		
		//System.out.println("circle number of points: " + c.getPointCount());
		
		//System.out.println("getPoints.length: " + points.length);
		int j = 0;
		for(int i = 0; i < c.getPointCount() - 5; i += 5) {
			//subs[j] = new Circle(c.getPoint(i)[0], c.getPoint(i)[1], 75);
			//g.draw(subs[j]);
			j++;
		}
		
		for(int i = 0; i < 10; i++) {
			int degs = 36 * (i + 1);
			//System.out.println("degs: " + degs);
			points[i] = PointOnCircle(250, degs, new Point(width / 2, height / 2));
			subs[i] = new Circle(points[i].x, points[i].y, 75);
			//g.draw(subs[i]);
		}
		
		for(int i = 0; i < subs.length; i++) {
			for(int k = 0; k < 8; k++) {
				ap.add(PointOnCircle(75, 45 * (k + 1), new Point((int) subs[i].getCenterX(), (int) subs[i].getCenterY())));
			}
		}
		
		if(!runOnce) {
			for(int i = 0; i < subs.length; i++) {
				Point[] ps = new Point[36];
				
				for(int k = 0; k < 8; k++) {
					ps[k] = PointOnCircle(45, 10 * (k + 1), new Point((int) subs[i].getCenterX(), (int) subs[i].getCenterY()));
					for(int l = 0; l < ap.size(); l++) {
				//g.drawLine(ps[k].x, ps[k].y, ps[getRoundedIndex(ps.length, 20, k)].x, ps[getRoundedIndex(ps.length, 20, k)].y);
				if(k <= ps.length) {
					g.drawLine(ps[k].x, ps[k].y, subs[subs.length - 1].getX(), subs[subs.length - 1].getY());
				} else {
					g.drawLine(ps[k].x, ps[k].y, subs[k].getX(), subs[k].getY());
				}
						
						Point p1 = new Point(ps[k].x, ps[k].y);
						Point p2 = new Point(ap.get(l).x, ap.get(l).y);
						if(!Util.listContainsPoint(this.points, p1)) {
							this.points.add(p1);
						}
						if(!Util.listContainsPoint(this.points, p2)) {
							this.points.add(p2);
						}
						//g.drawLine(ps[k].x, ps[k].y, ap.get(l).x, ap.get(l).y);
					}
				}
			}
			for(int i = 0; i < this.points.size() - 1; i++) {
				if(num > 3) {
					if(col.getRed() == 255 && col.getBlue() == 255 && col.getGreen() == 255) {
						pos = false;
					} else if(col.getRed() == 0 && col.getBlue() == 0 && col.getGreen() == 0) {
						pos = true;
					}
					
					col = loopColor(col, pos);
					g.setColor(col);
					num = 0;
				}
				num++;
				//g.drawLine(this.points.get(i).x, this.points.get(i).y, this.points.get(i + 1).x, this.points.get(i + 1).y);
				
			}
		}
		g.setColor(Color.green);
		//g.draw(c);
		
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// TODO Auto-generated method stub
		
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
				b++;
			} else if(r >= 255) {
				g++;
			} else {
				r++;
			}
		} else if(!positive) {
			if(r <= 0 && g <= 0) {
				b--;
			} else if(r <= 0) {
				g--;
			} else {
				r--;
			}
		}
		System.out.println("r: " + r + " g: " + g + " b: " + b);
		return new Color(r, g, b);
	}
	
	public int getRoundedIndex(int length, int toAdd, int index) {
		if(index + toAdd >= length) {
			return toAdd - (length - index) + 1;
		}
		return index + toAdd;
	}
	
	public static Point PointOnCircle(float radius, float angleInDegrees, Point origin) {
		// Convert from degrees to radians via multiplication by PI/180
		float x = (float) (radius * Math.cos(angleInDegrees * Math.PI / 180F)) + origin.x;
		float y = (float) (radius * Math.sin(angleInDegrees * Math.PI / 180F)) + origin.y;
		
		return new Point((int) x, (int) y);
	}
	
}
