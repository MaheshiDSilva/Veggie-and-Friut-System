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
import lk.ijse.veggieSystem.model.CustomerModel;
import lk.ijse.veggieSystem.model.ItemModel;
import lk.ijse.veggieSystem.to.Customer;
import lk.ijse.veggieSystem.to.Item;
import lk.ijse.veggieSystem.util.Navigation;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemFormController {
    public JFXTextField txtItemId;
    public JFXTextField txtItemName;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public TableView tableView;
    public TableColumn tblItemId;
    public TableColumn tblItemName;
    public TableColumn tblUnitPrice;
    public TableColumn tblQtyOnHand;
    public TableColumn tblAction;
    public AnchorPane itemFormContext;
    public Label lblItemId;
    public Label lblItemName;
    public Label lblUnitPrice;
    public Label lblQtyOnHand;
    public Label lblTotal;
    public TableColumn tblTotal;

    public void initialize(){
        tblItemId.setCellValueFactory(new PropertyValueFactory("itemId"));
        tblItemName.setCellValueFactory(new PropertyValueFactory("itemName"));
        tblUnitPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        tblQtyOnHand.setCellValueFactory(new PropertyValueFactory("qtyOnHand"));
        tblTotal.setCellValueFactory(new PropertyValueFactory("total"));

        try {
            ObservableList <Item> list= ItemModel.loadAllItem();
            tableView.setItems(list);

        } catch (ClassNotFoundException | SQLException  e) {
            e.printStackTrace();
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        getTotal();
        String itemId = txtItemId.getText();
        String itemName=txtItemName.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qtyOnHand= Integer.parseInt(txtQtyOnHand.getText());
        double total=Double.parseDouble(lblTotal.getText());

        Item item = new Item(itemId,itemName,unitPrice,qtyOnHand,total);
        try {
            boolean isAdded = ItemModel.save(item);

            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item Added!").show();
                initialize();
                clear();
                ItemModel.loadAllItem();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                clear();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtItemId.getText();
        try {
            boolean delete = ItemModel.delete(id);
            if(delete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item deleted!").show();
                initialize();
                clear();
                ItemModel.loadAllItem();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                clear();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        getTotal();
        Item item=new Item(txtItemId.getText(),txtItemName.getText()
                ,Double.parseDouble(txtUnitPrice.getText()),Integer.parseInt(txtQtyOnHand.getText()),
                Double.parseDouble(lblTotal.getText()));
        try {
            boolean update = ItemModel.update(item);
            if(update) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item updated!").show();
                initialize();
                clear();
                ItemModel.loadAllItem();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            clear();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String id = txtItemId.getText();
        searchData(id);
    }

    public void txtItemIdOnAction(ActionEvent actionEvent) {
        String id = txtItemId.getText();
        searchData(id);
    }

    private void getTotal(){
      double total=(double) Integer.parseInt(txtQtyOnHand.getText())*Double.parseDouble(txtUnitPrice.getText());
      lblTotal.setText(String.valueOf(total));
    }

    private void fillData(Item item) {
        txtItemId.setText(item.getItemId());
        txtItemName.setText(item.getItemName());
        txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
        txtQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
        lblTotal.setText(String.valueOf(item.getTotal()));
    }

    public void searchData(String id){
        try {
            Item item = ItemModel.search(id);
            if(item != null) {
                fillData(item);
            }else{
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                clear();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void clear(){
        txtItemId.clear();
        txtItemName.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
    }

    public void txtItemIdKeyReleased(KeyEvent keyEvent) {
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

    public void txtItemNameKeyReleased(KeyEvent keyEvent) {
        if (txtItemName.getText().equals("")) {
            lblItemName.setText("");
        } else {
            Pattern pattern = Pattern.compile("^([a-zA-Z]{1,})([a-zA-Z]{1,})$");
            Matcher matcher = pattern.matcher(txtItemName.getText());

            boolean isMatches = matcher.matches();

            if (!isMatches) {
                lblItemName.setText("Invalid Name !!!");
            } else {
                lblItemName.setText("");
            }
        }
    }

    public void txtUnitPriceKeyReleased(KeyEvent keyEvent) {

    }

    public void txtQtyOnHandKeyReleased(KeyEvent keyEvent) {
    }
}

