package Entity;

import java.util.Date;

public class OrderDetail {
    private int orderId;
    private int customerId;
    private int productId;
    private int quantity;
    private double price;
    private Date orderDate;

    public OrderDetail(int orderId, int customerId, int productId, int quantity, double price) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public Date getOrderDate() {
        return orderDate;
    }
}