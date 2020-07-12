package com.acet.EIMS;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindowController {
    @FXML
    public Button addbtn;
    @FXML
    public Button deletebtn;
    @FXML
    public Button updatebtn;
    @FXML
    public Button viewbtn;


    public void handleMouse(MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getSource().equals(addbtn)){
            Stage st= (Stage)addbtn.getScene().getWindow();
//            Stage st= new Stage();
            BorderPane root=(BorderPane) FXMLLoader.load(getClass().getResource("addScreen.fxml"));
            Scene scene= new Scene(root,600,600);
            st.setScene(scene);
            st.show();

        }else if(mouseEvent.getSource().equals(deletebtn)){


            Stage st= (Stage)addbtn.getScene().getWindow();
//            Stage st= new Stage();
            BorderPane root=(BorderPane) FXMLLoader.load(getClass().getResource("deleteScreen.fxml"));
            Scene scene= new Scene(root,600,300);
            st.setScene(scene);
            st.show();

        }else if(mouseEvent.getSource().equals(updatebtn)){

            Stage st= (Stage)addbtn.getScene().getWindow();
//            Stage st= new Stage();
            BorderPane root=(BorderPane) FXMLLoader.load(getClass().getResource("updateScreen.fxml"));
            Scene scene= new Scene(root,600,600);
            st.setScene(scene);
            st.show();
        }else if(mouseEvent.getSource().equals(viewbtn)){

            Stage st= (Stage)addbtn.getScene().getWindow();
//            Stage st= new Stage();
            BorderPane root=(BorderPane) FXMLLoader.load(getClass().getResource("viewScreen.fxml"));
            Scene scene= new Scene(root,700,400);
            st.setScene(scene);
            st.show();
        }
    }
}
