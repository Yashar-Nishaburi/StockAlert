public class Sort // 22. Sorting algorithms - QuickSort
{//Partition and quickSort function are just proof of concept used for same functions which work with float and string below
    public static int partition(int low, int high, int[] a)//Partitioning function of QUICKSORT
    {
        int pivot = a[low];
        int j = high;
        int i = low;
        while (i < j)
        {
            do
            {
                i++;
            }while (a[i]<=pivot);
            do{
                j--;
            }while (a[j]> pivot);
            if (i<j)
            {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        int temp1 = a[low];
        a[low] = a[j];
        a[j] = temp1;
        return j;
    }

    public static void quickSort(int low, int high, int[] a)//Recursive use of partitioning function
    {
        int j = 0;
        if(low < high) {
            j = partition(low, high, a);
            quickSort(low, j, a);
            quickSort(j + 1, high, a);
        }
    }

    public static int partitionPrice(int low, int high, product[] a)//Partitioning function with floats and array product input
    {
        float pivot = a[low].getPrice();
        int j = high;
        int i = low;
        while (i<j)
        {
            do{
                i++;
            }while (a[i].getPrice()<=pivot);
            do{
                j--;
            }while (a[j].getPrice()> pivot);
            if (i<j)
            {
                product temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        product temp1 = a[low];
        a[low] = a[j];
        a[j] = temp1;
        return j;
    }

    public static void quickSortPrice(int low, int high, product[] a)//Recursive use of partitioning function
    {
        int j = 0;
        if(low < high) {
            j = partitionPrice(low, high, a);
            quickSortPrice(low, j, a);
            quickSortPrice(j + 1, high, a);
        }
    }

    public static int partitionName(int low, int high, product[] a)//Partitioning function working with strings
    {
        String pivot = a[low].getName();
        int j = high;
        int i = low;
        while (i<j)
        {
            do{
                i++;
            }while (((a[i].getName()).compareTo(pivot))<0);
            do{
                j--;
            }while (((a[j].getName()).compareTo(pivot))>0);
            if (i<j)
            {
                product temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        product temp1 = a[low];
        a[low] = a[j];
        a[j] = temp1;
        return j;
    }

    public static void quickSortName(int low, int high, product[] a)//Recursive use of partitioning function
    {
        int j = 0;
        if(low < high) {
            j = partitionName(low, high, a);
            quickSortName(low, j, a);
            quickSortName(j + 1, high, a);
        }
    }
//Efficiency tester for OG quicksort algo. Simply uncomment and run.
/*
    public static void main(String[] args)
    {

        int[] TestArray = new int[9999];
        Random rand = new Random();
        int upperbound = 5000;
        int int_random = 0;
        for (int j = 0; j<20; j++)
        {
            for (int i = 0; i <= TestArray.length - 2; i++)
            {
                int_random = rand.nextInt(upperbound);
                TestArray[i] = int_random;
            }
            TestArray[TestArray.length - 1] = 9999999;
            //System.out.println(Arrays.toString(TestArray));
            int low = 0;
            int high = TestArray.length - 1;
            long TimeStart = System.currentTimeMillis();
            Sort(low, high, TestArray);
            //System.out.println(Arrays.toString(TestArray));
            long TimeEnd = System.currentTimeMillis();
            System.out.print("Time taken for algo: ");
            System.out.println(TimeEnd - TimeStart);
        }
    }
     */
}