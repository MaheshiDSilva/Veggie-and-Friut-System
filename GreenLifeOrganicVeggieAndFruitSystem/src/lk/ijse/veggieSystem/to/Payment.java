package lk.ijse.veggieSystem.to;

public class Payment {
    private String payId;
    private String cusId;
    private String type;
    private String date;
    private String time;
    private double amount;

    public Payment(String payId, String cusId, String type, String date, String time, double amount) {
        this.payId = payId;
        this.cusId = cusId;
        this.type = type;
        this.date = date;
        this.time = time;
        this.amount = amount;
    }

    public Payment() {
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
