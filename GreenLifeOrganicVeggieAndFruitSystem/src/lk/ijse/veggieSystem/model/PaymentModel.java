package lk.ijse.veggieSystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.veggieSystem.to.Item;
import lk.ijse.veggieSystem.to.Payment;
import lk.ijse.veggieSystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentModel {
    public static boolean save(Payment payment) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO payment VALUES (?,?,?,?,?,?)";
        return CrudUtil.execute(sql, payment.getPayId(), payment.getCusId(), payment.getType(), payment.getDate(),
                payment.getTime(), payment.getAmount());
    }

    public static Payment search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM payment WHERE payId=?";
        ResultSet result = CrudUtil.execute(sql, id);

        if (result.next()) {
            return new Payment(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getDouble(6)
            );
        }
        return null;
    }

    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM payment WHERE payId=?";
        return CrudUtil.execute(sql, id);
    }

    public static boolean update(Payment payment) throws SQLException, ClassNotFoundException {
        String sql="UPDATE payment SET cusId=?,type=?,date=?,time=?,amount=? WHERE payId=?";
        return CrudUtil.execute(sql,payment.getCusId(),payment.getType(),payment.getDate(),payment.getTime(),
                payment.getAmount(),payment.getPayId());
    }
    public static ObservableList<Payment> loadAllPayment() throws SQLException, ClassNotFoundException {
        ObservableList<Payment>list= FXCollections.observableArrayList();
        String sql="SELECT * FROM payment";
        ResultSet resultSet = CrudUtil.execute(sql);
        while(resultSet.next()){
            Payment payment=new Payment(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getDouble(6)

            );
            list.add(payment);
        }
        return list;
    }

}
