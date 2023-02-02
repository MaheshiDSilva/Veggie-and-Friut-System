package lk.ijse.veggieSystem.to;

public class SupplierItemDetail {
    private String supplierItemId;
    private String supId;
    private String itemId;
    private String date;
    private String time;
    private double price;
    private int qty;

    public SupplierItemDetail(String supplierItemId, String supId, String itemId, String date, String time, double price, int qty) {
        this.supplierItemId = supplierItemId;
        this.supId = supId;
        this.itemId = itemId;
        this.date = date;
        this.time = time;
        this.price = price;
        this.qty = qty;
    }

    public SupplierItemDetail() {
    }

    public String getSupplierItemId() {
        return supplierItemId;
    }

    public void setSupplierItemId(String supplierItemId) {
        this.supplierItemId = supplierItemId;
    }

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
