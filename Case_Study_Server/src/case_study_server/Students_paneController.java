package case_study_server;

import static case_study_server.StudentsDAO.checkStudent;
import static case_study_server.StudentsDAO.checkStudent_n;
import static case_study_server.StudentsDAO.deleteStudent;
import static case_study_server.StudentsDAO.getStudentGPA;
import static case_study_server.StudentsDAO.insertStudent;
import static case_study_server.StudentsDAO.updateStudent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javax.swing.JOptionPane;

public class Students_paneController implements Initializable {

    @FXML
    private AnchorPane headpane;
    @FXML
    private TextField stu_id;
    @FXML
    private TextField stu_name;
    @FXML
    private TextField city;
    @FXML
    private TextField street;
    @FXML
    private TextField faculty;
    @FXML
    private TextField dept_id;
    @FXML
    private Button add_stu;
    @FXML
    private Button update_stu;
    @FXML
    private Button delete_stu;
    @FXML
    private Button add_grade;
    @FXML
    private Button get_gpa;
    @FXML
    private TextField phone_num;

    @FXML
    public void add_student() {
        String id = stu_id.getText();
        String name = stu_name.getText().toUpperCase();
        String c = city.getText();
        String s = street.getText();
        String f = faculty.getText();
        String d = dept_id.getText();
        String n = phone_num.getText();
        if (stu_id.getText().isEmpty() || stu_name.getText().isEmpty() || city.getText().isEmpty()
                || street.getText().isEmpty() || faculty.getText().isEmpty()
                || dept_id.getText().isEmpty() || phone_num.getText().isEmpty()) {
            String e = "<html><body style='font-family: Arial; font-size: 12px;'>All fields are necessary to be filled</body></html>";
            JOptionPane.showMessageDialog(null, e);
        } else {

            System.out.println("Connecting to database...");

            int r = insertStudent(id, name, c, s, f, d, n);
            String r2 = checkStudent(id);
            stu_name.clear();
            stu_id.clear();
            city.clear();
            street.clear();
            faculty.clear();
            dept_id.clear();
            phone_num.clear();
            if (r > 0) {
                System.out.println("Insert Successfully");
                String e = "<html><body style='font-family: Arial; font-size: 12px;'>Insert Successfully</body></html>";
                JOptionPane.showMessageDialog(null, e, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else if (r2.length() != 0) {
                System.out.println(" Existsing Student");
                String e = "<html><body style='font-family: Arial; font-size: 12px;'>This Student Is Already Exist</body></html>";
                JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                System.out.println("Failed Insert");
                String e = "<html><body style='font-family: Arial; font-size: 12px;'>Failed Insert!! Check The Information You Entered</body></html>";
                JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @FXML
    public void upadte_student() {
        String id = stu_id.getText();
        String name = stu_name.getText().toUpperCase();
        String c = city.getText();
        String s = street.getText();
        String f = faculty.getText();
        String d = dept_id.getText();
        if (stu_id.getText().isEmpty() || stu_name.getText().isEmpty() || city.getText().isEmpty()
                || street.getText().isEmpty() || faculty.getText().isEmpty()
                || dept_id.getText().isEmpty() || phone_num.getText().isEmpty()) {
            String e = "<html><body style='font-family: Arial; font-size: 12px;'>All fields are necessary to be filled</body></html>";
            JOptionPane.showMessageDialog(null, e);
        } else {
            int r = updateStudent(id, name, c, s, f, d);
            stu_name.clear();
            stu_id.clear();
            city.clear();
            street.clear();
            faculty.clear();
            dept_id.clear();
            if (r > 0) {
                System.out.println("Update Successfully");
                String e = "<html><body style='font-family: Arial; font-size: 12px;'>Update Successfully</body></html>";
                JOptionPane.showMessageDialog(null, e, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("Failed update");
                String e = "<html><body style='font-family: Arial; font-size: 12px;'>Failed update!! Check The Information You Entered</body></html>";
                JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @FXML
    public void Delete_student() {

        String name = stu_name.getText().toUpperCase();
        if (stu_name.getText().isEmpty()) {
            String e = "<html><body style='font-family: Arial; font-size: 12px;'>Only Name Field Is Necessary To Be Filled</body></html>";
            JOptionPane.showMessageDialog(null, e);
        } else {
            int r = deleteStudent(name);
            String r2 = checkStudent_n(name);
            stu_name.clear();
            if (r > 0) {
                System.out.println("Delete Successfully");
                String e = "<html><body style='font-family: Arial; font-size: 12px;'>Delete Successfully</body></html>";
                JOptionPane.showMessageDialog(null, e, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else if (r2 == null) {
                System.out.println("Does not exist");
                String e = "<html><body style='font-family: Arial; font-size: 12px;'>This Student Does not Exist</body></html>";
                JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                System.out.println("Failed Delete");
                String e = "<html><body style='font-family: Arial; font-size: 12px;'>Failed Delete!! Check The Information You Entered</body></html>";
                JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @FXML
    public void Retrive_student() {

        String id = stu_id.getText();
        if (stu_id.getText().isEmpty()) {
            String e = "<html><body style='font-family: Arial; font-size: 12px;'>Only ID Field Is Necessary To Be Filled</body></html>";
            JOptionPane.showMessageDialog(null, e);
        } else {
            String r = getStudentGPA(id);
            String r2 = checkStudent(id);
            stu_id.clear();
            if (r != null) {
                System.out.println(r);
                String s = "<html><body style='font-family: Arial; font-size: 12px;'>" + r + "</body></html>";
                JOptionPane.showMessageDialog(null, s, "Information", JOptionPane.INFORMATION_MESSAGE);
            } else if (r2 == null) {
                System.out.println(r);
                String s = "<html><body style='font-family: Arial; font-size: 12px;'>This Student Does Not Exist</body></html>";
                JOptionPane.showMessageDialog(null, s, "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                System.out.println("Failed Retrieve");
                String f = "<html><body style='font-family: Arial; font-size: 12px;'>Failed Retrieve!! Check The Information You Entered.</body></html>";
                JOptionPane.showMessageDialog(null, f, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @FXML
    public void Add_grade() throws IOException {
        System.out.println("button clicked");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Add_Grade_pane.fxml"));
        Parent root = loader.load();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ADD COURSE GRADE");
        alert.setHeaderText(null);
        alert.getDialogPane().setContent(root);

        ButtonType okButton = new ButtonType("OK");
        alert.getButtonTypes().setAll(okButton);

        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        alert.showAndWait();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
