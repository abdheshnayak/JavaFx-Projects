package com.nayak.EMSystem;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindowController {

    public Button EducationMinister;
    public Button TechnicalEducation;
    public Button higherEducation;
    public Button schoolEducation;

    public void handleMouseClick(MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getSource().equals(schoolEducation)){


//            Stage st=(Stage)EducationMinister.getScene().getWindow();
            Stage st= new Stage();
            BorderPane root=(BorderPane) FXMLLoader.load(getClass().getResource("schoolLoginPage.fxml"));
            Scene scene= new Scene(root,500,500);
            st.setTitle("EIMS");
            st.setScene(scene);
            st.show();

        }else if(mouseEvent.getSource().equals(higherEducation)){

//            Stage st=(Stage)EducationMinister.getScene().getWindow();
            Stage st= new Stage();
            BorderPane root=(BorderPane) FXMLLoader.load(getClass().getResource("higherLoginPage.fxml"));
            Scene scene= new Scene(root,500,500);
            st.setTitle("EIMS");
            st.setScene(scene);
            st.show();

        }else if(mouseEvent.getSource().equals(TechnicalEducation)){

//            Stage st=(Stage)EducationMinister.getScene().getWindow();
            Stage st= new Stage();
            BorderPane root=(BorderPane) FXMLLoader.load(getClass().getResource("technicalLoginPage.fxml"));
            Scene scene= new Scene(root,500,500);
            st.setTitle("EIMS");
            st.setScene(scene);
            st.show();

        }else if(mouseEvent.getSource().equals(EducationMinister)){

//            Stage st=(Stage)EducationMinister.getScene().getWindow();
            Stage st= new Stage();
            BorderPane root=(BorderPane) FXMLLoader.load(getClass().getResource("adminLoginPage.fxml"));
            Scene scene= new Scene(root,500,500);
            st.setTitle("EIMS");
            st.setScene(scene);
            st.show();

        }
    }
}
