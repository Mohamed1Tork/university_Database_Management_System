/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package case_study_server;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LORD LAPTOP
 */
public class University_paneController implements Initializable {

    @FXML
    private BorderPane main;
    @FXML
    private AnchorPane anchorpanevboxmainpane;
    @FXML
    private ImageView makeawishimgmain;
    @FXML
    private ImageView imageStudentspane;
    @FXML
    private Button bttnStudents;
    @FXML
    private ImageView imageDepartmentspane;
    @FXML
    private Button bttnDepartments;
    @FXML
    private ImageView imageCoursespane;
    @FXML
    private Button buttCourses;
    @FXML
    private ImageView imagReportspane;
    @FXML
    private Button buttReports;
    @FXML
    private AnchorPane headpane;
    @FXML
    private AnchorPane pagepane;
    
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private Button logpoutbtn;
    @FXML
    private ImageView welcome;

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
    private void loadPageProfile(MouseEvent event) {
    }

    @FXML
    private void studentsPage(ActionEvent event) throws IOException {
   
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Students_pane.fxml"));
        Parent root= fxmlLoader.load();
        main.setCenter(root);
    
    }

    @FXML
    private void loadFriends(MouseEvent event) {
    }

    @FXML
    private void departmentsPage(ActionEvent event) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Departments_pane.fxml"));
        Parent root= fxmlLoader.load();
        main.setCenter(root);
    }

    @FXML
    private void loadItemsPage(MouseEvent event) {
    }

    @FXML
    private void coursesPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Courses_pane.fxml"));
        Parent root= fxmlLoader.load();
        main.setCenter(root);
    }

    @FXML
    private void loadConnectPage(MouseEvent event) {
    }

    @FXML
    private void reportsPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Report_pane.fxml"));
        Parent root= fxmlLoader.load();
        main.setCenter(root);
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login_pane.fxml"));
        root= loader.load();
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
