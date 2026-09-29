package com.supermarket.cart_checkout.model;

import java.math.BigDecimal;

public class Offer {
    private Product product; 
    private int reqQuantity; 
    private BigDecimal offerPrice; 

    public Offer(Product product, int reqQuantity, BigDecimal offerPrice){
        this.product = product; 
        this.reqQuantity = reqQuantity; 
        this.offerPrice = offerPrice; 
    }

    public Product getProduct(){
        return this.product; 
    }

    public int getReqQuantity(){
        return this.reqQuantity; 
    }

    public BigDecimal getUnittBundlePrice(){
        return this.offerPrice; 
    }
}
