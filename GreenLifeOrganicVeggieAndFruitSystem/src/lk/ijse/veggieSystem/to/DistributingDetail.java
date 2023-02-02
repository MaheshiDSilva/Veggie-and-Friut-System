package lk.ijse.veggieSystem.to;

public class DistributingDetail {
    private String dId;
    private String userId;
    private String date;
    private String time;
    private double amount;
    private String orderId;

    public DistributingDetail(String dId, String userId, String date, String time, double amount, String orderId) {
        this.dId = dId;
        this.userId = userId;
        this.date = date;
        this.time = time;
        this.amount = amount;
        this.orderId = orderId;
    }

    public DistributingDetail() {
    }

    public String getDId() {
        return dId;
    }

    public void setDId(String dId) {
        this.dId = dId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
