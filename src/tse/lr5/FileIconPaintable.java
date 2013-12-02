package tse.lr5;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import tse.Util;

/**
 *
 * @author aNNiMON
 */
public class FileIconPaintable extends Thread implements PaintableObject {
    
    private final Polygon polygon, fill;
    private final Color color, darkColor;
    
    private double x, y;
    private double radius, currentAngle, rotAngle;
    
    public FileIconPaintable(double scale, Color color, boolean rotateRight) {
        this.color = color;
        this.darkColor = color.darker();
        
         polygon = new Polygon();
         polygon.addPoint(0, 0);
         polygon.addPoint((int) (50 * scale), 0);
         polygon.addPoint((int) (50 * scale), (int) (23 * scale));
         polygon.addPoint((int) (32 * scale), (int) (36 * scale));
         polygon.addPoint(0, (int) (36 * scale));
         
         fill = new Polygon();
         fill.addPoint((int) (50 * scale), (int) (23 * scale));
         fill.addPoint((int) (32 * scale), (int) (36 * scale));
         fill.addPoint((int) (36 * scale), (int) (23 * scale));
         fill.addPoint((int) (41 * scale), (int) (25 * scale));
         
         radius = 1;
         currentAngle = 0;
         rotAngle = rotateRight ? 0.1 : -0.1;
    }
    
    public Rectangle getBounds() {
        Rectangle rect = polygon.getBounds();
        rect.setLocation((int)x, (int)y);
        return rect;
    }
    
    @Override
    public void run() {
        while (true) {
            radius += 0.01;
            if (currentAngle > Math.PI * 2) currentAngle = 0;
            currentAngle += rotAngle;
            
            move(Math.cos(currentAngle) * radius, Math.sin(currentAngle) * radius);
            try {
                Thread.sleep(80);
            } catch (InterruptedException ex) {
                Util.handleException(ex);
            }
        }
    }
    
    public synchronized void move(double dx, double dy) {
        x += dx;
        y += dy;
        
        Rectangle r1 = getBounds();
        if (x < 0) x = 2;
        else if ( (x + r1.width) >= FileIconsWindow.WIDTH) {
            x = FileIconsWindow.WIDTH - r1.width - 2;
        }
        if (y < 0) y = 2;
        else if ( (y + r1.height) >= FileIconsWindow.HEIGHT) {
            y = FileIconsWindow.HEIGHT - r1.height - 2;
        }
    }
    
    public synchronized void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public synchronized void changeRotateDirection() {
        rotAngle = -rotAngle;
        currentAngle += rotAngle * 2;
    }
    
    public synchronized boolean isCollide(FileIconPaintable p) {
        return (getBounds().intersects(p.getBounds()));
    }
    
    @Override
    public void draw(Graphics g) {
        g.translate((int)x, (int)y);
        
        g.setColor(color);
        g.drawPolygon(polygon);
        g.setColor(darkColor);
        g.fillPolygon(fill);
        
        g.translate(-(int)x, -(int)y);
    }
    
}