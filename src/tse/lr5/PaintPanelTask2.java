package tse.lr5;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
        setFocusable(true);
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
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                final int step = 5;
                FileIconPaintable p1 = getControllablePaintable();
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        p1.move(0, -step);
                        break;
                    case KeyEvent.VK_DOWN:
                        p1.move(0, step);
                        break;
                    case KeyEvent.VK_LEFT:
                        p1.move(-step, 0);
                        break;
                    case KeyEvent.VK_RIGHT:
                        p1.move(step, 0);
                        break;
                }
            }
        });
    }
    
    public void startRepainterThread() {
        new Thread(this).start();
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
