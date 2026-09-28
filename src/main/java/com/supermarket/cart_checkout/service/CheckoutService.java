package com.supermarket.cart_checkout.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.supermarket.cart_checkout.model.Cart;
import com.supermarket.cart_checkout.model.CartItem;
import com.supermarket.cart_checkout.model.Offer;

public class CheckoutService {

    // checkoutservice has the knowledge of offers
    private List<Offer> offers = new ArrayList<>(); 

    public CheckoutService(List<Offer> offers){
        this.offers = offers; 
    }

    public BigDecimal calculateTotal(Cart cart) {

        BigDecimal total = BigDecimal.ZERO;

        int countRegularItems=0; //items with regular price
        int countBundle=0;  // items with offer price

        BigDecimal regularPrice = BigDecimal.ZERO; 
        BigDecimal bundlePrice = BigDecimal.ZERO;

        for (int i = 0; i < cart.size(); i++) {
            CartItem cartItem = cart.getByindex(i);

            regularPrice = cartItem.getUnitPrice(); 
            countRegularItems = cartItem.getQuantity(); 

            for (Offer offer: offers){

                if (offer.getProduct()==cartItem.getProduct()){

                    countRegularItems = cartItem.getQuantity() % offer.getReqQuantity();

                    countBundle =  (cartItem.getQuantity() - countRegularItems)
                    /offer.getReqQuantity();   
                    
                    // price*quantity
                    regularPrice = regularPrice.multiply(BigDecimal.valueOf(countRegularItems));
                    bundlePrice = offer.getOfferPrice().multiply(BigDecimal.valueOf(countBundle)); 
                }
            }
            
            // necessary for code with no offer path
            regularPrice = regularPrice.multiply(BigDecimal.valueOf(countRegularItems));

            total = total.add(regularPrice.add(bundlePrice)); 
        }
        return total;
    }
}
