package com.supermarket.cart_checkout.service;

import java.math.BigDecimal;

import com.supermarket.cart_checkout.model.Cart;

public class CheckoutService {



    public BigDecimal calculateTotal(Cart cart) {   

        BigDecimal total = BigDecimal.ZERO; 

        for (int i=0; i<cart.size(); i++){
            total = total.add(cart.get(i).getProduct().getPrice()); 
        }
        return total; 
       
    }

}
