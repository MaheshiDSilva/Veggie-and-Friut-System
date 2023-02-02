package lk.ijse.veggieSystem.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.veggieSystem.model.CustomerModel;
import lk.ijse.veggieSystem.model.DashBoardModel;
import lk.ijse.veggieSystem.to.Customer;
import lk.ijse.veggieSystem.to.DashBoard;
import lk.ijse.veggieSystem.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DashBoardFormController {
    public JFXTextField txtCusId;
    public JFXTextField txtMobile;
    public JFXTextField txtCusName;
    public JFXTextField txtAddress;
    public TableView tableView;
    public TableColumn tblCusId;
    public TableColumn tblMobile;
    public TableColumn tblAddress;
    public TableColumn tblUserId;
    public TableColumn tblCustomerName;
    public TableColumn tblAction;
    public ComboBox cmbUserId;
    public AnchorPane CustomerFormContext;
    public Pane dashBoardContext;
    public AnchorPane customerFormContext;
    public TableColumn tblCusName;
    public JFXTextField txtUserId;
    public Label lblCusId;
    public Label lblUserId;
    public Label lblCusName;
    public Label lblCusMobile;

    public void btnCus1OnAction(ActionEvent actionEvent) throws IOException {
       setUi("/lk/ijse/veggieSystem/view/CustomerForm");
    }

    public void btnItem1OnAction(ActionEvent actionEvent) throws IOException {
       setUi("/lk/ijse/veggieSystem/view/ItemForm");
    }

    public void btnOrder1OnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/veggieSystem/view/OrderForm");

    }

    public void btnSupplier1OnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/veggieSystem/view/SupplierForm");
    }

    public void btnEmployee1OnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/veggieSystem/view/EmployeeForm");
    }

    public void btnPayment1OnAction(ActionEvent actionEvent) throws IOException {
       setUi("/lk/ijse/veggieSystem/view/PaymentForm");
    }

    public void btnDD1OnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/veggieSystem/view/DistributingDForm");
    }

    public void btnReport1OnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/veggieSystem/view/ReportForm");
    }

    public void btnPlaceOrder1OnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/veggieSystem/view/PlaceOrderForm");
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage= (Stage) dashBoardContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/veggieSystem/view/LoginForm.fxml"))));
        stage.centerOnScreen();

    }

    public void btnSupplierItemDetail(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/veggieSystem/view/SupplierItemForm");

    }

    public void btnNewUserOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/veggieSystem/view/UserForm");
    }
    private void setUi(String ui) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        customerFormContext.getChildren().clear();
        customerFormContext.getChildren().add(load);
    }


    public void initialize(){
        tblCusId.setCellValueFactory(new PropertyValueFactory("cusId"));
        tblUserId.setCellValueFactory(new PropertyValueFactory("userId"));
        tblCusName.setCellValueFactory(new PropertyValueFactory("cusName"));
        tblMobile.setCellValueFactory(new PropertyValueFactory("mobile"));
        tblAddress.setCellValueFactory(new PropertyValueFactory("address"));

        try {
            ObservableList<DashBoard> list= DashBoardModel.loadAllDashBoard();
            tableView.setItems(list);

        } catch (ClassNotFoundException | SQLException  e) {
            e.printStackTrace();
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String cusId = txtCusId.getText();
        String userId=txtUserId.getText();
        String cusName = txtCusName.getText();
        String mobile=txtMobile.getText();
        String address = txtAddress.getText();


        DashBoard dashBoard = new DashBoard(cusId, userId,cusName, mobile,address);
        try {
            boolean isAdded = DashBoardModel.save(dashBoard);

            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Added!").show();
                initialize();
                clear();
                DashBoardModel.loadAllDashBoard();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                clear();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        DashBoard dashBoard=new DashBoard(txtCusId.getText(),txtUserId.getText()
                ,txtCusName.getText(),txtMobile.getText(),txtAddress.getText());
        try {
            boolean update = DashBoardModel.update(dashBoard);
            if(update) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer updated!").show();
                initialize();
                clear();
                DashBoardModel.loadAllDashBoard();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            clear();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtCusId.getText();
        try {
            boolean delete = DashBoardModel.delete(id);
            if(delete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer deleted!").show();
                initialize();
                clear();
                DashBoardModel.loadAllDashBoard();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                clear();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public void txtCusIdOnAction(ActionEvent actionEvent) {
        String id = txtCusId.getText();
        searchData(id);
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String id = txtCusId.getText();
        searchData(id);
    }

    private void fillData(DashBoard dashBoard) {
        txtCusId.setText(dashBoard.getCusId());
        txtUserId.setText(dashBoard.getUserId());
        txtCusName.setText(dashBoard.getCusName());
        txtMobile.setText(dashBoard.getMobile());
        txtAddress.setText(dashBoard.getAddress());
    }
    public void searchData(String id){
        try {
            DashBoard dashBoard = DashBoardModel.search(id);
            if(dashBoard!= null) {
                fillData(dashBoard);
            }else{
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                clear();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void clear(){
        txtCusId.clear();
        txtUserId.clear();
        txtCusName.clear();
        txtMobile.clear();
        txtAddress.clear();

    }

    //validation
    public void txtCusIdKeyReleased(KeyEvent keyEvent) {
        if (txtCusId.getText().equals("")) {
            lblCusId.setText("");
        } else {
            Pattern pattern = Pattern.compile("(C0)([1-9]{1})([0-9]{0,})");
            Matcher matcher = pattern.matcher(txtCusId.getText());

            boolean isMatches = matcher.matches();

            if (!isMatches) {
                lblCusId.setText("Invalid Id !!!");
            } else {
                lblCusId.setText("");
            }
        }
    }

    public void txtUserIdKeyReleased(KeyEvent keyEvent) {
        if (txtUserId.getText().equals("")) {
            lblUserId.setText("");
        } else {
            Pattern pattern = Pattern.compile("(U0)([1-9]{1})([0-9]{0,})");
            Matcher matcher = pattern.matcher(txtUserId.getText());

            boolean isMatches = matcher.matches();

            if (!isMatches) {
                lblUserId.setText("Invalid Id !!!");
            } else {
                lblUserId.setText("");
            }
        }
    }

    public void txtCusNameKeyReleased(KeyEvent keyEvent) {
        if (txtCusName.getText().equals("")) {
            lblCusName.setText("");
        } else {
            Pattern pattern = Pattern.compile("^[a-zA-Z]{1,}$");
            Matcher matcher = pattern.matcher(txtCusName.getText());

            boolean isMatches = matcher.matches();

            if (!isMatches) {
                lblCusName.setText("Invalid Name !!!");
            } else {
                lblCusName.setText("");
            }
        }
    }

    public void txtMobileKeyReleased(KeyEvent keyEvent) {
        if (txtMobile.getText().equals("")) {
            lblCusMobile.setText("");
        } else {
            Pattern pattern = Pattern.compile("^[0]{1}[7]{1}[01245678]{1}[0-9]{7}$");
            Matcher matcher = pattern.matcher(txtMobile.getText());

            boolean isMatches = matcher.matches();

            if (!isMatches) {
                lblCusMobile.setText("Invalid Mobile No !!!");
            } else {
                lblCusMobile.setText("");
            }
        }
    }
}