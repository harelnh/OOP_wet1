package wet1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import wet1.LocationChangingShape;

/**
 * LocationChangingOval represents an ellipse shape. It contains the information about
 * it's vertical and horizontal lengths and the total size of the ellipse.
 */

public class LocationChangingOval extends LocationChangingShape
{
	private int a;
	private int b;
	private double size;

	/**
	 * Abs. Function:
	 *  Let e be a LocationChangingOval instance. e represent an oval
	 * (ellipse) shape e horizontal length (from center to edge) is at e.a e
	 * vertical length (from center to edge) is at e.b e size is at e.size and
	 * calculated by e.size = PI*a*b
	 * 
	 * 
	 * Rep Invariant
	 *  e.a > 0 
	 *  e.b > 0 
	 *  e.size > 0 && size = PI*a*b
	 * 
	 */

	/**
	 * @effects Initializes this with a given location and color, horizontal length
	 *          a, and vertical lengh b. a and b are positive integers. the size of
	 *          the oval is calculated as PI*a*b.
	 */
	public LocationChangingOval(Point location, Color color, int a, int b)
	{
		super(location, color);

		this.a = a;
		this.b = b;
		this.size = Math.PI * a * b;

		// update the empty default bounding rectangle according to oval dimensions.
		Rectangle ovalBoundingRectangle = new Rectangle(location.x, location.y, 2 * a, 2 * b);
		this.setBoundingRectangle(ovalBoundingRectangle);
		checkRep();

	}

	/**
	 * @effects update shapes bounding rectangle with current this.a and this.b values
	 */
	public void updateBoundingRectangle()
	{
		checkRep();
		Rectangle newRactangle = new Rectangle(this.getLocation().x, this.getLocation().y,
				2 * a, 2 * b);
		this.setBoundingRectangle(newRactangle);
		checkRep();
	}
	
	/**
	 * @return a value of this
	 */
	public int getA()
	{
		checkRep();
		return a;
	}

	
	/**
	 * @modifies this
	 * @effects Sets a of this
	 */
	public void setA(int a)
	{
		checkRep();
		this.a = a;
		this.size = Math.PI * this.a * this.b;
		updateBoundingRectangle();
		checkRep();
	}

	/**
	 * @return b value of this
	 */
	public int getB()
	{
		checkRep();
		return b;
	}

	/**
	 * @modifies this
	 * @effects Sets b of this
	 */
	public void setB(int b)
	{
		checkRep();
		this.b = b;
		this.size = Math.PI * this.a * this.b;
		updateBoundingRectangle();
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
		
		// changing a and b according to dimension. size value is
		// changed automatically at a and b "setter" functions
		this.setA(dimension.width/2);
		this.setB(dimension.height/2); 
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
		g2.fillOval(this.getBounds().x, this.getBounds().y, this.getBounds().width, 
				this.getBounds().height);
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
		LocationChangingOval newLocationChangingOval;
		
		// TODO - why can't we insert try-catch here
		newLocationChangingOval = (LocationChangingOval) super.clone();		
		// TODO do we need to manually clone a,b,size
		newLocationChangingOval.a = this.getA();
		newLocationChangingOval.b = this.getB();
		newLocationChangingOval.size = this.getSize();
		newLocationChangingOval.checkRep();
		checkRep();
		return newLocationChangingOval;
		
	}
	
	@Override
	/** 
	 * @effects check representation invariant conditions 
	 */
	public void checkRep()
	{
		super.checkRep();
		assert this.a > 0: "ovals 'a' value must be greater than 0";
		assert this.b > 0: "ovals 'b' value must be greater than 0"; 
		assert this.size > 0: "ovals 'size' value must be greater than 0";
		assert this.size == this.a * this.b * Math.PI: "ovals 'size' value must be equal to a*b*pi";		
	}

}
