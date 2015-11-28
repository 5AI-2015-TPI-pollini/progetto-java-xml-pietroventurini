package readingurl;

import GMaps.Location;
import GMaps.GMapsXPathHandler;
import Weather.WeatherXPathHandler;
import java.io.BufferedReader;
import java.io.IOException; 
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/*
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
*/

public class Geocode extends Application {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        try {
            
            launch(args);
            
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Choose a location: ");
            String location;
            
            location = br.readLine();
            
            
            XMLRetriver object = new XMLRetriver(GMapsURLHandler.generateURL(location));
            GMapsXPathHandler XPH = new GMapsXPathHandler(object.retriveResult());
            Location locations[] = XPH.extractFromDoc();
            System.out.println("Searched: "+ location);
            for (int i = 0; i < locations.length; i++)
            {
                System.out.println(i+1 + ")\n" + locations[i]);
            }
            
            Scanner in = new Scanner(System.in);
            int location_id;
            do {
                System.out.println("Choose the location: ");
                location_id = in.nextInt() -1 ;
            } while (0 > location_id || location_id >= locations.length);
            
            
            XMLRetriver object2 = new XMLRetriver(WeatherURLHandler.generateURL(locations[location_id]));
            WeatherXPathHandler WXPH = new WeatherXPathHandler(object2.retriveResult());
            locations[location_id].setForecast(WXPH.extractFromDoc());
            System.out.println(locations[location_id].getForecast());
        } catch (IOException ex) {
            Logger.getLogger(Geocode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        primaryStage.setTitle("GEOCODING");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
    
    
    
}
