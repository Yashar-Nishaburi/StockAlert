from bs4 import BeautifulSoup as soup
from urllib.request import Request, urlopen
import time

filename = "products.csv"
f = open(filename, "a")


#connects to the page and reads and saves raw HTML
for i in (0,25,50,75):
    my_url = 'https://www.adorama.com/l/Computers/Computer-Components/Video-and-Graphics-Cards?startAt='+ str(i) +'&sel=Expansion-Ports_HDMI'
    hdr = {'User-Agent': 'Mozilla/5.0'}
    client = Request(my_url,headers=hdr)
    page = urlopen(client).read()
    #souped = soup(page)
    #raw_html = uClient.read()
    #page.close()

    #parsing the HTML
    page_soup = soup(page, "html.parser")
    #print (page_soup.h1)

    containers = page_soup.findAll("div",{"class":"item"})
    #print (len(containers))
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

        time.sleep(0.01)
#name = divWithInfo.h2.a.b
f.close()
#print (name)
