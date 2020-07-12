package com.nayak.EMSystem;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminMW {
    public Button AddOrgnization;
    public Button AnalyzeData;

    public void handleMouseClick(MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getSource().equals(AddOrgnization)){

            Stage st=(Stage) AddOrgnization.getScene().getWindow();
            BorderPane root=(BorderPane) FXMLLoader.load(getClass().getResource("addOrg.fxml"));
            Scene scene= new Scene(root,500,500);
            st.setTitle("EIMS");
            st.setScene(scene);
            st.show();

        }else if(mouseEvent.getSource().equals(AnalyzeData)) {

            Stage st = (Stage) AddOrgnization.getScene().getWindow();
            BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("analyzeWindow.fxml"));
            Scene scene = new Scene(root, 900, 500);
            st.setTitle("EIMS");
            st.setScene(scene);
            st.show();
        }
    }
}
