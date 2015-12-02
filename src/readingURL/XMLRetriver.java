package readingURL;

import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

/**
 * This class is used to establish a connection to the URL indicated (Eventually using a proxy)
 * and to retrieve data into a Document.
 * 
 * @author Pietro Venturini
 */

public class XMLRetriver {
 private static final DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();

    private final URL url;
    private Document doc;
    private URLConnection urlConnection;
    
    private static Proxy proxy;
    
    public static void setProxy(Proxy newProxy) {
        XMLRetriver.proxy = newProxy;
    }
    

    public XMLRetriver(URL url) {
        this.url = url;
    }
      
    public Document retriveResult() {
                try {   
                    if(proxy == null) {
                        urlConnection = url.openConnection();
                    } 
                    else {
                        urlConnection = url.openConnection(proxy);
                    }
                    // Parse the xml received into a Document
                    doc = docBuilderFactory.newDocumentBuilder().parse(urlConnection.getInputStream());  
                    
                } catch (Exception ex) {
                    System.out.println("Retriving Info Error");
                }
                return doc;
            }
}