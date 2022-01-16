public class ArrayProcessing
{
    public static void printArray(product[] a)
    {
        for(int i = 0; i < a.length-1; i++)
        {
            System.out.println(a[i] );
        }
    }

    public static void sortArray(product[] a) throws Exception
    {
        String setting = Config.read("SortBy");
            //System.out.println(setting);
        if (setting.equals("Price"))
        {
            //System.out.println("true");
            int low = 0;
            int high = a.length - 1;
            //long TimeStart = System.currentTimeMillis();
            Sort.quickSortPrice(low, high, a);
        } else if (setting.equals("Name"))
        {
            //System.out.println("true");
            int low = 0;
            int high = a.length - 1;
            //long TimeStart = System.currentTimeMillis();
            Sort.quickSortName(low, high, a);
        }
    }

    public static void sortArray(product[] a, String con) throws Exception
    {
        //System.out.println(setting);
        if (con.equals("Price"))
        {
            //System.out.println("true");
            int low = 0;
            int high = a.length - 1;
            //long TimeStart = System.currentTimeMillis();
            Sort.quickSortPrice(low, high, a);
        } else if (con.equals("Name"))
        {
            //System.out.println("true");
            int low = 0;
            int high = a.length - 1;
            //long TimeStart = System.currentTimeMillis();
            Sort.quickSortName(low, high, a);
        }
    }
    public static void displayProcessed(product[] a) throws Exception
    {
        String searchCheck = Config.read("SearchFor");

        if (searchCheck.equals("null"))
        {
            sortArray(a);
            printArray(a);
        }else
        {
            int len = Search.matchCount(a,searchCheck);
            product [] temp = Search.searchWCount(a, searchCheck,len);
            if (len>0)
            {
                sortArray(temp);
                printArray(temp);
            }else
            {
                System.out.println(">>Could not find: "+searchCheck);
            }
        }
    }
}

