package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeFormController {
    public JFXButton btnStudent;
    public JFXButton bnRoom;
    public JFXButton btnPassword;
    public AnchorPane context;
    public AnchorPane background;
    public JFXButton BtnReservation;
    public JFXButton btnRemainKeyMoney;
    public JFXButton btnBack;

    public void btnStudentOnAction(ActionEvent actionEvent) throws IOException {
        context.getChildren().clear();
        Parent parent= FXMLLoader.load(getClass().getResource("../view/StudentForm.fxml"));
        context.getChildren().add(parent);
    }

    public void btnRoomOnAction(ActionEvent actionEvent) throws IOException {
        context.getChildren().clear();
        Parent parent=FXMLLoader.load(getClass().getResource("../view/RoomForm.fxml"));
        context.getChildren().add(parent);
    }

    public void btnPasswordOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) background.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"))));
    }

    public void btnReservationOnAction(ActionEvent actionEvent) throws IOException {
        context.getChildren().clear();
        Parent parent=FXMLLoader.load(getClass().getResource("../view/ReservationForm.fxml"));
        context.getChildren().add(parent);
    }

    public void btnRemainKeyMoneyOnAction(ActionEvent actionEvent) throws IOException {
        context.getChildren().clear();
        Parent parent=FXMLLoader.load(getClass().getResource("../view/RemainKeyMoneyForm.fxml"));
        context.getChildren().add(parent);
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) background.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"))));
    }
}
