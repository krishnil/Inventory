package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.InHouse;
import model.InventoryList;
import model.Outsourced;


//FXML Controller class

public class AddPartController implements Initializable {
    
    Stage stage;
    Parent scene;
    
    @FXML
    private RadioButton inHouseBtn;

    @FXML
    private ToggleGroup Type;

    @FXML
    private RadioButton outsourcedBtn;

    @FXML
    private Label mcTextLabel;

    @FXML
    private TextField idTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField invTxt;

    @FXML
    private TextField priceTxt;

    @FXML
    private TextField mcText;

    @FXML
    private TextField maxTxt;

    @FXML
    private TextField minTxt;

    @FXML
    void onActionDisplayCompany(ActionEvent event) {
        
        mcTextLabel.setText("Company Name");

    }

    @FXML
    void onActionDisplayMachine(ActionEvent event) {
        
        mcTextLabel.setText("Machine ID");

    }

    
    //Returns to Main Screen after confirmation. 
    @FXML
    void onActionDisplayMain(ActionEvent event) throws IOException {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Data will not be saved, continue?");
        
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.isPresent() && result.get() == ButtonType.OK){
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show(); 
        }
        

    }

    
    //Saves the part .
    @FXML
    void onActionSavePart(ActionEvent event) throws IOException {
        
        try{
            
        int id = 4;
        String name = nameTxt.getText();
        int inv = Integer.parseInt(invTxt.getText());
        double price = Double.parseDouble(priceTxt.getText());
        int max = Integer.parseInt(maxTxt.getText());
        int min = Integer.parseInt(minTxt.getText());
        
        if(min > max){          //Checks if minimum value is less than maximum.
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Minimum value cannot exceed Maximum value.");
            alert.showAndWait();
            
        }
        
        else{
            
            if(inHouseBtn.isSelected())
                InventoryList.addPart(new InHouse(id, name, price, inv, min, max, Integer.parseInt(mcText.getText())));
            else
                InventoryList.addPart(new Outsourced(id, name, price, inv, min, max, mcText.getText()));


            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
            
        }
        
        }
        
        catch(NumberFormatException e){     //Checks if input has correct data type.
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please enter a valid value for each field.");
            alert.showAndWait();
            
        }

    }

    

    //Initializes the controller class.

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        mcTextLabel.setText("Machine ID");
        
    }    

    
}
