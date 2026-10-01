package com.supermarket.cart_checkout.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CheckoutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test void returnsCorrectTotalWithOfferApplied() throws Exception {
        String body = """ 
        [{"name": "Apple", "quantity": 2}, {"name": "Mango", "quantity": 1}] """;

        this.mockMvc.perform(post("/checkout")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk())
                .andExpect(content().string("2.75"));
    }

    @Test void returnsBadRequestForUnknownProduct() throws Exception {
        String body = """ 
        [{"name": "Banana", "quantity": 1}] """;

        this.mockMvc.perform(post("/checkout")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test void returnsCorrectTotalWhenQuantityIsMissing() throws Exception {
        // no quantity means one item
        String body = """
                [{"name": "Apple"}] """;

        this.mockMvc.perform(post("/checkout")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk())
                .andExpect(content().string("0.30"));
    }


    @Test void returnsBadRequestForZeroQuantity() throws Exception {
        String body = """
            [{"name": "Apple", "quantity": 0}] """;

        this.mockMvc.perform(post("/checkout")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body))
            .andExpect(status().isBadRequest());
}
}