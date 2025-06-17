
// // package com.example.shopping.controller;

// // import com.example.shopping.model.NewOrder;
// // import com.example.shopping.service.OrderService;
// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.http.ResponseEntity;
// // import org.springframework.web.bind.annotation.*;

// // import java.util.List;
// // import com.example.shopping.model.OrderItem;

// // @RestController
// // @RequestMapping("/api/orders")
// // public class ApiOrderController {

// //     @Autowired
// //     private OrderService orderService;

// //     private List<OrderItem> orderItems;

// //     public List<OrderItem> getOrderItems() {
// //         return orderItems;
// //     }

// //     public void setOrderItems(List<OrderItem> orderItems) {
// //         this.orderItems = orderItems;
// //     }

// //     @PostMapping
// //     public ResponseEntity<NewOrder> placeOrder(@RequestBody NewOrder newOrder) {
// //         NewOrder createdOrder = orderService.placeOrder(newOrder, orderItems);
// //         return ResponseEntity.ok(createdOrder);
// //     }

// //     @GetMapping("/{orderId}")
// //     public ResponseEntity<NewOrder> getOrderById(@PathVariable Integer orderId) {
// //         NewOrder order = orderService.getOrderById(orderId);
// //         return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
// //     }

// //     @GetMapping
// //     public ResponseEntity<List<NewOrder>> getAllOrders() {
// //         List<NewOrder> orders = orderService.getAllOrders();
// //         return ResponseEntity.ok(orders);
// //     }
// // }


// package com.example.shopping.controller;

// import com.example.shopping.model.NewOrder;
// import com.example.shopping.model.OrderItem;
// import com.example.shopping.service.CartService;
// import com.example.shopping.service.OrderService;

// import jakarta.servlet.http.HttpSession;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.ArrayList;
// import java.util.List;
// // import com.example.shopping.model.OrderItem; // No longer directly used here for incoming payload

// @RestController
// @RequestMapping("/api/orders")
// public class ApiOrderController {

//     @Autowired
//     private OrderService orderService;

//     // The orderItems list and its getters/setters here are no longer needed
//     // as the orderItems should be part of the NewOrder object in the @RequestBody.
//     // private List<OrderItem> orderItems;
//     // public List<OrderItem> getOrderItems() { return orderItems; }
//     // public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }

// //     @PostMapping("/from-cart")
// //     public ResponseEntity<NewOrder> placeOrderFromCart(@RequestBody NewOrder newOrder, @Autowired CartService cartService) {
// //         // Use cart items from the session
// //         List<OrderItem> cartItems = cartService.getCartItems();
// //         if (cartItems == null || cartItems.isEmpty()) {
// //             return ResponseEntity.badRequest().build();
// //         }
// //         System.out.println("Cart items in session: " + cartItems);
// //         newOrder.setOrderItems(new ArrayList<>(cartItems));
// //         NewOrder createdOrder = orderService.placeOrder(newOrder);
// //         cartService.clearCart();
// //         return ResponseEntity.ok(createdOrder);
// // }

//     // @PostMapping("/from-cart")
//     // public ResponseEntity<NewOrder> placeOrderFromCart(@RequestBody NewOrder newOrder, @Autowired CartService cartService) {    
//     //     System.out.println(">>> /api/orders/from-cart endpoint called");
//     //     if (newOrder == null) {
//     //         System.out.println(">>> newOrder is null!");
//     //         return ResponseEntity.badRequest().build();
//     //     }
//     //     System.out.println(">>> newOrder received: " + newOrder);

//     //     // Use cart items from the session
//     //     List<OrderItem> cartItems = cartService.getCartItems();
//     //     System.out.println(">>> Cart items in session: " + cartItems);

//     //     if (cartItems == null || cartItems.isEmpty()) {
//     //         System.out.println(">>> Cart is empty or null, returning 400");
//     //         return ResponseEntity.badRequest().build();
//     //     }

//     //     newOrder.setOrderItems(new ArrayList<>(cartItems));
//     //     NewOrder createdOrder = orderService.placeOrder(newOrder);
//     //     System.out.println(">>> Order placed: " + createdOrder);
//     //     cartService.clearCart();
//     //     return ResponseEntity.ok(createdOrder);
//     // }

//     @PostMapping("/from-cart")
//     public ResponseEntity<NewOrder> placeOrderFromCart(
//             @RequestBody NewOrder newOrder,
//             HttpSession session) {
//         System.out.println(">>> /api/orders/from-cart endpoint called");

//         @SuppressWarnings("unchecked")
//         List<OrderItem> cartItems = (List<OrderItem>) session.getAttribute("cartItems");
//         System.out.println(">>> Cart items in session: " + cartItems);

//         if (cartItems == null || cartItems.isEmpty()) {
//             System.out.println(">>> Cart is empty or null, returning 400");
//             return ResponseEntity.badRequest().build();
//         }

//         newOrder.setOrderItems(new ArrayList<>(cartItems));
//         NewOrder createdOrder = orderService.placeOrder(newOrder);
//         System.out.println(">>> Order placed: " + createdOrder);
//         session.removeAttribute("cartItems");
//         return ResponseEntity.ok(createdOrder);
//     }

//     @PostMapping
//     public ResponseEntity<NewOrder> placeOrder(@RequestBody NewOrder newOrder) {
//         // The orderItems should already be populated within the newOrder object
//         // by Jackson deserialization when it maps the incoming JSON.
//         NewOrder createdOrder = orderService.placeOrder(newOrder); // FIX: Removed orderItems argument
//         return ResponseEntity.ok(createdOrder);
//     }

//     @GetMapping("/{orderId}")
//     public ResponseEntity<NewOrder> getOrderById(@PathVariable Long orderId) { // FIX: Changed Integer to Long
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
import com.example.shopping.model.OrderItem;
import com.example.shopping.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class ApiOrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/from-cart")
    public ResponseEntity<NewOrder> placeOrderFromCart(@RequestBody NewOrder newOrder, HttpSession session) {
        System.out.println(">>> /api/orders/from-cart endpoint called");
        if (newOrder == null) {
            System.out.println(">>> newOrder is null!");
            return ResponseEntity.badRequest().build();
        }
        System.out.println(">>> newOrder received: " + newOrder);

        @SuppressWarnings("unchecked")
        List<OrderItem> cartItems = (List<OrderItem>) session.getAttribute("cartItems");
        System.out.println(">>> Cart items in session: " + cartItems);

        if (cartItems == null || cartItems.isEmpty()) {
            System.out.println(">>> Cart is empty or null, returning 400");
            return ResponseEntity.badRequest().build();
        }

        newOrder.setOrderItems(new ArrayList<>(cartItems));
        NewOrder createdOrder = orderService.placeOrder(newOrder);
        System.out.println(">>> Order placed: " + createdOrder);
        session.removeAttribute("cartItems");
        return ResponseEntity.ok(createdOrder);
    }
}