import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Config
{
    public static String read(String a) throws Exception
    {
        String fetch;
        Properties prop = new Properties();
        FileInputStream ip= new FileInputStream(Main.path+"config.properties");
        prop.load(ip);
        fetch = prop.getProperty(a);
        ip.close();
        return fetch;
    }

    public static void write(String key, String value) throws IOException
    {
        Properties prop = new Properties();
        FileInputStream ip = new FileInputStream(Main.path + "config.properties");
        prop.load(ip);
        prop.setProperty(key, value);
        ip.close();
        FileOutputStream ipp = new FileOutputStream(Main.path + "config.properties");
        prop.store(ipp, null);
        ipp.close();
    }
/*
    public static void main(String[] args) throws Exception
    {
        System.out.println(read("SearchFor"));
        write("SearchFor","Amongus");
        System.out.println(read("SearchFor"));
    }
 */
}
