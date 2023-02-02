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
import lk.ijse.veggieSystem.model.EmployeeModel;
import lk.ijse.veggieSystem.model.ItemModel;
import lk.ijse.veggieSystem.to.Employee;
import lk.ijse.veggieSystem.to.Item;
//import lk.ijse.veggieSystem.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeFormController {
    public JFXTextField txtMobile;
    public JFXTextField txtUserId;
    public JFXTextField txtName;
    public JFXTextField txtId;
    public JFXTextField txtSalary;
    public JFXTextField txtAddress;
    public JFXTextField txtNIC;
    public JFXTextField txtRole;
    public TableView tableView;
    public TableColumn tblId;
    public TableColumn tblUserId;
    public TableColumn tblName;
    public TableColumn tblAddress;
    public TableColumn tblMobile;
    public TableColumn tblSalary;
    public TableColumn tblNIC;
    public TableColumn tblRole;
    public TableColumn tblAction;
    public AnchorPane employeeFormContext;
    public Label lblId;
    public Label lblUserId;
    public Label lblName;
    public Label lblMobile;
    public Label lblNIC;

    public void initialize(){
        tblId.setCellValueFactory(new PropertyValueFactory("id"));
        tblUserId.setCellValueFactory(new PropertyValueFactory("userId"));
        tblName.setCellValueFactory(new PropertyValueFactory("name"));
        tblAddress.setCellValueFactory(new PropertyValueFactory("address"));
        tblMobile.setCellValueFactory(new PropertyValueFactory("mobile"));
        tblSalary.setCellValueFactory(new PropertyValueFactory("salary"));
        tblNIC.setCellValueFactory(new PropertyValueFactory("nic"));
        tblRole.setCellValueFactory(new PropertyValueFactory("role"));

        try {
            ObservableList<Employee> list= EmployeeModel.loadAllEmployee();
            tableView.setItems(list);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String userId=txtUserId.getText();
        String name=txtName.getText();
        String address=txtAddress.getText();
        String mobile=txtMobile.getText();
        double salary=Double.parseDouble(txtSalary.getText());
        String nic=txtNIC.getText();
        String role=txtRole.getText();

        Employee employee = new Employee(id,userId,name,address,mobile,salary,nic,role);
        try {
            boolean isAdded = EmployeeModel.save(employee);

            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Added!").show();
                initialize();
                clear();
                EmployeeModel.loadAllEmployee();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                clear();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            boolean delete = EmployeeModel.delete(id);
            if(delete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee deleted!").show();
                initialize();
                clear();
                EmployeeModel.loadAllEmployee();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                clear();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        Employee employee=new Employee(txtId.getText(),txtUserId.getText(),txtName.getText(),txtAddress.getText(),
                txtMobile.getText(),Double.parseDouble(txtSalary.getText()),txtNIC.getText(),txtRole.getText());

        try {
            boolean update = EmployeeModel.update(employee);
            if(update) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee updated!").show();
                initialize();
                clear();
                EmployeeModel.loadAllEmployee();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            clear();
        }
    }


    public void btnSearchOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        searchData(id);
    }
    public void txtIdOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        searchData(id);
    }

    private void fillData(Employee employee) {
        txtId.setText(employee.getId());
        txtUserId.setText(employee.getUserId());
        txtName.setText(employee.getName());
        txtAddress.setText(employee.getAddress());
        txtMobile.setText(employee.getMobile());
        txtSalary.setText(String.valueOf(employee.getSalary()));
        txtNIC.setText(employee.getNic());
        txtRole.setText(employee.getRole());
    }
    public void searchData(String id){

        try {
            Employee employee = EmployeeModel.search(id);
            if(employee != null) {
                fillData(employee);
            }else{
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                clear();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public void clear(){
        txtId.clear();
        txtUserId.clear();
        txtName.clear();
        txtAddress.clear();
        txtMobile.clear();
        txtSalary.clear();
        txtNIC.clear();
        txtRole.clear();
    }

    //validation

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

    public void txtUserIdKeyReleased(KeyEvent keyEvent) {
        if (txtUserId.getText().equals("")) {
            lblUserId.setText("");
        } else {
            Pattern pattern = Pattern.compile("^(U0)([1-9]{1})([0-9]{0,})$");
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

    public void txtIdKeyReleased(KeyEvent keyEvent) {
        if (txtId.getText().equals("")) {
            lblId.setText("");
        } else {
            Pattern pattern = Pattern.compile("(E0)([1-9]{1})([0-9]{0,})");
            Matcher matcher = pattern.matcher(txtId.getText());

            boolean isMatches = matcher.matches();

            if (!isMatches) {
                lblId.setText("Invalid Id !!!");
            } else {
                lblId.setText("");
            }
        }
    }

    public void txtNICKeyReleased(KeyEvent keyEvent) {
        if (txtNIC.getText().equals("")) {
            lblNIC.setText("");
        } else {
            Pattern pattern = Pattern.compile("^(?:19|20)?\\d{2}[0-9]{10}|[0-9]{9}[x|X|v|V]$");
            Matcher matcher = pattern.matcher(txtNIC.getText());

            boolean isMatches = matcher.matches();

            if (!isMatches) {
                lblNIC.setText("Invalid NIC !!!");
            } else {
                lblNIC.setText("");
            }
        }
    }
}



