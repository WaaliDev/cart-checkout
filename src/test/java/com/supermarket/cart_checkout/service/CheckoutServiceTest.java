package com.supermarket.cart_checkout.service;

import com.supermarket.cart_checkout.model.Cart;
import com.supermarket.cart_checkout.model.CartItem;
import com.supermarket.cart_checkout.model.Offer;
import com.supermarket.cart_checkout.model.Product;
import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutServiceTest {

    // already defined products, minimum code repitition
    Product apple = new Product("Apple", new BigDecimal("0.30"));
    Product milk = new Product("milk", new BigDecimal("1.30"));
    Product mango = new Product("Mango", new BigDecimal("2.30"));
    Product avocado = new Product("Avocado", new BigDecimal("1.00"));
    
    // offers
    Offer appleOffer = new Offer(apple, 2, new BigDecimal("0.45"));
    Offer mangoOffer = new Offer(mango, 2, new BigDecimal("3.50"));
    Offer avocadoOffer = new Offer(avocado, 3, new BigDecimal("2.00"));

    // for tests without offer
    private final CheckoutService checkoutServiceNoOffer = new CheckoutService(List.of());
    // for tests with offer
    CheckoutService checkoutServiceIncOffer = new CheckoutService(List.of(appleOffer, mangoOffer,
            avocadoOffer));

    // an empty card
    Cart cart = new Cart();

    @Test
    public void returnZeroTotalForEmptyCart() {

        // cart is already empty
        assertEquals(BigDecimal.ZERO, checkoutServiceNoOffer.calculateTotal(cart));
    }

    @Test
    public void returnsCorrectTotalForSingleCartItem() {
        cart.add(new CartItem(apple));

        assertEquals(new BigDecimal("0.30"), checkoutServiceNoOffer.calculateTotal(cart));
    }

    @Test
    public void returnsCorrectTotalForMultipleSameCartItems() {
        cart.add(new CartItem(apple));
        cart.add(new CartItem(apple));

        assertEquals(new BigDecimal("0.60"), checkoutServiceNoOffer.calculateTotal(cart));
    }

    @Test
    public void returnsCorrectTotalForDifferentCartItems() {
        cart.add(new CartItem(apple));
        cart.add(new CartItem(milk));

        assertEquals(new BigDecimal("1.60"), checkoutServiceNoOffer.calculateTotal(cart));
    }

    @Test
    void returnsCorrectTotalForCartItemWithQuantity() {

        CartItem cartitem = new CartItem(apple, 2);

        cart.add(cartitem);

        assertEquals(new BigDecimal("0.60"), 
                checkoutServiceNoOffer.calculateTotal(cart));
    }

    @Test
    void returnsCombinedQuantityForSameProductAddedSeparately() {

        cart.add(new CartItem(apple));
        cart.add(new CartItem(apple));

        assertEquals(2, cart.getByProduct(apple).getQuantity());
    }

    @Test
    void returnsCombinedDifferentQuantitiesOfSameProduct() {

        cart.add(new CartItem(apple));
        cart.add(new CartItem(apple, 2));

        assertEquals(3, cart.getByProduct(apple).getQuantity());
    }

    @Test
    void returnsCorrectTotalWithMulibuyOffer() {

        cart.add(new CartItem(apple));
        cart.add(new CartItem(apple));

        CheckoutService checkoutService = new CheckoutService(List.of(appleOffer));

        assertEquals(new BigDecimal("0.45"),
                checkoutService.calculateTotal(cart));
    }

    @Test
    void returnsCorrectTotalWhenMultipleProductsHaveOffers() {

        // each product is eligible for an offer
        cart.add(new CartItem(apple, 2));
        cart.add(new CartItem(mango, 2));

        assertEquals(new BigDecimal("3.95"),
                checkoutServiceIncOffer.calculateTotal(cart));
    }

    @Test
    void returnsCorrectTotalWithAndWithoutOffers() {

        // with one apple has no offer
        cart.add(new CartItem(apple));

        // two mangoes have an offer
        cart.add(new CartItem(mango, 2)); // or two times calling mango api

        assertEquals(new BigDecimal("3.80"),
                checkoutServiceIncOffer.calculateTotal(cart));
    }

    @Test
    void returnsCorrectTotalWhenProductWithoutOfferFollowsOffer() {

        // one by one or mentioning quantity
        cart.add(new CartItem(mango));
        cart.add(new CartItem(mango));

        // mulk has no offer defined
        cart.add(new CartItem(milk));

        assertEquals(new BigDecimal("4.80"),
                checkoutServiceIncOffer.calculateTotal(cart));
    }

    @Test
    void returnsCorrectTotalWhenMultipleItemsRemainAfterOffer() {

        // avocado has offer on 3 avocados
        // so remainder would be 2 regular items
        cart.add(new CartItem(avocado, 5));

        assertEquals(new BigDecimal("4.00"), 
                checkoutServiceIncOffer.calculateTotal(cart));
    }

    @Test
    void returnsCorrectTotalWhenOfferAppliesMultipleTimes() {

        // offer on 2 apples, bought four
        cart.add(new CartItem(apple, 4));

        assertEquals(new BigDecimal("0.90"), 
                checkoutServiceIncOffer.calculateTotal(cart));
    }

    @Test
    void returnsCorrectTotalWhenItemsAreAddedInAnyOrder() {

        // in any order, first apple
        cart.add(new CartItem(apple));
        // different item - mango
        cart.add(new CartItem(mango));
        // apple again
        cart.add(new CartItem(apple));

        assertEquals(new BigDecimal("2.75"),
                checkoutServiceIncOffer.calculateTotal(cart));
    }
}