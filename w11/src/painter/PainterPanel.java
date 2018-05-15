package painter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PainterPanel extends JPanel implements ActionListener{

	//private static final long serialVersionUID = 1L;
    
	private DrawPanel drawPanel;
	
	private JPanel actionPanel;
	private JButton clear;
	private JButton redo;
	private JButton undo;
	
	private JPanel tttPanel;
	private JButton tictactoe;
	private JButton O;
	private JButton X;
	
	private JPanel shapePanel;
	private JButton Random;
	private JButton Square;
	private JButton Circle;
	private JButton Diamond;
	private JButton Square2;
	private JButton Circle2;	
	
	private JPanel buttonPanel;
	
	private JButton colorbtn;
	private JButton colorbtn2;
	private JPanel ColorPanel;
	
	private Icon colorIcon;
	private Color color;

	final int SQUA = 1;	
	final int CRCL = 2;
	final int DIAM = 3;
	final int SQUA2 = 4;
	final int CRCL2 = 5;	
	
	public PainterPanel() {
		super();
		this.setLayout(new BorderLayout());
		drawPanel = new DrawPanel();
		add(drawPanel,BorderLayout.CENTER);	
		
		shapePanel = new JPanel(new GridBagLayout()); 
		GridBagConstraints gbc = new GridBagConstraints();	
		buttonPanel = new JPanel();	
		
	    tttPanel = new JPanel();
	    
	    tictactoe = new JButton("tictactoe");
	    tictactoe.addActionListener(this);
	    tttPanel.add(tictactoe);
	    
	    O = new JButton("O");
	    O.addActionListener(this);
	    tttPanel.add(O);
	    
	    X = new JButton("X");
	    X.addActionListener(this);
	    tttPanel.add(X);
	    
	    add(tttPanel, BorderLayout.NORTH);
		
	    
		actionPanel = new JPanel();
		
		clear = new JButton ("CLEAR");
		clear.addActionListener(this);
		actionPanel.add(clear);
		
		redo = new JButton ("REDO");
		redo.addActionListener(this);
		actionPanel.add(redo);
		
		undo = new JButton("UNDO");
		undo.addActionListener(this);
		actionPanel.add(undo);
		
		buttonPanel.add(actionPanel);
			
		ColorPanel = new JPanel();
		
		colorIcon = new ImageIcon(getClass().getResource("color.png"));
		colorbtn = new JButton("Color1",colorIcon);
		colorbtn.addActionListener(this);
		ColorPanel.add(colorbtn);
		
		colorbtn2 = new JButton("Color2",colorIcon);
		colorbtn2.addActionListener(this);
		ColorPanel.add(colorbtn2);
		
		buttonPanel.add(ColorPanel);
		
		Random = new JButton("Random");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
	    Random.addActionListener(this);
	    shapePanel.add(Random,gbc);
	    
	    Square = new JButton("Square");
	    gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
	    Square.addActionListener(this);
	    shapePanel.add(Square,gbc);
	    
	    Circle = new JButton("Circle");
	    gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
	    Circle.addActionListener(this);
	    shapePanel.add(Circle,gbc);
	    
	    Diamond = new JButton("Diamond");
	    gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
	    Diamond.addActionListener(this);
	    shapePanel.add(Diamond,gbc);
	    
	    Square2 = new JButton("Square2");
	    gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
	    Square2.addActionListener(this);
	    shapePanel.add(Square2,gbc);
	    
	    Circle2 = new JButton("Circle2");
	    gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
	    Circle2.addActionListener(this);
	    shapePanel.add(Circle2,gbc);
	    
	  
	    buttonPanel.add(shapePanel);
	    
	    add(buttonPanel, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==clear) {
			drawPanel.clear();
		}else if(e.getSource()==Random) {		
			drawPanel.Random(10);
		}else if(e.getSource()==Square) {
		    drawPanel.setColor(color);
		    drawPanel.setShape(SQUA );
		}else if(e.getSource()==Circle) {
			drawPanel.setColor(color);
			drawPanel.setShape(CRCL);
		}else if(e.getSource()==Diamond) {
			drawPanel.setColor(color);
			drawPanel.setShape(DIAM);
		}else if(e.getSource()==Square2) {
			drawPanel.setColor(color);
			drawPanel.setShape(SQUA2);
		}else if(e.getSource()==Circle2) {
			drawPanel.setColor(color);
			drawPanel.setShape(CRCL2);
		}else if(e.getSource()==colorbtn) {
			color = JColorChooser.showDialog(this,"Choose a color", color);
			colorbtn.setBackground(color);
		}else if(e.getSource()==colorbtn2) {
			color = JColorChooser.showDialog(this, "Choose a color", color);
			colorbtn.setBackground(color);
		}
		
	}
	
}
