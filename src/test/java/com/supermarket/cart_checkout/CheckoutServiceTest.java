package com.supermarket.cart_checkout;

import org.junit.jupiter.api.Test;

import com.supermarket.cart_checkout.service.CheckoutService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

public class CheckoutServiceTest {

    @Test
    public void emptyCartShouldReturnZeroTotal() {
        CheckoutService checkoutService = new CheckoutService();    
        BigDecimal total = checkoutService.calculateTotal();
        assertEquals(BigDecimal.ZERO, total);
    }
}
