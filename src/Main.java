import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static boolean inputSupplied = false;
    public static String path = "C:\\Users\\Yashar\\IdeaProjects\\StockAlert\\src\\"; //Enter path of project folder - Products.csv and Config.cfg Must be in same folder as code

    public static void main(String[] args) throws Exception
    {
        NetTest.attemptConnection();

        new SettingGUI();

        //CSVUpdate.clear();
        //CSVUpdate.update();
        product[] array = (product[]) FetchProducts.productUpdate();
        ArrayProcessing.displayProcessed(array);
        //ArrayProcessing.printArray(array);
        System.out.println("===============================================");
        System.out.println("===============================================");

        new RepeatingTask(10000, new TaskInformation()
        {
            @Override
            public void runTask()
            {
                try
                {
                    //CSVUpdate.Clear();
                    //CSVUpdate.Update();
                    product[] array = (product[]) FetchProducts.productUpdate();
                    ArrayProcessing.displayProcessed(array);
                    //ArrayProcessing.printArray(array);
                    System.out.println("===============================================");
                    System.out.println("===============================================");

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public boolean shouldSkipTimer()
            {
                if(inputSupplied)
                {
                    inputSupplied = false;
                    return true;
                }

                return false;
            }
        }).start();

    }
}
