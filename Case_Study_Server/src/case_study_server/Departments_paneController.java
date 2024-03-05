/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package case_study_server;

import static case_study_server.DepartmentsDAO.checkDepartment;
import static case_study_server.DepartmentsDAO.checkDepartment_n;
import static case_study_server.DepartmentsDAO.deleteDepartment;
import static case_study_server.DepartmentsDAO.insertDepartment;
import static case_study_server.DepartmentsDAO.updateDepartment;
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
public class Departments_paneController implements Initializable {

    @FXML
    private TextField dept_id;
    @FXML
    private TextField dept_name;
    @FXML
    private TextField add_grade;
    @FXML
    private Button add_dept;
    @FXML
    private Button update_dept;
    @FXML
    private Button delete_dept;
    @FXML
    private Button get_departments;
    @FXML
    private Button get_courses;

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
    private void update_dept(ActionEvent event) {
        String id = dept_id.getText();
        String n = dept_name.getText().toUpperCase();
        String g = add_grade.getText().toUpperCase();

        if (dept_id.getText().isEmpty() || dept_name.getText().isEmpty() || add_grade.getText().isEmpty()) {
            String e = "<html><body style='font-family: Arial; font-size: 12px;'>All fields are necessary to be filled</body></html>";
            JOptionPane.showMessageDialog(null, e);
        } else {
            int r = updateDepartment(id, n, g);
            dept_id.clear();
            dept_name.clear();
            add_grade.clear();
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
    private void delete_dept(ActionEvent event) {
        String n = dept_name.getText().toUpperCase();
        System.out.println(n);
        if (dept_name.getText().isEmpty()) {
            String e = "<html><body style='font-family: Arial; font-size: 12px;'>Only Name Field Is Necessary To Be Filled</body></html>";
            JOptionPane.showMessageDialog(null, e);
        } else {
            int r = deleteDepartment(n);
            String r2 = checkDepartment_n(n);
            dept_name.clear();
            if (r > 0) {
                System.out.println("Delete Successfully");
                String e = "<html><body style='font-family: Arial; font-size: 12px;'>Delete Successfully</body></html>";
                JOptionPane.showMessageDialog(null, e, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else if (r2 == null) {
                System.out.println("Does not exist");
                String e = "<html><body style='font-family: Arial; font-size: 12px;'>This Department Does not Exist</body></html>";
                JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                System.out.println("Failed Delete ");
                String e = "<html><body style='font-family: Arial; font-size: 12px;'>Failed Delete!! This Department Has Students</body></html>";
                JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @FXML
    private void get_departments(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Departments_Info_pane.fxml"));
        Parent root = loader.load();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("DEPARTMENTS");
        alert.setHeaderText(null);
        alert.getDialogPane().setContent(root);

        ButtonType okButton = new ButtonType("OK");
        alert.getButtonTypes().setAll(okButton);

        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        alert.showAndWait();
    }

    @FXML
    private void add_dept(ActionEvent event) {
        String id = dept_id.getText();
        String n = dept_name.getText().toUpperCase();
        String g = add_grade.getText().toUpperCase();

        if (dept_id.getText().isEmpty() || dept_name.getText().isEmpty() || add_grade.getText().isEmpty()) {
            String e = "<html><body style='font-family: Arial; font-size: 12px;'>All fields are necessary to be filled</body></html>";
            JOptionPane.showMessageDialog(null, e);
        } else {
            int r = insertDepartment(id, n, g);
            String r2 = checkDepartment(id);
            dept_id.clear();
            dept_name.clear();
            add_grade.clear();
            if (r > 0) {
                System.out.println("Insert Successfully");
                String e = "<html><body style='font-family: Arial; font-size: 12px;'>Insert Successfully</body></html>";
                JOptionPane.showMessageDialog(null, e, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else if (r2.length() != 0) {
                System.out.println(" Existsing Department");
                String e = "<html><body style='font-family: Arial; font-size: 12px;'>This Department Is Already Exist</body></html>";
                JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                System.out.println("Failed Insert");
                String e = "<html><body style='font-family: Arial; font-size: 12px;'>Failed Insert!! Check The Information You Entered</body></html>";
                JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    @FXML
    private void get_courses(ActionEvent event) throws IOException {
        String d = dept_name.getText().toUpperCase();
        String r2 = checkDepartment_n(d);

        if (!d.isEmpty()) {
            if (r2 == null) {
                System.out.println("Does not exist");
                String e = "<html><body style='font-family: Arial; font-size: 12px;'>This Department Does not Exist</body></html>";
                JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            } else {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("DepartmentCourses_pane.fxml"));
                Parent root = loader.load();

                DepartmentCourses_paneController c = loader.getController();
                c.setParameter(d);

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

            String e = "<html><body style='font-family: Arial; font-size: 12px;'>Please Enter A Department Name.</body></html>";
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
