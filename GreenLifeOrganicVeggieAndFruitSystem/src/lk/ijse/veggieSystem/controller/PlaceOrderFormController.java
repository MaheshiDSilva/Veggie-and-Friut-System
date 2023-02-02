package lk.ijse.veggieSystem.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.veggieSystem.db.DBConnection;
import lk.ijse.veggieSystem.model.CustomerModel;
import lk.ijse.veggieSystem.model.ItemModel;
import lk.ijse.veggieSystem.model.OrderItemDetailModel;
import lk.ijse.veggieSystem.model.OrderModel;
import lk.ijse.veggieSystem.to.Order;
import lk.ijse.veggieSystem.to.OrderItemDetail;
import lk.ijse.veggieSystem.util.CrudUtil;
//import lk.ijse.veggieSystem.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlaceOrderFormController {
    public Label txtOrderId;
    public Label txtDate;
    public Label txtCusName;
    public JFXComboBox cmbItemId;
    public Label txtItemName;
    public Label txtUnitPrice;
    public Label txtQtyOnHand;
    public TableColumn tblItemId;
    public TableColumn tblItemName;
    public TableColumn tblQty;
    public TableColumn tblUnitPrice;
    public TableColumn tblTotal;
    public Label txtTotal;
    public Label txtTime;
    public JFXComboBox cmbCusId;
    public AnchorPane placeOrderFormContext;
    public TableView tblOrder;
    public TextField txtQty;
    public Label lblOrderItemId;

    public void initialize() {
        getOrderId();
        getCustomerId();
        getItemId();
        setDate();
        setTime();
        getNextOrderId();
        clearField();
        getNextOrderItemId();
    }

    private void getNextOrderItemId() {
        try {
            ResultSet set = OrderItemDetailModel.LastId();
            if (set.next()) {
                String[] d00 = set.getString(1).split("OI");
                int id = Integer.parseInt(d00[1]);
                id++;
                lblOrderItemId.setText("OI" + id);
            } else {
                lblOrderItemId.setText("OI01");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearField() {
        txtQty.setText("");
    }

    private void getNextOrderId() {
        try {
            ResultSet set = OrderModel.LastId();
            if (set.next()) {
                String[] d00 = set.getString(1).split("O0");
                int id = Integer.parseInt(d00[1]);
                id++;
                txtOrderId.setText("O0" + id);
            } else {
                txtOrderId.setText("O001");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void setTime() {
        LocalTime time = LocalTime.now();
        txtTime.setText(String.valueOf(time));
    }

    private void setDate() {
        LocalDate date = LocalDate.now();
        txtDate.setText(String.valueOf(date));
    }

    private void getItemId() {
        ArrayList<String> regNo = new ArrayList<>();
        try {
            ResultSet set = ItemModel.loadItemId();
            while (set.next()) {
                regNo.add(set.getString(1));
            }
            cmbItemId.getItems().addAll(regNo);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void getCustomerId() {
        ArrayList<String> ids = new ArrayList<>();
        try {
            ResultSet set = CustomerModel.loadCustomerIds();
            while (set.next()) {
                ids.add(set.getString(1));
            }
            cmbCusId.getItems().addAll(ids);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void getOrderId() {
    }

    String itemId = "";

    public void cmbItemIdOnAction(ActionEvent actionEvent) {
        try {
            ResultSet set = ItemModel.getItemName(cmbItemId.getValue());
            itemId = (String) cmbItemId.getValue();
            if (set.next()) {
                txtItemName.setText(set.getString(1));
                txtUnitPrice.setText(set.getString(2));
                txtQtyOnHand.setText(set.getString(3));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    // public static boolean pladeOrder(Order order) throws SQLException, ClassNotFoundException {

//            DBConnection.getInstance().getConnection().setAutoCommit(false);
//
//            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("Insert into `order` values(?,?,?,?,?)");
//            stm.setObject(1, order.getOrderId());
//            stm.setObject(2, order.getDate());
//            stm.setObject(3, order.getTime());
//            stm.setObject(4, order.getOrderType());
//            stm.setObject(5, order.getCusId());
//
//            boolean isAddedOrder = stm.executeUpdate() > 0;
//
//            if (isAddedOrder) {
//                boolean saveOrder = OrderModel.save(order);
//
//                if (saveOrder) {
//                    boolean updateStock = OrderItemDetailModel.update(orderItem);
//
//                    if (updateStock) {
//                        DBConnection.getInstance().getConnection().commit();
//                        return true;
//                    }
//                }
//            }
//
//            DBConnection.getInstance().getConnection().rollback();
//
//        }finally {
//        try {
//            DBConnection.getInstance().getConnection().setAutoCommit(true);
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
   // }



    public void btnRemoveOnAction(ActionEvent actionEvent) {
        try {
            if (OrderItemDetailModel.delete(lblOrderItemId.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!...").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING, "Deleted Failed!...").show();
        }
    }


    public void cmbCusIdOnAction(ActionEvent actionEvent) {
        try {
            ResultSet set = CustomerModel.getName(cmbCusId.getValue());
            if (set.next()) {
                txtCusName.setText(set.getString(1));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void clear() {
        lblOrderItemId.setText(null);
        txtOrderId.setText(null);
        cmbItemId.setItems(null);
        txtItemName.setText(null);
        txtUnitPrice.setText(null);
        txtQty.clear();
        txtTotal.setText(null);
        txtDate.setText(null);
        txtDate.setText(null);
    }

    public void txtQtyOnAction(ActionEvent actionEvent) {

        String qty = txtQty.getText();
        String price = String.valueOf(txtUnitPrice.getText());
        tblItemId.setCellValueFactory(new PropertyValueFactory("itemId"));
        tblItemName.setCellValueFactory(new PropertyValueFactory("itemName"));
        tblUnitPrice.setCellValueFactory(new PropertyValueFactory("UnitPrice"));
        tblQty.setCellValueFactory(new PropertyValueFactory("qty"));
        tblTotal.setCellValueFactory(new PropertyValueFactory("total"));
    }

    public void btnPrintABillOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/lk/ijse/veggieSystem/view/CustomerReportForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void btnListOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/veggieSystem/view/OrderItemDetail");
    }

    private void setUi(String ui) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        placeOrderFormContext.getChildren().clear();
        placeOrderFormContext.getChildren().add(load);
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {

    }

    private void search() throws ClassNotFoundException, SQLException {
        OrderItemDetail oid = OrderItemDetailModel.search(lblOrderItemId.getText());
        if (oid != null) {
            fillData(oid);
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result").show();
        }
    }

    private void fillData(OrderItemDetail oid) {
        lblOrderItemId.setText(oid.getOrderItemId());
        txtOrderId.setText(oid.getOrderId());
        txtItemName.setText(oid.getItemName());
        txtUnitPrice.setText(String.valueOf(oid.getUnitPrice()));
        txtQty.setText(String.valueOf(oid.getQty()));
        txtTotal.setText(String.valueOf(oid.getTotal()));

    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        double tot = (double) Integer.parseInt(txtQty.getText()) * Double.parseDouble(txtUnitPrice.getText());
        txtTotal.setText(String.valueOf(tot));

        OrderItemDetail ord = new OrderItemDetail(lblOrderItemId.getText(), txtOrderId.getText(),
                (String) cmbItemId.getValue(), txtItemName.getText(), Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQty.getText()), Double.parseDouble(txtTotal.getText()), txtDate.getText(), txtTime.getText());

        try {
            boolean isAdded = OrderItemDetailModel.save(ord);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order Added!").show();
                initialize();
                clear();
                OrderItemDetailModel.loadAllOrderItem();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                clear();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}