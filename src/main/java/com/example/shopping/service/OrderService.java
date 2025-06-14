// package com.example.shopping.service;

// import com.example.shopping.model.NewOrder;
// import com.example.shopping.model.OrderItem;
// import com.example.shopping.repository.NewOrderRepository;
// import com.example.shopping.repository.OrderItemRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import java.math.BigDecimal;
// import java.util.List;

// @Service
// public class OrderService {

//     @Autowired
//     private NewOrderRepository newOrderRepository;

//     @Autowired
//     private OrderItemRepository orderItemRepository;

//     @Transactional
//     public NewOrder placeOrder(NewOrder newOrder, List<OrderItem> orderItems) {
//         BigDecimal totalAmount = calculateTotalAmount(orderItems);
//         newOrder.setTotalAmount(totalAmount);
//         NewOrder savedOrder = newOrderRepository.save(newOrder);

//         for (OrderItem item : orderItems) {
//             item.setOrder(savedOrder);
//             item.setId(savedOrder.getOrderId());
//             orderItemRepository.save(item);
//         }

//         return savedOrder;
//     }

//     private BigDecimal calculateTotalAmount(List<OrderItem> orderItems) {
//         return orderItems.stream()
//                 .map(item -> BigDecimal.valueOf(item.getPriceAtPurchase()).multiply(BigDecimal.valueOf(item.getQuantity())))
//                 .reduce(BigDecimal.ZERO, BigDecimal::add);
//     }

//     public List<NewOrder> getAllOrders() {
//         return newOrderRepository.findAll();
//     }

//     public NewOrder getOrderById(int orderId) {
//         return newOrderRepository.findById(orderId).orElse(null);
//     }
// }

package com.example.shopping.service;

import com.example.shopping.model.NewOrder;
import com.example.shopping.model.OrderItem;
import com.example.shopping.repository.NewOrderRepository;
import com.example.shopping.repository.OrderItemRepository; // Not directly used in the revised logic for saving, but might be for other operations
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime; // Added import for LocalDateTime
import java.util.List;
import java.util.Optional; // Added import for Optional for getOrderById

@Service
public class OrderService {

    @Autowired
    private NewOrderRepository newOrderRepository;

    // @Autowired // This repository might not be directly needed for saving if cascading is set up
    // private OrderItemRepository orderItemRepository; // Keep or remove based on other use cases

    @Transactional
    public NewOrder placeOrder(NewOrder newOrder) { // Modified method signature

        // Set order date and created/updated timestamps if not already set by incoming JSON
        if (newOrder.getOrderDate() == null) {
            newOrder.setOrderDate(LocalDateTime.now());
        }
        if (newOrder.getCreatedAt() == null) {
            newOrder.setCreatedAt(LocalDateTime.now());
        }
        newOrder.setUpdatedAt(LocalDateTime.now()); // Always update on save/place order

        // Ensure total amount is calculated based on items received
        // The NewOrder object passed here should have its orderItems list populated by Jackson
        // and the setOrderItems method in NewOrder should have linked them.
        BigDecimal totalAmount = calculateTotalAmount(newOrder.getOrderItems());
        newOrder.setTotalAmount(totalAmount);

        // Set an initial order status if not provided
        if (newOrder.getOrderStatus() == null || newOrder.getOrderStatus().isEmpty()) {
            newOrder.setOrderStatus("PENDING");
        }

        // Save the NewOrder. Due to CascadeType.ALL on orderItems,
        // all associated OrderItems will also be saved automatically.
        // No need for a separate loop to save order items here.
        NewOrder savedOrder = newOrderRepository.save(newOrder);

        // Note: The setOrderItems method in NewOrder.java should already handle
        // setting the 'order' back-reference for each OrderItem when the list is set.
        // If you were manually adding items to the list, you would call addOrderItem
        // which internally sets the back-reference.

        return savedOrder;
    }

    private BigDecimal calculateTotalAmount(List<OrderItem> orderItems) {
        // Defensive check, although the list should be initialized as ArrayList now
        if (orderItems == null || orderItems.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return orderItems.stream()
                // FIX: Changed getPriceAtPurchase() to getPrice()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<NewOrder> getAllOrders() {
        return newOrderRepository.findAll();
    }

    public NewOrder getOrderById(Long orderId) { // Changed int to Long for orderId
        return newOrderRepository.findById(orderId).orElse(null);
    }
}