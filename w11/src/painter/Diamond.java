package painter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class Diamond extends Shape{
	
	private int tx1,tx2,tx3,tx4;
	private int ty1,ty2,ty3,ty4;

	public Diamond(int x1, int y1, int x2, int y2, Color color, boolean filled) {
		super(x1,x2,y1,y2,color,filled);
	}
	
	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Polygon poly = null; 
		g2d.setColor(color);
	    		
		if(Math.abs(x1-x2)>Math.abs(y1-y2)) {
			tx1 = Math.min(x1, x2) + Math.abs(y1-y2)%2;
			ty1 = Math.min(y1, y2);
			tx2 = Math.min(x1, x2) + Math.abs(y1-y2)%2;
			ty2 = Math.abs(y1-y2);
			tx3 = tx1;
			ty3 = Math.max(y1, y2);
			tx4 = Math.min(x1, x2);
			ty4 = ty2;
		} else if(Math.abs(y1-y2) > Math.abs(x1-x2)) {
			tx1 = Math.min(x1, x2) + Math.abs(x1-y2)%2;
			ty1 = Math.min(y1, y2);
			tx2 = Math.max(x1, x2);
			ty2 = Math.min(y1, y2) + Math.abs(x1-x2)%2;
			tx3 = tx1;
			ty3 = Math.max(y1, y2);
			tx4 = Math.min(x1, x2);
			ty4 = ty2;
		}
		
		int px1 = tx1;
		int py1 = ty1;
		int px2 = tx2;
		int py2 = ty2;
		int px3 = tx3;
		int py3 = ty3;
		int px4 = tx4;
		int py4 = ty4;
		
		poly = new Polygon(
				new int[] {px1, px2, px3, px4},
				new int[] {py1, py2, py3, py4},
				4);
		
		if(filled) {
			g2d.fill(poly);
		}else {
			g2d.draw(poly);
		}
		
		
	}

}
