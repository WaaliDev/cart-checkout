package com.supermarket.cart_checkout.model;

public class CartItem {
    private Product product; 
    private int amount; 

    public CartItem(Product product){
        this(product,1); 
    }

    public CartItem(Product product, int amount){
        this.product = product; 
        this.amount = amount;
    }

    public Product getProduct(){
        return this.product; 
    }

    public int getAmount(){
        return this.amount; 
    } 

}
