package com.supermarket.cart_checkout.model;

import java.util.List;
import java.util.ArrayList;

public class Cart {

    private List<CartItem> Cart = new ArrayList<>(); 

     // an empty Cart
    public Cart(){}

    public void add(CartItem cartItem){
        Cart.add(cartItem); 
    }    

    public CartItem get(int index){
        return this.Cart.get(index); 
    }

    public int size(){
        return this.Cart.size(); 
    }
}
