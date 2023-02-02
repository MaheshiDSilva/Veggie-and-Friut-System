package lk.ijse.veggieSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class OrderItemDetailController {

    public TableView tblOrder;
    public TableColumn tblOrderItemId;
    public TableColumn tblOrderId;
    public TableColumn tblItemId;
    public TableColumn tblItemName;
    public TableColumn tblUnitPrice;
    public TableColumn tblQty;
    public TableColumn tblTotal;
    public TableColumn tblDate;
    public TableColumn tblTime;
    public AnchorPane orderitemDetailContext;

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/veggieSystem/view/PlaceOrderForm");
    }
    private void setUi(String ui) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        orderitemDetailContext.getChildren().clear();
        orderitemDetailContext.getChildren().add(load);
    }
}
