/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package case_study_server;

import static case_study_server.DepartmentsDAO.getDepartments;
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
public class Departments_Info_paneController implements Initializable {

    @FXML
    private TableColumn<DepartmentsDTO, String> dept_id;
    @FXML
    private TableColumn<DepartmentsDTO, String> dept_name;
    @FXML
    private TableColumn<DepartmentsDTO, String> add_grade;
    @FXML
    private TableView<DepartmentsDTO> departments;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ArrayList<DepartmentsDTO> d = getDepartments();
        ObservableList<DepartmentsDTO> deps = FXCollections.observableArrayList(d);
        dept_id.setCellValueFactory(new PropertyValueFactory<>("dept_id"));
        dept_name.setCellValueFactory(new PropertyValueFactory<>("dept_Name"));
        add_grade.setCellValueFactory(new PropertyValueFactory<>("add_grade"));
        departments.setItems(deps);
    }

}
