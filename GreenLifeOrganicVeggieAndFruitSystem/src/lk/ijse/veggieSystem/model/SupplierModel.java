package lk.ijse.veggieSystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.veggieSystem.to.Supplier;
import lk.ijse.veggieSystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierModel {
    public static boolean save(Supplier supplier) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO supplier VALUES (?,?,?,?,?)";
        return CrudUtil.execute(sql,supplier.getSupId(),supplier.getName(),supplier.getAddress(),supplier.getMobile(),
                supplier.getAmount());
    }
    

    public static ObservableList<Supplier> loadAllSupplier() throws SQLException, ClassNotFoundException {
        ObservableList<Supplier>list= FXCollections.observableArrayList();
        String sql="SELECT * FROM supplier";
        ResultSet resultSet = CrudUtil.execute(sql);
        while(resultSet.next()){
            Supplier supplier=new Supplier(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5)

            );
            list.add(supplier);
        }
        return list;
    }


    public static Supplier search(String supId) throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM supplier WHERE supId=?";
        ResultSet result = CrudUtil.execute(sql,supId);

        if (result.next()){
            return new Supplier(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getDouble(5)
            );
        }
        return null;
    }


    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM supplier WHERE supId=?";
        return CrudUtil.execute(sql, id);
    }

    public static boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {
        String sql="UPDATE supplier SET name=?,address=?,mobile=?,amount=? WHERE supId=?";
        return CrudUtil.execute(sql,supplier.getName(),supplier.getAddress(),supplier.getMobile(),supplier.getAmount(),
                supplier.getSupId());
    }
}
