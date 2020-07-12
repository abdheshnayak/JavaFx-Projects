package com.nayak.EMSystem;

import com.nayak.EMSystem.helperClass.DatabaseHandler;
import com.nayak.EMSystem.helperClass.staticClass;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class TechAddStu {
    @FXML
    public TextField nameField;
    @FXML
    public TextField addressField;
    @FXML
    public TextField dobField;
    @FXML
    public Button addBtn;
    @FXML
    public Button closeBtn;
    @FXML
    public TextField gmailField;
    @FXML
    public TextField conNumber;
    @FXML
    public CheckBox maleCheckBox;
    @FXML
    public CheckBox femaleCheckBox;
    @FXML
    public TextField RollNo;
    @FXML
    public TextField stream;
    @FXML
    public ChoiceBox streamSelector;

    public void initialize(){
        streamSelector.getItems().clear();
        String[] streamData=new String[6];
        streamData[0]="B.TECH";
        streamData[1]="MEDICAL";
        streamData[2]="AGRICULTURE";
        streamData[3]="PHARMECY";
        streamData[4]="B.COM";
        streamData[5]="B.SC";
        streamSelector.getItems().addAll(streamData);

        streamSelector.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

            }
        });


    }

    public void handleMouse(MouseEvent mouseEvent) throws SQLException, IOException {
        if (mouseEvent.getSource().equals(addBtn)){

            DatabaseHandler db= new DatabaseHandler();
            boolean b=true;
//            INSERT INTO `HISTU`(`NAME`, `STREAM`, `ADDRESS`, `ROLL_NUM`, `DOJ`, `DOB`, `GMAIL`, `CON_NUM`, `GENDER`, `AFF_NUM`, `STATUS`) VALUES ([value-1],[value-2],[value-3],[value-4],[value-5],[value-6],[value-7],[value-8],[value-9],[value-10],[value-11])
            b=db.write("INSERT INTO `TECHSTU`(`NAME`, `STREAM`, `ADDRESS`, `ROLL_NUM`, `DOB`, `GMAIL`, `CON_NUM`, `GENDER`, `AFF_NUM`, `STATUS`) VALUES ('" +
                    nameField.getText().trim() +
                    "','" +
                    streamSelector.getValue().toString() +
                    "','" +
                    addressField.getText().trim()+
                    "','" +
                    RollNo.getText().trim() +
                    "','" +
                    dobField.getText().trim() +
                    "','" +
                    gmailField.getText().trim() +
                    "','" +
                    conNumber.getText().trim() +
                    "','" +
                    staticClass.gender +
                    "','" +
                    staticClass.aff_num +
                    "','" +
                    "ACTIVE" +
                    "');");

            System.out.println(b);
            if(b){
                JOptionPane.showMessageDialog(null,"Data is added","Success", JOptionPane.PLAIN_MESSAGE);
            }
        }else if(mouseEvent.getSource().equals(closeBtn)){

            Stage st=(Stage)closeBtn.getScene().getWindow();
            BorderPane root=(BorderPane) FXMLLoader.load(getClass().getResource("schoolMW.fxml"));
            Scene scene= new Scene(root,500,500);
            st.setScene(scene);
            st.show();
        }
    }

    public void checkBoxHandle(ActionEvent actionEvent) {
        if(actionEvent.getSource().equals(maleCheckBox)){
            femaleCheckBox.setSelected(false);
            staticClass.gender='M';
        }else if (actionEvent.getSource().equals(femaleCheckBox)){
            maleCheckBox.setSelected(false);
            staticClass.gender='F';
        }
    }

}
