/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package case_study_server;

import static case_study_server.CoursesDAO.getCourses;
import static case_study_server.DepartmentsDAO.getCoursesByDepartment;
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
public class DepartmentCourses_paneController implements Initializable {

    @FXML
    private TableView<CoursesDTO> d_Courses;
    @FXML
    private TableColumn<CoursesDTO, Integer> cour_id;
    @FXML
    private TableColumn<CoursesDTO, String> cour_name;
    @FXML
    private TableColumn<CoursesDTO, Integer> hours;

    private String d;

    public void setParameter(String d) {
        this.d = d;
        populateCoursesTable();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization logic can be moved out or removed since we're populating
        // the table after setting the parameter
    }

    private void populateCoursesTable() {
        ArrayList<CoursesDTO> c = getCoursesByDepartment(d);
        ObservableList<CoursesDTO> courses = FXCollections.observableArrayList(c);
        cour_id.setCellValueFactory(new PropertyValueFactory<>("cour_id"));
        cour_name.setCellValueFactory(new PropertyValueFactory<>("cour_name"));
        hours.setCellValueFactory(new PropertyValueFactory<>("credit_hours"));
        d_Courses.setItems(courses);
    }
}

