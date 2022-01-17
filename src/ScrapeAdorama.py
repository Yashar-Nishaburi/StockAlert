from bs4 import BeautifulSoup as soup
from urllib.request import Request, urlopen
import time

filename = "products.csv"
f = open(filename, "a")

def GET_UA():
    uastrings = ["Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.111 Safari/537.36", \
                 "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.72 Safari/537.36", \
                 "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10) AppleWebKit/600.1.25 (KHTML, like Gecko) Version/8.0 Safari/600.1.25", \
                 "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0", \
                 "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.111 Safari/537.36", \
                 "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.111 Safari/537.36", \
                 "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/600.1.17 (KHTML, like Gecko) Version/7.1 Safari/537.85.10", \
                 "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko", \
                 "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0", \
                 "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.104 Safari/537.36" \
                 ]

    return random.choice(uastrings)

#connects to the page and reads and saves raw HTML
for i in (0,25,50,75):
    my_url = 'https://www.adorama.com/l/Computers/Computer-Components/Video-and-Graphics-Cards?startAt='+ str(i) +'&sel=Expansion-Ports_HDMI'
    hdr = {'User-Agent': GET_UA()}
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
