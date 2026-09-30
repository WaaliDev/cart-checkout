package com.supermarket.cart_checkout.config;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.supermarket.cart_checkout.model.Offer;
import com.supermarket.cart_checkout.model.Product;

/* This class defines the store's 
products and current offers, and 
provides them to the application.
 */

@Configuration 
public class StoreConfig {

      // already defined products, minimum code repitition
    private final Product apple = new Product("Apple", new BigDecimal("0.30"));
    private final Product milk = new Product("milk", new BigDecimal("1.30"));
    private final Product mango = new Product("Mango", new BigDecimal("2.30"));
    private final Product avocado = new Product("Avocado", new BigDecimal("1.00"));
    
    // offers
    Offer appleOffer = new Offer(apple, 2, new BigDecimal("0.45"));
    Offer mangoOffer = new Offer(mango, 2, new BigDecimal("3.50"));
    Offer avocadoOffer = new Offer(avocado, 3, new BigDecimal("2.00"));


    // this function is useful for controllers
    @Bean 
    public  Map<String, Product>productsByName(){
        Map<String, Product> productNameMap = Map.of(
            apple.getName(), apple,
            milk.getName(), milk,
            mango.getName(), mango,
            avocado.getName(), avocado
        ); 
        return productNameMap; 
    }
    @Bean
    public List<Offer> offers(){
        List<Offer> offers = new ArrayList<>(); 
        offers = List.of(appleOffer, mangoOffer, avocadoOffer); 
        return offers; 
    }
}
