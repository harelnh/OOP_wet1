package wet1;
import java.awt.*;

/**
 * A AngleChangingSector  is a Shape that is defined by a starting angle and a arc angle.
 * using its step() method cahnges the arc angle, it increses it by one up to 359 and thendecreases it by 1 until 0.
 * a AngleChangingSector consists
 * of the following set of properties: {location, color, startAngle, arcAngle}
 */

public class AngleChangingSector extends Shape implements Animatable
{
    private int startAngle;
    private int arcAngle;
    private Dimension dimension;
    private double size;
    private boolean opening = true;
    /**
     * Abs. Function:
     *  Let s be a AngleChangingSector instance.
     *  s represents a sector of an oval defined by its dimension, startAngle and arcAngle.
     *  The location of the sector is the same as the location of the
     *  bounding rectangle (accessed by s.getLocation()). The dimensions of the sector
     *  are are such that s.dimension.getHight(), s.dimension.getWidth() are the dimensions from the center of
     *  the bounding oval from the center to th edges. size = s.dimension.getWidth() * s.dimension.getHight() * PI * (arcAngle/360)
     *
     *
     * Rep Invariant
     *  s.dimension.getHight() > 0
     *  s.dimension.getWidth() > 0
     *  s.size > 0
     *  size = s.dimension.getWidth() * s.dimension.getHight() * PI * (arcAngle/360)
     *
     */

    /**
     *
     * @effects Initializes this with a given location and color, angles and dimensions.
     */
    public AngleChangingSector(Point location, Color color, int startAngle, int arcAngle, Dimension dimension)
    {
        super(point,color);
        this.dimension = new Dimension(dimension);
        this.startAngle = startAngle;
        this.arcAngle = arcAngle;
        calcSize();
        if(this.arcAngle == 359)
        {
            this.opening = false;
        }
        checkRep();
    }
    /**
     * @modifies this
     * @effects sets dimensions of the sector and calculates size accordingly
     */
    public void setSize(Dimension dimension)
    {
        checkRep();
        this.dimension = new Dimension(dimension);
        calcSize();
        checkRep();
    }

    /**
     * @return the bounding rectangle of this.
     */
    @Override
    public Rectangle getBounds()
    {
        checkRep();
        Rectangle rectangle = new Rectangle(this.getLocation(), this.dimension);
        return rectangle;
    }

    /**
     * @moddifies this
     * @effects changes angles according to specification.
     */
    public void step(Rectangle bound)
    {
        checkRep();
        if(arcAngle == 359)
        {
            opening = false;
        }
        if(arcAngle == 0)
        {
            opening = true;
        }
        if(opening)
        {
            arcAngle += 1;
        }
        else
        {
            arcAngle += 1;
        }
        calcSize();
        checkRep();
    }
    /**
     * @moddifies this
     * @effects changes size according to dimension and angles
     */
    private void calcSize() //TODO throw exception
    {
        checkRep();
        this.size = this.dimension.getWidth() * this.dimension.getHight() * Math.PI * (this.arcAngle/360) ;
        checkRep();
    }

    /**
     * @modifies g
     * @effects Draws this onto g.
     */
    @Override
    public void draw(Graphics g){
        int x, y, width, height;
        x = (int)this.getLocation().getX();
        y= (int)this.getLocation().getY();
        width = (int)this.dimension.getWidth();
        height = (int)this.dimension.getHeight();
        g.setColor(this.getColor());
        g.fillArc(x, y, width, height, startAngle, arcAngle);
    }


    @Override
    /**
     * @effects check representation invariant conditions
     */
    public void checkRep()
    {
        assert this.dimension.getHight() > 0: "sectors dimensions should be biger then 0";
        assert this.dimension.getwidth() > 0: "sectors dimensions should be biger then 0";
        assert this.arcAngle >= 0 and this.arcAngle < 360: "angles should be between 0 and 359"
        assert this.startAngle >= 0 and this.startAngle < 360: "angles should be between 0 and 359"
        assert this.size > 0: "sectors 'size' value must be greater than 0";
        assert this.size == this.dimension.getWidth() * this.dimension.getHight * Math.PI * (arcAngle/360) ; "sector 'size' value must be equal to a*b*pi*angle/360";
    }


}