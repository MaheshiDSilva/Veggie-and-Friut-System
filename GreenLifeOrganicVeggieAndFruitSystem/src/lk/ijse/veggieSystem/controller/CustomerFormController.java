package lk.ijse.veggieSystem.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.veggieSystem.model.CustomerModel;
import lk.ijse.veggieSystem.model.ItemModel;
import lk.ijse.veggieSystem.to.Customer;
import javafx.scene.control.Label;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static lk.ijse.veggieSystem.model.CustomerModel.search;

public class CustomerFormController {
    public AnchorPane customerFormContext;
    public JFXTextField txtCusId;
    public JFXTextField txtAddress;
    public TableView tableView;
    public TableColumn tblCusId;
    public TableColumn tblUserId;
    public TableColumn tblCusName;
    public TableColumn tblMobile;
    public TableColumn tblAddress;
    public TableColumn tblAction;
    public JFXTextField txtMobile;
    public JFXTextField txtCusName;
    public ComboBox cmbUserId;
    public JFXTextField txtUserId;
    public Label lblCusId;
    public Label lblUserId;
    public Label lblCusName;
    public Label lblCusMobile;

    public void initialize() {
        tblCusId.setCellValueFactory(new PropertyValueFactory("cusId"));
        tblUserId.setCellValueFactory(new PropertyValueFactory("userId"));
        tblCusName.setCellValueFactory(new PropertyValueFactory("cusName"));
        tblMobile.setCellValueFactory(new PropertyValueFactory("mobile"));
        tblAddress.setCellValueFactory(new PropertyValueFactory("address"));

        try {
            ObservableList<Customer> list = CustomerModel.loadAllCustomer();
            tableView.setItems(list);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String cusId = txtCusId.getText();
        String userId = txtUserId.getText();
        String cusName = txtCusName.getText();
        String mobile = txtMobile.getText();
        String address = txtAddress.getText();

        Customer customer = new Customer(cusId, userId, cusName, mobile, address);
        try {
            boolean isAdded = CustomerModel.save(customer);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Added!").show();
                initialize();
                clear();
                CustomerModel.loadAllCustomer();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                clear();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtCusId.getText();
        try {
            boolean delete = CustomerModel.delete(id);
            if (delete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer deleted!").show();
                initialize();
                clear();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                clear();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        Customer customer = new Customer(txtCusId.getText(), txtUserId.getText()
                , txtCusName.getText(), txtMobile.getText(), txtAddress.getText());
        try {
            boolean update = CustomerModel.update(customer);
            if (update) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer updated!").show();
                initialize();
                clear();
                CustomerModel.loadAllCustomer();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            clear();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String id = txtCusId.getText();
        searchData(id);
    }

    public void txtCusIdOnAction(ActionEvent actionEvent) {
        String id = txtCusId.getText();
        searchData(id);
    }

    private void fillData(Customer customer) {
        txtCusId.setText(customer.getCusId());
        txtUserId.setText(customer.getUserId());
        txtCusName.setText(customer.getCusName());
        txtMobile.setText(customer.getMobile());
        txtAddress.setText(customer.getAddress());
    }

    public void searchData(String id) {
        try {
            Customer customer = search(id);
            if (customer != null) {
                fillData(customer);
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                clear();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void clear() {
        txtCusId.clear();
        txtUserId.clear();
        txtCusName.clear();
        txtMobile.clear();
        txtAddress.clear();
    }

    //validation
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
}




