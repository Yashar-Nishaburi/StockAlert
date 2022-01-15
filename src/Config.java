import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Config
{
    public static String read(String a) throws Exception
    {
        Properties prop = new Properties();
        FileInputStream ip= new FileInputStream(Main.path+"config.properties");
        prop.load(ip);

        return (prop.getProperty(a));
    }

    public static void write(String key, String value) throws IOException
    {
        Properties prop =new Properties();
        prop.load(new FileInputStream(Main.path+"config.properties"));
        prop.setProperty(key, value);
        prop.store(new FileOutputStream(Main.path+"config.properties"),null);
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
