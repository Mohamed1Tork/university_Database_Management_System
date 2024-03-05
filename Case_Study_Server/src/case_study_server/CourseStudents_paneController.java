/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package case_study_server;

import static case_study_server.CoursesDAO.getStudentsByCourse;
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
public class CourseStudents_paneController implements Initializable {

    @FXML
    private TableView<StudentsDTO> c_Students;
    @FXML
    private TableColumn<StudentsDTO, Integer> stu_id;
    @FXML
    private TableColumn<StudentsDTO, String> stu_name;
    @FXML
    private TableColumn<StudentsDTO, String> grade;

    private String c;

    public void setParameter(String c) {
        this.c = c;
        populateStudentsTable();
    }
    /**
     
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void populateStudentsTable() {
        ArrayList<StudentsDTO> s = getStudentsByCourse(c);
        ObservableList<StudentsDTO> students = FXCollections.observableArrayList(s);
        stu_id.setCellValueFactory(new PropertyValueFactory<>("stu_id"));
        stu_name.setCellValueFactory(new PropertyValueFactory<>("st_Name"));
        grade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        c_Students.setItems(students);
    }
    
}
