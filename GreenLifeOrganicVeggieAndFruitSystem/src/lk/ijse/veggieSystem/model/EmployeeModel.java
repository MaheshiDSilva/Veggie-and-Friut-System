package lk.ijse.veggieSystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.veggieSystem.to.Employee;
import lk.ijse.veggieSystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeModel {
    public static boolean save(Employee employee) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO employee VALUES (?,?,?,?,?,?,?,?)";
        return CrudUtil.execute(sql,employee.getId(),employee.getUserId(),employee.getName(),employee.getAddress(),
                employee.getMobile(),employee.getSalary(),employee.getNic(),employee.getRole());
    }
    public static Employee search(String id) throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM employee WHERE id=?";
        ResultSet result = CrudUtil.execute(sql, id);

        if (result.next()){
            return new Employee(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getDouble(6),
                    result.getString(7),
                    result.getString(8)
            );
        }
        return null;
    }
    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM employee WHERE id=?";
        return CrudUtil.execute(sql, id);
    }

    public static boolean update(Employee employee) throws SQLException, ClassNotFoundException {
        String sql="UPDATE employee SET userId=?,name=?,address=?,mobile=?,salary=?,nic=?,role=? WHERE id=?";
        return CrudUtil.execute(sql,employee.getUserId(),employee.getName(),employee.getAddress(),employee.getMobile(),
                employee.getSalary(),employee.getNic(),employee.getRole(),employee.getId());
    }
    public static ObservableList<Employee> loadAllEmployee() throws SQLException, ClassNotFoundException {
        ObservableList<Employee>list= FXCollections.observableArrayList();
        String sql="SELECT * FROM employee";
        ResultSet resultSet = CrudUtil.execute(sql);
        while(resultSet.next()){
            Employee employee=new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getDouble(6),
                    resultSet.getString(7),
                    resultSet.getString(8)
            );
            list.add(employee);
        }
        return list;
    }



}
