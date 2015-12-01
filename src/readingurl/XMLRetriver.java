package readingurl;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;


public class XMLRetriver {
 private static final DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();

    private final URL url;
    private Document doc;

    public XMLRetriver(URL url) {
        this.url = url;
    }


    public Document retriveResult() {
                try {
                    URLConnection urlConnection = url.openConnection();
                    // Open the http connection
                    //Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.0.1", 8080));
                    //URLConnection urlConnection = url.openConnection(proxy); 
                    // Parse the xml
                    doc = docBuilderFactory.newDocumentBuilder().parse(urlConnection.getInputStream());  
                    
                } catch (Exception ex) {
                    System.out.println("Retriving Info Error");
                }
                return doc;
            }
}