package lk.ijse.veggieSystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.veggieSystem.to.SupplierItemDetail;
import lk.ijse.veggieSystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierItemDetailModel {
    public static boolean save(SupplierItemDetail supplierItemDetail) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO supplier_item_detail VALUES (?,?,?,?,?,?,?)";
        return CrudUtil.execute(sql,supplierItemDetail.getSupplierItemId(),supplierItemDetail.getSupId(),
                supplierItemDetail.getItemId(),supplierItemDetail.getDate(),supplierItemDetail.getTime(),
                supplierItemDetail.getPrice(),supplierItemDetail.getQty());
    }
    public static SupplierItemDetail search(String supplierItemId) throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM  supplier_item_detail WHERE supplierItemId=?";
        ResultSet result = CrudUtil.execute(sql,supplierItemId);

        if (result.next()){
            return new SupplierItemDetail(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getDouble(6),
                    result.getInt(7)
            );
        }
        return null;
    }
    public static boolean delete(String supplierItemId) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM supplier_item_detail WHERE supplierItemId=?";
        return CrudUtil.execute(sql,supplierItemId);
    }

    public static boolean update(SupplierItemDetail supplierItemDetail) throws SQLException, ClassNotFoundException {
        String sql="UPDATE supplier_item_detail SET supId=?,itemId=?,date=?,time=?,price=?,qty=? WHERE supplierItemId=?";
        return CrudUtil.execute(sql,supplierItemDetail.getSupId(),supplierItemDetail.getItemId(),supplierItemDetail.getDate(),
                supplierItemDetail.getTime(),supplierItemDetail.getPrice(),supplierItemDetail.getQty(),
                supplierItemDetail.getSupplierItemId());
    }
    public static ObservableList<SupplierItemDetail> loadAllSupplierItemDetail() throws SQLException, ClassNotFoundException {
        ObservableList<SupplierItemDetail>list= FXCollections.observableArrayList();
        String sql="SELECT * FROM supplier_item_detail";
        ResultSet resultSet = CrudUtil.execute(sql);
        while(resultSet.next()){
            SupplierItemDetail supplierItemDetail=new SupplierItemDetail(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getDouble(6),
                    resultSet.getInt(7)

            );
            list.add(supplierItemDetail);
        }
        return list;
    }
}
