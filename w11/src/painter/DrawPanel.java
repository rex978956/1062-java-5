package painter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.JPanel;

public class DrawPanel extends JPanel implements  MouseListener,MouseMotionListener{

	private static final long serialVersionUID = 1L;

    final int MAX = 100;
    private Shape shapes[];
    private int shapeCount;
    private int x1,y1,x2,y2;
    private Color color;
    private boolean filled;
    private int shapeType;
    
    private Shape currentShape;
    
    final int SHAPE = 5;
	final int SQUA =1;
	final int CRCL =2;
	final int DIAM =3;
	final int SQUA2 =4;
	final int CRCL2 =5;	
    		
    Random rand;
    
	public DrawPanel() {
		super();
		shapes = new Shape[MAX];
        rand = new Random();
        addMouseListener(this);
        addMouseMotionListener(this);
	}
	
	public void Random(int n) {
		for(int i=0; i<n;i++) {
			x1 = rand.nextInt(400);
			y1 = rand.nextInt(400);
			x2 = rand.nextInt(400);
			y2 = rand.nextInt(400);
			color = new Color(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256));
			filled = rand.nextBoolean();
			shapeType = rand.nextInt(SHAPE)+1;
			
			switch(shapeType) {
			case SQUA:		
			    shapes[shapeCount++] = new Square( x1,  y1, x2,  y2,  color, filled);
			    break;
			case CRCL:
				shapes[shapeCount++] = new Circle( x1,  y1,  x2,  y2,  color,  filled);
	            break;		
			case DIAM:
				shapes[shapeCount++] = new Diamond( x1,  y1,  x2,  y2,  color,  filled);
	            break;
			case SQUA2:
				System.out.println("SQUARE2");
				break;
			case CRCL2:
				System.out.println("SQUARE");
				break;
			}
			repaint();
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i=0;i<shapeCount;i++) {
			shapes[i].draw(g);
		}
	}
	
	public void clear() {
		shapeCount = 0;
		repaint();		
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setShape(int shapeType) {
		this.shapeType = shapeType;
		
	}
	
    @Override
	public void mousePressed(MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();
		x2 = x1;
		y2 = y1;
		switch(shapeType) {
		case SQUA:		
		    currentShape = new Square( x1,  y1, x2,  y2,  color, filled);
		    break;
		case CRCL:
			currentShape = new Circle( x1,  y1,  x2,  y2,  color,  filled);
            break;		
		case DIAM:
			currentShape = new Diamond( x1,  y1,  x2,  y2,  color,  filled);
            break;
		case SQUA2:
		//	currentShape = new Square2();
			break;
		case CRCL2:
		//	currentShape = new Circle2();
			break;
		}
	}

    @Override
	public void mouseDragged(MouseEvent e) {
		currentShape.x2 = e.getX();
		currentShape.y2 = e.getY();
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		x2 = e.getX();
		y2 = e.getY();
		
		switch(shapeType) {
		case SQUA:		
		    shapes[shapeCount++] = new Square( x1,  y1, x2,  y2,  color, filled);
		    break;
		case CRCL:
			shapes[shapeCount++] = new Circle( x1,  y1,  x2,  y2,  color,  filled);
            break;		
		case DIAM:
			shapes[shapeCount++] = new Diamond( x1,  y1,  x2,  y2,  color,  filled);
            break;
		case SQUA2:
		//	shapes[shapeCount++] = new Square2();
			break;
		case CRCL2:
		//	shapes[shapeCount++] = new Circle2();
			break;
		}
	}

	

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();
		
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	

	

	

}
