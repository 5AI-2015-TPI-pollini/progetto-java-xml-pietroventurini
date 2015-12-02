package readingurl;

import GMaps.Location;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class is used to analyze the Document received from
 * OpenWeather (Open Weather APIs) using XPath queries.
 * 
 * @author Pietro Venturini
 */

public class WeatherURLHandler {
    private static final String URL_PREFIX = "http://api.openweathermap.org/data/2.5/weather?";
    private static final String LATITUDE_PREFIX = "lat=";
    private static final String LONGITUDE_PREFIX = "&lon=";
    private static final String METRIC_UNITS = "&units=metric"; //use '&units=imperial' to get Fahrenheit
    private static final String APP_ID ="&appid=2de143494c0b295cca9337e1e96b00e0";
    private static final String XML_MODE = "&mode=xml"; //use '&mode=json' to get a json document   
    private Location location;
    private static URL URL;
    
    
    public static URL generateURL(Location location) {
        //create URL String
        StringBuilder URLBuilder = new StringBuilder(URL_PREFIX);
        URLBuilder.append(LATITUDE_PREFIX);
        URLBuilder.append(location.getCoordinates().getX());
        URLBuilder.append(LONGITUDE_PREFIX);
        URLBuilder.append(location.getCoordinates().getY());
        URLBuilder.append(METRIC_UNITS);
        URLBuilder.append(APP_ID);
        URLBuilder.append(XML_MODE);
        
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
