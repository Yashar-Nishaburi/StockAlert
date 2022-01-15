import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static String path = "/Users/yasharnishaburi/IdeaProjects/StockAlert/src/products.csv"; //Enter the path of products.csv - Must be in same folder as code
    public static void main(String[] args) throws Exception
    {
            NetTest.AttemptConnection();

            System.out.println("Refreshed");

            new RepeatingTask(10000, new TaskInformation()
            {
                @Override
                public void runTask()
                {
                    try
                    {
                        //CSVUpdate.Clear();
                        //CSVUpdate.Update();
                        product[] array = (product[]) FetchProducts.ProductUpdate();
                        ArrayProcessing.printArray(array);

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

            while (true)
            {
                scanner.nextLine();
                inputSupplied = true;
            }
    }
}
