package com.supermarket.cart_checkout.controller;

import java.util.Collection;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supermarket.cart_checkout.model.Product;

@RestController 
public class ProductController{

    private Map<String, Product> products; 

    public ProductController(Map<String, Product> products){
        this.products = products; 
    }

    @GetMapping("/products")  
    public Collection<Product> products(){
        return products.values();
    }
}
