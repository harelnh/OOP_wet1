package wet1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * LocationAndColorChangingTriangle represents a right-angled triangle shape.
 * The location of the vertex of the right angle is identical to the bounding 
 * rectangle of the shape. The lengths of the right-angle sides also identical to the rectangle 
 * width and height. 
 * 
 */
public class LocationAndColorChangingTriangle extends ColorAndLocationChangingShape
{
	
	private int xSideLength;
	private int ySideLength;
	private double size;
	/**
	 * Abs. Function:
	 *  Let t be a LocationAndColorChangingTriangle instance. t represents 
	 *  a right-angled triangle shape. 
	 *  The location of the vertex of the right angle is the same as the location of the 
	 *  bounding rectangle (accessed by t.getLocation()). The lengths of the right-angle 
	 *  sides are at t.ySideLength and at t.xSideLength. the size is at t.size 
	 *  
	 * 
	 * Rep Invariant
	 *  t.xSideLength > 0
	 *  t.ySideLength > 0
	 *  t.size > 0
	 *  t.size == xSideLength * ySideLength / 2 
	 * 
	 */
	
	/**
	 * @effects Initializes this with a given location and color, horizontal side length
	 *          xSideLength_ , and vertical size lengh ySideLength_. 
	 */
	public LocationAndColorChangingTriangle(Point location, Color color, int xSideLength_,
			int ySideLength_)
	{
		super(location, color);
		this.xSideLength = xSideLength_;
		this.ySideLength = ySideLength_;
		this.size = xSideLength_ * ySideLength_ / 2;
		
		// update bounding rectangle params
		Rectangle newRectangle = new Rectangle(location.x, location.y, xSideLength_, ySideLength_);
		this.setBoundingRectangle(newRectangle);
		checkRep();		
	}
	
	
	
	/**
	 * @return xSideLength value of this
	 */	
	public int getxSideLength()
	{
		checkRep();
		return xSideLength;
	}

	/**
	 * @modifies this
	 * @effects Sets xSideLength of this
	 */
	public void setxSideLength(int xSideLength)
	{
		checkRep();
		this.xSideLength = xSideLength;
		this.size = this.xSideLength * this.ySideLength / 2;
		updateBoundingRectangle();
		checkRep();
	}

	/**
	 * @return ySideLength value of this
	 */	
	public int getySideLength()
	{
		checkRep();
		return ySideLength;
	}
	
	/**
	 * @modifies this
	 * @effects Sets ySideLength of this.ySideLength
	 */
	public void setySideLength(int ySideLength)
	{
		checkRep();
		this.ySideLength = ySideLength;
		this.size = this.ySideLength * this.xSideLength / 2;
		updateBoundingRectangle();
		checkRep();
	}

	/**
	 * @effects update shapes bounding rectangle with current this.a and this.b values
	 */


	private void updateBoundingRectangle()
	{
		checkRep();
		Rectangle newRectangle = new Rectangle(this.getLocation().x, this.getLocation().y,
				this.xSideLength, this.ySideLength);
		this.setBoundingRectangle(newRectangle);
		checkRep();
		
	}





	/**
	 * @return size value of this.
	 */
	public double getSize()
	{
		checkRep();
		return size;
	}
	
	/**
	 * @modifies this
	 * @effects Resizes this so that its bounding rectangle has the specified
	 *          dimension. If this cannot be resized to the specified dimension =>
	 *          this is not modified, throws ImpossibleSizeException.
	 */
	public void setSize(Dimension dimension) throws ImpossibleSizeException
	{
		checkRep();	
		
		//TODO - implement that exeption. what if null 
		
		if(dimension.height <= 0 || dimension.width <= 0)
			throw new ImpossibleSizeException();
		
		// changing xSideLength and ySideLength according to dimension. size value is
		// changed automatically at a and b "setter" functions
		this.setxSideLength(dimension.width);
		this.setySideLength(dimension.height); 
		checkRep();
	}
	
	@Override
	/**
	 * @modifies g
	 * @effects Draws this onto g.
	 */
	public void draw(Graphics g) 
	{
		// TODO - a complete guess
		checkRep();		
		Graphics2D g2 = (Graphics2D) g;
		// creating arrays of x and y points coordinates of the triangle vertexes.
		int[] xPoints = {this.getLocation().x, this.getLocation().x, this.getLocation().x + xSideLength};
		int[] yPoints = {this.getLocation().y, this.getLocation().y + ySideLength, this.getLocation().y};
		g2.fillPolygon(xPoints, yPoints, 3);
		g2.setColor(this.getColor());
		checkRep();
	}
	
	@Override
	/**
	 * @effects Creates and returns a copy of this.
	 */
	public Object clone()
	{
		checkRep();
		LocationAndColorChangingTriangle newLocationAndColorChangingTriangle;
		
		// TODO - why can't we insert try-catch here
		newLocationAndColorChangingTriangle = (LocationAndColorChangingTriangle) super.clone();		
		// TODO do we need to manually clone a,b,size
		newLocationAndColorChangingTriangle.xSideLength = this.xSideLength;
		newLocationAndColorChangingTriangle.ySideLength = this.ySideLength;
		newLocationAndColorChangingTriangle.size = this.size;
		newLocationAndColorChangingTriangle.checkRep();
		checkRep();
		return newLocationAndColorChangingTriangle;
		
	}
	
	@Override
	/** 
	 * @effects check representation invariant conditions 
	 */
	public void checkRep()
	{
		super.checkRep();
		assert this.xSideLength > 0: "this 'xSideLength' value must be greater than 0";
		assert this.ySideLength > 0: "this 'ySideLength' value must be greater than 0"; 
		assert this.size > 0: "this 'size' value must be greater than 0";
		assert this.size == xSideLength * ySideLength / 2: 
			"triangle 'size' value must be equal to xSideLength * ySideLength / 2";		
	}
	

}
