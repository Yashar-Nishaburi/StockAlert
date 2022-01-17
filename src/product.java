public class product // Class of each graphics card product
{
    private String name; //8. Private attributes - Data hiding
    private float price;
    private boolean stock;
    private String link;

    public product(String name, float price, boolean stock, String link)
    {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.link = link;
    }

    // 8. 9. 10. Getters setters and constructors - Reading and writing data to product object
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public float getPrice()
    {
        return price;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

    public boolean isStock()
    {
        return stock;
    }

    public void setStock(boolean stock)
    {
        this.stock = stock;
    }

    public String getLink()
    {
        return link;
    }

    public void setLink(String link)
    {
        this.link = link;
    }

    public boolean equals(product o)
    {
        if (this.name == o.name)
            return true;
        return false;
    }

    //11. toString - Used to display each product obj
    @Override
    public String toString()
    {
        return "product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", link='" + link + '\'' +
                '}';
    }
}
