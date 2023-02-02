package lk.ijse.veggieSystem.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.veggieSystem.model.CustomerModel;
import lk.ijse.veggieSystem.model.ItemModel;
import lk.ijse.veggieSystem.model.OrderModel;
import lk.ijse.veggieSystem.to.Item;
import lk.ijse.veggieSystem.to.Order;
//import lk.ijse.veggieSystem.util.Navigation;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderFormController {
    public JFXTextField txtOrderId;
    public Label lblDate;
    public Label lblTime;
    public Label lblCusId;
    public JFXCheckBox chbByPhone;
    public JFXCheckBox chbPhysically;
    public TableView tableView;
    public TableColumn tblOrderId;
    public TableColumn tblDate;
    public TableColumn tblTime;
    public TableColumn tblOrderType;
    public TableColumn tblCusId;
    public TableColumn tblAction;
    public AnchorPane orderFormContext;
    public JFXComboBox cmbCusId;
    public JFXComboBox cmbOrderType;
    public JFXTextField txtOrderType;
    public JFXTextField txtCusId;
    public Label lblOrderId;
    public Label lblOrderType;

    public void initialize(){
        setDate();
        setTime();

        tblOrderId.setCellValueFactory(new PropertyValueFactory("orderId"));
        tblDate.setCellValueFactory(new PropertyValueFactory("date"));
        tblTime.setCellValueFactory(new PropertyValueFactory("time"));
        tblOrderType.setCellValueFactory(new PropertyValueFactory("orderType"));
        tblCusId.setCellValueFactory(new PropertyValueFactory("cusId"));

        try {
            ObservableList<Order> list= OrderModel.loadAllOrder();
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
        String orderId = txtOrderId.getText();
        String date=lblDate.getText();
        String time=lblTime.getText();
        String orderType=txtOrderType.getText();
        String cusId=txtCusId.getText();

        Order order = new Order(orderId,date,time,orderType,cusId);
        try {
            boolean isAdded = OrderModel.save(order);

            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order Added!").show();
                initialize();
                clear();
                OrderModel.loadAllOrder();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                clear();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String orderId = txtOrderId.getText();
        try {
            boolean delete = OrderModel.delete(orderId);
            if(delete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order deleted!").show();
                initialize();
                clear();
                OrderModel.loadAllOrder();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                clear();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        Order order=new Order(txtOrderId.getText(),lblDate.getText(),lblTime.getText(),txtOrderType.getText(),txtCusId.getText());

        try {
            boolean update = OrderModel.update(order);
            if(update) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order updated!").show();
                initialize();
                clear();
                OrderModel.loadAllOrder();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            clear();
        }
    }


    public void btnSearchOnAction(ActionEvent actionEvent) {
        String id = txtOrderId.getText();
        searchData(id);
    }

    public void txtOrderIdOnAction(ActionEvent actionEvent) {
        String id = txtOrderId.getText();
        searchData(id);
    }
    private void fillData(Order order) {
        txtOrderId.setText(order.getOrderId());
        lblDate.setText(order.getDate());
        lblTime.setText(order.getTime());
        txtOrderType.setText(order.getOrderType());
        txtCusId.setText(order.getCusId());
    }

    public void searchData(String id){
        try {
            Order order= OrderModel.search(id);
            if(order != null) {
                fillData(order);
            }else{
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                clear();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void clear(){
        txtOrderId.clear();
        txtOrderType.clear();
        txtCusId.clear();
    }

    public void txtOrderIdKeyReleased(KeyEvent keyEvent) {
        if (txtOrderId.getText().equals("")) {
            lblOrderId.setText("");
        } else {
            Pattern pattern = Pattern.compile("(O0)([1-9]{1})([0-9]{0,})");
            Matcher matcher = pattern.matcher(txtOrderId.getText());

            boolean isMatches = matcher.matches();

            if (!isMatches) {
                lblOrderId.setText("Invalid Id !!!");
            } else {
                lblOrderId.setText("");
            }
        }
    }

    public void txtOrderTypeKeyReleased(KeyEvent keyEvent) {
        if (txtOrderType.getText().equals("")) {
            lblOrderType.setText("");
        } else {
            Pattern pattern = Pattern.compile("^[1-2]{1}$");
            Matcher matcher = pattern.matcher(txtOrderType.getText());

            boolean isMatches = matcher.matches();

            if (!isMatches) {
                lblOrderType.setText("Invalid Order Type !!!");
            } else {
                lblOrderType.setText("");
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
