package com.nayak.EMSystem;

import com.nayak.EMSystem.helperClass.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SchoolLoginPageController {

    @FXML
    public PasswordField loginPassword;
    @FXML
    public TextField loginID;
    @FXML
    public Button closeBtn;
    @FXML
    public Button loginBtn;


    public void getButtonEvent(MouseEvent mouseEvent) throws IOException, SQLException {
        if(mouseEvent.getSource().equals(loginBtn)) {
            DatabaseHandler db = new DatabaseHandler();
            ResultSet rs;
//            loginID.setText("uvs6099");
//            loginPassword.setText("uday6099");
                rs = db.read("SELECT NAME, password FROM USERDATA WHERE AFF_NUM = lower('" +
                        loginID.getText().trim() +
                        "') AND PASSWORD= '" +
                        loginPassword.getText().trim() +
                        "'  AND TYPE ='SCHOOL EDUCATION'");
                rs.next();

            try {

                String hello = rs.getString(1);
//                System.out.println(rs.getString(1));

                staticClass.myParent="schoolMW.fxml";
                staticClass.aff_num=loginID.getText().trim();
                Stage st = (Stage) loginBtn.getScene().getWindow();
                BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("schoolMW.fxml"));
                Scene scene = new Scene(root, 500, 500);
                st.setTitle("EMS");
                st.setScene(scene);
                st.show();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error:" + e.getMessage(), "Error Occured", JOptionPane.ERROR_MESSAGE);
                System.out.println("hello");
            }
        }else if(mouseEvent.getSource().equals(closeBtn)){
            Stage st=(Stage)closeBtn.getScene().getWindow();
            BorderPane root=(BorderPane) FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
            Scene scene= new Scene(root,500,500);
            st.setScene(scene);
            st.show();
        }
    }
}
