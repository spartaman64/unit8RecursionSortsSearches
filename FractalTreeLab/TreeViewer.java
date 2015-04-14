 
	//********************************************************************
	//TreeViewer.java       Author: John Lie
	//
	//Demonstrates the use of recursion.
	//@gcschmit (19 July 2014): converted from an applet to an application
	//********************************************************************

	import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class TreeViewer implements ActionListener
{


	private final int WIDTH = 600;
	private final int HEIGHT = 600;

	//private final int MIN = 1, MAX = 9;

	private JButton display;
	private JLabel  orderLabel, fillerLabel;
	private JLabel lengthLabel, decrementLabel, limitLabel, angleLabel, colorLabel;
	private JComboBox<String> lengthList, decrementList, limitList, angleList, colorList;
	
	private String[] lengths =  { "10", "15", "20", "25", "30" , "35", "40", "45", "50","55",
		"60"};
	private String[] decrements = { "1", "2", "3", "4", "5" , "6", "7", "8", "9"};
	private String[] limits = { "5", "10", "15", "20", "25" , "30", "35", "40"};
	private String[]  angles = { "10", "20", "30", "40", "50" , "60"}; 
	private String[] colors = { "White", "Red", "Blue", "Yellow", "Green" , "Cyan"};
	
	private TreeComponent drawing;
	private JPanel panel, tools;
	private JFrame frame;

	private double length = 40;			// starting branch length pixels
	private double decrement = 5;		// amount to decrement the branch pixels
	private double limit = 25;			// smallest size of the branch pixel
	private double angle = 40;			// angle between the branches in degrees
	private Color color = Color.blue ;
	//-----------------------------------------------------------------
	//  Sets up the components for the applet.
	//-----------------------------------------------------------------
	public static void main(String[] args)
	{
	    @SuppressWarnings("unused")
		TreeViewer viewer = new TreeViewer();
	}

	public TreeViewer()
	{
	    tools = new JPanel ();
	    tools.setLayout (new BoxLayout(tools, BoxLayout.X_AXIS));
//	    tools.setBackground (Color.yellow);
	    tools.setOpaque (true);
        
	    lengthLabel = new JLabel (" Length: ");
	    lengthLabel.setForeground (Color.black);
	    
	    // set length combo box
	   
        lengthList = new JComboBox<String>(lengths);
        lengthList.setSelectedIndex(6);
        lengthList.addActionListener(this);
	    
        // set decrement
        
	    decrementLabel= new JLabel (" Decrement: ");
	    decrementLabel.setForeground (Color.black);
	            
        decrementList = new JComboBox<String>(decrements);
        decrementList.setSelectedIndex(4);
        decrementList.addActionListener(this);       
        
        // set limit
        limitLabel= new JLabel (" Limit: ");
	    limitLabel.setForeground (Color.black);
	           
        limitList = new JComboBox<String>(limits);
        limitList.setSelectedIndex(4);
        limitList.addActionListener(this);             
        
        // set angle
        
        angleLabel= new JLabel (" Angle: ");
	    angleLabel.setForeground (Color.black);
	            
        angleList = new JComboBox<String>(angles);
        angleList.setSelectedIndex(3);
        angleList.addActionListener(this);                    
        
        // set color
        colorLabel= new JLabel (" Color: ");
	    colorLabel.setForeground (Color.black);
	            
        colorList = new JComboBox<String>(colors);
        colorList.setSelectedIndex(2);
        colorList.addActionListener(this);                       
        
        fillerLabel = new JLabel("  ");
        
        display = new JButton ("Display");
        display.addActionListener(this);
        
	    orderLabel = new JLabel ("Options: ");
	    orderLabel.setForeground (Color.black);

	    tools.add (lengthLabel);
	    tools.add (Box.createHorizontalStrut (20));
	    tools.add(lengthList);
	    tools.add(decrementLabel);
	    tools.add (decrementList);
	    tools.add(limitLabel);
	    tools.add(limitList);
	    tools.add (angleLabel);
	    tools.add (angleList);
	    tools.add(colorLabel);
	    tools.add(colorList);
	    tools.add(fillerLabel);
	    tools.add(display);
	    tools.setBorder(BorderFactory.createEmptyBorder(5,10,10,10));


//	    tools.add (Box.createHorizontalStrut (20));
//	    tools.add (orderLabel);

	    
	    
	    
	    
	    
	    drawing = new TreeComponent (length, decrement,
	    		limit,  angle, color );

	    panel = new JPanel();
	    panel.add (tools);
	    panel.add (drawing);

	    frame = new JFrame();
	    frame.setTitle("Tree Builder");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(WIDTH, HEIGHT);
	    frame.add(panel);
	    frame.setVisible(true);
	}

	//-----------------------------------------------------------------
	//  Determines which button was pushed, and sets the new order
	//  if it is in range.
	//-----------------------------------------------------------------
	public void actionPerformed (ActionEvent event)
	{
		
		if(event.getSource() == lengthList ){
			
			int index = lengthList.getSelectedIndex();
			drawing.setLength(Double.parseDouble(lengths[index]));
			
		}
		
		if(event.getSource() == decrementList ){
			
			int index = decrementList.getSelectedIndex();
			drawing.setDecrement(Double.parseDouble(decrements[index]));
			
		}		
		
		if(event.getSource() == limitList ){
			
			int index = limitList.getSelectedIndex();
			drawing.setLimit(Double.parseDouble(limits[index]));
			
		}		
		
		if(event.getSource() == angleList ){
			
			int index = angleList.getSelectedIndex();
			drawing.setAngle(Double.parseDouble(angles[index]));
			
		}	
		
		if(event.getSource() == colorList ){
			
			int index = colorList.getSelectedIndex();
			
			switch( index) {
			//private String[] colors = { "White", "Red", "Blue", "Yellow", "Green" , "Cyan"};			
				case 0:	drawing.setColor(Color.white );
						break;
					
				case 1: drawing.setColor(Color.red);
						break;
					
				case 2:	drawing.setColor(Color.blue);
						break;
					
				case 3: drawing.setColor(Color.yellow );
						break;
						
				case 4: drawing.setColor(Color.green);
						break;
						
				case 5: drawing.setColor(Color.cyan );
						break;			
				default:
					
						drawing.setColor(Color.green);
				
			}
		}	
		
		if(event.getSource() == display ){
			
			frame.repaint();
			
		}	
	}
}



