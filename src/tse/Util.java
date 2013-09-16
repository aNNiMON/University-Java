package tse;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 *
 * @author aNNiMON
 */
public class Util {
    
    private static final boolean DEBUG = true;
    
    public static String readDescription(int labNumber, int index) {
        final StringBuilder text = new StringBuilder();
        final String resource = "/res/desc" + labNumber + "_" + index + ".txt";
        BufferedReader in = null;
        try {
            InputStream stream = getInputStream(resource);
            InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
            in = new BufferedReader(reader);
            
            String line = in.readLine();
            while (line != null) {
                text.append(line);
                line = in.readLine();
            }
        } catch (IOException | NullPointerException ex) {
            text.append("Error");
            handleException(ex);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    handleException(ex);
                }
            }
        }
        return text.toString();
    }
    
    public static double readDouble(Scanner sc) {
        while (!sc.hasNextDouble()) {
            System.out.println("Ошибка! Неверный формат!");
            sc.next();
        }
        return sc.nextDouble();
    }
    
    public static Image readImageRes(String name) {
        try {
            InputStream is = getInputStream("/res/images/" + name);
            return ImageIO.read(is);
        } catch (IOException ex) {
            handleException(ex);
        }
        return null;
    }
    
    public static void handleException(Exception ex) {
        if (DEBUG) ex.printStackTrace();
    }
    
    private static InputStream getInputStream(String resource) {
        return Runtime.getRuntime().getClass().getResourceAsStream(resource);
    }
}
