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
import model.Part;


//FXML Controller class

public class ModifyPartController implements Initializable {
    
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
    private TextField mcTxt;

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

    
    //Returns to main screen after confirmation.
    @FXML
    void onActionDisplayMain(ActionEvent event) throws IOException {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Changes will not be saved, continue?");
        
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.isPresent() && result.get() == ButtonType.OK){
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show(); 
        }

    }

    
    //Updates the part.
    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {
        
        try{
            
            int id = Integer.parseInt(idTxt.getText());
            String name = nameTxt.getText();
            int inv = Integer.parseInt(invTxt.getText());
            double price = Double.parseDouble(priceTxt.getText());
            int max = Integer.parseInt(maxTxt.getText());
            int min = Integer.parseInt(minTxt.getText());
            
            if(min > max){      //Checks if minimum value is less than maximum value.
                
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("Minimum value cannot exceed Maximum value.");
                alert.showAndWait();
            
            }
            
            else{

                if(inHouseBtn.isSelected())
                    InventoryList.updatePart(id, new InHouse(id, name, price, inv, min, max, Integer.parseInt(mcTxt.getText())));
                else
                    InventoryList.updatePart(id, new Outsourced(id, name, price, inv, min, max, mcTxt.getText()));


                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            
            }
        
        }
        
        catch(NumberFormatException e){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please enter a valid value for each field.");
            alert.showAndWait();
            
        }
        
    }
    
    
    //Displays the selected parts information in the text fields.
    public void setPart(Part part){
        
        idTxt.setText(String.valueOf(part.getId()));
        nameTxt.setText(part.getName());
        invTxt.setText(String.valueOf(part.getInv()));
        priceTxt.setText(String.valueOf(part.getPrice()));
        maxTxt.setText(String.valueOf(part.getMax()));
        minTxt.setText(String.valueOf(part.getMin()));
        if(part instanceof InHouse){
            
            mcTxt.setText(String.valueOf(((InHouse) part).getMachineId()));
            inHouseBtn.setSelected(true);
            mcTextLabel.setText("Machine ID");
            
            
        }
        if(part instanceof Outsourced) {
            
            mcTxt.setText(((Outsourced) part).getCompanyName());
            outsourcedBtn.setSelected(true);
            mcTextLabel.setText("Company Name");
        }
            
    }   


    //Initializes the controller class.

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    
}
