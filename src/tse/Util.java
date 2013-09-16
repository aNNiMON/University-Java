package tse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author aNNiMON
 */
public class Util {
    
    public static String readDescription(int labNumber, int index) {
        final StringBuilder text = new StringBuilder();
        final String resource = "/res/desc" + labNumber + "_" + index + ".txt";
        BufferedReader in = null;
        try {
            InputStream stream = Runtime.getRuntime().getClass().getResourceAsStream(resource);
            InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
            in = new BufferedReader(reader);
            
            String line = in.readLine();
            while (line != null) {
                text.append(line);
                line = in.readLine();
            }
        } catch (IOException | NullPointerException ex) {
            text.append("Error");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return text.toString();
    }
}
