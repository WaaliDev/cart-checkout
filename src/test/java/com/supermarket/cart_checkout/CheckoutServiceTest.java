package com.supermarket.cart_checkout;

import com.supermarket.cart_checkout.model.Cart;
import com.supermarket.cart_checkout.model.CartItem;
import com.supermarket.cart_checkout.model.Product;
import com.supermarket.cart_checkout.service.CheckoutService;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutServiceTest {

    @Test
    public void returnZeroTotalForEmptyCart() {
        Cart cart = new Cart();

        CheckoutService checkoutService = new CheckoutService();

        assertEquals(BigDecimal.ZERO, checkoutService.calculateTotal(cart));
    }

    @Test
    public void returnsCorrectTotalForSingleCartItem() {
        Product apple = new Product("Apple", new BigDecimal("0.30"));

        Cart cart = new Cart();
        cart.add(new CartItem(apple));

        CheckoutService checkoutService = new CheckoutService();

        assertEquals(new BigDecimal("0.30"), checkoutService.calculateTotal(cart));
    }

    @Test
    public void returnsCorrectTotalForMultipleSameCartItems() {
        Product apple = new Product("Apple", new BigDecimal("0.30"));

        Cart cart = new Cart();
        cart.add(new CartItem(apple));
        cart.add(new CartItem(apple));

        CheckoutService checkoutService = new CheckoutService();

        assertEquals(new BigDecimal("0.60"), checkoutService.calculateTotal(cart));
    }

    @Test
    public void returnsCorrectTotalForDifferentCartItems() {
        Product apple = new Product("Apple", new BigDecimal("0.30"));
        Product milk = new Product("milk", new BigDecimal("1.30"));

        Cart cart = new Cart();
        cart.add(new CartItem(apple));
        cart.add(new CartItem(milk));

        CheckoutService checkoutService = new CheckoutService();

        assertEquals(new BigDecimal("1.60"), checkoutService.calculateTotal(cart));
    }

    @Test void returnsCorrectTotalForCartItemWithQuantity(){
        Product apple = new Product("Apple", new BigDecimal("0.30"));

        CartItem cartitem = new CartItem(apple,2); 

        Cart cart = new Cart(); 
        cart.add(cartitem);

        CheckoutService checkoutService = new CheckoutService(); 

        assertEquals(new BigDecimal("0.60"), checkoutService.calculateTotal(cart));
    }

}