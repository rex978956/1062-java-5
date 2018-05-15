package painter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class Square extends Shape{
    public Square(int x1, int y1, int x2, int y2, Color color, boolean filled) {
    	super(x1,y1,x2,y2,color,filled);
    }
       
    int tx2, tx3, tx4;
    int ty2, ty3, ty4; 

	@Override
	public void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(color);
		Polygon poly = null;
		
		int px1 = Math.min(x1,x2);
		int py1 = Math.min(y1,y2);
		
		if(Math.abs(x1-x2)>Math.abs(y1-y2)) {
			tx2 = px1+ Math.abs(y1-y2);
			ty2 = py1;
			tx3 = tx2;
			ty3 = Math.max(y1, y2);
			tx4 = px1;
			ty4 = ty3;
		}else if(Math.abs(y1-y2)>Math.abs(x1-x2)) {
			tx2 = Math.max(x1,x2);
			ty2 = py1;
			tx3 = tx2;
			ty3 = py1+Math.abs(x1-x2);
			tx4 = px1;
			ty4 = ty3;			
		}
		
		int px2 = tx2;
		int py2 = ty2;
		int px3 = tx3;
		int py3 = ty3;
		int px4 = tx4;
		int py4 = ty4;
		
		poly = new Polygon(
				new int[] {px1,px2,px3,px4},
				new int[] {py1,py2,py3,py4},
				4);
		
		if(filled) {
			g2d.fill(poly);
		}else {
			g2d.draw(poly);
		}
	}
}
