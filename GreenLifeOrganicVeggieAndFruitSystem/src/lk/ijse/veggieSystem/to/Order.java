package lk.ijse.veggieSystem.to;

public class Order {
    private String orderId;
    private String date;
    private String time;
    private String orderType;
    private String cusId;

    public Order(String orderId, String date, String time, String orderType, String cusId) {
        this.orderId = orderId;
        this.date = date;
        this.time = time;
        this.orderType = orderType;
        this.cusId = cusId;
    }

    public Order() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }
}
