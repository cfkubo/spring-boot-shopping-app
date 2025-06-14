// package com.example.shopping.model;

// // import javax.persistence.*;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Table;
// import jakarta.persistence.Id;
// import jakarta.persistence.OneToMany;
// import jakarta.persistence.CascadeType;
// import jakarta.persistence.FetchType;
// import jakarta.persistence.JoinColumn;

// import java.math.BigDecimal;
// import java.time.LocalDateTime;
// import java.util.List;
// // import com.example.shopping.model.OrderItem;


// @Entity
// @Table(name = "new_orders")
// public class NewOrder {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long orderId;

//     @Column(name = "customer_id")
//     private Integer customerId;

//     @Column(name = "customer_name", nullable = false)
//     private String customerName;

//     @Column(name = "customer_email")
//     private String customerEmail;

//     @Column(name = "order_date", updatable = false)
//     private LocalDateTime orderDate;

//     @Column(name = "total_amount", nullable = false)
//     private BigDecimal totalAmount;

//     @Column(name = "currency", length = 3)
//     private String currency;

//     @Column(name = "order_status", length = 50)
//     private String orderStatus;

//     @Column(name = "shipping_address")
//     private String shippingAddress;

//     @Column(name = "billing_address")
//     private String billingAddress;

//     @Column(name = "payment_method")
//     private String paymentMethod;

//     @Column(name = "transaction_id")
//     private String transactionId;

//     @Column(name = "shipping_cost", precision = 8, scale = 2)
//     private BigDecimal shippingCost;

//     @Column(name = "discount_amount", precision = 8, scale = 2)
//     private BigDecimal discountAmount;

//     @Column(name = "estimated_delivery_date")
//     private LocalDateTime estimatedDeliveryDate;

//     @Column(name = "actual_delivery_date")
//     private LocalDateTime actualDeliveryDate;

//     @Column(name = "notes")
//     private String notes;

//     @Column(name = "created_at", updatable = false)
//     private LocalDateTime createdAt;

//     @Column(name = "updated_at")
//     private LocalDateTime updatedAt;

//     // Add this field
//     @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//     @JoinColumn(name = "order_id") // This assumes OrderItem has a column 'order_id'
//     private List<OrderItem> orderItems;

//     // Getters and Setters
//     public Long getOrderId() {
//         return orderId;
//     }

//     public void setOrderId(Long orderId) {
//         this.orderId = orderId;
//     }

//     public Integer getCustomerId() {
//         return customerId;
//     }

//     public void setCustomerId(Integer customerId) {
//         this.customerId = customerId;
//     }

//     public String getCustomerName() {
//         return customerName;
//     }

//     public void setCustomerName(String customerName) {
//         this.customerName = customerName;
//     }

//     public String getCustomerEmail() {
//         return customerEmail;
//     }

//     public void setCustomerEmail(String customerEmail) {
//         this.customerEmail = customerEmail;
//     }

//     public LocalDateTime getOrderDate() {
//         return orderDate;
//     }

//     public void setOrderDate(LocalDateTime orderDate) {
//         this.orderDate = orderDate;
//     }

//     public BigDecimal getTotalAmount() {
//         return totalAmount;
//     }

//     public void setTotalAmount(BigDecimal totalAmount) {
//         this.totalAmount = totalAmount;
//     }

//     public String getCurrency() {
//         return currency;
//     }

//     public void setCurrency(String currency) {
//         this.currency = currency;
//     }

//     public String getOrderStatus() {
//         return orderStatus;
//     }

//     public void setOrderStatus(String orderStatus) {
//         this.orderStatus = orderStatus;
//     }

//     public String getShippingAddress() {
//         return shippingAddress;
//     }

//     public void setShippingAddress(String shippingAddress) {
//         this.shippingAddress = shippingAddress;
//     }

//     public String getBillingAddress() {
//         return billingAddress;
//     }

//     public void setBillingAddress(String billingAddress) {
//         this.billingAddress = billingAddress;
//     }

//     public String getPaymentMethod() {
//         return paymentMethod;
//     }

//     public void setPaymentMethod(String paymentMethod) {
//         this.paymentMethod = paymentMethod;
//     }

//     public String getTransactionId() {
//         return transactionId;
//     }

//     public void setTransactionId(String transactionId) {
//         this.transactionId = transactionId;
//     }

//     public BigDecimal getShippingCost() {
//         return shippingCost;
//     }

//     public void setShippingCost(BigDecimal shippingCost) {
//         this.shippingCost = shippingCost;
//     }

//     public BigDecimal getDiscountAmount() {
//         return discountAmount;
//     }

//     public void setDiscountAmount(BigDecimal discountAmount) {
//         this.discountAmount = discountAmount;
//     }

//     public LocalDateTime getEstimatedDeliveryDate() {
//         return estimatedDeliveryDate;
//     }

//     public void setEstimatedDeliveryDate(LocalDateTime estimatedDeliveryDate) {
//         this.estimatedDeliveryDate = estimatedDeliveryDate;
//     }

//     public LocalDateTime getActualDeliveryDate() {
//         return actualDeliveryDate;
//     }

//     public void setActualDeliveryDate(LocalDateTime actualDeliveryDate) {
//         this.actualDeliveryDate = actualDeliveryDate;
//     }

//     public String getNotes() {
//         return notes;
//     }

//     public void setNotes(String notes) {
//         this.notes = notes;
//     }

//     public LocalDateTime getCreatedAt() {
//         return createdAt;
//     }

//     public void setCreatedAt(LocalDateTime createdAt) {
//         this.createdAt = createdAt;
//     }

//     public LocalDateTime getUpdatedAt() {
//         return updatedAt;
//     }

//     public void setUpdatedAt(LocalDateTime updatedAt) {
//         this.updatedAt = updatedAt;
//     }

//     public List<OrderItem> getOrderItems() {
//         return orderItems;
//     }

//     public void setOrderItems(List<OrderItem> orderItems) {
//         this.orderItems = orderItems;
//     }
// }

package com.example.shopping.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
// import jakarta.persistence.JoinColumn; // REMOVE this JoinColumn import and usage from here!

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList; // Import ArrayList
import java.util.List;


@Entity
@Table(name = "new_orders")
public class NewOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "order_date", updatable = false)
    private LocalDateTime orderDate;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "currency", length = 3)
    private String currency;

    @Column(name = "order_status", length = 50)
    private String orderStatus;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "billing_address")
    private String billingAddress;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "shipping_cost", precision = 8, scale = 2)
    private BigDecimal shippingCost;

    @Column(name = "discount_amount", precision = 8, scale = 2)
    private BigDecimal discountAmount;

    @Column(name = "estimated_delivery_date")
    private LocalDateTime estimatedDeliveryDate;

    @Column(name = "actual_delivery_date")
    private LocalDateTime actualDeliveryDate;

    @Column(name = "notes")
    private String notes;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // --- CRITICAL CHANGES BELOW ---

    // 1. Initialize the list to prevent NullPointerException
    // 2. Use 'mappedBy' for bidirectional relationship, referring to the field name in OrderItem
    // 3. orphanRemoval = true is good practice for parent-child relationships
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>(); // Initialize the list here!

    // --- CRITICAL CHANGES ABOVE ---

    // Getters and Setters
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(BigDecimal shippingCost) {
        this.shippingCost = shippingCost;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public LocalDateTime getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(LocalDateTime estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public LocalDateTime getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    public void setActualDeliveryDate(LocalDateTime actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        // Clear existing items and add new ones to properly manage the collection
        // This is important for JPA to track changes and for orphanRemoval to work.
        this.orderItems.clear();
        if (orderItems != null) {
            this.orderItems.addAll(orderItems);
            // IMPORTANT: Set the back-reference (the 'order' field in each OrderItem)
            for (OrderItem item : orderItems) {
                item.setOrder(this);
            }
        }
    }

    // Helper methods to add/remove items (good practice)
    public void addOrderItem(OrderItem item) {
        if (this.orderItems == null) { // Should not be null with initialization, but defensive
            this.orderItems = new ArrayList<>();
        }
        this.orderItems.add(item);
        item.setOrder(this); // Set the back-reference
    }

    public void removeOrderItem(OrderItem item) {
        if (this.orderItems != null) {
            this.orderItems.remove(item);
            item.setOrder((NewOrder) null); // Clear the back-reference
        }
    }
}
