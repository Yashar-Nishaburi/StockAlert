from bs4 import BeautifulSoup as soup
from urllib.request import Request, urlopen

filename = "products.csv"
f = open(filename, "a")

#connects to the page and reads and saves raw HTML
my_url = 'https://www.gamestop.com/consoles-hardware/desktops-laptops/pc-components/video-cards?start=0&sz=30'
hdr = {'User-Agent': 'Mozilla/5.0'}
client = Request(my_url,headers=hdr)
page = urlopen(client).read()
#souped = soup(page)
#raw_html = uClient.read()
#page.close()

#parsing the HTML
page_soup = soup(page, "html.parser")
containers = page_soup.findAll("div",{"class":"product-tile product-detail"})
#print (len(containers))

for container in containers:
    
    title = container.div.a["title"]
    #title = title_container[0].h2.a.text.strip()


    status_container = container.findAll("div",{"class":"availability product-availability global-availability mb-2"})
    status = status_container[0]["data-available"]

    if (status == "false"):
        status = "Out of stock"
    else:
        status = "In stock"
    
    price_container = container.findAll("span","actual-price")
    price = price_container[0].text.strip()

    link = container.div.a["href"]
    
    f.write(title.replace(",", "|") + "," + price.replace(",", "").replace("$", "") + "," + status + "," + "https://www.gamestop.com" + link + "\n")

f.close()    
#name = divWithInfo.h2.a.b

#print (name)
