/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package case_study_server;

import static case_study_server.CoursesDAO.checkCourse_n;
import static case_study_server.ReportDAO.getReports;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LORD LAPTOP
 */
public class Report_paneController implements Initializable {

    @FXML
    private TextField Cour_name;
    @FXML
    private Button reportbutt;
    @FXML
    private TableView<ReportDTO> Report;
    @FXML
    private TableColumn<ReportDTO, Integer> cour_id;
    @FXML
    private TableColumn<ReportDTO, String> cours_name;
    @FXML
    private TableColumn<ReportDTO, String> semester;
    @FXML
    private TableColumn<ReportDTO, String> stu_name;
    @FXML
    private TableColumn<ReportDTO, String> stu_grade;
    @FXML
    private TableColumn<ReportDTO, Float> avg_gpa;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void getReport(ActionEvent event) {
        String n = Cour_name.getText().toUpperCase();
        if (Cour_name.getText().isEmpty()) {
            String e = "<html><body style='font-family: Arial; font-size: 12px;'>Course Name field is necessary to be filled</body></html>";
            JOptionPane.showMessageDialog(null, e);
        } else {
            ArrayList<ReportDTO> r = getReports(n);
            String r2 = checkCourse_n(n);

            if (r2 == null) {
                String e = "<html><body style='font-family: Arial; font-size: 12px;'>This Course Does not Exist</body></html>";
                JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            }else if (r.isEmpty()) {
                System.out.println("Does not exist");
                String e = "<html><body style='font-family: Arial; font-size: 12px;'>No Students Enrolled In This Course Yet!!</body></html>";
                JOptionPane.showMessageDialog(null, e);
            }

            ObservableList<ReportDTO> report = FXCollections.observableArrayList(r);
            cour_id.setCellValueFactory(new PropertyValueFactory<>("cour_id"));
            cours_name.setCellValueFactory(new PropertyValueFactory<>("cour_Name"));
            semester.setCellValueFactory(new PropertyValueFactory<>("semester"));
            stu_name.setCellValueFactory(new PropertyValueFactory<>("stu_name"));
            stu_grade.setCellValueFactory(new PropertyValueFactory<>("stu_grade"));
            avg_gpa.setCellValueFactory(new PropertyValueFactory<>("avg_GPA"));
            Report.setItems(report);

        }
    }

}
