package com.acet.EIMS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.acet.EIMS.helperClasses.*;
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
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateScreenController {
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
    public Button updateBtn;
    @FXML
    public Button closeBtn;
    @FXML
    public Button searchBtn;
    @FXML
    public TextField oldEmpIDField;
    public CheckBox maleCheckBox;
    public CheckBox femaleCheckBox;
    public TextField gmailField;
    public TextField conNumber;


    public void handleMouse(MouseEvent mouseEvent) throws SQLException, IOException{
        if (mouseEvent.getSource().equals(updateBtn)){

            DatabaseHandler2 db = new DatabaseHandler2();
            boolean b;
            b=db.write("UPDATE `EMP` SET `NAME`='" +
                    nameField.getText().trim() +
                    "',`ADDRESS`='" +
                    addressField.getText().trim() +
                    "',`CTZ`='" +
                    CTZFeld.getText().trim() +
                    "',`POSITION`='" +
                    positionField.getText().trim() +
                    "',`EMP_ID`='" +
                    empidField.getText().trim() +
                    "',`DOB`='" +
                    dobField.getText().trim() +
                    "',`GMAIL`='" +
                    gmailField.getText().trim() +
                    "',`CON_NUM`='" +
                    conNumber.getText().trim() +
                    "',`SEX`='" +
                    staticClass.gender +
                    "' WHERE EMP_ID='" +
                    oldEmpIDField.getText().trim() +
                    "'");
//            b=db.write("UPDATE `emp` SET `name`='" +
//                    nameField.getText().trim() +
//                    "',`address`='" +
//                    addressField.getText().trim() +
//                    "',`ctz`='" +
//                    CTZFeld.getText().trim() +
//                    "',`position`='" +
//                    positionField.getText().trim() +
//                    "',`emp_id`='" +
//                    empidField.getText().trim() +
//                    "',`dob`='" +
//                    dobField.getText().trim() +
//                    "' WHERE emp_id = " +
//                    oldEmpIDField.getText().trim() +
//                    ";");
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
        else if(mouseEvent.getSource().equals(searchBtn)){
            DatabaseHandler2 db= new DatabaseHandler2();
            ResultSet rs;
            rs=db.read("SELECT `NAME`, `ADDRESS`, `CTZ`, `POSITION`, `EMP_ID`, `DOJ`, `DOB`, `GMAIL`, `CON_NUM`, `SEX` FROM `EMP` WHERE emp_id = " +
                     oldEmpIDField.getText().trim()+
                    ";");
            rs.next();
            try {
                nameField.setText(rs.getString(1));
                addressField.setText(rs.getString(2));
                CTZFeld.setText(rs.getString(3));
                positionField.setText(rs.getString(4));
                empidField.setText(String.valueOf(rs.getInt(5)));
                dobField.setText(rs.getString(7));
                gmailField.setText(rs.getString(8));
                conNumber.setText(rs.getString(9));
//                System.out.println(rs.getString(10));
                if(rs.getString(10).equals("M")){
                    maleCheckBox.setSelected(true);
                    staticClass.gender='M';
                }else {
                    femaleCheckBox.setSelected(true);
                    staticClass.gender='F';
                }
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"Please Enter Right Informations","No Data Found", JOptionPane.ERROR_MESSAGE);
            }
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
