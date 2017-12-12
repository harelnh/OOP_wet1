package wet1;

import java.awt.*;


/**
 * LocationChangingNumberedOval represents an ellipse shape with an id number.
 * Objects id number is given by the order of creation since the first instance. 
 * first instance id is 1.
 */

public class LocationChangingNumberedOval extends LocationChangingOval
{
	
	static int instanceCounter = 0;
	private int id; 
	/**
	 * Abs. Func
	 * Let e be an instance of LocationChangingNumberedOval.
	 * e represent an ellipse shape with an id number at e.id
	 * e.draw will draw the id number in middle of the ellipse figure.
	 * 
	 * Rep. Invariant
	 * e.id >=1 
	 */
	
	
	/**
	 * @effects initialize an ellipse shape and update it's id value
	 * according to the order of it's creation.
	 */
	public LocationChangingNumberedOval(Point location, Color color, Dimension dimension)
	{
		super(location, color, dimension);
		instanceCounter++;
		id = instanceCounter;
		checkRep();
	}
		
	
	/**
	 * @return id value of this.
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @modifies this
	 * @effects Sets id of this.
	 */
	public void setId(int id)
	{
		this.id = id;
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
		super.draw(g);		
		Graphics2D g2 = (Graphics2D) g;
		g2.drawString(Integer.toString(this.id), this.getLocation().x +(int)this.getWidth()/2,
				this.getLocation().y + (int)this.getHeight()/2);
		checkRep();
	}

	
	@Override
	/**
	 * @effects Creates and returns a copy of this.
	 */
	public Object clone()
	{
		checkRep();
		LocationChangingNumberedOval newLocationChangingNumberedOval;
		
		newLocationChangingNumberedOval = (LocationChangingNumberedOval)super.clone();
		newLocationChangingNumberedOval.id = this.id;
		checkRep();
		return newLocationChangingNumberedOval;
	
	
	}
	

	@Override
	/** 
	 * @effects check representation invariant conditions 
	 */
	public void checkRep()
	{
		super.checkRep();
		assert this.id > 0: "numbered oval id value must be greater than 0";			
	}
	
}
