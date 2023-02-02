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
import javafx.scene.paint.Paint;
import lk.ijse.veggieSystem.model.DistributingDetailModel;
import lk.ijse.veggieSystem.model.ItemModel;
import lk.ijse.veggieSystem.to.DistributingDetail;
import lk.ijse.veggieSystem.to.Item;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DistributingDetailFormController {
    public JFXTextField txtDId;
    public JFXTextField txtUserId;
    public Label lblDate;
    public Label lblTime;
    public JFXTextField txtAmount;
    public JFXTextField txtOrderId;
    public TableView tableView;
    public TableColumn tblUserId;
    public TableColumn tblDId;
    public TableColumn tblDate;
    public TableColumn tblTime;
    public TableColumn tblAmount;
    public TableColumn tblOrderId;
    public TableColumn tblAction;
    public AnchorPane distributingDFormContext;
    public Label lblDId;
    public Label lblUserId;
    public Label lblOrderId;
    public Label lblAmount;

    public void initialize(){
        setDate();
        setTime();

        tblDId.setCellValueFactory(new PropertyValueFactory("dId"));
        tblUserId.setCellValueFactory(new PropertyValueFactory("userId"));
        tblDate.setCellValueFactory(new PropertyValueFactory("date"));
        tblTime.setCellValueFactory(new PropertyValueFactory("time"));
        tblAmount.setCellValueFactory(new PropertyValueFactory("amount"));
        tblOrderId.setCellValueFactory(new PropertyValueFactory("orderId"));

        try {
            ObservableList<DistributingDetail> list= DistributingDetailModel.loadAllDistributingDetail();
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
        String dId = txtDId.getText();
        String userId=txtUserId.getText();
        String date=lblDate.getText();
        String time=lblTime.getText();
        double amount=Double.parseDouble(txtAmount.getText());
        String orderId=txtOrderId.getText();

        DistributingDetail distributingDetail = new DistributingDetail(dId,userId,date,time,amount,orderId);
        try {
            boolean isAdded = DistributingDetailModel.save(distributingDetail);

            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Distributing Detail Added!").show();
                initialize();
                clear();
                DistributingDetailModel.loadAllDistributingDetail();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                clear();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String dId = txtDId.getText();
        try {
            boolean delete = DistributingDetailModel.delete(dId);
            if (delete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Distributing Detail deleted!").show();
                initialize();
                clear();
                DistributingDetailModel.loadAllDistributingDetail();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                clear();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        DistributingDetail distributingDetail=new DistributingDetail(txtDId.getText(),txtUserId.getText(),
                lblDate.getText(),lblTime.getText(),Double.parseDouble(txtAmount.getText()),txtOrderId.getText());

        try {
            boolean update = DistributingDetailModel.update(distributingDetail);
            if(update) {
                new Alert(Alert.AlertType.CONFIRMATION, "Distributing Detail updated!").show();
                initialize();
                clear();
                DistributingDetailModel.loadAllDistributingDetail();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            clear();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String id = txtDId.getText();
        searchData(id);
    }
    public void txtDIdOnAction(ActionEvent actionEvent) {
        String id = txtDId.getText();
        searchData(id);
    }
    private void fillData(DistributingDetail distributingDetail) {
        txtDId.setText(distributingDetail.getDId());
        txtUserId.setText(distributingDetail.getUserId());
        lblDate.setText(distributingDetail.getDate());
        lblTime.setText(distributingDetail.getTime());
        txtAmount.setText(String.valueOf(distributingDetail.getAmount()));
        txtOrderId.setText(distributingDetail.getOrderId());
    }

    public void searchData(String dId){
        try {
            DistributingDetail distributingDetail = DistributingDetailModel.search(dId);
            if(distributingDetail != null) {
                fillData(distributingDetail);
            }else{
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                clear();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void clear(){
        txtDId.clear();
        txtUserId.clear();
        txtAmount.clear();
        txtOrderId.clear();
    }

    public void txtDIdKeyReleased(KeyEvent keyEvent) {
        Pattern pattern = Pattern.compile("(D0)([1-9]{1})([0-9]{0,})");
        Matcher matcher = pattern.matcher(txtDId.getText());

        boolean isMatches = matcher.matches();

        if (!isMatches) {
            lblDId.setText("Invalid Id !!!");
        } else {
            lblDId.setText("");
        }
    }

    public void txtUserIdKeyReleased(KeyEvent keyEvent) {
        Pattern pattern = Pattern.compile("(U0)([1-9]{1})([0-9]{0,})");
        Matcher matcher = pattern.matcher(txtUserId.getText());

        boolean isMatches = matcher.matches();

        if (!isMatches) {
            lblUserId.setText("Invalid Id !!!");
        } else {
            lblUserId.setText("");
        }
    }

    public void txtOrderIdKeyReleased(KeyEvent keyEvent) {
        Pattern pattern = Pattern.compile("(O0)([1-9]{1})([0-9]{0,})");
        Matcher matcher = pattern.matcher(txtOrderId.getText());

        boolean isMatches = matcher.matches();

        if (!isMatches) {
            lblOrderId.setText("Invalid Id !!!");
        } else {
            lblOrderId.setText("");
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
