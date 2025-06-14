// // package com.example.shopping.ui;

// // import com.example.shopping.model.NewOrder;
// // import com.example.shopping.service.OrderService;
// // import com.vaadin.flow.component.button.Button;
// // import com.vaadin.flow.component.formlayout.FormLayout;
// // import com.vaadin.flow.component.notification.Notification;
// // import com.vaadin.flow.component.textfield.TextField;
// // import com.vaadin.flow.router.Route;
// // import org.springframework.beans.factory.annotation.Autowired;

// // @Route("checkout")
// // public class CheckoutView extends FormLayout {

// //     private final OrderService orderService;

// //     private TextField customerName = new TextField("Name");
// //     private TextField customerEmail = new TextField("Email");
// //     private TextField shippingAddress = new TextField("Shipping Address");
// //     private TextField billingAddress = new TextField("Billing Address");
// //     private Button placeOrderButton = new Button("Place Order");

// //     @Autowired
// //     public CheckoutView(OrderService orderService) {
// //         this.orderService = orderService;

// //         add(customerName, customerEmail, shippingAddress, billingAddress, placeOrderButton);
// //         placeOrderButton.addClickListener(e -> placeOrder());
// //     }

// //     private void placeOrder() {
// //         NewOrder newOrder = new NewOrder();
// //         newOrder.setCustomerName(customerName.getValue());
// //         newOrder.setCustomerEmail(customerEmail.getValue());
// //         newOrder.setShippingAddress(shippingAddress.getValue());
// //         newOrder.setBillingAddress(billingAddress.getValue());
// //         newOrder.setTotalAmount(java.math.BigDecimal.valueOf(calculateTotalAmount())); // Implement this method to calculate total

// //         orderService.placeOrder(newOrder, getOrderItems());
// //         Notification.show("Order placed successfully!");
// //         clearFields();
// //     }

// //     private void clearFields() {
// //         customerName.clear();
// //         customerEmail.clear();
// //         shippingAddress.clear();
// //         billingAddress.clear();
// //     }

// //     private double calculateTotalAmount() {
// //         // Implement logic to calculate total amount based on cart items
// //         return 0.0; // Placeholder
// //     }

// //     private java.util.List<com.example.shopping.model.OrderItem> getOrderItems() {
// //         // TODO: Implement logic to retrieve order items from the cart or session
// //         return new java.util.ArrayList<>();
// //     }
// // }

// import com.vaadin.flow.router.Route;
// import org.springframework.beans.factory.annotation.Autowired;

// import java.util.ArrayList; // Added import for ArrayList
// import java.util.List; // Added import for List

// @Route("checkout")
// public class CheckoutView extends FormLayout {

//     private final OrderService orderService;

//     private TextField customerName = new TextField("Name");
//     private TextField customerEmail = new TextField("Email");
//     private TextField shippingAddress = new TextField("Shipping Address");
//     private TextField billingAddress = new TextField("Billing Address");
//     private Button placeOrderButton = new Button("Place Order");

//     @Autowired
//     public CheckoutView(OrderService orderService) {
//         this.orderService = orderService;

//         add(customerName, customerEmail, shippingAddress, billingAddress, placeOrderButton);
//         placeOrderButton.addClickListener(e -> placeOrder());
//     }

//     private void placeOrder() {
//         NewOrder newOrder = new NewOrder();
//         newOrder.setCustomerName(customerName.getValue());
//         newOrder.setCustomerEmail(customerEmail.getValue());
//         newOrder.setShippingAddress(shippingAddress.getValue());
//         newOrder.setBillingAddress(billingAddress.getValue());
//         newOrder.setTotalAmount(java.math.BigDecimal.valueOf(calculateTotalAmount())); // Implement this method to calculate total

//         // FIX: Set order items on the newOrder object before passing it to the service
//         // The getOrderItems() method needs to return actual cart items from your application logic.
//         newOrder.setOrderItems(getOrderItems());

//         orderService.placeOrder(newOrder); // FIX: Removed orderItems argument
//         Notification.show("Order placed successfully!");
//         clearFields();
//     }

//     private void clearFields() {
//         customerName.clear();
//         customerEmail.clear();
//         shippingAddress.clear();
//         billingAddress.clear();
//     }

//     private double calculateTotalAmount() {
//         // Implement logic to calculate total amount based on cart items
//         // This should probably iterate through a list of items and sum their prices.
//         // For demonstration, returning a placeholder.
//         return 0.0; // Placeholder
//     }

//     private List<OrderItem> getOrderItems() { // Changed return type to java.util.List
//         // TODO: Implement logic to retrieve order items from the cart or session.
//         // For now, returning an empty list or some dummy data for testing.
//         // Example: If you had a shopping cart service, you'd get items from there.
//         return new ArrayList<>(); // Returning an empty list for compilation
//     }
// }


package com.example.shopping.ui;

import com.example.shopping.model.NewOrder;
import com.example.shopping.model.OrderItem; // Import for OrderItem
import com.example.shopping.service.OrderService; // Import for OrderService
import com.vaadin.flow.component.button.Button; // Import for Button
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField; // Import for TextField
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList; // Import for ArrayList
import java.util.List; // Import for List

@Route("checkout")
public class CheckoutView extends FormLayout {

    private final OrderService orderService;

    private TextField customerName = new TextField("Name");
    private TextField customerEmail = new TextField("Email");
    private TextField shippingAddress = new TextField("Shipping Address");
    private TextField billingAddress = new TextField("Billing Address");
    private Button placeOrderButton = new Button("Place Order");

    @Autowired
    public CheckoutView(OrderService orderService) {
        this.orderService = orderService;

        add(customerName, customerEmail, shippingAddress, billingAddress, placeOrderButton);
        placeOrderButton.addClickListener(e -> placeOrder());
    }

    private void placeOrder() {
        NewOrder newOrder = new NewOrder();
        newOrder.setCustomerName(customerName.getValue());
        newOrder.setCustomerEmail(customerEmail.getValue());
        newOrder.setShippingAddress(shippingAddress.getValue());
        newOrder.setBillingAddress(billingAddress.getValue());
        newOrder.setTotalAmount(java.math.BigDecimal.valueOf(calculateTotalAmount())); 

        // Set order items on the newOrder object before passing it to the service
        newOrder.setOrderItems(getOrderItems());

        orderService.placeOrder(newOrder); 
        Notification.show("Order placed successfully!");
        clearFields();
    }

    private void clearFields() {
        customerName.clear();
        customerEmail.clear();
        shippingAddress.clear();
        billingAddress.clear();
    }

    private double calculateTotalAmount() {
        // Implement logic to calculate total amount based on cart items
        return 0.0; // Placeholder
    }

    private List<OrderItem> getOrderItems() { 
        // TODO: Implement logic to retrieve order items from the cart or session.
        return new ArrayList<>(); 
    }
}