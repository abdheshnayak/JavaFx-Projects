package com.nayak.EMSystem;

import com.nayak.EMSystem.helperClass.*;
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
import java.sql.SQLException;

public class PassedOut {
    @FXML
    public Button closeBtn;
    @FXML
    public Button submitBtn;
    @FXML
    public TextField rollNo;

    public void handleMouse(MouseEvent mouseEvent) throws IOException, SQLException {

        if(mouseEvent.getSource().equals(submitBtn)){

            String tableName;
            if(staticClass.myParent.equals("technicalMW.fxml")){
                tableName="TECHSTU";
            }else if(staticClass.myParent.equals("higherMW.fxml")){
                tableName="HISTU";
            }else if (staticClass.myParent.equals("schoolMW.fxml")){
                tableName="SCHOOLSTU";
            }

            DatabaseHandler db = new DatabaseHandler();
            boolean b;
            b=db.write("UPDATE `" +
                    "SCHOOLSTU" +
                    "` SET `STATUS`= 'PASSEDOUT' WHERE ROLL_NUM = '" +
                    rollNo.getText().trim() +
                    "' AND AFF_NUM = '" +
                    staticClass.aff_num +
                    "';");

            if(b){
                JOptionPane.showMessageDialog(null,"Data is UPDATED succesfully.","Success", JOptionPane.PLAIN_MESSAGE);
            }
        }else if(mouseEvent.getSource().equals(closeBtn)){
            Stage st=(Stage)closeBtn.getScene().getWindow();
            BorderPane root=(BorderPane) FXMLLoader.load(getClass().getResource(staticClass.myParent));
            Scene scene= new Scene(root,500,500);
            st.setScene(scene);
            st.show();
        }
    }
}
