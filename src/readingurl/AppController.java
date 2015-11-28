package readingurl;

import GMaps.GMapsXPathHandler;
import GMaps.Location;
import Weather.WeatherXPathHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class AppController implements Initializable {

    @FXML
    private TableView<Location> table;
    
    @FXML
    private Button myButton;    
    @FXML
    private TextField myText;    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    @FXML 
    public void buttonEvent () {
        if (myText.getText().isEmpty()) return;
        System.out.println("Testo: " + myText.getText());
        XMLRetriver object = new XMLRetriver(GMapsURLHandler.generateURL(myText.getText()));
        GMapsXPathHandler XPH = new GMapsXPathHandler(object.retriveResult());
        Location locations[] = XPH.extractFromDoc();
        for (int i = 0; i < locations.length; i++) {
            XMLRetriver object2 = new XMLRetriver(WeatherURLHandler.generateURL(locations[i]));
            WeatherXPathHandler WXPH = new WeatherXPathHandler(object2.retriveResult());
            locations[i].setForecast(WXPH.extractFromDoc());
        }
        
    }
    
    @FXML 
    public void closeEvent () {
        Platform.exit();
    }
    
}
