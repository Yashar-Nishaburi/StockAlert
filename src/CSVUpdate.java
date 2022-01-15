import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileWriter;

public class CSVUpdate
{
    public static void clear() throws IOException
    {
        new FileWriter(Main.path, false).close();
    }

    public static void update() throws Exception
    {

        String[] callAndArgs = {"python3", "ScrapeAdorama.py"};
        Process p = Runtime.getRuntime().exec(callAndArgs);

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

        String s;
        while ((s = stdInput.readLine()) != null) {
            //System.out.println(s);
        }

        while ((s = stdError.readLine()) != null) {
            //System.out.println(s);
        }

        callAndArgs [1] = "ScrapeGamestop.py";
        Process p2 = Runtime.getRuntime().exec(callAndArgs);

        //BufferedReader stdInput = new BufferedReader(new InputStreamReader(p2.getInputStream()));
        //BufferedReader stdError = new BufferedReader(new InputStreamReader(p2.getErrorStream()));

        while ((s = stdInput.readLine()) != null) {
            //System.out.println(s);
        }

        while ((s = stdError.readLine()) != null) {
            //System.out.println(s);
        }

    }
}
