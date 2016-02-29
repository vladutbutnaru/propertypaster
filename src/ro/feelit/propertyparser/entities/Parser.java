/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.feelit.propertyparser.entities;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author vlad.butnaru
 */
public class Parser {

    private String absPath = "http://www.urbanedgeny.com/";
    private ArrayList<String> linksFromPage = new ArrayList<String>();
    private ArrayList<Property> listOfProperties = new ArrayList<Property>();

    public void getPropertyLinksForManhattan() throws IOException {
        //Manhattan
        Document doc = Jsoup.connect("http://www.urbanedgeny.com/results?nh1=90&p%5Bmin%5D=&p%5Bmax%5D=&bd=&ba=").get();
        Elements links = doc.select("div.listing"); // a with href

        //first page
        for (Element e : links) {
            Elements linkToProperty = e.select("div.middle").select("a[href]");

            for (Element i : linkToProperty) {
                linksFromPage.add(i.attr("href"));
                System.out.println(i.attr("href"));

            }

        }
        //end first page
//
////page 2 and so on
//        String nextPage;
//        //for each page up to the latest
//        for (int i = 1; i <= 258; i++) {
//            nextPage = "http://www.urbanedgeny.com/results?page=" + i + "&nh1=90&p[min]=&p[max]=&bd=&ba=";
//            doc = Jsoup.connect(nextPage).get();
//            links = doc.select("div.listing"); // a with href
//            for (Element e : links) {
//                Elements linkToProperty = e.select("div.middle").select("a[href]");
//                System.out.println("current page: " + i);
//                for (Element a : linkToProperty) {
//                    linksFromPage.add(a.attr("href"));
//                    System.out.println(a.attr("href"));
//
//                }
//
//            }
//
//        }

    }

    public void parsePropertyData() throws IOException, InterruptedException{
        //for every link get data for property
        String currentPropertyLink;
        Property property = new Property();
    for(String linkToProperty : linksFromPage){
        property = new Property();
    currentPropertyLink  = absPath + linkToProperty;
    System.out.println(currentPropertyLink);
    //access the link and get the document
    Document doc = Jsoup.connect(currentPropertyLink).get();
    //get the image path or default image
     Elements contentArea = doc.select("div#slide-img-1");
     try{
     Elements images = contentArea.select("a");
     Element image = images.select("img").first();
     property.imagePath = image.attr("src");
     System.out.println(image.attr("src"));
     }
     catch(Exception e){
          property.imagePath = "default image";
     System.out.println("default image");
     
     }
     //--------end image path
   
    
    //get the price
   Elements listingTab = doc.select("div#listing-overview");
  property.price = listingTab.select("div").get(1).html();
//-------- end price 
  property.type = listingTab.select("a").get(0).html();
   
  //get the type
  
  
  
  //----end type
    //prevent timeout
    Thread.sleep(1000);   
    listOfProperties.add(property);
    }
    
    
    
    
    }
}
