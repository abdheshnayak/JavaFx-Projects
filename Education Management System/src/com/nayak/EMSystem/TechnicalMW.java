package com.nayak.EMSystem;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TechnicalMW {
    public Button AddStudent;
    public Button PassOut;
    public Button DroppedOut;

    public void handleMouseClick(MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getSource().equals(AddStudent)){

            Stage st=(Stage)AddStudent.getScene().getWindow();
            BorderPane root=(BorderPane) FXMLLoader.load(getClass().getResource("techAddStu.fxml"));
            Scene scene= new Scene(root,500,500);
            st.setTitle("EIMS");
            st.setScene(scene);
            st.show();

        }else if(mouseEvent.getSource().equals(PassOut)){

            Stage st=(Stage)AddStudent.getScene().getWindow();
            BorderPane root=(BorderPane) FXMLLoader.load(getClass().getResource("passedOut.fxml"));
            Scene scene= new Scene(root,500,500);
            st.setTitle("EIMS");
            st.setScene(scene);
            st.show();

        }else if(mouseEvent.getSource().equals(DroppedOut)){

            Stage st=(Stage)AddStudent.getScene().getWindow();
            BorderPane root=(BorderPane) FXMLLoader.load(getClass().getResource("dropOut.fxml"));
            Scene scene= new Scene(root,500,500);
            st.setTitle("EIMS");
            st.setScene(scene);
            st.show();
        }
    }
}
