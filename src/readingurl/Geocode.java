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


public class Geocode extends Application {

    /**
     * The Geocode class initializes the GUI
     * 
     * @author Pietro Venturini
     * @version 1.0 
     */
    
    public static void main(String[] args) {
            launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        primaryStage.setTitle("GEOCODING");
        primaryStage.setScene(new Scene(root, 850, 500));
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> System.exit(0)); //Closing the program when 'X' is pressed
    }

}
