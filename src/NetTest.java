import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;

public class NetTest
{
    public static boolean testConnection()
    {
        try
        {
            URL url = new URL("https://www.adorama.com/l/Computers/Computer-Components/Video-and-Graphics-Cards");
            URLConnection connection = url.openConnection();
            connection.connect();
            return true;
        } catch (MalformedURLException e)
        {
            return false;
        } catch (IOException e)
        {
            return false;
        }
    }

    public static void attemptConnection()
    {
        boolean connectionStatus = false;
        int attempt = 1;
        while (connectionStatus == false && attempt <= 3)
        {
            System.out.println(">>Attempting internet connection - Attempt #:" + attempt);
            connectionStatus = testConnection();
            if (connectionStatus == false)
            {
                if (attempt != 3)
                    System.out.println(">>Connection failed - retrying in 10 seconds");
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
                return;
            }
        }
        System.out.println(">>Connection failed after 3 attempts - exiting program");
        System.exit(0);
    }

}
