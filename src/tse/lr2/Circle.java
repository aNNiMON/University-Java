package tse.lr2;

import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author aNNiMON
 */
public class Circle extends Ellipse implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public Circle() {
        super();
    }
    
    public Circle(Point center, int radius) {
        super(new Point(center.x - radius, center.y - radius),
              new Point(center.x + radius, center.y - radius),
              new Point(center.x - radius, center.y + radius),
              new Point(center.x + radius, center.y + radius));
    }

    public boolean isConsist(Point point) {
        Point center = getCenterPoint();
        int radius = center.x - points[0].x;
        
        double tmp = Math.pow(point.x - center.x, 2);
        tmp += Math.pow(point.y - center.y, 2);
        
        return (tmp <= (radius * radius));
    }
    
    @Override
    public String toString() {
        return "Круг - твой друг!";
    }
    
}
