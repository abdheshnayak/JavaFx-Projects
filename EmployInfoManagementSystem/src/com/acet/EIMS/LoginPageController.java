package com.acet.EIMS;

import com.acet.EIMS.helperClasses.DatabaseHandler2;
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
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPageController {

    @FXML
    public TextField loginID;
    @FXML
    public PasswordField loginPassword;
    @FXML
    public Button closeBtn;
    @FXML
    public Button loginBtn;

    public void getButtonEvent(MouseEvent mouseEvent) throws SQLException{
        if(mouseEvent.getSource().equals(closeBtn)){
            Stage st= (Stage)closeBtn.getScene().getWindow();
            st.close();
        }else if(mouseEvent.getSource().equals(loginBtn)){
            DatabaseHandler2 db = new DatabaseHandler2();
            ResultSet rs;
//            loginID.setText("uvs6099");
//            loginPassword.setText("uday6099");
            rs=db.read("SELECT admin_name, password FROM admin WHERE admin_name = lower('" +
                    loginID.getText().trim() +
                    "') AND password= '" +
                    loginPassword.getText().trim() +
                    "'");

            rs.next();
            try {

                String hello=rs.getString(1);
//                System.out.println(rs.getString(1));

                Stage st=(Stage)loginBtn.getScene().getWindow();
                BorderPane root=(BorderPane) FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
                Scene scene= new Scene(root,500,500);
                st.setTitle("EIMS");
                st.setScene(scene);
                st.show();

            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"Error:" + e.getMessage(),"Error Occured", JOptionPane.ERROR_MESSAGE);
                System.out.println("hello");
            }
        }
    }
}
