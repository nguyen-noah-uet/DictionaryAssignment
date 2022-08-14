package GUI.Services;

import java.net.URL;
import java.net.URLConnection;

public class CheckInternetConnectivity {
    public static boolean IsConnected() {
        try {
            URL url = new URL("https://www.google.com/");
            URLConnection connection = url.openConnection();
            connection.connect();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
