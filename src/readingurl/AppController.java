package readingurl;

import GMaps.GMapsXPathHandler;
import GMaps.Location;
import Weather.WeatherXPathHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;


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
    
    @FXML 
    public void buttonEvent () {
        if (myText.getText().isEmpty()) 
            return;

        XMLRetriver object = new XMLRetriver(GMapsURLHandler.generateURL(myText.getText())); //creating XMLRetriver object with the text put by the user.
        GMapsXPathHandler XPH = new GMapsXPathHandler(object.retriveResult());
        Location locations[] = XPH.extractFromDoc();
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
    
    @FXML 
    public void closeEvent () {
        Platform.exit();
    }
    
   
    
}
