package lk.ijse.veggieSystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.veggieSystem.to.DistributingDetail;
import lk.ijse.veggieSystem.to.Item;
import lk.ijse.veggieSystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DistributingDetailModel {
    public static boolean save(DistributingDetail distributingDetail) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO distributing_detail VALUES (?,?,?,?,?,?)";
        return CrudUtil.execute(sql, distributingDetail.getDId(), distributingDetail.getUserId(),
                distributingDetail.getDate(), distributingDetail.getTime(), distributingDetail.getAmount(),
                distributingDetail.getOrderId());
    }

    public static DistributingDetail search(String dId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM distributing_detail WHERE dId=?";
        ResultSet result = CrudUtil.execute(sql, dId);

        if (result.next()) {
            return new DistributingDetail(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getDouble(5),
                    result.getString(6)
            );
        }
        return null;
    }

    public static boolean delete(String dId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM distributing_detail WHERE dId=?";
        return CrudUtil.execute(sql, dId);
    }

    public static boolean update(DistributingDetail distributingDetail) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE distributing_detail SET userId=?,date=?,time=?,amount=?,orderId=? WHERE dId=?";
        return CrudUtil.execute(sql, distributingDetail.getUserId(), distributingDetail.getDate(), distributingDetail.getTime(),
                distributingDetail.getAmount(), distributingDetail.getOrderId(), distributingDetail.getDId());
    }

    public static ObservableList<DistributingDetail> loadAllDistributingDetail() throws SQLException, ClassNotFoundException {
        ObservableList<DistributingDetail> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM distributing_detail";
        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            DistributingDetail distributingDetail = new DistributingDetail(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    resultSet.getString(6)

            );
            list.add(distributingDetail);
        }
        return list;
    }

}