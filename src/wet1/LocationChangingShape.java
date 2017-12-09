package wet1;

import java.awt.*;
import java.util.Random;

//import com.sun.org.apache.xml.internal.security.keys.keyresolver.implementations.PrivateKeyResolver;

/**
 * A LocationChaningShape is a Shape that can change its location using its
 * step() method. A LocationChaningShape has a velocity property that determines
 * the speed of location changing. Thus, a typical LocationChaningShape consists
 * of the following set of properties: {location, color, shape, size, velocity}
 */
public abstract class LocationChangingShape extends Shape implements Animatable
{

	// Abstraction Function:
	// Let s be an instance of this class.
	// This class extends Shape with horizontal velocity at s.horizVel,
	// vertical velocity at s.vertVel and bounding rectangle
	// at s.boundingRectangle which initialized to a 0x0 Rectangle
	// with it's center at s.location

	// Representation Invariant:
	//
	// -5 <= s.horizVel <= 5 && horizVel != 0
	// -5 <= vertVel <= 5 && vertVel != 0
	// s.location.equals(s.boundingRectangle)
	//

	private int xVel;
	private int yVel;
	private Rectangle boundingRectangle;
	private static int[] velValuesArr =
	{ -5, -4, -3, -2, -1, 1, 2, 3, 4, 5 };

	/**
	 * @effects Initializes this with a a given location and color. Each of the
	 *          horizontal and vertical velocities of the new object is set to a
	 *          random integral value i such that -5 <= i <= 5 and i != 0. the
	 *          bounding rectangle of this is initialized to a 0x0 rectangle at the
	 *          given location.
	 */
	LocationChangingShape(Point location, Color color)
	{
		super(location, color);
		Random rand = new Random();
		xVel = velValuesArr[rand.nextInt(10)];
		yVel = velValuesArr[rand.nextInt(10)];
		boundingRectangle = new Rectangle(location);
		checkRep();
	}

	/**
	 * @return the horizontal velocity of this.
	 */
	public int getVelocityX()
	{
		checkRep();
		return this.xVel;
	}

	/**
	 * @return the bounding rectangle of this.
	 */
	public Rectangle getBounds()
	{
		checkRep();
		return this.boundingRectangle.getBounds();
	}

	/**
	 * @return the vertical velocity of this.
	 */
	public int getVelocityY()
	{
		checkRep();
		return this.yVel;
	}

	/**
	 * @modifies this
	 * @effects Sets the horizontal velocity of this to velocityX and the vertical
	 *          velocity of this to velocityY.
	 */
	public void setVelocity(int velocityX, int velocityY)
	{
		checkRep();
		this.xVel = velocityX;
		this.yVel = velocityY;
		checkRep();
	}

	/**
	 * @modifies this
	 * @effects Sets the bounding rectangle of this to rectangle
	 */
	public void setBoundingRectangle(Rectangle rectangle)
	{
		checkRep();
		this.boundingRectangle = rectangle;
		this.setLocation(rectangle.getLocation());
		checkRep();
	}

	/**
	 * @modifies this
	 * @effects Moves this to the given location, i.e. this.getLocation() returns
	 *          location after call has completed. we override it here because when
	 *          location is changed we change rectangle location as well.
	 */
	@Override
	public void setLocation(Point location)
	{
		checkRep();
		this.setLocation(location);
		this.boundingRectangle.setLocation(location);
		checkRep();
	}

	/**
	 * @modifies this
	 * @effects Let p = location v = (vx, vy) = velocity r = the bounding rectangle
	 *          of this If (part of r is outside bound) or (r is within bound but
	 *          adding v to p would bring part of r outside bound) { If adding v to
	 *          p would move r horizontally farther away from the center of bound,
	 *          vx = -vx If adding v to p would move r vertically farther away from
	 *          the center of bound, vy = -vy } p = p + v
	 */
	public void step(Rectangle bound)
	{
		// TODO (BOM): Implement this method
		checkRep();
		// create temporary rectangle that simulates the step with current params
		Rectangle tmpMovedRect = new Rectangle(this.boundingRectangle.x + this.xVel,
				this.boundingRectangle.y + this.yVel, this.boundingRectangle.width, this.boundingRectangle.height);

		if (!bound.contains(this.boundingRectangle)
				|| bound.contains(boundingRectangle) && !bound.contains(tmpMovedRect))
		{
			if (tmpMovedRect.getCenterX() > bound.getCenterX())
			{
				this.setVelocity(-getVelocityX(), getVelocityY());
			}
			if (tmpMovedRect.getCenterY() > bound.getCenterY())
			{
				this.setVelocity(getVelocityX(), -getVelocityY());
			}
		}

		// update shape and boundingRectangle locations.
		Point newLocation = new Point(this.boundingRectangle.x + this.xVel,
				this.boundingRectangle.y + this.yVel);
		this.setLocation(newLocation);
		this.boundingRectangle.setLocation(newLocation);
		checkRep();
	}

	@Override
	/**
	 * @effects Creates and returns a copy of this.
	 */
	public Object clone()
	{
		checkRep();
		LocationChangingShape newLocationChangingShape;

		newLocationChangingShape = (LocationChangingShape) super.clone();

		// TODO do we need to manually clone velocities
		newLocationChangingShape.xVel = this.xVel;
		newLocationChangingShape.yVel = this.yVel;
		newLocationChangingShape.boundingRectangle = this.boundingRectangle.getBounds();
		newLocationChangingShape.checkRep();
		checkRep();
		return newLocationChangingShape;
	}


	@Override
	/**
	 * @effects check repsentation invariant conditions 
	 */
	public void checkRep()
	{
		super.checkRep();
		// make sure Shape.location is the same as boundingRect location
		assert this.getLocation()
				.equals(boundingRectangle.getLocation()) : "Shape and boundingRectangle locations must be identical";
	}
}
