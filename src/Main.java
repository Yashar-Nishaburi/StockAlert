public class Main
{
    public static boolean inputSupplied = false;
    public static String path = "/Users/yasharnishaburi/IdeaProjects/StockAlert/src/"; //Enter path of project folder - Products.csv and Config.cfg Must be in same folder as code
    public static product [] updated = new product[5];
    public static product [] array;

    public static void main(String[] args) throws Exception
    {
        NetTest.attemptConnection();

        new SettingGUI();

        //CSVUpdate.clear();
        //CSVUpdate.update();
        //Thread.sleep(5000);
        array = (product[]) FetchProducts.productUpdate();
        ArrayProcessing.displayProcessed(array);
        //ArrayProcessing.printArray(array);
        System.out.println("===============================================");
        System.out.println("===============================================");
        product[] temp = (product[]) FetchProducts.productUpdate();

        new RepeatingTask(300000, new TaskInformation()
        {
            @Override
            public void runTask()
            {
                try
                {
                    //CSVUpdate.Clear();
                    //CSVUpdate.Update();
                    //Thread.sleep(5000);
                    product[] temp = array;
                    array = (product[]) FetchProducts.productUpdate();
                    ArrayProcessing.displayProcessed(array);
                    //System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                    //ArrayProcessing.printArray(temp);
                    System.out.println("***********************************************");
                    CheckRestock.NewlyStocked(array, temp);
                    ArrayProcessing.printArrayAlt(updated);
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
