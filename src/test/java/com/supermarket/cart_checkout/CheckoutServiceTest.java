package com.supermarket.cart_checkout;

import com.supermarket.cart_checkout.model.Cart;
import com.supermarket.cart_checkout.model.CartItem;
import com.supermarket.cart_checkout.model.Offer;
import com.supermarket.cart_checkout.model.Product;
import com.supermarket.cart_checkout.service.CheckoutService;
import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutServiceTest {

    // for tests without offer
    private final CheckoutService checkoutServiceNoOffer = new CheckoutService(List.of());

    @Test
    public void returnZeroTotalForEmptyCart() {
        Cart cart = new Cart();

        assertEquals(BigDecimal.ZERO, checkoutServiceNoOffer.calculateTotal(cart));
    }

    @Test
    public void returnsCorrectTotalForSingleCartItem() {
        Product apple = new Product("Apple", new BigDecimal("0.30"));

        Cart cart = new Cart();
        cart.add(new CartItem(apple));

        assertEquals(new BigDecimal("0.30"), checkoutServiceNoOffer.calculateTotal(cart));
    }

    @Test
    public void returnsCorrectTotalForMultipleSameCartItems() {
        Product apple = new Product("Apple", new BigDecimal("0.30"));

        Cart cart = new Cart();
        cart.add(new CartItem(apple));
        cart.add(new CartItem(apple));

        assertEquals(new BigDecimal("0.60"), checkoutServiceNoOffer.calculateTotal(cart));
    }

    @Test
    public void returnsCorrectTotalForDifferentCartItems() {
        Product apple = new Product("Apple", new BigDecimal("0.30"));
        Product milk = new Product("milk", new BigDecimal("1.30"));

        Cart cart = new Cart();
        cart.add(new CartItem(apple));
        cart.add(new CartItem(milk));

        assertEquals(new BigDecimal("1.60"), checkoutServiceNoOffer.calculateTotal(cart));
    }

    @Test void returnsCorrectTotalForCartItemWithQuantity(){
        Product apple = new Product("Apple", new BigDecimal("0.30"));

        CartItem cartitem = new CartItem(apple,2); 

        Cart cart = new Cart(); 
        cart.add(cartitem);

        assertEquals(new BigDecimal("0.60"), checkoutServiceNoOffer.calculateTotal(cart));
    }

    @Test void returnsCombinedQuantityForSameProductAddedSeparately(){
        Product apple = new Product("Apple", new BigDecimal("0.30"));

        Cart cart = new Cart();
        cart.add(new CartItem(apple));
        cart.add(new CartItem(apple));

        assertEquals(2, cart.getByProduct(apple).getQuantity());
    }

    @Test void returnsCombinedDifferentQuantitiesOfSameProduct(){
        Product apple = new Product("Apple", new BigDecimal("0.30"));

        Cart cart = new Cart();
        cart.add(new CartItem(apple));
        cart.add(new CartItem(apple, 2));

        assertEquals(3, cart.getByProduct(apple).getQuantity());
    }

    @Test void returnsCorrectTotalWithMulibuyOffer(){
        Product apple = new Product("Apple", new BigDecimal("0.30"));

        // same task description example
        Offer offer = new Offer(apple,2, new BigDecimal("0.45")); 

        Cart cart = new Cart();
        cart.add(new CartItem(apple));
        cart.add(new CartItem(apple));

        CheckoutService checkoutService = new CheckoutService(List.of(offer)); 
        
        assertEquals(new BigDecimal("0.45"), checkoutService.calculateTotal(cart)); 
    }
}