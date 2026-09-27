package com.supermarket.cart_checkout.service;

import java.math.BigDecimal;

import com.supermarket.cart_checkout.model.Cart;
import com.supermarket.cart_checkout.model.CartItem;

public class CheckoutService {

    public BigDecimal calculateTotal(Cart cart) {

        BigDecimal total = BigDecimal.ZERO;

        for (int i = 0; i < cart.size(); i++) {
            CartItem cartItem = cart.get(i);

            BigDecimal productPrice = cartItem.getUnitPrice();
            // quanity of type BigDecimal for easier multiplication
            BigDecimal quantity = BigDecimal.valueOf(cartItem.getQuantity());

            total = total.add(productPrice.multiply(quantity)); // price*quantity
        }
        return total;
    }
}
