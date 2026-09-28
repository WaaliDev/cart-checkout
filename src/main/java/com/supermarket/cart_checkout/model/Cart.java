package com.supermarket.cart_checkout.model;

import java.util.List;
import java.util.ArrayList;

public class Cart {

    private List<CartItem> cart = new ArrayList<>(); 

     // an empty Cart
    public Cart(){}   

    public CartItem getByindex(int index){
        return this.cart.get(index); 
    }

    public CartItem getByProduct(Product product){
        return this.cart.stream().
        // equals because no duplicate products
        filter(cartItem -> cartItem.getProduct().equals(product))
        .findFirst().orElse(null); 
    }

    public void add(CartItem cartItem){
        CartItem existingItem = getByProduct(cartItem.getProduct()); 

        if (existingItem!=null){
            existingItem.increaseQuantity(cartItem.getQuantity());
        }
        else{
            cart.add(cartItem);
        }
    } 

    public int size(){
        return this.cart.size(); 
    }
}
