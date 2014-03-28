package module2.variant2;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;
import static module2.variant2.Constants.CIRCLES_COUNT;
import static module2.variant2.Constants.PORT;

/**
 *
 * @author aNNiMON
 */
public final class SocketServer extends Thread {
    
    private static final int GENERATE_DELAY = 2000;
    
    public static void main(String[] args) throws IOException {
        new SocketServer().start();
    }
    
    private final Circle[] circles;
    private final ServerSocket serverSocket;
    private final Timer timer;
    
    public SocketServer() throws IOException {
        serverSocket = new ServerSocket(PORT);
        circles = new Circle[CIRCLES_COUNT];
        for (int i = 0; i < CIRCLES_COUNT; i++) {
            circles[i] = new Circle();
            circles[i].generate();
        }
        System.out.println(getInetAddress());
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                synchronized (circles) {
                    for (Circle circle : circles) {
                        circle.generate();
                    }
                }
            }
        }, GENERATE_DELAY, 1000);
    }
    
    public static String getInetAddress() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    @Override
    public void run() {
        while (true) {
            try {
                final Socket socket = serverSocket.accept();
                new WorkingThread(socket).start();
            } catch (IOException ex) {
                ex.printStackTrace();// break;
            }
        }
    }
    
    private class WorkingThread extends Thread {
        
        private final DataOutputStream dos;
        
        public WorkingThread(Socket socket) throws IOException {
            this.dos = new DataOutputStream(socket.getOutputStream());
        }

        @Override
        public void run() {
            while (true) {  
                try {
                    dos.writeInt(circles.length);
                    for (Circle circle : circles) {
                        circle.writeData(dos);
                    }
//                    try {
//                        Thread.sleep(GENERATE_DELAY);
//                    } catch (InterruptedException ex) { }
                } catch (IOException ex) { }
            }
        }
    }
}
