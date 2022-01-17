class Search
{ // 21. Search - Linear search, unfortunately. Good enough for now :)
    static int matchCount(product[] arr, String x)//Finds the number of matching products in OG array to make new array of search results with appropriate size
    {
        int count = 0;
        for (int i = 0; i < arr.length; i++)
        {
            if ((arr[i].getName().toLowerCase().contains(x.toLowerCase())))
            {
                count++;
            }
        }
        return count;
    }

    static product[] searchWCount(product[] arr, String x, int total) //Search function only works knowing how many matches there are - Uses matchCount above.
    {
        int count = 0;
        product[] array = new product[total + 1];
        array[total] = new product("zzzzzzzzz", 999999999, false, "zzzzzzzzzz");

        for (int i = 0; i < arr.length; i++)
        {
            if ((arr[i].getName().toLowerCase().contains(x.toLowerCase())))
            {
                array[count] = arr[i];
                count++;
            }
        }
        return array;
    }

    static int searchWOCount(product[] arr, String x)//Simple linear search
    {

        for (int i = 0; i < arr.length; i++)
        {
            if ((arr[i].getName().toLowerCase().contains(x.toLowerCase())))
            {
                return i;
            }
        }
        return -1;
    }
}
