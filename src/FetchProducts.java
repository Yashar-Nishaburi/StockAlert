import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FetchProducts // Fetches products from the CSV file
{
    public static Object[] productUpdate() throws IOException
    {
        product[] array;// 17. Use of Arrays - to store and use (Sort/search) all scraped products
        String line = "";
        String splitBy = ",";
        int total = 0;
        int count = 0;
        boolean statusTemp;
        float priceTemp;

            BufferedReader brr = new BufferedReader(new FileReader(Main.path+"products.csv")); // Reads total lines of csv to make array with according size
            while ((line = brr.readLine()) != null)   //total number of products
            {
                total ++;
            }

            brr.close();

            array = new product[total+1];
            array[total] = new product("zzzzzzzzz", 999999999, false, "zzzzzzzzzz"); //Added to the end of array to make the use of Quicksort easier - Infinity marker

            BufferedReader br = new BufferedReader(new FileReader(Main.path+"products.csv"));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] product = line.split(splitBy);    // use comma as separator
                //System.out.println("Product [Name=" + product[0] + ", Price=" + product[1] + ", Status=" + product[2] + ", Link=" + product[3] +"]");
                if (product[2].equals("Out of stock")) // Convert srt of stock from csv to boolean for array ease of use
                    statusTemp = false;
                else
                    statusTemp = true;

                priceTemp = Float.parseFloat(product[1]); // Convert str of price to float
                array[count] = new product(product[0], priceTemp, statusTemp, product[3]);
                count ++;
            }
            br.close();
            count = 0;
            return array;
    }
}