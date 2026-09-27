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
    public void emptyCartShouldReturnZeroTotal() {
        Cart cart = new Cart();
        CheckoutService checkoutService = new CheckoutService();
        BigDecimal total = checkoutService.calculateTotal(cart);

        assertEquals(BigDecimal.ZERO, total);
    }

    @Test
    public void returnsCorrectTotalForSingleCartItem() {
        Product apple = new Product("Apple", new BigDecimal("0.30"));

        Cart cart = new Cart();
        cart.add(new CartItem(apple));
        CheckoutService checkoutService = new CheckoutService();
        
        assertEquals(checkoutService.calculateTotal(cart), new BigDecimal("0.30"));
    }
}
