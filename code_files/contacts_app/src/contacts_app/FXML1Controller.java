/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contacts_app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author malah
 */
public class FXML1Controller implements Initializable {

    @FXML
    private TextField fNameText;
    @FXML
    private TextField sNameText;
    @FXML
    private TextField numberText;
    @FXML
    private ListView<String> nameList;
    @FXML
    private ListView<String> mobileList;
    @FXML
    private Label msg;

    ObservableList<String> names = FXCollections.observableArrayList();
    ObservableList<String> numbers = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fNameText.setStyle(" -fx-text-box-border: black");
        sNameText.setStyle(" -fx-text-box-border: black");
        numberText.setStyle(" -fx-text-box-border: black");
    }

    @FXML
    private void onAddEntry(ActionEvent event) {
        int nameCounter = 0;
        if (fNameText.getText() == "" || sNameText.getText() == "" || numberText.getText() == "") {

            msg.setText("You Didn't Complete The Inputs!!");
            msg.setTextFill(Color.RED);
            msg.setWrapText(true);
            fNameText.setStyle(" -fx-text-box-border: red");
            sNameText.setStyle(" -fx-text-box-border: red");
            numberText.setStyle(" -fx-text-box-border: red");
        } else {
            String[] s = (numberText.getText()).split("");
            if (s.length != 10) {
                if (s.length < 10) {
                    msg.setText("the mobile number has less than 10 numbers!!");
                    msg.setTextFill(Color.RED);
                    msg.setWrapText(true);
                    numberText.setStyle(" -fx-text-box-border: red");

                } else {
                    msg.setText("the mobile number has more than 10 numbers!!");
                    msg.setTextFill(Color.RED);
                    numberText.setStyle(" -fx-text-box-border: red");
                }
            } else {
                int letterCounter = 0;
                int numberCounter = 0;

                char[] numberChars = (numberText.getText()).toCharArray();
                for (char c : numberChars) {
                    if (!Character.isDigit(c)) {
                        letterCounter += 1;
                    }
                }

                char[] name1Chars = (fNameText.getText()).toCharArray();
                for (char c : name1Chars) {
                    if (Character.isDigit(c)) {
                        numberCounter += 1;
                    }
                }

                char[] name2Chars = (sNameText.getText()).toCharArray();
                for (char c : name2Chars) {
                    if (Character.isDigit(c)) {
                        numberCounter += 1;
                    }
                }
                if (letterCounter > 0 || numberCounter > 0) {
                    msg.setText("invalid mobile number or name!!");
                    msg.setTextFill(Color.RED);
                    msg.setWrapText(true);
                    numberText.setStyle(" -fx-text-box-border: red");
                    fNameText.setStyle(" -fx-text-box-border: red");
                    sNameText.setStyle(" -fx-text-box-border: red");
                } else if (letterCounter == 0 && numberCounter == 0) {
                    String name = fNameText.getText() + " " + sNameText.getText();
                    for (String item : names) {
                        if (item.equalsIgnoreCase(name)) {

                            nameCounter += 1;
                            break;
                        }

                    }

                    if (nameCounter > 0) {
                        msg.setText("The name is already in the list!!");
                        msg.setTextFill(Color.RED);
                    } else {
                        nameList.getItems().add(name);
                        names.add(name);
                        mobileList.getItems().add(numberText.getText());
                        numbers.add(numberText.getText());
                        msg.setText("");
                    }

                    /*fNameText.setText("");
                    sNameText.setText("");
                    numberText.setText("");
                    msg.setText("");*/
                    fNameText.setStyle(" -fx-text-box-border: black");
                    sNameText.setStyle(" -fx-text-box-border: black");
                    numberText.setStyle(" -fx-text-box-border: black");

                }
            }
        }
    }

    @FXML
    private void onDeleteEntry(ActionEvent event) {

        String selectedName = nameList.getSelectionModel().getSelectedItem();
        String selectedNumber = mobileList.getSelectionModel().getSelectedItem();
        
        if (selectedName != null) {
            int index1 = nameList.getSelectionModel().getSelectedIndex();
            names.remove(selectedName);
            nameList.getItems().remove(selectedName);
            numbers.remove(index1);
            mobileList.getItems().remove(index1);
        } else {
            int index1 = mobileList.getSelectionModel().getSelectedIndex();
            numbers.remove(selectedNumber);
            mobileList.getItems().remove(selectedNumber);
            names.remove(index1);
            nameList.getItems().remove(index1);
        }

        nameList.getSelectionModel().clearSelection();
        mobileList.getSelectionModel().clearSelection();

        nameList.getSelectionModel().select(null);
        mobileList.getSelectionModel().select(null);

    }

    public void getInfo2(ObservableList<String> nameList, ObservableList<String> numberList) {
        this.names = nameList;
        this.numbers = numberList;

        this.nameList.getItems().clear();
        this.mobileList.getItems().clear();

        for (String item : names) {
            this.nameList.getItems().add(item);
        }

        for (String item : numbers) {
            this.mobileList.getItems().add(item);
        }

//        this.nameList.setItems(nameList);
//        this.mobileList.setItems(numberList);
    }

    @FXML
    private void onSearch(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML2.fxml"));

        ((Node) event.getSource()).getScene().getWindow().hide();

        Parent root = loader.load();

        FXML2Controller scene2contr = loader.getController();
        scene2contr.getInfo1(names, numbers);

        Stage stage = new Stage();

        Scene scene = new Scene(root);
        stage.setTitle("Search");
        stage.setScene(scene);
        stage.show();
    }

}
