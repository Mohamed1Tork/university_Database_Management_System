/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package case_study_server;

import static case_study_server.StudentsDAO.addStudentGrade;
import java.awt.HeadlessException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LORD LAPTOP
 */
public class Add_Grade_paneController implements Initializable {

    @FXML
    private Text textcardrecharge1;
    @FXML
    private TextField stu_grade;
    @FXML
    private Text textcardrecharge2;
    @FXML
    private Text textcardrecharge;
    @FXML
    private TextField stu_id;
    @FXML
    private Button buttconfirm;
    @FXML
    private TextField Course_name;
    @FXML
    private Text textcardrecharge21;
    @FXML
    private TextField semester;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void add_grade(ActionEvent event) {
        try {
            String id = stu_id.getText();
            String c = Course_name.getText().toUpperCase();
            String g = stu_grade.getText().toUpperCase();
            String s = semester.getText();
            int r = addStudentGrade(id, c, g,s);
            stu_id.clear();
            stu_grade.clear();
            Course_name.clear();
            semester.clear();
            if (r > 0) {
                System.out.println("Added Successfully");
                String e = "<html><body style='font-family: Arial; font-size: 12px;'>Added Successfully</body></html>";
                JOptionPane.showMessageDialog(null, e, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("Failed Adding");
                String e = "<html><body style='font-family: Arial; font-size: 12px;'>Failed Adding!! Check The Information You Entered</body></html>";
                JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException ex) {
            System.out.println("An exception occurred: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "An error occurred", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}


