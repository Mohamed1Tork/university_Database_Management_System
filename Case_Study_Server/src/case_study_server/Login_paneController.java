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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LORD LAPTOP
 */
public class Login_paneController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginbt;
    @FXML
    private Text wronglogin;

    private Parent root;
    private Stage stage;
    private Scene scene;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void clearMsg(MouseEvent event) {
    }

    @FXML
    private void clearMsg(ActionEvent event) {
        wronglogin.setText("");
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        if ("admin".equals(email.getText().toLowerCase()) && "1".equals(password.getText())) {
            // Load the server_pane.fxml scene for the root user
            root = FXMLLoader.load(getClass().getResource("University_pane.fxml"));
            stage = (Stage) loginbt.getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            wronglogin.setText("Wrong Email or Password");
        }
    }

}
