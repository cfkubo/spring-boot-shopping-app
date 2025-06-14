package com.example.shopping.ui;

import com.example.shopping.model.NewOrder;
import com.example.shopping.model.OrderItem; // Import for OrderItem
import com.example.shopping.service.OrderService; // Import for OrderService
import com.example.shopping.service.CartService;
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
    private final CartService cartService;

    private TextField customerName = new TextField("Name");
    private TextField customerEmail = new TextField("Email");
    private TextField shippingAddress = new TextField("Shipping Address");
    private TextField billingAddress = new TextField("Billing Address");
    private Button placeOrderButton = new Button("Place Order");

    @Autowired
    public CheckoutView(OrderService orderService, CartService cartService) {
        this.orderService = orderService;
        this.cartService = cartService;

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
        newOrder.setOrderItems(cartService.getCartItems());

        orderService.placeOrder(newOrder); 
        cartService.clearCart();
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