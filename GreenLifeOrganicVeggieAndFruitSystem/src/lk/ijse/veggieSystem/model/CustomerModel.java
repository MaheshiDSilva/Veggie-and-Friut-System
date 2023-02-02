package lk.ijse.veggieSystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import lk.ijse.veggieSystem.to.Customer;
import lk.ijse.veggieSystem.to.Item;
import lk.ijse.veggieSystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerModel {
    public static boolean save(Customer customer) throws SQLException, ClassNotFoundException {
    String sql="INSERT INTO customer VALUES(?,?,?,?,?)";
    return CrudUtil.execute(sql, customer.getCusId(), customer.getUserId(), customer.getCusName(), customer.getMobile(),
                 customer.getAddress());
    }

    public static ResultSet loadCustomerIds() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT cusId FROM customer");
    }
    public static ResultSet getName(Object id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT cusName FROM customer WHERE cusId=?",id);
    }

    public static Customer search(String id) throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM customer WHERE cusId=?";
        ResultSet result = CrudUtil.execute(sql, id);

        if (result.next()){
            return new Customer(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5)
            );
        }
        return null;
    }

    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM customer WHERE cusId=?";
        return CrudUtil.execute(sql, id);
    }

    public static boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        String sql="UPDATE customer SET userId=?,cusName=?,mobile=?,address=? WHERE cusId=?";
        return CrudUtil.execute(sql,customer.getUserId(),customer.getCusName(),customer.getMobile(), customer.getAddress(),
                customer.getCusId());

    }
    public static ObservableList<Customer> loadAllCustomer() throws SQLException, ClassNotFoundException {
        ObservableList<Customer>list= FXCollections.observableArrayList();
        String sql="SELECT * FROM customer";
        ResultSet resultSet = CrudUtil.execute(sql);
        while(resultSet.next()){
            Customer customer=new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
            list.add(customer);
        }
        return list;
    }

}
