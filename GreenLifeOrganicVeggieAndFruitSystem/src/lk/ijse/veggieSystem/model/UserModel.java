package lk.ijse.veggieSystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.veggieSystem.to.Item;
import lk.ijse.veggieSystem.to.User;
import lk.ijse.veggieSystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
    public static boolean save(User user) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO user VALUES (?,?,?,?,?,?,?)";
        return CrudUtil.execute(sql,user.getUserId(),user.getUserName(),user.getPassword(),user.getName(),
                user.getUserAddress(),user.getUserMobile(),user.getUserEmail());
    }

    public static User search(String userId) throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM user WHERE userId=?";
        ResultSet result = CrudUtil.execute(sql, userId);

        if (result.next()){
            return new User(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6),
                    result.getString(7)
            );
        }
        return null;
    }

    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM user WHERE userId=?";
        return CrudUtil.execute(sql, id);
    }

    public static boolean update(User user) throws SQLException, ClassNotFoundException {
        String sql="UPDATE user SET userName=?,password=?,name=?,userAddress=?,userMobile=?,userEmail=? WHERE userId=?";
        return CrudUtil.execute(sql,user.getUserName(),user.getPassword(),user.getName(),user.getUserAddress(),user.getUserMobile(),
                user.getUserEmail(),user.getUserId());
    }
    public static ObservableList<User> loadAllUser() throws SQLException, ClassNotFoundException {
        ObservableList<User>list= FXCollections.observableArrayList();
        String sql="SELECT * FROM user";
        ResultSet resultSet = CrudUtil.execute(sql);
        while(resultSet.next()){
            User user=new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );
            list.add(user);
        }
        return list;
    }
}
