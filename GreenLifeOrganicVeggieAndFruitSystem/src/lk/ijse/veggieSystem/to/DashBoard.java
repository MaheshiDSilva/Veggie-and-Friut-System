package lk.ijse.veggieSystem.to;

public class DashBoard {
    private String CusId;
    private String userId;
    private String cusName;
    private String mobile;
    private String address;

    public DashBoard() {
    }

    public DashBoard(String cusId, String userId, String cusName, String mobile, String address) {
        CusId = cusId;
        this.userId = userId;
        this.cusName = cusName;
        this.mobile = mobile;
        this.address = address;
    }

    public String getCusId() {
        return CusId;
    }

    public void setCusId(String cusId) {
        CusId = cusId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
