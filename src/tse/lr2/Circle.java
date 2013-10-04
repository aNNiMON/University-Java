package tse.lr2;

import java.awt.Point;

/**
 *
 * @author aNNiMON
 */
public class Circle extends Ellipse {

    public boolean isConsist(Point point) {
        Point center = getCenterPoint();
        int radius = center.x - points[0].x;
        
        double tmp = Math.pow(point.x - center.x, 2);
        tmp += Math.pow(point.y - center.y, 2);
        
        return (tmp <= (radius * radius));
    }
    
    @Override
    public String toString() {
        return "This is circle!\n" + super.toString();
    }
    
}
