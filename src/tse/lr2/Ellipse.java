package tse.lr2;

import java.awt.Point;
import java.util.Arrays;

/**
 *
 * @author aNNiMON
 */
public class Ellipse {
    
    private static final int POINTS = 4;
    
    protected final Point[] points;

    public Ellipse() {
        points = new Point[POINTS];
    }

    public Ellipse(Point p1, Point p2, Point p3, Point p4) {
        points = new Point[] {
            p1, p2, p3, p4
        };
    }
    
    public Ellipse(Ellipse ellipse) {
        points = Arrays.copyOf(ellipse.points, POINTS);
    }
    
    public Point getCenterPoint() {
        int cx = (points[1].x - points[0].x) / 2  + points[0].x;
        int cy = (points[2].y - points[0].y) / 2 + points[0].y;
        Point center = new Point(cx, cy);
        return center;
    }
    
    public double getSquare() {
        Point center = getCenterPoint();
        double hor = center.x - points[0].x;
        double ver = center.y - points[0].y;
        return Math.PI * hor * ver;
    }
    
    public double getDistanceOfCentres(Ellipse ellipse) {
        return (getCenterPoint().distance(ellipse.getCenterPoint()));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Ellipse) {
            Ellipse ellipse = (Ellipse) obj;
            return (ellipse.points).equals(obj);
        }
        return super.equals(obj); 
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < POINTS; i++) {
            sb.append(points[i].toString()).append('\t');
        }
        sb.append("\nSquare: ").append(getSquare());
        sb.append("\nCenter: ").append(getCenterPoint().toString());
        return sb.toString();
    }
    
}
