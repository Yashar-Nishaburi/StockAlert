import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class CheckRestock
{
    public static void NewlyStocked ( product[] updated, product[] dated) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException // Gets updated and old array of obj for camparison
    {
        int temp;
        Sort.quickSortName(0, dated.length-1, dated);
        for (int i = 0; i < updated.length-1; i++ )//Searches for each product in the new array
        {
            temp = Search.binarySearch( dated, updated[i].getName());
            if ((!dated[temp].isStock()) && (updated[i].isStock())) //is it in stock now whilst previously out of stock
            {
                for (int j = 4; j>0; j--)//Updating list of newly restocked products
                {
                    Main.updated[j]=Main.updated[j-1];
                }
                Main.updated[0]= new productNew(updated[i].getName(), updated[i].getPrice(), updated[i].isStock(), updated[i].getLink(), getDate());
                SettingGUI.modelRef.addRow(new Object[]{getDate(), updated[i].getName(), updated[i].getPrice(),updated[i].isStock(), updated[i].getLink()});
                Main.mainGui.labelStatus.append(">> ("+getDate()+") - New Restock: " + dated[temp] + "\n");
                Audio.playClip();
            }
        }
    }

    public static String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        //System.out.println(dtf.format(now));
        return dtf.format(now);
    }
}

/*
This is overall a terrible way to go about doing this. But it's the only way to compare the two lists whilst making sure it doesn't break if a new item is added or an item is removed.
It only works decent because there's maximum of 300 products in the array - FOR THE TIME BEING. As soon as the program is expanded to work with more websites it'll start slowing down
drastically. I'll later maybe implement a Binary Search function for this portion of the project since I already have a QuickSort as well.
 */