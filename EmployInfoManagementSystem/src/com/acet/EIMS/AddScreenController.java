package com.acet.EIMS;

import com.acet.EIMS.helperClasses.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class AddScreenController {
    @FXML
    public TextField nameField;
    @FXML
    public TextField addressField;
    @FXML
    public TextField CTZFeld;
    @FXML
    public TextField positionField;
    @FXML
    public TextField empidField;
    @FXML
    public TextField dobField;
    @FXML
    public Button addBtn;
    @FXML
    public Button closeBtn;
    public TextField gmailField;
    public TextField conNumber;
    public CheckBox maleCheckBox;
    public CheckBox femaleCheckBox;

    public void handleMouse(MouseEvent mouseEvent) throws SQLException, IOException {
        if (mouseEvent.getSource().equals(addBtn)){

            DatabaseHandler2 db= new DatabaseHandler2();
            boolean b;
            b=db.write("INSERT INTO `EMP`(`NAME`, `ADDRESS`, `CTZ`, `POSITION`, `EMP_ID`, `DOB`, `GMAIL`, `CON_NUM`, `SEX`) VALUES ('" +
                    nameField.getText().trim() +
                    "','" +
                    addressField.getText().trim() +
                    "','" +
                    CTZFeld.getText().trim() +
                    "','" +
                    positionField.getText().trim() +
                    "','" +
                    empidField.getText().trim() +
                    "','" +
                    dobField.getText().trim() +
                    "','" +
                    gmailField.getText().trim() +
                    "','" +
                    conNumber.getText().trim() +
                    "','" +
                    staticClass.gender +
                    "')");
//            b=db.write("INSERT INTO `emp`(`name`, `address`, `ctz`, `position`, `emp_id`, `dob`) VALUES ('" +
//                    nameField.getText().trim() +
//                    "','" +
//                    addressField.getText().trim() +
//                    "','" +
//                    CTZFeld.getText().trim() +
//                    "','" +
//                    positionField.getText().trim() +
//                    "'," +
//                    empidField.getText().trim() +
//                    ",'" +
//                    dobField.getText().trim() +
//                    "');");
            System.out.println(b);
            if(b){
                JOptionPane.showMessageDialog(null,"Data is added","Success", JOptionPane.PLAIN_MESSAGE);
            }
        }else if(mouseEvent.getSource().equals(closeBtn)){

            Stage st=(Stage)closeBtn.getScene().getWindow();
            BorderPane root=(BorderPane) FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
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
