package tse.lr5;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import tse.Util;

/**
 * Панель рисования.
 * @author aNNiMON
 */
public class PaintPanelTask2 extends PaintPanel implements Runnable {
    
    public PaintPanelTask2(int width, int height) {
        super(width, height);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                getControllablePaintable().setLocation(evt.getX(), evt.getY());
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent evt) {
                getControllablePaintable().setLocation(evt.getX(), evt.getY());
            }
        });
    }
    
    public void startRepainterThread() {
        new Thread(this).start();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public void run() {
        while (true) {
            synchronized (paintables) {
                for (PaintableObject p1 : paintables) {
                    FileIconPaintable f1 = (FileIconPaintable) p1;
                    checkCollide(f1);
                }
            }
            repaint();
             try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Util.handleException(ex);
            }
        }
    }

    private void checkCollide(FileIconPaintable f1) {
        for (PaintableObject p2 : paintables) {
            if (f1.equals(p2)) continue;
            FileIconPaintable f2 = (FileIconPaintable) p2;
            if (f1.isCollide(f2)) {
                f1.changeRotateDirection();
                return;
            }
        }
    }
    
    private synchronized FileIconPaintable getControllablePaintable() {
        return (FileIconPaintable) paintables.iterator().next();
    }
}
