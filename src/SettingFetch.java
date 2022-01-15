import java.util.Timer; //code by: Nirmal- thInk beYond - Fetched from stack overflow
import java.util.TimerTask;
import java.io.*;
public class SettingFetch
{
    private static String str = "";

    static TimerTask task = new TimerTask()
    {
        public void run()
        {
            if( str.equals("") )
            {
                System.out.println( "you input nothing. exit..." );
                //System.exit( 0 );
            }
        }
    };

    public static void getInput() throws Exception
    {
        Timer timer = new Timer();
        timer.schedule( task, 10*1000 );

        System.out.println( "Input a string within 10 seconds: " );
        BufferedReader in = new BufferedReader(
                new InputStreamReader( System.in ) );
        str = in.readLine();

        timer.cancel();
        System.out.println( "you have entered: "+ str );
    }


    public static void main( String[] args ) throws Exception
    {
        while (true) {
            try {
                (new SettingFetch()).getInput();
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println("main exit...");
        }
    }
}

