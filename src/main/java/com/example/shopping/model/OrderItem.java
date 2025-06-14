// // // package com.example.shopping.model;

// // // // import javax.persistence.*;

// // // // import com.vaadin.flow.component.template.Id;

// // // import jakarta.persistence.Column;
// // // import jakarta.persistence.Entity;
// // // import jakarta.persistence.GenerationType;
// // // import jakarta.persistence.Table;
// // // import jakarta.persistence.GeneratedValue;
// // // import jakarta.persistence.*;

// // // @Entity
// // // @Table(name = "order_items")
// // // public class OrderItem {

// // //     @Id
// // //     @GeneratedValue(strategy = GenerationType.IDENTITY)
// // //     private Long orderItemId;

// // //     @Column(name = "order_id", nullable = false)
// // //     private Long orderId;

// // //     @Column(name = "product_id", nullable = false)
// // //     private Long productId;

// // //     @Column(nullable = false)
// // //     private int quantity;

// // //     @Column(name = "price_at_purchase", nullable = false)
// // //     private double priceAtPurchase;

// // //     public OrderItem() {
// // //     }

// // //     public OrderItem(Long orderId, Long productId, int quantity, double priceAtPurchase) {
// // //         this.orderId = orderId;
// // //         this.productId = productId;
// // //         this.quantity = quantity;
// // //         this.priceAtPurchase = priceAtPurchase;
// // //     }

// // //     public Long getOrderItemId() {
// // //         return orderItemId;
// // //     }

// // //     public void setOrderItemId(Long orderItemId) {
// // //         this.orderItemId = orderItemId;
// // //     }

// // //     public Long getOrderId() {
// // //         return orderId;
// // //     }

// // //     public void setOrderId(NewOrder orderId) {
// // //         this.orderId = orderId;
// // //     }

// // //     public Long getProductId() {
// // //         return productId;
// // //     }

// // //     public void setProductId(Long productId) {
// // //         this.productId = productId;
// // //     }

// // //     public int getQuantity() {
// // //         return quantity;
// // //     }

// // //     public void setQuantity(int quantity) {
// // //         this.quantity = quantity;
// // //     }

// // //     public double getPriceAtPurchase() {
// // //         return priceAtPurchase;
// // //     }

// // //     public void setPriceAtPurchase(double priceAtPurchase) {
// // //         this.priceAtPurchase = priceAtPurchase;
// // //     }
// // // }

// // package com.example.shopping.model;

// // import jakarta.persistence.Entity;
// // import jakarta.persistence.Id;
// // import jakarta.persistence.GeneratedValue;
// // import jakarta.persistence.GenerationType;
// // import jakarta.persistence.Column;
// // import jakarta.persistence.ManyToOne; // For the relationship back to NewOrder
// // import jakarta.persistence.JoinColumn; // For the foreign key

// // import java.math.BigDecimal;

// // @Entity
// // @Table(name = "order_items") // Assuming a table named order_items
// // public class OrderItem {

// //     @Id
// //     @GeneratedValue(strategy = GenerationType.IDENTITY)
// //     private Long id; // Primary key for OrderItem itself

// //     // This is the foreign key column in the order_items table.
// //     // The 'name' attribute corresponds to the column name in the database.
// //     // This field 'order' is what 'mappedBy="order"' in NewOrder refers to.
// //     @ManyToOne
// //     @JoinColumn(name = "order_id", nullable = false) // Links to NewOrder (NewOrder's orderId)
// //     private NewOrder order; // Reference to the parent Order object

// //     @Column(name = "product_id", nullable = false)
// //     private Long productId; // Assuming this maps to your NewProduct's productId

// //     @Column(nullable = false)
// //     private Integer quantity;

// //     @Column(nullable = false)
// //     private BigDecimal price; // Price at the time of order

// //     // Default constructor (required by JPA/Jackson for deserialization)
// //     public OrderItem() {
// //     }

// //     // Constructor with fields (optional, but useful)
// //     public OrderItem(NewOrder order, Long productId, Integer quantity, BigDecimal price) {
// //         this.order = order;
// //         this.productId = productId;
// //         this.quantity = quantity;
// //         this.price = price;
// //     }

// //     // Getters and Setters
// //     public Long getId() {
// //         return id;
// //     }

// //     public void setId(Long id) {
// //         this.id = id;
// //     }

// //     public NewOrder getOrder() {
// //         return order;
// //     }

// //     // CRITICAL: This method MUST accept a NewOrder object, not a Long.
// //     public void setOrder(NewOrder order) {
// //         this.order = order;
// //     }

// //     public Long getProductId() {
// //         return productId;
// //     }

// //     public void setProductId(Long productId) {
// //         this.productId = productId;
// //     }

// //     public Integer getQuantity() {
// //         return quantity;
// //     }

// //     public void setQuantity(Integer quantity) {
// //         this.quantity = quantity;
// //     }

// //     public BigDecimal getPrice() {
// //         return price;
// //     }

// //     public void setPrice(BigDecimal price) {
// //         this.price = price;
// //     }
// // }

// package com.example.shopping.model;

// import jakarta.persistence.Entity;
// import jakarta.persistence.Id;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Column;
// import jakarta.persistence.ManyToOne; // For the relationship back to NewOrder
// import jakarta.persistence.JoinColumn; // For the foreign key
// import jakarta.persistence.Table; // ADDED: Import for @Table annotation

// import java.math.BigDecimal;

// @Entity
// @Table(name = "order_items") // Assuming a table named order_items
// public class OrderItem {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id; // Primary key for OrderItem itself

//     // This is the foreign key column in the order_items table.
//     // The 'name' attribute corresponds to the column name in the database.
//     // This field 'order' is what 'mappedBy="order"' in NewOrder refers to.
//     @ManyToOne
//     @JoinColumn(name = "order_id", nullable = false) // Links to NewOrder (NewOrder's orderId)
//     private NewOrder order; // Reference to the parent Order object

//     @Column(name = "product_id", nullable = false)
//     private Long productId; // Assuming this maps to your NewProduct's productId

//     @Column(nullable = false)
//     private Integer quantity;

//     @Column(nullable = false)
//     private BigDecimal price; // Price at the time of order

//     // Default constructor (required by JPA/Jackson for deserialization)
//     public OrderItem() {
//     }

//     // Constructor with fields (optional, but useful)
//     public OrderItem(NewOrder order, Long productId, Integer quantity, BigDecimal price) {
//         this.order = order;
//         this.productId = productId;
//         this.quantity = quantity;
//         this.price = price;
//     }

//     // Getters and Setters
//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public NewOrder getOrder() {
//         return order;
//     }

//     // CRITICAL: This method MUST accept a NewOrder object, not a Long.
//     public void setOrder(NewOrder order) {
//         this.order = order;
//     }

//     public Long getProductId() {
//         return productId;
//     }

//     public void setProductId(Long productId) {
//         this.productId = productId;
//     }

//     public Integer getQuantity() {
//         return quantity;
//     }

//     public void setQuantity(Integer quantity) {
//         this.quantity = quantity;
//     }

//     public BigDecimal getPrice() {
//         return price;
//     }

//     public void setPrice(BigDecimal price) {
//         this.price = price;
//     }

//     public long getPriceAtPurchase() {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'getPriceAtPurchase'");
//     }

//     public void setOrder(Long orderId) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'setOrder'");
//     }
// }

package com.example.shopping.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne; // For the relationship back to NewOrder
import jakarta.persistence.JoinColumn; // For the foreign key
import jakarta.persistence.Table; // ADDED: Import for @Table annotation

import java.math.BigDecimal;

@Entity
@Table(name = "order_items") // Assuming a table named order_items
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key for OrderItem itself

    // This is the foreign key column in the order_items table.
    // The 'name' attribute corresponds to the column name in the database.
    // This field 'order' is what 'mappedBy="order"' in NewOrder refers to.
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false) // Links to NewOrder (NewOrder's orderId)
    private NewOrder order; // Reference to the parent Order object

    @Column(name = "product_id", nullable = false)
    private Long productId; // Assuming this maps to your NewProduct's productId

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal price; // Price at the time of order

    // Default constructor (required by JPA/Jackson for deserialization)
    public OrderItem() {
    }

    // Constructor with fields (optional, but useful)
    public OrderItem(NewOrder order, Long productId, Integer quantity, BigDecimal price) {
        this.order = order;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NewOrder getOrder() {
        return order;
    }

    // CRITICAL: This method MUST accept a NewOrder object, not a Long.
    public void setOrder(NewOrder order) {
        this.order = order;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
