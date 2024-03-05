/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package case_study_server;

import static case_study_server.CoursesDAO.getCourses;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author LORD LAPTOP
 */
public class Courses_Info_paneController implements Initializable {

    @FXML
    private TableView<CoursesDTO> courses;
    @FXML
    private TableColumn<CoursesDTO, String> cour_id;
    @FXML
    private TableColumn<CoursesDTO, String> cour_name;
    @FXML
    private TableColumn<CoursesDTO, String> hours;
    @FXML
    private TableColumn<CoursesDTO, String> dept_id;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<CoursesDTO> c = getCourses();
        ObservableList<CoursesDTO> cours = FXCollections.observableArrayList(c);
        cour_id.setCellValueFactory(new PropertyValueFactory<>("cour_id"));
        cour_name.setCellValueFactory(new PropertyValueFactory<>("cour_name"));
        hours.setCellValueFactory(new PropertyValueFactory<>("credit_hours"));
        dept_id.setCellValueFactory(new PropertyValueFactory<>("dept_id"));
        courses.setItems(cours);
    }    
    
}
