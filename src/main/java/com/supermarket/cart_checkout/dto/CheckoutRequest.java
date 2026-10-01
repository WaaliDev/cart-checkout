package com.supermarket.cart_checkout.dto;


public class CheckoutRequest {
    private String name; 
    private Integer quantity; 

    public void setName(String name){
        this.name = name; 
    }
    public void setQuantity(int quantity){
        this.quantity = quantity; 
    }

    public String getName(){
        return this.name; 
    }

    // Integer because it could return null
    public Integer getQuantity(){
        return this.quantity; 
    }
}

