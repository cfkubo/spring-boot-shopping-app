package com.example.shopping.service;

import com.example.shopping.model.NewProduct;
import com.example.shopping.model.OrderItem;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class CartService {
    private final List<OrderItem> cartItems = new ArrayList<>();

    public List<OrderItem> getCartItems() {
        return cartItems;
    }

    public void addToCart(NewProduct product, int quantity) {
        cartItems.add(new OrderItem(null, product.getProductId(), quantity, product.getPrice()));
    }

    public void clearCart() {
        cartItems.clear();
    }
}