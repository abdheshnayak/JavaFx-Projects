package com.nayak.EMSystem;

import com.nayak.EMSystem.helperClass.DatabaseHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnalyzeWindow {
    @FXML
    public TableColumn status;
    @FXML
    public TableColumn numOfSutdent;
    @FXML
    public TableColumn percentOfStudent;
    @FXML
    public TableColumn Remark;
    @FXML
    public Button closeBtn;
    @FXML
    public ChoiceBox areaSelector;
    @FXML
    public Button showBtn;
    @FXML
    public TableView view;

    public void initialize() {
        view.setEditable(true);

        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        numOfSutdent.setCellValueFactory(new PropertyValueFactory<>("nofStu"));

        percentOfStudent.setCellValueFactory(new PropertyValueFactory<>("perofStu"));
        Remark.setCellValueFactory(new PropertyValueFactory<>("rem"));


        areaSelector.getItems().clear();
        String[] streamData = new String[3];
        streamData[0] = "STATE";
        streamData[1] = "ALL";
        streamData[2] = "DISTRICT";
        areaSelector.getItems().addAll(streamData);

        areaSelector.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

            }
        });
    }

    public void handleMouse(MouseEvent mouseEvent) throws SQLException {
        int a,b,c,d,n,m;

        if(mouseEvent.getSource().equals(showBtn)){
            DatabaseHandler db = new DatabaseHandler();
            ResultSet[] rs=new ResultSet[10];
            if(areaSelector.getValue().equals("ALL")){
                rs[0]=db.read("SELECT COUNT(*) FROM HISTU;");
                rs[0].next();
                int stuall=Integer.parseInt(rs[0].getString(1));
//                int sdlkf= Integer.parseInt(rs[0].getString(1));
                rs[0]=db.read("SELECT COUNT(*) FROM SCHOOLSTU;");
                rs[0].next();
                int SCHstuall=Integer.parseInt(rs[0].getString(1));
                rs[0]=db.read("SELECT COUNT(*) FROM TECHSTU;");
                rs[0].next();
                int TECHSTuall=Integer.parseInt(rs[0].getString(1));


                rs[0]=db.read("SELECT COUNT(*) FROM HISTU WHERE STATUS= 'PASSED';");
                rs[0].next();
                int stuspassed=Integer.parseInt(rs[0].getString(1));
                rs[0]=db.read("SELECT COUNT(*) FROM SCHOOLSTU WHERE STATUS= 'PASSED';");
                rs[0].next();
                int SCHstupassed=Integer.parseInt(rs[0].getString(1));
                rs[0]=db.read("SELECT COUNT(*) FROM TECHSTU WHERE STATUS= 'PASSED';");
                rs[0].next();
                int TECHSTupassed=Integer.parseInt(rs[0].getString(1));

                rs[0]=db.read("SELECT COUNT(*) FROM HISTU WHERE STATUS ='DROPOUT';");
                rs[0].next();
                int stuDROP=Integer.parseInt(rs[0].getString(1));
                rs[0]=db.read("SELECT COUNT(*) FROM SCHOOLSTU WHERE STATUS ='DROPOUT';");
                rs[0].next();
                int SCHstDORP=Integer.parseInt(rs[0].getString(1));
                rs[0]=db.read("SELECT COUNT(*) FROM TECHSTU WHERE STATUS ='DROPOUT';");
                rs[0].next();
                int TECHSTuDROP=Integer.parseInt(rs[0].getString(1));


                view.getItems().clear();

               try {
                   view.getItems().addAll(new data("Higher Passed",String.valueOf(stuspassed),String.valueOf((stuspassed*100)/(stuspassed+stuDROP)),""));
                   view.getItems().addAll(new data("Higher DROPED",String.valueOf(stuspassed),String.valueOf((stuDROP*100)/(stuspassed+stuDROP)),""));
                   view.getItems().addAll(new data("School Passed",String.valueOf(SCHstupassed),String.valueOf((SCHstupassed*100)/(SCHstDORP+SCHstDORP)),""));
                   view.getItems().addAll(new data("School DROPED",String.valueOf(SCHstDORP),String.valueOf((SCHstDORP*100)/(SCHstDORP+SCHstDORP)),""));
                   view.getItems().addAll(new data("TECHNICAL Passed",String.valueOf(TECHSTupassed),String.valueOf((TECHSTupassed*100)/(TECHSTupassed+TECHSTuDROP)),""));
                   view.getItems().addAll(new data("TECHNICAL DROPED",String.valueOf(TECHSTuDROP),String.valueOf((TECHSTuDROP*100)/(TECHSTupassed+TECHSTuDROP)),""));

               }catch (Exception e){
                   System.out.println(e);
               }
            }else if(areaSelector.getValue().equals("STATE")){


                DatabaseHandler db2= new DatabaseHandler();
                a=b=c=d=0;
                try {
                rs[1]=db2.read("SELECT `AFF_NUM`, `STATE` FROM `USERDATA` WHERE 1");
                rs[2]=db.read("SELECT `AFF_NUM`, `STATUS` FROM `HISTU` WHERE 1");
                while (rs[2].next()){
                    rs[1]=db2.read("SELECT `AFF_NUM`, `STATE` FROM `USERDATA` WHERE 1");
                    while (rs[1].next())
                    if(rs[2].getString(1).equals(rs[1].getString(1))){
                        if(rs[1].getString(2).equals("PUNJAB") && rs[2].getString(2).equals("PASSED")){
                            a++;
                        }
                        else if(rs[1].getString(2).equals("BIHAR") && rs[2].getString(2).equals("PASSED")){
                            b++;
                        }
                        else if(rs[1].getString(2).equals("PUNJAB") && rs[2].getString(2).equals("DROPOUT")){
                            c++;
                        }
                        else if(rs[1].getString(2).equals("BIHAR") && rs[2].getString(2).equals("DROPOUT")){
                            d++;
                        }
                    }
                }}catch (Exception e){
                    System.out.println(e);
                }
                try {

                    view.getItems().clear();
                    view.getItems().add(new data("PUNJAB passed HIGHER", String.valueOf(a), String.valueOf((a * 100) / (a + c)), ""));
                    view.getItems().add(new data("PUNJAB DROPED HIGHER", String.valueOf(c), String.valueOf((c * 100) / (a + c)), ""));
                    view.getItems().add(new data("BIHAR passed HIGHER", String.valueOf(b), String.valueOf((b * 100) / (b + d)), ""));
                    view.getItems().add(new data("PUNJAB DROPED HIGHER", String.valueOf(d), String.valueOf((d * 100) / (b + d)), ""));

                }catch (Exception e){
                    System.out.println(e);
                }
            }

        }
    }


    public static class data {
        public String status;
        public String nofStu;
        public String perofStu;
        public String rem;

        public data(String status, String nofStu, String perofStu, String rem) {
            this.status = status;
            this.nofStu = nofStu;
            this.perofStu = perofStu;
            this.rem = rem;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getNofStu() {
            return nofStu;
        }

        public void setNofStu(String nofStu) {
            this.nofStu = nofStu;
        }

        public String getPerofStu() {
            return perofStu;
        }

        public void setPerofStu(String perofStu) {
            this.perofStu = perofStu;
        }

        public String getRem() {
            return rem;
        }

        public void setRem(String rem) {
            this.rem = rem;
        }
    }
    }
