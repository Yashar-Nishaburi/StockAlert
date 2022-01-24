import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileWriter;

public class CSVUpdate
{
    public static void clear() throws IOException //Clear the CSV file for re writing the data
    {
        new FileWriter(Main.path + "products.csv", false).close();
    }

    public static void update() throws Exception
    {//PITA - Very fussy to run Python code that takes long time to execute in Java
        String[] callAndArgs = {"python3", "Master.py"};
        Process p = Runtime.getRuntime().exec(callAndArgs);
        /*
        try
        {
            Process p = Runtime.getRuntime().exec(callAndArgs);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
         */
        //Made buffers for Input and Errors as a form of debugging but product breaks when processes are commented out
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        //Empty while strings - Presumably in conjunction with the buffer readers above creates enough delay for the python script to run as it now wains for errors till the end.
        String s;
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
        }

        while ((s = stdError.readLine()) != null) {
            System.out.println(s);//For now, it simply prints out the errors thrown by the Python script - Dunno how to deal with the errors
        }
    }
}
