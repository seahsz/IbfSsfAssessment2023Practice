package ssf.ibfAssessment2023.models;

public class OrderConfirmation {

    private String orderId;

    private String address;

    private boolean rush;

    private double subTotal;

    private double totalCost;

    
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public boolean isRush() { return rush; }
    public void setRush(boolean rush) { this.rush = rush; }

    public double getSubTotal() { return subTotal; }
    public void setSubTotal(double subTotal) { this.subTotal = subTotal; }

    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }


    public OrderConfirmation(String orderId, String address, boolean rush, double subTotal, double totalCost) {
        this.orderId = orderId;
        this.address = address;
        this.rush = rush;
        this.subTotal = subTotal;
        this.totalCost = totalCost;
    }
    
}
