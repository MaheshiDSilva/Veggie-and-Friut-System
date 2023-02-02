package lk.ijse.veggieSystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.veggieSystem.to.Item;
import lk.ijse.veggieSystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemModel {
    public static boolean save(Item item) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO item VALUES (?,?,?,?,?)";
        return CrudUtil.execute(sql,item.getItemId(),item.getItemName(),item.getUnitPrice(),item.getQtyOnHand(),item.getTotal());
    }

    public static ResultSet loadItemId() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT itemId FROM item");
    }

    public static ResultSet getItemName(Object value) throws SQLException, ClassNotFoundException {
    return CrudUtil.execute("SELECT itemName,unitPrice,qtyOnHand,total FROM item WHERE itemId=?",value);
    }

    public static Item search(String itemId) throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM item WHERE itemId=?";
        ResultSet result = CrudUtil.execute(sql, itemId);

        if (result.next()){
            return new Item(
                    result.getString(1),
                    result.getString(2),
                    result.getDouble(3),
                    result.getInt(4),
                    result.getDouble(5)
            );
        }
        return null;
    }


    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM item WHERE itemId=?";
        return CrudUtil.execute(sql, id);
    }

    public static boolean update(Item item) throws SQLException, ClassNotFoundException {
        String sql="UPDATE item SET itemName=?,unitPrice=?,qtyOnHand=?,total=? WHERE itemId=?";
        return CrudUtil.execute(sql,item.getItemName(),item.getUnitPrice(),item.getQtyOnHand(),item.getTotal(),item.getItemId());
    }
    public static ObservableList<Item>loadAllItem() throws SQLException, ClassNotFoundException {
        ObservableList<Item>list= FXCollections.observableArrayList();
        String sql="SELECT * FROM item";
        ResultSet resultSet = CrudUtil.execute(sql);
        while(resultSet.next()){
            Item item=new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4),
                    resultSet.getDouble(5)

            );
            list.add(item);
        }
        return list;
    }

}
