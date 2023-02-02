package lk.ijse.veggieSystem.model;

import lk.ijse.veggieSystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaceOrderModel {
public static ResultSet LastId() throws SQLException, ClassNotFoundException {
    return CrudUtil.execute("SELECT orderId FROM order ORDER BY orderId DESC LIMIT 1");
}

}
