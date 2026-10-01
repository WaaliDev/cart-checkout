package com.supermarket.cart_checkout.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.supermarket.cart_checkout.dto.CheckoutRequest;
import com.supermarket.cart_checkout.model.Cart;
import com.supermarket.cart_checkout.model.CartItem;
import com.supermarket.cart_checkout.model.Offer;
import com.supermarket.cart_checkout.model.Product;
import com.supermarket.cart_checkout.service.CheckoutService;

@RestController 
public class CheckoutController {
    public final CheckoutService checkoutService;
    
    // given by bean
    List<Offer> offers = new ArrayList<>(); 
    Map<String, Product> productByName = new HashMap<>(); 

    public CheckoutController(List<Offer> offers, Map<String, Product> productByName ){
        this.offers = offers; 
        this.productByName = productByName; 
        checkoutService = new CheckoutService(offers); 
    }

    @PostMapping("/checkout")
    public BigDecimal checkoutTotal(@RequestBody List<CheckoutRequest> checkoutRequest ){
        
        Cart tempCart = new Cart(); 

        for (CheckoutRequest requestItems: checkoutRequest){
            if(productByName.containsKey(requestItems.getName())){
                if(requestItems.getQuantity()==null){
                    tempCart.add(new CartItem(productByName.get(requestItems.getName()))); 
                }
                else{
                tempCart.add(new CartItem(productByName.get(requestItems.getName()), requestItems.getQuantity()));
                }
            }
            else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unknown product: " + requestItems.getName());
            }
        }
        return checkoutService.calculateTotal(tempCart); 
    }
}

