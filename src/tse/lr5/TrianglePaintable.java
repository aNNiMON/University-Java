package tse.lr5;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

/**
 * @author aNNiMON
 */
public class TrianglePaintable implements PaintableObject {
    
    private final Polygon triangle;
    private final Color fillColor, strokeColor;
    
    public TrianglePaintable(Color fillColor, Color strokeColor, Point p1, Point p2, Point p3) {
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        triangle = new Polygon();
        triangle.addPoint(p1.x, p1.y);
        triangle.addPoint(p2.x, p2.y);
        triangle.addPoint(p3.x, p3.y);
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(fillColor);
        g.fillPolygon(triangle);
        g.setColor(strokeColor);
        g.drawPolygon(triangle);
    }
    
}
