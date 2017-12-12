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
	private Dimension dimension;
	private double size;

	/**
	 * Abs. Function:
	 *  Let e be a LocationChangingOval instance. e represents an oval
	 * (ellipse) shape with a dimension paramater so that the dimension specified is the dimension of the shapes
	 * bounding rectangle. size is at e.size and
	 * calculated by e.size = PI*a*b
	 * 
	 * 
	 * Rep Invariant
	 *  e.dimension.getWidth()> 0
	 *  e.dimension.getHeight()> 0
	 *  e.size > 0 && size = PI*dimension.getWidth()*imension.getHeight()
	 * 
	 */

	/**
	 * @effects Initializes this with a given location and color, horizontal length
	 *          a, and vertical lengh b. dimension.getWidth()and imension.getHeight()are positive integers. the size of
	 *          the oval is calculated as PI*a*b.
	 */
	public LocationChangingOval(Point location, Color color, Dimension dimension)
	{
		super(location, color);

		this.dimension = new Dimension(dimension);
		this.size = Math.PI * dimension.getWidth()* dimension.getHeight();

		// update the empty default bounding rectangle according to oval dimensions.
		Rectangle ovalBoundingRectangle = new Rectangle(location.x, location.y,  (int)dimension.getWidth(), (int)dimension.getHeight());
		this.setBoundingRectangle(ovalBoundingRectangle);
		checkRep();

	}

	/**
	 * @effects update shapes bounding rectangle with current this.dimension.getWidth()and this.imension.getHeight()values
	 */
	public void updateBoundingRectangle()
	{
		checkRep();
		Rectangle newRactangle = new Rectangle(this.getLocation().x, this.getLocation().y,
				 (int)dimension.getWidth(),  (int)dimension.getHeight());
		this.setBoundingRectangle(newRactangle);
		checkRep();
	}

	/**
	 * @modifies this
	 * @effects Sets dimension of this
	 */
	private void setDimension(Dimension dimension)
	{
		checkRep();
		this.dimension = new Dimension(dimension);
		this.size = Math.PI * this.dimension.getWidth()* this.dimension.getHeight();
		updateBoundingRectangle();
		checkRep();
	}

	/**
	 * @return dimension.width value of this.
	 */
	public double getWidth()
	{
		checkRep();
		return dimension.getWidth();
	}
	/**
	 * @return dimension.height value of this.
	 */
	public double getHeight()
	{
		checkRep();
		return dimension.getHeight();
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
		if(dimension.height <= 0 || dimension.width <= 0)
			throw new ImpossibleSizeException(dimension);
		else{
			Dimension ovalDimension = new Dimension();
			ovalDimension.setSize(dimension.getWidth() , dimension.getHeight() );
			this.setDimension(ovalDimension);
		}
		checkRep();
	}


	@Override
	/**
	 * @modifies g
	 * @effects Draws this onto g.
	 */
	public void draw(Graphics g) 
	{
		// TODO - dimension.getWidth()complete guess
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
		newLocationChangingOval.dimension = new Dimension(this.dimension);
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
		assert this.dimension.getWidth()> 0: "ovals 'a' value must be greater than 0";
		assert this.dimension.getHeight()> 0: "ovals 'b' value must be greater than 0";
		assert this.size > 0: "ovals 'size' value must be greater than 0";
		assert this.size == this.dimension.getWidth()* this.dimension.getHeight()* Math.PI: "ovals 'size' value must be equal to a*imension.getHeight()*pi";
	}

}
