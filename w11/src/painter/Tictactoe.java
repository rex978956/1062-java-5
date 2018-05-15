package painter;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class Tictactoe extends Shape{
    
	int px1,px2,px3,px4;
	int py1,py2,py3,py4;
	int tx2,tx3,tx4;
	int ty2,ty3,ty4;
		
	public Tictactoe(int x1, int y1, int x2, int y2) {
		super(x1, y1, x2, y2);
		
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Polygon poly = null;
		g2d.setColor(color);
		
		px1 = Math.min(x1, x2);
		py1 = Math.min(y1, y2);
		
		if(Math.abs(x1-x2)> Math.abs(y1-y2)) {
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
			
			    px2 = tx2;
			    py2 = ty2;
			    px3 = tx3;
			    py3 = ty3;
			    px4 = tx4;
			    py4 = ty4;
		        
			    poly = new Polygon(
			    	   new int[] {px1,px2,px3,px4},
			    	   new int[] {py1,py2,py3,py4},	   
			    		4);
			    
			    g2d.draw(poly);
			    
			    g.drawLine(px1+(px2)*(1%3), py1, px1+(px2)*(1%3), py3);
			    g.drawLine(px1+(px2)*(2%3), py1, px1+(px2)*(2%3), py3);
			    g.drawLine(px1, py1+(py3)*(1%3), px2, py1+(py3)*(1%3));
			    g.drawLine(px1, py1+(py3)*(2%3), px2, py1+(py3)*(2%3));	
	}
    
}
