// package com.example.shopping.controller;

// import com.example.shopping.model.NewOrder;
// import com.example.shopping.service.OrderService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import com.example.shopping.model.OrderItem;

// @RestController
// @RequestMapping("/api/orders")
// public class ApiOrderController {

//     @Autowired
//     private OrderService orderService;

//     private List<OrderItem> orderItems;

//     public List<OrderItem> getOrderItems() {
//         return orderItems;
//     }

//     public void setOrderItems(List<OrderItem> orderItems) {
//         this.orderItems = orderItems;
//     }

//     @PostMapping
//     public ResponseEntity<NewOrder> placeOrder(@RequestBody NewOrder newOrder) {
//         NewOrder createdOrder = orderService.placeOrder(newOrder, orderItems);
//         return ResponseEntity.ok(createdOrder);
//     }

//     @GetMapping("/{orderId}")
//     public ResponseEntity<NewOrder> getOrderById(@PathVariable Integer orderId) {
//         NewOrder order = orderService.getOrderById(orderId);
//         return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
//     }

//     @GetMapping
//     public ResponseEntity<List<NewOrder>> getAllOrders() {
//         List<NewOrder> orders = orderService.getAllOrders();
//         return ResponseEntity.ok(orders);
//     }
// }


package com.example.shopping.controller;

import com.example.shopping.model.NewOrder;
import com.example.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// import com.example.shopping.model.OrderItem; // No longer directly used here for incoming payload

@RestController
@RequestMapping("/api/orders")
public class ApiOrderController {

    @Autowired
    private OrderService orderService;

    // The orderItems list and its getters/setters here are no longer needed
    // as the orderItems should be part of the NewOrder object in the @RequestBody.
    // private List<OrderItem> orderItems;
    // public List<OrderItem> getOrderItems() { return orderItems; }
    // public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }

    @PostMapping
    public ResponseEntity<NewOrder> placeOrder(@RequestBody NewOrder newOrder) {
        // The orderItems should already be populated within the newOrder object
        // by Jackson deserialization when it maps the incoming JSON.
        NewOrder createdOrder = orderService.placeOrder(newOrder); // FIX: Removed orderItems argument
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<NewOrder> getOrderById(@PathVariable Long orderId) { // FIX: Changed Integer to Long
        NewOrder order = orderService.getOrderById(orderId);
        return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<NewOrder>> getAllOrders() {
        List<NewOrder> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
}
