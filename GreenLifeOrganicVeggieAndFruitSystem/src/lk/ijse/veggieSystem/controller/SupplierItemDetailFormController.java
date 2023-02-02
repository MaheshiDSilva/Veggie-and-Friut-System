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
import lk.ijse.veggieSystem.model.SupplierItemDetailModel;
import lk.ijse.veggieSystem.to.SupplierItemDetail;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplierItemDetailFormController {
    public JFXTextField txtItemId;
    public JFXTextField txtPrice;
    public JFXTextField txtQty;
    public Label lblDate;
    public Label lblTime;
    public JFXTextField txtSupId;
    public TableView tableView;
    public TableColumn tblSupId;
    public TableColumn tblItemId;
    public TableColumn tblDate;
    public TableColumn tblTime;
    public TableColumn tblPrice;
    public TableColumn tblQty;
    public TableColumn tblAction;
    public AnchorPane supplierItemFormContext;
    public JFXTextField txtSupplierItemId;
    public TableColumn tblSupplierItemId;
    public Label lblSupItemId;
    public Label lblSupId;
    public Label lblItemId;
    public Label lblPrice;
    public Label lblQty;

    public void initialize(){
        setDate();
        setTime();

        tblSupplierItemId.setCellValueFactory(new PropertyValueFactory("supplierItemId"));
        tblSupId.setCellValueFactory(new PropertyValueFactory("supId"));
        tblItemId.setCellValueFactory(new PropertyValueFactory("itemId"));
        tblDate.setCellValueFactory(new PropertyValueFactory("date"));
        tblTime.setCellValueFactory(new PropertyValueFactory("time"));
        tblPrice.setCellValueFactory(new PropertyValueFactory("price"));
        tblQty.setCellValueFactory(new PropertyValueFactory("qty"));

        try {
            ObservableList<SupplierItemDetail> list= SupplierItemDetailModel.loadAllSupplierItemDetail();
            tableView.setItems(list);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    private void setTime() {
        LocalTime time=LocalTime.now();
        lblTime.setText(String.valueOf(time));
    }

    private void setDate() {
        LocalDate date=LocalDate.now();
        lblDate.setText(String.valueOf(date));
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String supplierItemId = txtSupplierItemId.getText();
        String supId=txtSupId.getText();
        String itemId=txtItemId.getText();
        String date=lblDate.getText();
        String time=lblTime.getText();
        double price = Double.parseDouble(txtPrice.getText());
        int qty= Integer.parseInt(txtQty.getText());

        SupplierItemDetail supplierItemDetail = new SupplierItemDetail(supplierItemId,supId,itemId,date,time,price,qty);
        try {
            boolean isAdded = SupplierItemDetailModel.save(supplierItemDetail);

            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier-Item Detail Added!").show();
                initialize();
                clear();
                SupplierItemDetailModel.loadAllSupplierItemDetail();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                clear();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtSupplierItemId.getText();
        try {
            boolean delete = SupplierItemDetailModel.delete(id);
            if(delete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier-Item Detail deleted!").show();
                initialize();
                clear();
                SupplierItemDetailModel.loadAllSupplierItemDetail();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                clear();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        SupplierItemDetail supplierItemDetail=new SupplierItemDetail(txtSupplierItemId.getText(),txtSupId.getText(),
                txtItemId.getText(),lblDate.getText(),lblTime.getText(),Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtQty.getText()));

        try {
            boolean update = SupplierItemDetailModel.update(supplierItemDetail);
            if(update) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier-Item Detail updated!").show();
                initialize();
                clear();
                SupplierItemDetailModel.loadAllSupplierItemDetail();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            clear();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String id = txtSupplierItemId.getText();
        searchData(id);
    }
    public void txtSupplierItemIdOnAction(ActionEvent actionEvent) {
        String id = txtSupplierItemId.getText();
        searchData(id);
    }

    private void fillData(SupplierItemDetail supplierItemDetail) {
        txtSupplierItemId.setText(supplierItemDetail.getSupplierItemId());
        txtSupId.setText(supplierItemDetail.getSupId());
        txtItemId.setText(supplierItemDetail.getItemId());
        lblDate.setText(supplierItemDetail.getDate());
        lblTime.setText(supplierItemDetail.getTime());
        txtPrice.setText(String.valueOf(supplierItemDetail.getPrice()));
        txtQty.setText(String.valueOf(supplierItemDetail.getQty()));
    }

    public void searchData(String id){
        try {
            SupplierItemDetail supplierItemDetail = SupplierItemDetailModel.search(id);
            if(supplierItemDetail != null) {
                fillData(supplierItemDetail);
            }else{
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                clear();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void clear(){
        txtSupplierItemId.clear();
        txtSupId.clear();
        txtItemId.clear();
        txtPrice.clear();
        txtQty.clear();
    }


    public void txtItemIdKeyRelased(KeyEvent keyEvent) {
        if (txtItemId.getText().equals("")) {
            lblItemId.setText("");
        } else {
            Pattern pattern = Pattern.compile("(I00)([1-9]{1})([0-9]{0,})");
            Matcher matcher = pattern.matcher(txtItemId.getText());

            boolean isMatches = matcher.matches();

            if (!isMatches) {
                lblItemId.setText("Invalid Id !!!");
            } else {
                lblItemId.setText("");
            }
        }

    }

    public void txtPriceKeyReleased(KeyEvent keyEvent) {
        if (txtPrice.getText().equals("")) {
            lblPrice.setText("");
        } else {
            Pattern pattern = Pattern.compile("^[0-9]{1,}$");
            Matcher matcher = pattern.matcher(txtPrice.getText());

            boolean isMatches = matcher.matches();

            if (!isMatches) {
                lblPrice.setText("Invalid Price !!!");
            } else {
                lblPrice.setText("");
            }
        }
    }


    public void txtQtyKeyReleased(KeyEvent keyEvent) {
        if (txtQty.getText().equals("")) {
            lblQty.setText("");
        } else {
            Pattern pattern = Pattern.compile("^[0-9]{1,}$");
            Matcher matcher = pattern.matcher(txtQty.getText());

            boolean isMatches = matcher.matches();

            if (!isMatches) {
                lblQty.setText("Invalid Qty !!!");
            } else {
                lblQty.setText("");
            }
        }
    }

    public void txtSupItemIdKeyReleased(KeyEvent keyEvent) {
        if (txtSupplierItemId.getText().equals("")) {
            lblSupItemId.setText("");
        } else {
            Pattern pattern = Pattern.compile("(SI0)([1-9]{1})([0-9]{0,})");
            Matcher matcher = pattern.matcher(txtSupplierItemId.getText());

            boolean isMatches = matcher.matches();

            if (!isMatches) {
                lblSupItemId.setText("Invalid Id !!!");
            } else {
                lblSupItemId.setText("");
            }
        }
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
    }





