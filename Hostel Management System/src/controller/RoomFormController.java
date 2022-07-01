package controller;

import bo.BOFactory;
import bo.custom.RoomBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.RoomDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class RoomFormController {
    public JFXTextField txtRoomTypeID;
    public JFXTextField txtRoomType;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtQty;
    public JFXButton btnSave;
    public TableView<RoomDTO> tblRoom;
    public TableColumn colRoomTypeID;
    public TableColumn colType;
    public TableColumn colKeyMoney;
    public TableColumn colQty;
    public JFXButton btnDelete;

    private final RoomBO roomBO = BOFactory.getInstance().getBO(BOFactory.BOType.ROOM);
    
    public void initialize(){
        colRoomTypeID.setCellValueFactory(new PropertyValueFactory<>("roomTypeID"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        try {
            loadAllRoom();
        } catch (Exception e) {
            e.printStackTrace();
        }

        tblRoom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            //btnSave.setDisable(newValue == null);

            if (newValue != null) {
                txtRoomTypeID.setText(newValue.getRoomTypeID());
                txtRoomType.setText(newValue.getType());
                txtKeyMoney.setText(String.valueOf(newValue.getKeyMoney()));
                txtQty.setText(String.valueOf(newValue.getQty()));

                /*txtItemCode.setDisable(false);
                txtDescription.setDisable(false);
                txtUnitPrice.setDisable(false);
                txtQtyOnHand.setDisable(false);*/
            }
        });
    }

    private void loadAllRoom() throws Exception {
        ArrayList<RoomDTO> allRooms = roomBO.getAll();
        for (RoomDTO roomDTO : allRooms) {
            tblRoom.getItems().add(new RoomDTO(roomDTO.getRoomTypeID(), roomDTO.getType(),roomDTO.getKeyMoney(),roomDTO.getQty()));
        };
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws Exception {
        String roomTypeID=txtRoomTypeID.getText();

        //Regex
        if (!roomTypeID.matches("^(R)[0-9]{3,5}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Room Type ID").show();
            txtRoomTypeID.requestFocus();
            txtRoomTypeID.setStyle("-jfx-focus-color: red");
            txtRoomTypeID.setStyle("-jfx-unfocus-color: red");
            return;
        }else if (!txtRoomType.getText().matches("^[A-z0-9 ,/]{4,20}$")){
            new Alert(Alert.AlertType.ERROR, "Invalid Room Type").show();
            txtRoomType.requestFocus();
            txtRoomType.setStyle("-jfx-focus-color: red");
            txtRoomType.setStyle("-jfx-unfocus-color: red");
            return;
        } else if (!txtKeyMoney.getText().matches("^[0-9]+[.]?[0-9]*$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Key Money").show();
            txtKeyMoney.requestFocus();
            txtKeyMoney.setStyle("-jfx-focus-color: red");
            txtKeyMoney.setStyle("-jfx-unfocus-color: red");
            return;
        } else if (!txtQty.getText().matches("^[0-9]{1,}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Qty").show();
            txtQty.requestFocus();
            txtQty.setStyle("-jfx-focus-color: red");
            txtQty.setStyle("-jfx-unfocus-color: red");
            return;
        }

        String type=txtRoomType.getText();
        double keyMoney= Double.parseDouble(txtKeyMoney.getText());
        Integer qty= Integer.valueOf(txtQty.getText());


        if (btnSave.getText().equalsIgnoreCase("Save")) {

            try {
                    roomBO.save(new RoomDTO(roomTypeID,type,keyMoney,qty));
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved.!").show();
                    //clearFields();

            } catch (Exception e) {
                System.out.println(e);
                new Alert(Alert.AlertType.ERROR, "Student didn't Saved!").showAndWait();
            }

            tblRoom.refresh();
            tblRoom.getItems().add(new RoomDTO(roomTypeID,type,keyMoney,qty));
            //loadAllRoom();

        }else{
            /*Update Item*/
            roomBO.update(new RoomDTO(roomTypeID,type,keyMoney,qty));
            RoomDTO roomDTO =tblRoom.getSelectionModel().getSelectedItem();
            roomDTO.setType(type);
            roomDTO.setKeyMoney(keyMoney);
            roomDTO.setQty(qty);
            tblRoom.refresh();
        }
        //itemAddBtn.fire();
        btnSave.setText("Save");
        //txtFieldClear();

    }

    private boolean existRoom(String roomTypeID) throws Exception {
        return roomBO.roomExist(roomTypeID);
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get().equals(ButtonType.YES)) {

            String roomTypeID = tblRoom.getSelectionModel().getSelectedItem().getRoomTypeID();

            roomBO.delete(roomTypeID);
            tblRoom.getItems().remove(tblRoom.getSelectionModel().getSelectedItem());
            tblRoom.getSelectionModel().clearSelection();
            //initUI();
        }
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
    }
}
