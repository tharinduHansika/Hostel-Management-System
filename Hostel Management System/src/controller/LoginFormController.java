package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public JFXButton btnLogin;
    public AnchorPane context;
    public CheckBox chbxShow;
    public Tooltip tooltip;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        String password=txtPassword.getText();
        String userName=txtUserName.getText();
        setUi(userName,password);
    }

    private void setUi(String userName,String password) throws IOException {
        if (userName.equals("admin") && txtPassword.getText().equals("1234")) {
            Stage stage = (Stage) context.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/HomeForm.fxml"))));
        }
        else{
            new Alert(Alert.AlertType.WARNING, "Try Again!").show();
        }
    }

    public void chbxShowOnAction(ActionEvent actionEvent) {
        if (chbxShow.isSelected()){
            txtPassword.setPromptText(txtPassword.getText());
            txtPassword.setText("");
            txtPassword.setDisable(true);

        }else {
            txtPassword .setText(txtPassword.getPromptText());
            txtPassword.setPromptText("");
            txtPassword.setDisable(false);
        }
    }
}
