package lk.ijse.veggieSystem.controller;

import com.jfoenix.controls.JFXCheckBox;
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
import lk.ijse.veggieSystem.model.PaymentModel;
import lk.ijse.veggieSystem.to.Item;
import lk.ijse.veggieSystem.to.Payment;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaymentFormController {
    public JFXTextField txtPayId;
    public JFXTextField txtCusId;
    
    public Label lblDate;
    public Label lblTime;
    public JFXTextField txtAmount;
    public TableView tableView;
    public TableColumn tblPayId;
    public TableColumn tblCusId;
    public TableColumn tblType;
    public TableColumn tblDate;
    public TableColumn tblTime;
    public TableColumn tblAmount;
    public TableColumn tblAction;
    public AnchorPane paymentFormContext;
    public JFXTextField txtType;
    public JFXCheckBox chbCash;
    public JFXCheckBox chbCard;
    public Label lblPayId;
    public Label lblCusId;
    public Label lblType;
    public Label lblAmount;

    public void initialize() {
        setDate();
        setTime();

        tblPayId.setCellValueFactory(new PropertyValueFactory("payId"));
        tblCusId.setCellValueFactory(new PropertyValueFactory("cusId"));
        tblType.setCellValueFactory(new PropertyValueFactory("type"));
        tblDate.setCellValueFactory(new PropertyValueFactory("date"));
        tblTime.setCellValueFactory(new PropertyValueFactory("time"));
        tblAmount.setCellValueFactory(new PropertyValueFactory("amount"));
        try {
            ObservableList<Payment> list = PaymentModel.loadAllPayment();
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
        String payId = txtPayId.getText();
        String cusId=txtCusId.getText();
        String type=txtType.getText();
        String date=lblDate.getText();
        String time=lblTime.getText();
        double amount = Double.parseDouble(txtAmount.getText());

        Payment payment = new Payment(payId,cusId,type,date,time,amount);
        try {
            boolean isAdded = PaymentModel.save(payment);

            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment Added!").show();
                initialize();
                clear();
                PaymentModel.loadAllPayment();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                clear();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtPayId.getText();
        try {
            boolean delete = PaymentModel.delete(id);
            if(delete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment deleted!").show();
                initialize();
                clear();
                PaymentModel.loadAllPayment();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                clear();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        Payment payment=new Payment(txtPayId.getText(),txtCusId.getText(),txtType.getText(),lblDate.getText(),
                lblTime.getText(),Double.parseDouble(txtAmount.getText()));

        try {
            boolean update = PaymentModel.update(payment);
            if(update) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment updated!").show();
                initialize();
                clear();
                PaymentModel.loadAllPayment();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            clear();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String id = txtPayId.getText();
        searchData(id);
    }
    public void txtPayIdOnAction(ActionEvent actionEvent) {
        String id = txtPayId.getText();
        searchData(id);
    }

    private void fillData(Payment payment) {
        txtPayId.setText(payment.getPayId());
        txtCusId.setText(payment.getCusId());
        txtType.setText(payment.getType());
        lblDate.setText(payment.getDate());
        lblTime.setText(payment.getTime());
        txtAmount.setText(String.valueOf(payment.getAmount()));
    }

    public void searchData(String id){
        try {
            Payment payment = PaymentModel.search(id);
            if(payment != null) {
                fillData(payment);
            }else{
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                clear();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void clear(){
        txtPayId.clear();
        txtCusId.clear();
        txtType.clear();
        txtAmount.clear();
    }

    public void txtPayIdKeyReleased(KeyEvent keyEvent) {
        if (txtPayId.getText().equals("")) {
            lblPayId.setText("");
        } else {
            Pattern pattern = Pattern.compile("(P0)([1-9]{1})([0-9]{0,})");
            Matcher matcher = pattern.matcher(txtPayId.getText());

            boolean isMatches = matcher.matches();

            if (!isMatches) {
                lblPayId.setText("Invalid Id !!!");
            } else {
                lblPayId.setText("");
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

    public void txtTypeKeyReleased(KeyEvent keyEvent) {
        if (txtType.getText().equals("")) {
            lblType.setText("");
        } else {
            Pattern pattern = Pattern.compile("^[1-2]{1}$");
            Matcher matcher = pattern.matcher(txtType.getText());

            boolean isMatches = matcher.matches();

            if (!isMatches) {
                lblType.setText("Invalid Type !!!");
            } else {
                lblType.setText("");
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

