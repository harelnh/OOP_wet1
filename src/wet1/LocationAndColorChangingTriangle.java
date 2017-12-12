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
	
	private Dimension dimension;
	private double size;
	/**
	 * Abs. Function:
	 *  Let t be a LocationAndColorChangingTriangle instance. t represents 
	 *  a right-angled triangle shape. 
	 *  The location of the vertex of the right angle is the same as the location of the 
	 *  bounding rectangle (accessed by t.getLocation()). The lengths of the right-angle 
	 *  sides are at t.dimension.getHeight() and at t.dimension.getWidth(). the size is at t.size
	 *  
	 * 
	 * Rep Invariant
	 *  t.dimension.getWidth() > 0
	 *  t.dimension.getHeight() > 0
	 *  t.size > 0
	 *  t.size == dimension.getWidth() * dimension.getHeight() / 2
	 * 
	 */
	
	/**
	 * @effects Initializes this with a given location and color, horizontal side length
	 *          dimension.getWidth()_ , and vertical size lengh dimension.getHeight()_.
	 */
	public LocationAndColorChangingTriangle(Point location, Color color, Dimension dimension)
	{
		super(location, color);
		this.dimension = new Dimension(dimension);
		this.size = dimension.getWidth() * dimension.getHeight() / 2;
		
		// update bounding rectangle params
		Rectangle newRectangle = new Rectangle(location.x, location.y, (int)dimension.getWidth(), (int)dimension.getHeight());
		this.setBoundingRectangle(newRectangle);
		checkRep();		
	}
	


	/**
	 * @modifies this
	 * @effects Sets dimension.getHeight() of this.dimension.getHeight()
	 */
	public void setDimension(Dimension dimension)
	{
		checkRep();
		this.dimension = new Dimension(dimension);
		this.size = this.dimension.getWidth() * this.dimension.getHeight() / 2;
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
				(int)this.dimension.getWidth(), (int)this.dimension.getHeight());
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
		
		if(dimension.getHeight() <= 0 || dimension.getWidth()<= 0)
			throw new ImpossibleSizeException(dimension);
		
		// changing dimension.getWidth() and dimension.getHeight() according to dimension. size value is
		// changed automatically at a and b "setter" functions
		this.dimension = new Dimension(dimension);
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
		int[] xPoints = {this.getLocation().x, this.getLocation().x, this.getLocation().x + (int)dimension.getWidth()};
		int[] yPoints = {this.getLocation().y, this.getLocation().y + (int)dimension.getHeight(), this.getLocation().y};
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
		newLocationAndColorChangingTriangle.dimension = new Dimension(this.dimension);
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
		assert this.dimension.getWidth() > 0: "this 'dimension.getWidth()' value must be greater than 0";
		assert this.dimension.getHeight() > 0: "this 'dimension.getHeight()' value must be greater than 0";
		assert this.size > 0: "this 'size' value must be greater than 0";
		assert this.size == dimension.getWidth() * dimension.getHeight() / 2:
			"triangle 'size' value must be equal to dimension.getWidth() * dimension.getHeight() / 2";
	}
	

}
