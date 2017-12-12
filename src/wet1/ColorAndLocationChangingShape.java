package wet1;

import java.awt.*;
import java.util.Random;


/**
 * A ColorAndLocationChaningShape is a Shape that can change its location and color using its step()
 * method.
 * Thus, a typical LocationChaningShape consists of the following set of
 * properties: {location, color, shape, size, velocity}
 */
public abstract class ColorAndLocationChangingShape extends LocationChangingShape{

    /**
     * Abstraction Function:
     * this class is identical to it's ancestor LocationChangingShape besides one change.
     * it overrides 'step' in a way that if some velocity has changed by super.step
     * it change also this.color to a random color
     * 
     *  Representation Invariant:
     *  since no fields has added over ancestor, the Rep Inv is identical to ancestors one.
     */



    /**
     * @effects Initializes this with a a given location and color. Each
     *          of the horizontal and vertical velocities of the new
     *          object is set to a random integral value i such that
     *          -5 <= i <= 5 and i != 0
     */
	ColorAndLocationChangingShape(Point location, Color color) {

		super(location, color);
		checkRep();

    }
    /**

     * @modifies this
     * @effects Changes the location of this as described in the specification
     *          of LocationChangingShape.step(Rectangle bound) &&
	 *			if the velocity of this needs to be changed (as described in LocationChangingShape.step(Rectangle bound)),
	 *			changes the color of this to a new random color;
	 *			else, does not change the color of this.
     */
    public void step(Rectangle bound) {
    	checkRep();
        int oldXVelocity = this.getVelocityX();
        int oldYVelocity = this.getVelocityY();
        super.step(bound);
        
        // velocity has changed at super.step
        if(oldXVelocity != this.getVelocityX() || oldYVelocity != this.getVelocityY()) 
        {
            System.out.println("color");
        	Random random = new Random();
        	this.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)) );
        }
        checkRep();
    }
}
