package com.acet.EIMS;

import com.acet.EIMS.helperClasses.DatabaseHandler2;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteScreenController {

    @FXML
    public TextField nameField;
    @FXML
    public TextField empidField;
    @FXML
    public TextField dobField;
    @FXML
    public Button DeleteBtn;
    @FXML
    public Button closeBtn;

    public void handleMouse(MouseEvent mouseEvent) throws  SQLException, IOException {
        if (mouseEvent.getSource().equals(DeleteBtn)){

            DatabaseHandler2 db=new DatabaseHandler2();
            boolean b;
            ResultSet rs;
            rs=db.read("SELECT COUNT(*) FROM EMP WHERE NAME = '" +
                    nameField.getText().trim() +
                            "' and EMP_ID = '" +
                            empidField.getText().trim() +
                            "' and DOB = '" +
                            dobField.getText().trim() +
                            "';");
            rs.next();
            if(rs.getInt(1)==1){

                b=db.write("DELETE FROM `EMP` WHERE NAME = '" +
                        nameField.getText().trim() +
                        "' and EMP_ID = '" +
                        empidField.getText().trim() +
                        "' and DOB = '" +
                        dobField.getText().trim() +
                        "';");
                if(b){
                    JOptionPane.showMessageDialog(null,"Data is Deleted","Success", JOptionPane.PLAIN_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(null,"Please Enter Right Informations","No Data Found", JOptionPane.ERROR_MESSAGE);
                }
            }else {
                JOptionPane.showMessageDialog(null,"Please Enter Right Informations","No Data Found", JOptionPane.ERROR_MESSAGE);
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
