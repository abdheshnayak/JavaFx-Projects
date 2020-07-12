package com.nayak.EMSystem;

import com.nayak.EMSystem.helperClass.DatabaseHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class AddOrg {
    @FXML
    public TextField nameField;
    @FXML
    public TextField aff_num;
    @FXML
    public TextField orgState;
    @FXML
    public Button addBtn;
    @FXML
    public Button closeBtn;
    @FXML
    public TextField orgdist;
    @FXML
    public PasswordField passwordField;
    @FXML
    public ChoiceBox typeSelector;

    public void initialize() {
        typeSelector.getItems().clear();
        String[] streamData = new String[3];
        streamData[0] = "SCHOOL EDUCATION";
        streamData[1] = "HIGHER EDUCATION";
        streamData[2] = "TECHNICAL EDUCATION";
        typeSelector.getItems().addAll(streamData);

        typeSelector.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

            }
        });
    }

        public void handleMouse(MouseEvent mouseEvent) throws SQLException, IOException {
        if (mouseEvent.getSource().equals(addBtn)){

            DatabaseHandler db= new DatabaseHandler();
            boolean b=true;
            b=db.write("INSERT INTO `USERDATA`(`NAME`, `AFF_NUM`, `PASSWORD`, `STATE`, `DISTRICT`, `TYPE`) VALUES ('" +
                    nameField.getText().trim() +
                    "','" +
                    aff_num.getText().trim() +
                    "','" +
                    passwordField.getText().trim() +
                    "','" +
                    orgState.getText().trim() +
                    "','" +
                    orgdist.getText().trim() +
                    "','" +
                    typeSelector.getValue() +
                    "');");

            System.out.println(b);
            if(b){
                JOptionPane.showMessageDialog(null,"Data is added","Success", JOptionPane.PLAIN_MESSAGE);
            }
        }else if(mouseEvent.getSource().equals(closeBtn)){

            Stage st=(Stage)closeBtn.getScene().getWindow();
            BorderPane root=(BorderPane) FXMLLoader.load(getClass().getResource("adminMW.fxml"));
            Scene scene= new Scene(root,500,500);
            st.setScene(scene);
            st.show();
        }
    }

}
