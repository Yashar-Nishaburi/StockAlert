public class ArrayProcessing
{
    public static void printArray(product[] a)
    {
        for(int i = 0; i < a.length-1; i++)
        {
            System.out.println(a[i] );
        }
    }

    public static void sortArrayPrice(product[] a) throws Exception
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
        }
    }
}
