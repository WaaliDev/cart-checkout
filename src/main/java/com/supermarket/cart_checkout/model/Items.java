package com.supermarket.cart_checkout.model;

import java.util.ArrayList;
import java.util.List;

public class Items {

    private List<Item> cart = new ArrayList<>();

    public Items() {}

    public Item getByindex(int index) {
        return this.cart.get(index);
    }

    public Item getByProduct(Product product) {
        return this.cart.stream()
                .filter(cartItem -> cartItem.getProduct().equals(product))
                .findFirst()
                .orElse(null);
    }

    public void add(Item cartItem) {
        Item existingItem = getByProduct(cartItem.getProduct());

        if (existingItem != null) {
            existingItem.increaseQuantity(cartItem.getQuantity());
        } else {
            cart.add(cartItem);
        }
    }

    public int size() {
        return this.cart.size();
    }
}