from bs4 import BeautifulSoup as soup
from urllib.request import Request, urlopen
import random
import time

filename = "products.csv"
f = open(filename, "a")

user_agents = [
    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36',
    'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36',
    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36',
    'Mozilla/5.0 (iPhone; CPU iPhone OS 12_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148',
    'Mozilla/5.0 (Linux; Android 11; SM-G960U) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.72 Mobile Safari/537.36'
]

#connects to the page and reads and saves raw HTML
for i in (0,25,50,75):
    user_agent = random.choice(user_agents)
    my_url = 'https://www.adorama.com/l/Computers/Computer-Components/Video-and-Graphics-Cards?startAt='+ str(i) +'&sel=Expansion-Ports_HDMI'
    hdr = {'User-Agent': user_agent}
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
