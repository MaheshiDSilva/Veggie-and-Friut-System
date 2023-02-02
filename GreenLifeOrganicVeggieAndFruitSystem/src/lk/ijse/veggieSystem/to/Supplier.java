package lk.ijse.veggieSystem.to;

public class Supplier {
    private String supId;
    private String name;
    private String address;
    private String mobile;
    private double amount;

    public Supplier(String supId, String name, String address, String mobile, double amount) {
        this.supId = supId;
        this.name = name;
        this.address = address;
        this.mobile = mobile;
        this.amount = amount;
    }

    public Supplier() {
    }

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


}
