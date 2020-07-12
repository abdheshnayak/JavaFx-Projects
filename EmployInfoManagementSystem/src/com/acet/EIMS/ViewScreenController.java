package com.acet.EIMS;

import com.acet.EIMS.helperClasses.DatabaseHandler2;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewScreenController {
    @FXML
    public Button searchBtn;
    public TableView resultArea;
    public TextField empnameField;
    public Button closeBtn;
    public TableColumn tName;
    public TableColumn tAddress;
    public TableColumn tCTZ;
    public TableColumn tPosition;
    public TableColumn tEmpID;
    public TableColumn tDob;
    public Button showAllbtn;
    public TableColumn tEmail;
    public TableColumn tPhone;
    public TableColumn tGender;

    public void handleMouse(MouseEvent mouseEvent) throws IOException, SQLException {


        resultArea.getItems().clear();
        if (mouseEvent.getSource().equals(searchBtn) && !empnameField.getText().equals("")) {


//            System.out.println(empnameField.getText().trim());


            resultArea.setEditable(true);
            tName.setCellValueFactory(new PropertyValueFactory<>("name"));
            tAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

            tCTZ.setCellValueFactory(new PropertyValueFactory<>("ctz"));
            tPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
            tEmpID.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
            tDob.setCellValueFactory(new PropertyValueFactory<>("dob"));

            tEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
            tPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            tGender.setCellValueFactory(new PropertyValueFactory<>("gender"));


            DatabaseHandler2 db = new DatabaseHandler2();
            ResultSet rs;
//            rs = db.read("SELECT `NAME`, `ADDRESS`, `CTZ`, `POSITION`, `EMP_ID`, `DOB`, `GMAIL`, `CON_NUM`, `SEX` FROM `EMP` WHERE WHERE NAME LIKE '" +
//                    empnameField.getText().trim() +
//                    "%';");
            rs =db.read("SELECT * FROM EMP WHERE NAME LIKE '" +
                    empnameField.getText().trim() +
                    "%';");
//            rs = db.read("SELECT * FROM `EMP` WHERE 1;");

            while (rs.next()) {
                resultArea.getItems().add(new data(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(8),rs.getString(9),rs.getString(10)));
            }

        } else if (mouseEvent.getSource().equals(closeBtn)) {

            Stage st = (Stage) closeBtn.getScene().getWindow();
            BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
            Scene scene = new Scene(root, 500, 500);
            st.setScene(scene);
            st.show();
        } else if (mouseEvent.getSource().equals(showAllbtn)) {
            resultArea.setEditable(true);

            tName.setCellValueFactory(new PropertyValueFactory<>("name"));
            tAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

            tCTZ.setCellValueFactory(new PropertyValueFactory<>("ctz"));
            tPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
            tEmpID.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
            tDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
            tEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
            tPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            tGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

            DatabaseHandler2 db = new DatabaseHandler2();
            ResultSet rs;
            rs = db.read("SELECT * FROM `EMP` WHERE 1;");
            while (rs.next()) {
//                System.out.println(rs.getString(9));
                resultArea.getItems().add(new data(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
            }

        }
    }

    public static class data {
        public String name;
        public String address;
        public String ctz;
        public String position;
        public String emp_id;
        public String dob;
        public String Email;
        public String phoneNumber;
        public String gender;

        public data(String name, String address, String ctz, String position, String emp_id, String dob, String email, String phoneNumber, String gender) {
            this.name = name;
            this.address = address;
            this.ctz = ctz;
            this.position = position;
            this.emp_id = emp_id;
            this.dob = dob;
            Email = email;
            this.phoneNumber = phoneNumber;
            this.gender = gender;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCtz() {
            return ctz;
        }

        public void setCtz(String ctz) {
            this.ctz = ctz;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getEmp_id() {
            return emp_id;
        }

        public void setEmp_id(String emp_id) {
            this.emp_id = emp_id;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String email) {
            Email = email;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }
    }
}
