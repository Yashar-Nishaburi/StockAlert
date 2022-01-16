class Search{
    static int matchCount(product[] arr, String x)
    {
        int count = 0;
        for(int i = 0; i<arr.length;i++) {
            if ((arr[i].getName().toLowerCase().contains(x.toLowerCase())))
            {
                count++;
            }
        }
        return count;
    }

    static product[] searchWCount(product[] arr, String x, int total)
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
}
