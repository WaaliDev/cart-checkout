package com.supermarket.cart_checkout.model;

import java.math.BigDecimal;

public class CartItem {
    private Product product; 
    private int quantity; 

    public CartItem(Product product){
        this(product,1); 
    }

    public CartItem(Product product, int amount){
        this.product = product; 
        this.quantity = amount;
    }

    public Product getProduct(){
        return this.product; 
    }

    public int getQuantity(){
        return this.quantity; 
    } 

    public BigDecimal getUnitPrice(){
        return this.product.getPrice(); 
    }

}
