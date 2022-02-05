import jdk.jfr.SettingControl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;

public class NetTest //3. "Input Validation" - Checks for Users internet connection
{
    public static boolean testConnection() //attempt connection to website.
    {
        try
        {
            URL url = new URL("https://www.adorama.com/l/Computers/Computer-Components/Video-and-Graphics-Cards");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.connect();
            int statusCode = connection.getResponseCode();
            return true;
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
            return false;
        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static void attemptConnection() //4. Error messages - Shows an error msg with every unsuccessful attempt. Exists system after 3
    {
        boolean connectionStatus = false;
        int attempt = 1;
        while (connectionStatus == false && attempt <= 3)
        {
            System.out.println(">>Attempting internet connection - Attempt #:" + attempt);
            Main.mainGui.labelStatus.append(">>Attempting internet connection - Attempt #:" + attempt + "\n");

            connectionStatus = testConnection();
            if (connectionStatus == false)
            {
                if (attempt != 3)
                {
                    System.out.println(">>Connection failed - retrying in 10 seconds");
                    Main.mainGui.labelStatus.append(">>Connection failed - retrying in 10 seconds\n");
                }
                attempt ++;
                try
                {
                    TimeUnit.SECONDS.sleep(10);
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
            }
            else
            {
                System.out.println(">>Connection established");
                Main.mainGui.labelStatus.append(">>Connection established\n");
                return;
            }
        }
        System.out.println(">>Connection failed after 3 attempts - exiting program");
        Main.mainGui.labelStatus.append(">>Connection failed after 3 attempts - exiting program\n");
        //gui.dispose();
        //System.exit(0);
    }

}
