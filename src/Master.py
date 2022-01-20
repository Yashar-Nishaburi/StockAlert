from bs4 import BeautifulSoup as soup
from urllib.request import Request, urlopen
import random
import time

filename = "../../../Desktop/src/products.csv"
f = open(filename, "a")

dotCount = 1

user_agents = [
    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36',
    'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36',
    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36',
    'Mozilla/5.0 (iPhone; CPU iPhone OS 12_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148',
    'Mozilla/5.0 (Linux; Android 11; SM-G960U) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.72 Mobile Safari/537.36'
]
#Scraping adorama ======================================
for i in (0,25,50,75):
    print("Fetching Adorama" + dotCount*("."))
    dotCount += 1
    my_url = 'https://www.adorama.com/l/Computers/Computer-Components/Video-and-Graphics-Cards?startAt='+ str(i) +'&sel=Expansion-Ports_HDMI'
    hdr = {'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36'}
    client = Request(my_url,headers=hdr)
    page = urlopen(client).read()
    page_soup = soup(page, "html.parser")
    containers = page_soup.findAll("div",{"class":"item"})
    containers.pop()
    for container in containers:
        
        title_container = container.findAll("div",{"class":"item-details"})
        title = title_container[0].h2.a.text.strip()

        status_container = container.findAll("div",{"class":"item-actions"})
        status = status_container[0].form.button.text.strip()

        if (status == "Temporarily not available"):
            status = "Out of stock"
        else:
            status = "In stock"
        
        price = container.find("div","prices").input["value"]

        link = container.a["href"]

        f.write(title.replace(",", "|") + "," + price.replace(",", "") + "," + status + "," + link + "\n")

        time.sleep(0.05)

#Scraping GameStop ======================================
my_url = 'https://www.gamestop.com/consoles-hardware/desktops-laptops/pc-components/video-cards?start=0&sz=30'
hdr = {'User-Agent': 'Mozilla/5.0'}
client = Request(my_url,headers=hdr)
page = urlopen(client).read()
page_soup = soup(page, "html.parser")
containers = page_soup.findAll("div",{"class":"product-tile product-detail"})
print("Fetching Gamestop"+ dotCount*("."))
dotCount += 1

for container in containers:
    
    title = container.div.a["title"]

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

#Scraping Evga ======================================

my_url = 'https://www.evga.com/products/ProductList.aspx?type=0'
hdr = {'User-Agent': 'Mozilla/5.0 (iPhone; CPU iPhone OS 12_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148'}
client = Request(my_url,headers=hdr)
page = urlopen(client).read()
page_soup = soup(page, "html.parser")
containers = page_soup.findAll("div",{"class":"list-item"})
print("Fetching Evga"+ dotCount*("."))

for container in containers:
        
    title_container = container.findAll("div",{"class":"pl-list-info"})
    title = title_container[0].div.a.text.strip()

    if ("OutOfStock" in str(title_container)):
        status = "Out of stock"
    else:
        status = "In stock"

    price_container = container.findAll("div",{"id":"divPriceFinal"})
    price = price_container[0].text.strip()

    link = container.find("div","pl-list-pname").a["href"]

    f.write(title.replace(",", "|") + "," + price.replace("$", "") + "," + status + "," + "https://www.evga.com/" + link + "\n")


f.close()

