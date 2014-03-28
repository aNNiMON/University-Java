package module2.variant2;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static module2.variant2.Constants.CIRCLES_COUNT;

/**
 *
 * @author aNNiMON
 */
public final class ClientForm extends JFrame {
    
    public static void main(String[] args) throws IOException {
        final String ip = JOptionPane.showInputDialog(null, "Введите IP", "127.0.0.1");
        ClientForm form = new ClientForm(ip);
        form.setVisible(true);
        form.setClient();
    }

    private final String ip;
    private SocketClient client;
    private final Circle[] circles;
    
    public ClientForm(String ip) {
        super();
        this.ip = ip;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Panel panel = new Panel();
        panel.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        add(panel);
        pack();
        circles = new Circle[CIRCLES_COUNT];
        for (int i = 0; i < CIRCLES_COUNT; i++) {
            circles[i] = new Circle();
        }
    }
    
    public void setClient() throws IOException {
        client = new SocketClient(ip, this);
        client.start();
    }
    
    public synchronized Circle getCircle(int index) {
        return circles[index];
    }
    
    private class Panel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            final Graphics2D g2d = (Graphics2D) g.create();
            synchronized (circles) {
                if (circles == null) return;
                for (Circle circle : circles) {
                    if (circle != null) {
                        circle.draw(g2d);
                    }
                }
            }
        }
    }
}
