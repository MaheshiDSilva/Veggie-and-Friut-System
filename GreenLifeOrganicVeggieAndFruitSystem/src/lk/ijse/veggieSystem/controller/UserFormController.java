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
import lk.ijse.veggieSystem.model.UserModel;
import lk.ijse.veggieSystem.to.Item;
import lk.ijse.veggieSystem.to.User;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserFormController {
    public JFXTextField txtUserId;
    public JFXTextField txtUserName;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtPassword;
    public JFXTextField txtMobile;
    public JFXTextField txtEmail;
    public TableView tableView;
    public TableColumn tblUserId;
    public TableColumn tblUserName;
    public TableColumn tblPassword;
    public TableColumn tblName;
    public TableColumn tblAddress;
    public TableColumn tblMobile;
    public TableColumn tblEmail;
    public TableColumn tblAction;
    public AnchorPane userFormContext;
    public Label lblUserId;
    public Label lblUserName;
    public Label lblPassword;
    public Label lblName;
    public Label lblMobile;
    public Label lblEmail;

    public void initialize(){
        tblUserId.setCellValueFactory(new PropertyValueFactory("userId"));
        tblUserName.setCellValueFactory(new PropertyValueFactory("userName"));
        tblPassword.setCellValueFactory(new PropertyValueFactory("password"));
        tblName.setCellValueFactory(new PropertyValueFactory("name"));
        tblAddress.setCellValueFactory(new PropertyValueFactory("userAddress"));
        tblMobile.setCellValueFactory(new PropertyValueFactory("userMobile"));
        tblEmail.setCellValueFactory(new PropertyValueFactory("userEmail"));

        try {
            ObservableList<User> list= UserModel.loadAllUser();
            tableView.setItems(list);

        } catch (ClassNotFoundException | SQLException  e) {
            e.printStackTrace();
        }
    }


    public void btnAddOnAction(ActionEvent actionEvent) {
        String userId=txtUserId.getText();
        String userName=txtUserName.getText();
        String password=txtPassword.getText();
        String name=txtName.getText();
        String userAddress=txtAddress.getText();
        String userMobile=txtMobile.getText();
        String userEmail=txtEmail.getText();

        User user=new User(userId,userName,password,name,userAddress,userMobile,userEmail);
        try {
            boolean isAdded = UserModel.save(user);

            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "user Added!").show();
                initialize();
                clear();
                UserModel.loadAllUser();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                clear();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtUserId.getText();
        try {
            boolean delete = UserModel.delete(id);
            if(delete) {
                new Alert(Alert.AlertType.CONFIRMATION, "User deleted!").show();
                initialize();
                clear();
                UserModel.loadAllUser();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                clear();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        User user=new User(txtUserId.getText(),txtUserName.getText(),txtPassword.getText(),txtName.getText(),
                txtAddress.getText(),txtMobile.getText(),txtEmail.getText());
        try {
            boolean update = UserModel.update(user);
            if(update) {
                new Alert(Alert.AlertType.CONFIRMATION, "User updated!").show();
                initialize();
                clear();
                UserModel.loadAllUser();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            clear();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String id = txtUserId.getText();
        searchData(id);
    }
    public void txtUserIdOnAction(ActionEvent actionEvent) {
        String id = txtUserId.getText();
        searchData(id);
    }

    private void searchData(String id) {
        try {
            User user = UserModel.search(id);
            if(user != null) {
                fillData(user);
            }else{
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                clear();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillData(User user) {
        txtUserId.setText(user.getUserId());
        txtUserName.setText(user.getUserName());
        txtPassword.setText(user.getPassword());
        txtName.setText(user.getName());
        txtAddress.setText(user.getUserAddress());
        txtMobile.setText(user.getUserMobile());
        txtEmail.setText(user.getUserEmail());
    }

    public void clear(){
        txtUserId.clear();
        txtUserName.clear();
        txtPassword.clear();
        txtName.clear();
        txtAddress.clear();
        txtMobile.clear();
        txtEmail.clear();
    }

    public void txtUserNameKeyReleased(KeyEvent keyEvent) {
        if (txtUserName.getText().equals("")) {
            lblUserName.setText("");
        } else {
            Pattern pattern = Pattern.compile("^[a-zA-Z]{1,}$");
            Matcher matcher = pattern.matcher(txtUserName.getText());

            boolean isMatches = matcher.matches();

            if (!isMatches) {
                lblUserName.setText("Invalid User Name !!!");
            } else {
                lblUserName.setText("");
            }
        }
    }

    public void txtPasswordKeyReleased(KeyEvent keyEvent) {
        if (txtPassword.getText().equals("")) {
            lblPassword.setText("");
        } else {
            Pattern pattern = Pattern.compile("^[0-9]{5}$");
            Matcher matcher = pattern.matcher(txtPassword.getText());

            boolean isMatches = matcher.matches();

            if (!isMatches) {
                lblPassword.setText("Invalid Password !!!");
            } else {
                lblPassword.setText("");
            }
        }
    }

    public void txtMobileKeyReleased(KeyEvent keyEvent) {
        if (txtMobile.getText().equals("")) {
            lblPassword.setText("");
        } else {
            Pattern pattern = Pattern.compile("^[0]{1}[7]{1}[01245678]{1}[0-9]{7}$");
            Matcher matcher = pattern.matcher(txtMobile.getText());

            boolean isMatches = matcher.matches();

            if (!isMatches) {
                lblMobile.setText("Invalid Mobile !!!");
            } else {
                lblMobile.setText("");
            }
        }
    }

    public void txtEmailKeyReleased(KeyEvent keyEvent) {
        if (txtEmail.getText().equals("")) {
            lblEmail.setText("");
        } else {
            Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
            Matcher matcher = pattern.matcher(txtEmail.getText());

            boolean isMatches = matcher.matches();

            if (!isMatches) {
                lblEmail.setText("Invalid Email !!!");
            } else {
                lblEmail.setText("");
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

    public void txtNameKeyReleased(KeyEvent keyEvent) {
        if (txtName.getText().equals("")) {
            lblName.setText("");
        } else {
            Pattern pattern = Pattern.compile("[A-Z][a-z]*");
            Matcher matcher = pattern.matcher(txtName.getText());

            boolean isMatches = matcher.matches();

            if (!isMatches) {
                lblName.setText("Invalid Name !!!");
            } else {
                lblName.setText("");
            }
        }
    }
}
