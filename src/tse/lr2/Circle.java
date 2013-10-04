package tse.lr2;

import java.awt.Point;

/**
 *
 * @author aNNiMON
 */
public class Circle extends Ellipse {
    
    public Circle() {
        super();
    }
    
    public Circle(Point p1, Point p2, Point p3, Point p4) {
        super(p1, p2, p3, p4);
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
