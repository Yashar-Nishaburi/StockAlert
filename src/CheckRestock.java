public class CheckRestock
{
    public static void NewlyStocked ( product[] updated, product[] dated)
    {
        int temp;

        for (int i = 0; i < updated.length-1; i++ )
        {
            temp = Search.searchWOCount( dated, updated[i].getName());
            if ((dated[temp].isStock() == false) && (updated[i].isStock()))
            {
                for (int j = 4; j>0; j--)
                {
                    Main.updated[j]=Main.updated[j-1];
                }
                Main.updated[0]=updated[i];
            }
        }
    }
}
