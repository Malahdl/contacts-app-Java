/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contacts_app;

import java.io.IOException;
import javafx.collections.ObservableList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author malah
 */
public class FXML2Controller implements Initializable {

    @FXML
    private TextField nameText;
    
    @FXML
    private Label label;
    
    ObservableList<String> contacts;
    ObservableList<String> numbers;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    } 
    
    public void getInfo1(ObservableList<String> nameList,ObservableList<String> numberList)
    {
        contacts = nameList;
        numbers = numberList;
        
    }

    @FXML
    private void onSearch(ActionEvent event) throws IOException 
    {
        int foundCounter = 0;
        label.setText("");
        if(nameText.getText() == "")
        {
            label.setText("Please Enter a Name.");
            label.setTextFill(Color.RED);
        }
        else
        {
            for (String item : contacts) 
            {
               String a [] = item.split(" ");

               if((nameText.getText().equalsIgnoreCase(a[0])) || (nameText.getText().equalsIgnoreCase(a[1])))
               {
                   foundCounter += 1;
                   label.setText(label.getText() + "Found " + a[0] + " " + a[1] + " with the number " + numbers.get(contacts.indexOf(item)) + "!!\n");
                   label.setTextFill(Color.GREEN);
                   
               }
               if(nameText.getText().equalsIgnoreCase(item))
               {
                   foundCounter += 1;
                   label.setText("Found " + nameText.getText() + " with the number " + numbers.get(contacts.indexOf(item)) + "!!");
                   label.setTextFill(Color.GREEN);
               }
               
            }
            if(foundCounter == 0)
               {
                   label.setText("This name doesn't exist in the list.");
                   label.setTextFill(Color.RED);
               }
            
        }
    }

    @FXML
    private void onBack(ActionEvent event) throws IOException 
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML1.fxml"));
        
        ((Node)event.getSource()).getScene().getWindow().hide();
        
        Parent root = loader.load();
        
        FXML1Controller scene1contr = loader.getController();
      
        scene1contr.getInfo2(contacts, numbers);
        
        Stage stage = new Stage();
        
        Scene scene = new Scene(root);
        stage.setTitle("Address Book");
        stage.setScene(scene);
        stage.show();
    }
    
}
