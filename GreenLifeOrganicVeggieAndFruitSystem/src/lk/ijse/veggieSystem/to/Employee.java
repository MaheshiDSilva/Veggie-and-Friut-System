package lk.ijse.veggieSystem.to;

public class Employee {
    private String id;
    private String userId;
    private String name;
    private String address;
    private String mobile;
    private double salary;
    private String nic;
    private String role;

    public Employee(String id, String userId, String name, String address, String mobile, double salary, String nic, String role) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.mobile = mobile;
        this.salary = salary;
        this.nic = nic;
        this.role = role;
    }

    public Employee() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
