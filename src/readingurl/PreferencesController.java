package readingurl;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * This is the controller of the Preferences,
 * Here are definied the structure and the events of its components.
 * 
 * @author Pietro Venturini
 */

public class PreferencesController implements Initializable{
    
    @FXML
    private CheckBox proxyCheckbox;
    
    @FXML
    private TextField addressField, portField;
    
    @FXML
    private Button cancelButton, applyButton;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
            addressField.setDisable(true);
            portField.setDisable(true);
            applyButton.setDisable(true);
    }
    
    //The Cancel Button closes the Preferences window
    public void cancelButtonEvent() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    
    //The Apply Button sets up the XMLRetriver (Eventually using a Proxy)
    public void applyButtonEvent() {
        if (!proxyCheckbox.isSelected())
            XMLRetriver.setProxy(null);
        else {
            Proxy newProxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(addressField.getText(), Integer.parseInt(portField.getText()))); //Setting up proxy
            XMLRetriver.setProxy(newProxy);
        }
        
        //Closing the Preferences Window
        Stage stage = (Stage) applyButton.getScene().getWindow();
        stage.close();
    }
    
    //The Checkbox establish the visibility of the other fields
    public void proxyCheckboxEvent() {
        if(proxyCheckbox.isSelected()) {
            addressField.setDisable(false);
            portField.setDisable(false);
            applyButton.setDisable(false);
        }
        else {
            addressField.setDisable(true);
            portField.setDisable(true);
            applyButton.setDisable(true);
        }
    }
 
}
