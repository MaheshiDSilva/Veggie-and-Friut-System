package lk.ijse.veggieSystem.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.veggieSystem.model.ItemModel;
import lk.ijse.veggieSystem.model.SupplierModel;
import lk.ijse.veggieSystem.to.Item;
import lk.ijse.veggieSystem.to.Supplier;
//import lk.ijse.veggieSystem.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplierFormController {
    public JFXTextField txtSupId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtMobile;
    public JFXTextField txtAmount;
    public TableView tableView;
    public TableColumn tblSupId;
    public TableColumn tblName;
    public TableColumn tblAddress;
    public TableColumn tblMobile;
    public TableColumn tblAmount;
    public TableColumn tblAction;
    public AnchorPane supplierFormContext;
    public Label lblSupId;
    public Label lblName;
    public Label lblMobile;
    public Label lblAmount;

    public void initialize(){
        tblSupId.setCellValueFactory(new PropertyValueFactory("supId"));
        tblName.setCellValueFactory(new PropertyValueFactory("name"));
        tblAddress.setCellValueFactory(new PropertyValueFactory("address"));
        tblMobile.setCellValueFactory(new PropertyValueFactory("mobile"));
        tblAmount.setCellValueFactory(new PropertyValueFactory("amount"));

        try {
            ObservableList<Supplier> list= SupplierModel.loadAllSupplier();
            tableView.setItems(list);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public void btnAddOnAction(ActionEvent actionEvent) {
        String supId = txtSupId.getText();
        String name=txtName.getText();
        String address=txtAddress.getText();
        String mobile=txtMobile.getText();
        double amount= Double.parseDouble(txtAmount.getText());

        Supplier supplier = new Supplier(supId,name,address,mobile,amount);
        try {
            boolean isAdded = SupplierModel.save(supplier);

            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Added!").show();
                initialize();
                clear();
                SupplierModel.loadAllSupplier();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                clear();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtSupId.getText();
        try {
            boolean delete = SupplierModel.delete(id);
            if(delete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier deleted!").show();
                initialize();
                clear();
                SupplierModel.loadAllSupplier();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                clear();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) {
        Supplier supplier=new Supplier(txtSupId.getText(),txtName.getText(),txtAddress.getText(),txtMobile.getText(),
                Double.parseDouble(txtAmount.getText()));
        try {
            boolean update = SupplierModel.update(supplier);
            if(update) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier updated!").show();
                initialize();
                clear();
                SupplierModel.loadAllSupplier();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            clear();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String id = txtSupId.getText();
        searchData(id);
    }
    public void txtSupIdOnAction(ActionEvent actionEvent) {
        String id = txtSupId.getText();
        searchData(id);
    }

    private void fillData(Supplier supplier) {
        txtSupId.setText(supplier.getSupId());
        txtName.setText(supplier.getName());
        txtAddress.setText(supplier.getAddress());
        txtMobile.setText(supplier.getMobile());
        txtAmount.setText(String.valueOf(supplier.getAmount()));
    }

    public void searchData(String id){
        try {
            Supplier supplier = SupplierModel.search(id);
            if(supplier != null) {
                fillData(supplier);
            }else{
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                clear();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public void clear(){
        txtSupId.clear();
        txtName.clear();
        txtAddress.clear();
        txtMobile.clear();
        txtAmount.clear();
    }


    public void txtSupIdKeyReleased(KeyEvent keyEvent) {
        if (txtSupId.getText().equals("")) {
            lblSupId.setText("");
        } else {
            Pattern pattern = Pattern.compile("(SU0)([1-9]{1})([0-9]{0,})");
            Matcher matcher = pattern.matcher(txtSupId.getText());

            boolean isMatches = matcher.matches();

            if (!isMatches) {
                lblSupId.setText("Invalid Id !!!");
            } else {
                lblSupId.setText("");
            }
        }
    }

    public void txtNameKeyReleased(KeyEvent keyEvent) {
        if (txtName.getText().equals("")) {
            lblName.setText("");
        } else {
            Pattern pattern = Pattern.compile("^[a-zA-Z]{1,}$");
            Matcher matcher = pattern.matcher(txtName.getText());

            boolean isMatches = matcher.matches();

            if (!isMatches) {
                lblName.setText("Invalid Name !!!");
            } else {
                lblName.setText("");
            }
        }
    }

    public void txtMobileKeyReleased(KeyEvent keyEvent) {
        if (txtMobile.getText().equals("")) {
            lblMobile.setText("");
        } else {
            Pattern pattern = Pattern.compile("^[0]{1}[7]{1}[01245678]{1}[0-9]{7}$");
            Matcher matcher = pattern.matcher(txtMobile.getText());

            boolean isMatches = matcher.matches();

            if (!isMatches) {
                lblMobile.setText("Invalid Mobile No !!!");
            } else {
                lblMobile.setText("");
            }
        }
    }

    public void txtAmountKeyReleased(KeyEvent keyEvent) {
        if (txtAmount.getText().equals("")) {
            lblAmount.setText("");
        } else {
            Pattern pattern = Pattern.compile("^[0-9]{1,}$");
            Matcher matcher = pattern.matcher(txtAmount.getText());

            boolean isMatches = matcher.matches();

            if (!isMatches) {
                lblAmount.setText("Invalid Amount !!!");
            } else {
                lblAmount.setText("");
            }
        }
    }
}
