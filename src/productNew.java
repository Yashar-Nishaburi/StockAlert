public class productNew extends product
{
    private String date;

    public productNew(String name, float price, boolean stock, String link, String date)
    {
        super(name, price, stock, link);
        this.date = date;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    @Override
    public String toString()
    {
        return  super.toString() +
                "Restock date='" + date;
    }
}
