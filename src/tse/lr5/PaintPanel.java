package tse.lr5;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * Панель рисования.
 * @author aNNiMON
 */
public class PaintPanel extends JPanel {
    
    private final List<PaintableObject> paintables;
    
    public PaintPanel(int width, int height) {
        paintables = new ArrayList<>();
        
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(width, height));
    }
    
    public synchronized void addPaintable(PaintableObject obj) {
        paintables.add(obj);
        invalidate();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (PaintableObject paintable : paintables) {
            paintable.draw(g);
        }
    }
}
