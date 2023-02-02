package lk.ijse.veggieSystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.veggieSystem.to.OrderItemDetail;
import lk.ijse.veggieSystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemDetailModel {
    public static boolean save(OrderItemDetail oid) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO  order_item_detail VALUES(?,?,?,?,?,?,?,?,?)";
        return CrudUtil.execute(sql,oid.getOrderItemId(),oid.getOrderId(),oid.getItemId(),oid.getItemName()
                ,oid.getUnitPrice(),oid.getQty(),oid.getTotal(),oid.getDate(),oid.getTime());
    }
    public static ResultSet LastId() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT orderItemId FROM order_item_detail ORDER BY orderItemId  DESC LIMIT 1");
    }

    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM  order_item_detail WHERE orderItemId=?";
        return CrudUtil.execute(sql,id);
    }

    public static OrderItemDetail search (String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM order_item_detail WHERE  orderItemId=?";
        ResultSet result = CrudUtil.execute(sql, id);
        if (result.next()) {
            return new OrderItemDetail(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getDouble(5),
                    result.getInt(6),
                    result.getDouble(7),
                    result.getString(8),
                    result.getString(9)
            );
        }
        return null;
    }
    public static ObservableList<OrderItemDetail> loadAllOrderItem() throws SQLException, ClassNotFoundException {
        ObservableList<OrderItemDetail>list= FXCollections.observableArrayList();
        String sql="SELECT * FROM order_item_detail ";
        ResultSet resultSet = CrudUtil.execute(sql);
        while(resultSet.next()){
            OrderItemDetail orderItemDetail=new OrderItemDetail(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    resultSet.getInt(6),
                    resultSet.getDouble(7),
                    resultSet.getString(8),
                    resultSet.getString(9)
            );
            list.add(orderItemDetail);
        }
        return list;
    }

}