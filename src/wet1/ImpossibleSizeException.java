package wet1;


/**
 * An ImpossibleSizeException is an Exception that will be thrown in case setting a
 * wrong size of shape.
 * This exception will return a different legal size.
 */
        import java.awt.Dimension;

public class ImpossibleSizeException extends Exception{
    private Dimension dimension;

    ImpossibleSizeException(Dimension dim){
        int width = (int)dim.getWidth();
        int height = (int)dim.getHeight();

        if(width >= 0) {
            if (width == 0)
                width = 1;
            else
                width = -width;
        }
        if(height <= 0) {
            if(height == 0)
                height = 1;
            else
                height = -height;
        }

        dimension = new Dimension(width, height);
    }
    public Dimension getDimension() {
        return dimension;
    }

}