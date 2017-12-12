package wet1;

import java.awt.*;

/**
 * A Shape is an abstraction of a shape object. A typical Shape consists of a
 * set of properties: {location, color, shape, size}. Shapes are mutable and
 * cloneable.
 */
public abstract class Shape implements Cloneable
{

	private Point location;
	private Color color;

	/**
	 * Abs. Function: represents a shape with it's location at this.location and
	 * it's color at this.color
	 * 
	 * Rep. Invariant: location != null /* color != null
	 *

	 * 
	 * /**
	 * 
	 * @effects Initializes this with a given location and color.
	 */
	public Shape(Point location, Color color)
	{
		setLocation(location);
		setColor(color);
	}

	/**
	 * @return the top left corner of the bounding rectangle of this.
	 */
	public Point getLocation()
	{
		Point locationCopy = new Point(this.location);
		return locationCopy;

	}

	/**
	 * @modifies this
	 * @effects Moves this to the given location, i.e. this.getLocation() returns
	 *          location after call has completed.
	 */
	public void setLocation(Point location)
	{
		checkRep();
		this.location = location.getLocation();
		checkRep();
	}

	/**
	 * @modifies this
	 * @effects Resizes this so that its bounding rectangle has the specified
	 *          dimension. If this cannot be resized to the specified dimension =>
	 *          this is not modified, throws ImpossibleSizeException (the exception
	 *          suggests an alternative dimension that is supported by this).
	 */
	public abstract void setSize(Dimension dimension) throws ImpossibleSizeException;

	/**
	 * @return the bounding rectangle of this.
	 */
	public abstract Rectangle getBounds();

	/**
	 * @return true if the given point lies inside the bounding rectangle of this
	 *         and false otherwise.
	 */
	public boolean contains(Point point)
	{
		return getBounds().contains(point);
	}

	/**
	 * @return color of this.
	 */
	public Color getColor()
	{
		return color;
	}

	/**
	 * @modifies this
	 * @effects Sets color of this.
	 */
	protected void setColor(Color color)
	{
		this.color = color;
	}

	/**
	 * @modifies g
	 * @effects Draws this onto g.
	 */
	public abstract void draw(Graphics g);

	/**
	 * @effects Creates and returns a copy of this.
	 */
	public Object clone()
	{
		checkRep();
		Shape newShape = null; // TODO - why necessary (not alike the tutorial)

		try
		{
			newShape = (Shape) super.clone();
		} catch (CloneNotSupportedException e)
		{
			// The exception will never be thrown by super.clone(), but the catch
			// prevents us from having to declare it in the signature of this.clone().
			assert false : "Got inside the CloneNotSupportedException" + " exception of Shape.clone()";
		}
		newShape.location = this.location.getLocation();
		newShape.checkRep();
		checkRep();
		return newShape;

	}

	/**
	 * @effects check repsentation invariant conditions 
	 */
	// TODO - is it ok to change it to void as at tutorial 3
	public void checkRep()
	{
		assert color == null : "color value cannot be null";
		assert location == null : "location value cannot be null";
	}
}
