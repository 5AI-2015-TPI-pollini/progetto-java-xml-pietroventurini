package Geocode;

import GMaps.GMapsXPathHandler;
import GMaps.Location;
import Weather.WeatherXPathHandler;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import readingURL.GMapsURLHandler;
import readingURL.WeatherURLHandler;
import readingURL.XMLRetriver;

/**
 * This is the controller of the GUI,
 * Here are definied the structure and the events of its components.
 * 
 * @author Pietro Venturini
 */

public class AppController implements Initializable {

    @FXML
    private TableView myTable;    
    @FXML
    private Button myButton;    
    @FXML
    private TextField myText;    
    @FXML
    private TableColumn locationColumn, cloudsColumn, latitudeColumn, longitudeColumn, temperatureColumn, minColumn, maxColumn;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
        // Initialize the table
            myTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            //Creating the columns
            locationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Location, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Location, String> p) {
                    return new SimpleStringProperty(p.getValue().getName());
                }
            });
            latitudeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Location, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Location, String> p) {
                    return new SimpleStringProperty(p.getValue().getLatitude());
                }
            });
            longitudeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Location, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Location, String> p) {
                    return new SimpleStringProperty(p.getValue().getLongitude());
                }
            });
            temperatureColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Location, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Location, String> p) {
                    return new SimpleStringProperty(String.valueOf(p.getValue().getForecast().getTemperature()));
                }
            });
            minColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Location, String>, ObservableValue<String>>() {
                 public ObservableValue<String> call(TableColumn.CellDataFeatures<Location, String> p) {
                    return new SimpleStringProperty(String.valueOf(p.getValue().getForecast().getMin()));
                }
            });
            maxColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Location, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Location, String> p) {
                    return new SimpleStringProperty(String.valueOf(p.getValue().getForecast().getMax()));
                }
            });
            cloudsColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Location, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Location, String> p) {
                    return new SimpleStringProperty(p.getValue().getForecast().getClouds());
                }
            });
    }
    
    @FXML //What Happens when the Search button is pressed.
    public void buttonEvent () {
        if (myText.getText().isEmpty()) 
            return;

        XMLRetriver object = new XMLRetriver(GMapsURLHandler.generateURL(myText.getText())); //creating XMLRetriver object with the text put by the user. It will return the object received from the GMaps APIs.
        GMapsXPathHandler XPH = new GMapsXPathHandler(object.retriveResult()); //Creating GMapsXPathHandler(XML Document to work on)
        Location locations[] = XPH.extractFromDoc(); //Converting the Location Document to Array[]
        //Retrieving weather for every location using OpenWeather APIs.
        for (int i = 0; i < locations.length; i++) { 
            XMLRetriver object2 = new XMLRetriver(WeatherURLHandler.generateURL(locations[i]));
            WeatherXPathHandler WXPH = new WeatherXPathHandler(object2.retriveResult());
            locations[i].setForecast(WXPH.extractFromDoc());
        }
        //Converting loactions Array to ObservableList
        final ObservableList locationsList = myTable.getItems();
        locationsList.clear();
        
        for (Location location: locations) {
            locationsList.add(location);
        }
        myTable.setItems(locationsList);
    
    }
    
    @FXML //Setting up and opening the Preferences Menu
    public void preferencesMenuEvent() throws IOException {

            Parent root = FXMLLoader.load(getClass().getResource("Preferences.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Preferences");
            stage.setScene(new Scene(root, 450, 290));
            stage.show();
    }
    
    @FXML //closing the system when close item is selected
    public void closeEvent () {
        System.exit(0);
    }

}
