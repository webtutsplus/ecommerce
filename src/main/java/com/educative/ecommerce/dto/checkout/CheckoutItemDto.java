package com.educative.ecommerce.dto.checkout;

public class CheckoutItemDto {

    private String productName;
    private int  quantity;
    private double price;
    private long productId;

    public CheckoutItemDto() {}

    public CheckoutItemDto(String productName, int quantity, double price, long productId) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice(){return price;}

    public long getProductId() {
        return productId;
    }

    public void setProductId(long id) {
        this.productId = id;
    }



}
