package com.supermarket.cart_checkout.model;

import java.math.BigDecimal;

public class Item {
    private Product product;
    private int quantity;

    public Item(Product product) {
        this(product, 1);
    }

    public Item(Product product, int amount) {
        this.product = product;
        this.quantity = amount;
    }

    public Product getProduct() {
        return this.product;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void increaseQuantity(int amount) {
        this.quantity = this.quantity + amount;
    }

    public BigDecimal getUnitPrice() {
        return this.product.getPrice();
    }
}