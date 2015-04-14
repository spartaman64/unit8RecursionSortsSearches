 
	//********************************************************************
	//TreeComponent.java       Author: John Li
	//
	//Represents a drawing surface on which to paint a tree.
	//********************************************************************

	import java.awt.*;
import javax.swing.JPanel;
public class TreeComponent extends JPanel{
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private final int PANEL_WIDTH = 550;			// panel width
	private final int PANEL_HEIGHT = 500;			// panel height


	private final int TOPX = 275, TOPY = 350;		// top of tree
	private final int BOTX = 275, BOTY = 420;		// bottom of tree	
	
	private double length ;			// starting branch length pixels
	private double decrement;		// amount to decrement the branch pixels
	private double limit;			// smallest size of the branch pixel
	private double angle;			// angle between the branches in degrees
	private Color color;			// color of the tree

	//-----------------------------------------------------------------
	//  Sets the initial fractal order to the value specified.
	//-----------------------------------------------------------------
	public TreeComponent (double length, double decrement, double limit, double angle,
			Color color)
	{
		this.length = length;
		this.decrement = decrement;
		this.limit = limit;
		this.angle = angle;
		this.color = color;
		
		setBackground (Color.black);
		setPreferredSize (new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
	}

	//-----------------------------------------------------------------
	// Draws the fractal recursively.  This method uses the Math 
	// trigonometric methods to first determine the starting angle of the 
	// branch and then apply the requested angle (1/2 to each of the
	// two branches). The end points of the new branch are calculated using
	// the sine and cosine values of the combined old and new branch angles. 
	// The branch length is decremented each recursive call until the minimum length
	// is achieved. 
	//-----------------------------------------------------------------
	public void drawFractal (double length, double decrement, double limit,
			double angle, int X1, int Y1, int X2, int Y2,  Graphics page)
	{
	  int  x3, y3, newDeltaX, newDeltaY;
	  double newLength, Theta, Gamma, rads, tan , deltaX, deltaY ;
	 
	  // exit the recursive call when the desired branch length is received.
	  
	  if (length <= limit)
		  return;
	  else
	  {
		  // calculate the new reduced branch length
		  
		  newLength = length - decrement;
		  Theta = 0.0;

		  // calculate the distance between the two desired points 
		  // to determine the base angle of the branch
		  
		  deltaX = (X1 - X2);		// calculate opposite leg
		  deltaY = (Y1 - Y2);		// calculate adjacent leg

		  // account for horizontal and vertical branches then calculate the 
		  // angle of the branch using the Math Tangent function.
		  
		  // Note: DeltaY has values OPPOSITE of the standard trigonometric representation!

		  if (deltaX == 0)			// branch on the Y axis?
			  if (deltaY < 0) 		// yes, in the upper quadrants?
				  Theta = 90;		// yes, start with 90 degrees
			  else
				  Theta = -90;		// no, start with -90 degrees
		  
		  else if (deltaY == 0) 	// branch on the X axis?
			  if (deltaX < 0 ) 		// yes, in quadrants II and III?
				  Theta = 180;		// yes, start with 180
			  else
				  Theta = 0;		// no, start with 0;
		  else {
			  
			  // calculate the angle of the branch using the legs of the triangle
			  
			  tan = Math.atan( deltaX/-deltaY);	// calculate tangent of supporting branch	 
			  Theta = Math.toDegrees(tan);		// Calculate the arc tangent in degrees

			  // adjust the angle to represent the branch's location in a trigonometric
			  // space with quadrants.
	
	
			  if (deltaX < 0) 
				  if (deltaY < 0) 			
					  Theta = -Theta + 90;		// in quadrant II
				  else
					  Theta = -Theta - 90;		// in quadrant III
			  else
				  if (deltaY < 0)			
					  Theta = 90 - Theta;		// in quadrant I
				  else
					  Theta = -90 - Theta;		// in quadrant IV
		  }

		  // calculate the new branch angle adjusting the branch angle
		  
		  Gamma = Theta - angle;				// create the new branch angle

		  // adjust the new angle to stay between 0 - 180 and 0 - -180
		  
		  if (Gamma >= 180) 
			  Gamma = -360 + Gamma;

		  if (Gamma <= -180)
			  Gamma = 360 + Gamma;

		  rads = Math.toRadians(Gamma);			// convert to radians

		  // calculate the sides (increments) to the new point using the angle.
		  
		  newDeltaX = (int)( Math.cos(rads) *  newLength);		// cosine = X value

		  newDeltaY = (int) (Math.sin(rads) * newLength);		// sine = Y value

		  x3 = X1 + newDeltaX;									// create the new location
		  y3 = Y1 - newDeltaY;

//		  ******************* Display the current leg and angles for testing ***************	
//		  DecimalFormat df = new DecimalFormat("#.##");
//
//		  System.out.println("Line: (" + x3 + "," + y3 + ") - (" + X1 + "," + Y1 + ")  angle: " + angle +
//				  " Theta: " + df.format(Theta) + " Gamma: " + df.format(Gamma));
//		  **********************************************************************************

		  page.drawLine(x3, y3, X1,Y1);	  					// draw the line

		  drawFractal (newLength,decrement,limit,angle,		// iterate the right branches
				  x3,y3,X1, Y1, page);

		  drawFractal (newLength,decrement,limit,-angle,	// iterate the left branches
				  x3,y3,X1, Y1, page);

	  }
	}
	
	//-----------------------------------------------------------------
	//  Performs the initial calls to the drawFractal method.
	//-----------------------------------------------------------------
	public void paintComponent (Graphics page)
	{
	  super.paintComponent (page);

	  page.setColor (color);
	  
	  page.drawLine(TOPX,TOPY,BOTX,BOTY);
	  
	  
	  drawFractal (length,decrement,limit,angle/2,
			  TOPX,TOPY,BOTX,BOTY, page);
	  
	  drawFractal (length,decrement,limit,-angle/2,
			  TOPX,TOPY,BOTX,BOTY, page);  

	}

	
	// getters and setters
	
	public double getDecrement() {
		return decrement;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getLimit() {
		return limit;
	}

	public double getAngle() {
		return angle;
	}

	public void setDecrement(double decrement) {
		this.decrement = decrement;
	}

	public void setLimit(double limit) {
		this.limit = limit;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}


	
	
}

