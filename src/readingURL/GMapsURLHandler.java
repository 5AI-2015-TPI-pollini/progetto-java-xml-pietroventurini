package readingURL;

import GMaps.Location;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * This class is used to build the URL for Geocode API's request
 * 
 * @author Pietro Venturini
 */

public class GMapsURLHandler {
    private static final String URL_PREFIX = "https://maps.googleapis.com/maps/api/geocode/";
    private static final String DOCUMENT_TYPE = "xml"; //use 'json' to receive a json document
    private static final String ADDRESS_PREFIX = "?address=";
    private Location location;
    private static URL URL;
    
    

   
    public static URL generateURL(String location) {
        //create URL String
        StringBuilder URLBuilder = new StringBuilder(URL_PREFIX);
        URLBuilder.append(DOCUMENT_TYPE);
        URLBuilder.append(ADDRESS_PREFIX);
        try {
            URLBuilder.append(URLEncoder.encode(location, "UTF-8")); //to handle spaces in the name of the location
        } catch (UnsupportedEncodingException ex) {
            System.out.println("URL encoding error");
        }
 
        try {
            //convert to URL
            URL = new URL(URLBuilder.toString());
        } catch (MalformedURLException ex) {
            System.out.println("URL conversion error");
            return null;
        }       
        return URL;
    }
    
}
