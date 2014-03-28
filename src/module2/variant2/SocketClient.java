package module2.variant2;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import static module2.variant2.Constants.PORT;

/**
 *
 * @author aNNiMON
 */
public final class SocketClient extends Thread {
    
    private final Socket socket;
    private final DataInputStream dis;
    private final ClientForm form;

    public SocketClient(String host, ClientForm form) throws IOException {
        this.form = form;
        socket = new Socket(host, PORT);
        dis = new DataInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        while (true) {
            try {
                final int size = dis.readInt();
                for (int i = 0; i < size; i++) {
                    Circle circle = form.getCircle(i);
                    circle.readData(dis);
                }
                form.repaint();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
