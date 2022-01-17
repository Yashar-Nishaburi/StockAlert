/*
   _____ __             __   ___    __          __
  / ___// /_____  _____/ /__/   |  / /__  _____/ /_
  \__ \/ __/ __ \/ ___/ //_/ /| | / / _ \/ ___/ __/
 ___/ / /_/ /_/ / /__/ ,< / ___ |/ /  __/ /  / /_
/____/\__/\____/\___/_/|_/_/  |_/_/\___/_/   \__/

 */
public class Main
{
    public static boolean inputSupplied = false; //While inputsupplied is false the repeating task function will run every 5 minutes on loop - if true, it auto forces out of loop and starts new 5 min counter
    public static String path = "C:\\Users\\Yashar\\IdeaProjects\\StockAlert\\src\\"; //Enter path of project folder - Products.csv and Config.cfg Must be in same folder as code
    public static product[] updated = new product[5];//Array of recently updated products
    public static product [] array;//

    public static void main(String[] args) throws Exception
    {
        NetTest.attemptConnection();

        new SettingGUI();

        /*
        Initial update of the CSV witch fetching products
        CSVUpate.Update() runs the python programs. It works if you uncomment it but only sometimes before you need to manually do captcha because no matter how hard I try Adorama wont let me have an easy time with scraping.
        Will hopefully figure out a way of spoofing so that not only I figure out the rate limiting of Adorama but can also make .py scripts for scraping other websites
         */
        //CSVUpdate.clear();
        //CSVUpdate.update();
        //Thread.sleep(5000); //This is needed - Once again python and java integration being annoying. Just safe measure to let py script fully run
        array = (product[]) FetchProducts.productUpdate();//Fetches products from csv.
        ArrayProcessing.displayProcessed(array);//Prints the products according to the fetched csg settings
        //ArrayProcessing.printArray(array);
        System.out.println("===============================================");
        System.out.println("===============================================");
        product[] temp = (product[]) FetchProducts.productUpdate();//Makes temp product for restcok comparison

        new RepeatingTask(300000, new TaskInformation()//Runs every 5 minutes - Force reruns if refresh button is pressed
        {
            @Override
            public void runTask()
            {
                try
                {
                    //CSVUpdate.clear();
                    //CSVUpdate.update();
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
