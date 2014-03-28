package module2.variant2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import static module2.variant2.Constants.HEIGHT;
import static module2.variant2.Constants.WIDTH;

/**
 *
 * @author aNNiMON
 */
public final class Circle {

    private int x, y;
    private int radius;
    private int color;

    public void draw(Graphics2D g) {
        g.setColor(new Color(color));
        g.fillOval(x, y, radius, radius);
    }
    
    public void writeData(DataOutputStream dos) throws IOException {
        dos.writeInt(x);
        dos.writeInt(y);
        dos.writeInt(radius);
        dos.writeInt(color);
    }
    
    public void readData(DataInputStream dis) throws IOException {
        x = dis.readInt();
        y = dis.readInt();
        radius = dis.readInt();
        color = dis.readInt();
    }
    
    public void generate() {
        radius = Util.rand(20, 100);
        x = Util.rand(WIDTH - radius);
        y = Util.rand(HEIGHT - radius);
        color = Util.randomColor(0, 255);
    }
    
    @Override
    public String toString() {
        return x + ":" + y + "   " + radius;
    }
}
