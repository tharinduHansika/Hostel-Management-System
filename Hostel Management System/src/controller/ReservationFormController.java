package controller;

import bo.BOFactory;
import bo.custom.ReservationBO;
import bo.custom.RoomBO;
import bo.custom.StudentBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.ReservationDTO;
import dto.RoomDTO;
import dto.StudentDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class ReservationFormController {

    public JFXComboBox <String>cmbStudentID;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNo;
    public JFXTextField txtDOB;
    public JFXTextField txtGender;
    public JFXComboBox <String>cmbRoomTypeID;
    public JFXTextField txtType;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtQty;
    public RadioButton rbYes;
    public RadioButton rbNo;
    public TableView<ReservationDTO> tblReservation;
    public TableColumn colReservation;
    public TableColumn colDate;
    public TableColumn colStudentID;
    public TableColumn colRoomTypeID;
    public TableColumn colStatus;

    private final StudentBO studentBO = BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT);
    private final RoomBO roomBO = BOFactory.getInstance().getBO(BOFactory.BOType.ROOM);
    private final ReservationBO reservationBO = BOFactory.getInstance().getBO(BOFactory.BOType.RESERVATION);

    public void initialize(){

        colReservation.setCellValueFactory(new PropertyValueFactory<>("resID"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colRoomTypeID.setCellValueFactory(new PropertyValueFactory<>("roomTypeID"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        try {
            loadAllReservations();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            loadAllStudentIDs();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            loadAllRoomTypeIDs();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //load Student Details
        cmbStudentID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //enableOrDisablePlaceOrderButton();

            if (newValue != null) {
                /*Search Customer*/
                if (!existStudent(newValue + "")) {
//                            "There is no such customer associated with the id " + id
                    new Alert(Alert.AlertType.ERROR, "There is no such Student associated with the id " + newValue + "").show();
                }

                StudentDTO studentDTO = null;
                try {
                    studentDTO = studentBO.search(newValue + "");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                txtName.setText(studentDTO.getName());
                        txtAddress.setText(studentDTO.getAddress());
                        txtContactNo.setText(studentDTO.getContactNo());
                        txtDOB.setText(String.valueOf(studentDTO.getDOB()));
                        txtGender.setText(studentDTO.getGender());

            } else {
                //txtCustID.clear();
            }
        });


        cmbRoomTypeID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //enableOrDisablePlaceOrderButton();

            if (newValue != null) {
                /*Search Room*/
                if (!existRoom(newValue + "")) {
//                            "There is no such customer associated with the id " + id
                    new Alert(Alert.AlertType.ERROR, "There is no such Room associated with the id " + newValue + "").show();
                }

                RoomDTO roomDTO = null;
                try {
                    roomDTO = roomBO.search(newValue + "");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                txtType.setText(roomDTO.getType());
                        txtKeyMoney.setText(String.valueOf(roomDTO.getKeyMoney()));
                        txtQty.setText(String.valueOf(roomDTO.getQty()));

            } else {
                //txtItemName.clear();
            }
        });

        tblReservation.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //btnDelete.setDisable(newValue == null);
            //btnSave.setText(newValue != null ? "Update" : "Save");
            //btnSave.setDisable(newValue == null);

            if (newValue != null) {
                cmbStudentID.setValue(newValue.getStudentID());
                cmbRoomTypeID.setValue(newValue.getRoomTypeID());
                if(newValue.equals("Yes")){
                    rbYes.setSelected(true);
                }else {
                    rbNo.setSelected(true);
                }
            }
        });

    }

    private boolean existRoom(String s) {
        return true;
    }

    private boolean existStudent(String s) {
        return true;
    }

    private void loadAllRoomTypeIDs() throws Exception {
        try {
            /*Get all rooms*/
            ArrayList<RoomDTO> all = roomBO.getAll();
            for (RoomDTO roomDTO : all) {
                cmbRoomTypeID.getItems().add(roomDTO.getRoomTypeID());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadAllStudentIDs() throws Exception {
        try {
            /*Get all student*/
            ArrayList<StudentDTO> all = studentBO.getAll();
            for (StudentDTO studentDTO : all) {
                cmbStudentID.getItems().add(studentDTO.getStudentID());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws Exception {
        String resID = generateNewResID();
        LocalDate date = LocalDate.now();
        String studentID = cmbStudentID.getValue();
        String roomID = cmbRoomTypeID.getValue();
        String status = "Yes";
        if (rbNo.isSelected()) {
            status = "No";
        }

        if (btnSave.getText().equalsIgnoreCase("Save")) {

            try {
                reservationBO.save(new ReservationDTO(resID, date, studentID,  roomID, status));
                new Alert(Alert.AlertType.CONFIRMATION, "Saved.!").show();
                //clearFields();
            } catch (Exception e) {
                System.out.println(e);
                new Alert(Alert.AlertType.ERROR, "Student didn't Saved!").showAndWait();
            }

            tblReservation.refresh();
            tblReservation.getItems().add(new ReservationDTO(resID, date, studentID,  roomID, status));
            //loadAllReservations();

        }
        //itemAddBtn.fire();
        btnSave.setText("Save");
        //txtFieldClear();
        
    }

    private boolean existReservation(String resID) {
        return true;
    }

    private void loadAllReservations() throws Exception {
        ArrayList<ReservationDTO> allRooms = reservationBO.getAll();
        for (ReservationDTO reservationDTO : allRooms) {
            tblReservation.getItems().add(new ReservationDTO(reservationDTO.getResID(), reservationDTO.getDate(), reservationDTO.getStudentID(),reservationDTO.getRoomTypeID(), reservationDTO.getStatus()));
        };
    }

    private String generateNewResID() throws IOException {
        return reservationBO.generateNewReservationID();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get().equals(ButtonType.YES)) {

            String reservationID = tblReservation.getSelectionModel().getSelectedItem().getResID();

            reservationBO.delete(reservationID);
            tblReservation.getItems().remove(tblReservation.getSelectionModel().getSelectedItem());
            tblReservation.getSelectionModel().clearSelection();
            //initUI();
        }
    }
}
