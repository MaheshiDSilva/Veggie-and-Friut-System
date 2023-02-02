package lk.ijse.veggieSystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.veggieSystem.to.Item;
import lk.ijse.veggieSystem.to.Order;
import lk.ijse.veggieSystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderModel {
    public static ResultSet LastId() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT orderId FROM `order` ORDER BY orderId  DESC LIMIT 1");
    }
    public static boolean save(Order order) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO `order` VALUES (?,?,?,?,?)";
        return CrudUtil.execute(sql,order.getOrderId(),order.getDate(),order.getTime(),order.getOrderType(),
                order.getCusId());
    }

    public static Order search(String orderId) throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM `order` WHERE orderId=?";
        ResultSet result = CrudUtil.execute(sql, orderId);

        if (result.next()){
            return new Order(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5)
            );
        }
        return null;
    }
    public static boolean delete(String orderId) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM `order` WHERE orderId=?";
        return CrudUtil.execute(sql, orderId);
    }

    public static boolean update(Order order) throws SQLException, ClassNotFoundException {
        String sql="UPDATE `order` SET date=?,time=?,orderType=?,cusId=? WHERE orderId=?";
        return CrudUtil.execute(sql,order.getDate(),order.getTime(),order.getOrderType(),order.getCusId(),
                order.getOrderId());
    }
    public static ObservableList<Order> loadAllOrder() throws SQLException, ClassNotFoundException {
        ObservableList<Order>list= FXCollections.observableArrayList();
        String sql="SELECT * FROM `order`";
        ResultSet resultSet = CrudUtil.execute(sql);
        while(resultSet.next()){
            Order order=new Order(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
            list.add(order);
        }
        return list;
    }


}
