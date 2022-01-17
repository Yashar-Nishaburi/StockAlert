public class ArrayProcessing
{
    public static void printArray(product[] a)//Prints product array except last index (Used as "Infinity" for quicksort)
    {
        for (int i = 0; i < a.length - 1; i++)//19. Data structure traversal
        {
            System.out.println(a[i]);
        }
    }

    public static void printArrayAlt(product[] a)//Normal print array function
    {
        for(int i = 0; i < a.length; i++)
        {
            System.out.println(a[i] );
        }
    }

    public static void sortArray(product[] a) throws Exception//Sorts array (Or doesn't) by name or price according to cfg
    {
        String setting = Config.read("SortBy");//Reading sort settings.
        if (setting.equals("Price"))
        {
            int low = 0;
            int high = a.length - 1;
            Sort.quickSortPrice(low, high, a);
        } else if (setting.equals("Name"))
        {
            int low = 0;
            int high = a.length - 1;
            Sort.quickSortName(low, high, a);
        }
    }

    public static void sortArray(product[] a, String con) throws Exception //Unused function for debugging purposes. Sorts array with manually given condition
    {
        if (con.equals("Price"))
        {
            int low = 0;
            int high = a.length - 1;
            Sort.quickSortPrice(low, high, a);
        } else if (con.equals("Name"))
        {
            int low = 0;
            int high = a.length - 1;
            Sort.quickSortName(low, high, a);
        }
    }
    public static void displayProcessed(product[] a) throws Exception // Double function. Firstly applies both search and sort settings and also prints array.
    {
        String searchCheck = Config.read("SearchFor"); //Read search settings

        if (searchCheck.equals("null")) //Default no search condition
        {
            sortArray(a);
            printArray(a);
        }else
        {
            int len = Search.matchCount(a,searchCheck);
            product [] temp = Search.searchWCount(a, searchCheck,len); //Creates array with the length of the products that match search criteria for sorting purposes.
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