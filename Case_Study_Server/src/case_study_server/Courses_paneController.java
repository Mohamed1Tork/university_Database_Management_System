/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package case_study_server;

import static case_study_server.CoursesDAO.checkCourse;
import static case_study_server.CoursesDAO.checkCourse_n;
import static case_study_server.CoursesDAO.deleteCourse;
import static case_study_server.CoursesDAO.insertCourse;
import static case_study_server.CoursesDAO.updateCourse;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LORD LAPTOP
 */
public class Courses_paneController implements Initializable {

    @FXML
    private Button add_course;
    @FXML
    private Button update_course;
    @FXML
    private Button delete_course;
    @FXML
    private Button get_courses;
    @FXML
    private TextField cour_id;
    @FXML
    private TextField cour_name;
    @FXML
    private TextField hours;
    @FXML
    private TextField dept_id;
    @FXML
    private Button get_students;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void add_course(ActionEvent event) {
        String id = cour_id.getText();
        String n = cour_name.getText().toUpperCase();
        String h = hours.getText();
        String d = dept_id.getText();
        if (cour_id.getText().isEmpty() || cour_name.getText().isEmpty() || hours.getText().isEmpty()
                || dept_id.getText().isEmpty()) {
            String e = "<html><body style='font-family: Arial; font-size: 12px;'>All fields are necessary to be filled</body></html>";
            JOptionPane.showMessageDialog(null, e);
        } else {
            int r = insertCourse(id, n, h, d);
            String r2 = checkCourse(id);
            dept_id.clear();
            cour_name.clear();
            cour_id.clear();
            hours.clear();
            if (r > 0) {
                System.out.println("Insert Successfully");
                String e = "<html><body style='font-family: Arial; font-size: 12px;'>Insert Successfully</body></html>";
                JOptionPane.showMessageDialog(null, e, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else if (r2.length() != 0) {
                System.out.println(" Existsing Course");
                String e = "<html><body style='font-family: Arial; font-size: 12px;'>This Course Is Already Exist</body></html>";
                JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                System.out.println("Failed Insert");
                String e = "<html><body style='font-family: Arial; font-size: 12px;'>Failed Insert!! Check The Information You Entered</body></html>";
                JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    @FXML
    private void update_course(ActionEvent event) {
        String id = cour_id.getText();
        String n = cour_name.getText().toUpperCase();
        String h = hours.getText();
        String d = dept_id.getText();
        if (cour_id.getText().isEmpty() || cour_name.getText().isEmpty() || hours.getText().isEmpty()
                || dept_id.getText().isEmpty()) {
            String e = "<html><body style='font-family: Arial; font-size: 12px;'>All fields are necessary to be filled</body></html>";
            JOptionPane.showMessageDialog(null, e);
        } else {
            int r = updateCourse(id, n, h, d);
            dept_id.clear();
            cour_name.clear();
            cour_id.clear();
            hours.clear();
            if (r > 0) {
                System.out.println("Update Successfully");
                String e = "<html><body style='font-family: Arial; font-size: 12px;'>Update Successfully</body></html>";
                JOptionPane.showMessageDialog(null, e, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("Failed Update");
                String e = "<html><body style='font-family: Arial; font-size: 12px;'>Failed Update!! Check The Information You Entered</body></html>";
                JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @FXML
    private void delete_course(ActionEvent event) {
        String n = cour_name.getText().toUpperCase();
        if (cour_name.getText().isEmpty()) {
            String e = "<html><body style='font-family: Arial; font-size: 12px;'>Name field is necessary to be filled</body></html>";
            JOptionPane.showMessageDialog(null, e);
        } else {
            int r = deleteCourse(n);
            String r2 = checkCourse_n(n);
            cour_name.clear();
            if (r > 0) {
                System.out.println("Delete Successfully");
                String e = "<html><body style='font-family: Arial; font-size: 12px;'>Delete Successfully</body></html>";
                JOptionPane.showMessageDialog(null, e, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else if (r2 == null) {
                System.out.println("Does not exist");
                String e = "<html><body style='font-family: Arial; font-size: 12px;'>This Course Does not Exist</body></html>";
                JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                System.out.println("Failed Delete");
                String e = "<html><body style='font-family: Arial; font-size: 12px;'>Failed Delete!! There Are Students Enroll In This Course>";
                JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @FXML
    private void get_courses(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Courses_Info_pane.fxml"));
        Parent root = loader.load();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("COURSES");
        alert.setHeaderText(null);
        alert.getDialogPane().setContent(root);

        ButtonType okButton = new ButtonType("OK");
        alert.getButtonTypes().setAll(okButton);

        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        alert.showAndWait();
    }

    @FXML
    private void get_students(ActionEvent event) throws IOException {
        String c = cour_name.getText().toUpperCase();
        String r2 = checkCourse_n(c);
        if (!c.isEmpty()) {
            if (r2 == null) {
                System.out.println("Does not exist");
                String e = "<html><body style='font-family: Arial; font-size: 12px;'>This Course Does not Exist</body></html>";
                JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CourseStudents_pane.fxml"));
                Parent root = loader.load();

                CourseStudents_paneController co = loader.getController();
                co.setParameter(c);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("COURSES");
                alert.setHeaderText(null);
                alert.getDialogPane().setContent(root);

                ButtonType okButton = new ButtonType("OK");
                alert.getButtonTypes().setAll(okButton);

                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

                alert.showAndWait();
            }
        } else {

            String e = "<html><body style='font-family: Arial; font-size: 12px;'>Please Enter A Course Name.</body></html>";
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
