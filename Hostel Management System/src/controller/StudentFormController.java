package controller;

import bo.BOFactory;
import bo.custom.StudentBO;
import bo.custom.impl.StudentBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.RoomDTO;
import dto.StudentDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class StudentFormController {
    public JFXTextField txtStudentID;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNo;
    public JFXTextField txtDOB;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public RadioButton rbMale;
    public RadioButton rbFemale;
    public TableView<StudentDTO> tblStudent;
    public TableColumn colStudentID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContactNo;
    public TableColumn colDOB;
    public TableColumn colGender;

    private final StudentBO studentBO = BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT);

    public void initialize(){
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        try {
            loadAllStudent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            //btnSave.setDisable(newValue == null);

            if (newValue != null) {
                txtStudentID.setText(newValue.getStudentID());
                txtName.setText(newValue.getName());
                txtAddress.setText(newValue.getAddress());
                txtContactNo.setText(newValue.getContactNo());
                txtDOB.setText(String.valueOf(newValue.getDOB()));
                if(newValue.getGender().equals("Male")){
                    rbMale.setSelected(true);
                }else{
                    rbFemale.setSelected(true);
                }

                /*txtItemCode.setDisable(false);
                txtDescription.setDisable(false);
                txtUnitPrice.setDisable(false);
                txtQtyOnHand.setDisable(false);*/
            }
        });
    }

    private void loadAllStudent() throws Exception {
        /*Get all student*/
        ArrayList<StudentDTO> allItems = studentBO.getAll();
        for (StudentDTO studentDTO : allItems) {
            tblStudent.getItems().add(new StudentDTO(studentDTO.getStudentID(), studentDTO.getName(),studentDTO.getAddress(),studentDTO.getContactNo(),studentDTO.getDOB(),studentDTO.getGender()));
        };
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws Exception {
        String id = txtStudentID.getText();

        //Regex
        if (!id.matches("^(S)[0-9]{3,5}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Room Type ID").show();
            txtStudentID.requestFocus();
            txtStudentID.setStyle("-jfx-focus-color: red");
            txtStudentID.setStyle("-jfx-unfocus-color: red");
            return;
        }else if (!txtName.getText().matches("^[A-z]{3,20}$")){
            new Alert(Alert.AlertType.ERROR, "Invalid Name").show();
            txtName.requestFocus();
            txtName.setStyle("-jfx-focus-color: red");
            txtName.setStyle("-jfx-unfocus-color: red");
            return;
        } else if (!txtAddress.getText().matches("^[A-z0-9 ,/]{4,20}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Address").show();
            txtAddress.requestFocus();
            txtAddress.setStyle("-jfx-focus-color: red");
            txtAddress.setStyle("-jfx-unfocus-color: red");
            return;
        } else if (!txtContactNo.getText().matches("[0-9]{1,}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Contact No.").show();
            txtContactNo.requestFocus();
            txtContactNo.setStyle("-jfx-focus-color: red");
            txtContactNo.setStyle("-jfx-unfocus-color: red");
            return;
        } else if (!txtDOB.getText().matches("^[0-9]{4}[-][0-9]{2}[-][0-9]{2}$")) {
        new Alert(Alert.AlertType.ERROR, "Invalid DOB").show();
            txtDOB.requestFocus();
            txtDOB.setStyle("-jfx-focus-color: red");
            txtDOB.setStyle("-jfx-unfocus-color: red");
        return;
    }

        String name = txtName.getText();
        String address = txtAddress.getText();
        LocalDate DOB = LocalDate.parse(txtDOB.getText());
        String contact = txtContactNo.getText();
        String gender = "Male";
        if (rbFemale.isSelected()) {
            gender = "Female";
        }

        if (btnSave.getText().equalsIgnoreCase("Save")) {

            try {
                    studentBO.save(new StudentDTO(id, name, address, contact, DOB, gender));
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved.!").show();
                    //clearFields();

            } catch (Exception e) {
                System.out.println(e);
                new Alert(Alert.AlertType.ERROR, "Student didn't Saved!").showAndWait();
            }

            tblStudent.refresh();
            tblStudent.getItems().add(new StudentDTO(id, name, address, contact, DOB, gender));
            //loadAllStudent();

        }else{

            /*Update Item*/
            studentBO.update(new StudentDTO(id, name, address, contact, DOB, gender));
            StudentDTO selectedItem =tblStudent.getSelectionModel().getSelectedItem();
            selectedItem.setName(name);
            selectedItem.setAddress(address);
            selectedItem.setContactNo(contact);
            selectedItem.setDOB(DOB);
            selectedItem.setGender(gender);
            tblStudent.refresh();
        }
        //itemAddBtn.fire();
        btnSave.setText("Save");
        //txtFieldClear();

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get().equals(ButtonType.YES)) {

            String studentID = tblStudent.getSelectionModel().getSelectedItem().getStudentID();

            studentBO.delete(studentID);
            tblStudent.getItems().remove(tblStudent.getSelectionModel().getSelectedItem());
            tblStudent.getSelectionModel().clearSelection();
            //initUI();
        }
    }

    private boolean existItem(String studentID) throws Exception {
        return studentBO.studentExist(studentID);
    }
}
