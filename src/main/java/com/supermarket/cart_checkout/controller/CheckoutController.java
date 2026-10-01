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
    public BigDecimal checkoutTotal(@RequestBody List<CheckoutRequest> checkoutRequest) {
        Cart cart = new Cart();

        for (CheckoutRequest requestItem : checkoutRequest) {
            Product product = this.productByName.get(requestItem.getName());

            if (product == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Unknown product: " + requestItem.getName());
            }
            if (!isValidQuantity(requestItem.getQuantity())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Quantity must be at least 1 for product: " + requestItem.getName());
            }
        
        // constructor -> quantity specified or not
        if (requestItem.getQuantity() == null) {
            cart.add(new CartItem(product));
        } else {
            cart.add(new CartItem(product, requestItem.getQuantity()));
        }
    }

    return this.checkoutService.calculateTotal(cart);
}

    private static boolean isValidQuantity(Integer quantity) {
        
        // missing quantity is allowed and means 1
        return quantity == null || quantity >= 1;
    }
}

