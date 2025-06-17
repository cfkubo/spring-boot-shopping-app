// package com.example.shopping.ui;

// import com.example.shopping.model.NewProduct;
// import com.example.shopping.service.ProductService;
// import com.vaadin.flow.component.button.Button;
// import com.vaadin.flow.component.grid.Grid;
// import com.vaadin.flow.component.orderedlayout.VerticalLayout;
// import com.vaadin.flow.component.notification.Notification;
// import com.vaadin.flow.router.Route;
// import org.springframework.beans.factory.annotation.Autowired;
// import com.example.shopping.service.ProductService;

// import java.util.List;

// @Route("cart")
// public class CartView extends VerticalLayout {

//     private final ProductService productService;
//     private final Grid<NewProduct> productGrid;
//     private final Button checkoutButton;

//     @Autowired
//     public CartView(ProductService productService) {
//         this.productService = productService;
//         this.productGrid = new Grid<>(NewProduct.class);
//         this.checkoutButton = new Button("Checkout");

//         setupGrid();
//         setupCheckoutButton();

//         add(productGrid, checkoutButton);
//     }

//     private void setupGrid() {
//         productGrid.setColumns("productId", "productName", "price", "stockQuantity");
//         productGrid.setItems(getCartProducts());
//     }

//     private List<NewProduct> getCartProducts() {
//         // This method should return the products in the cart.
//         // For now, we will return all products as a placeholder.
//         return productService.getAllProducts();
//     }

//     private void setupCheckoutButton() {
//         checkoutButton.addClickListener(event -> {
//             Notification.show("Proceeding to checkout...");
//             // Logic to navigate to the checkout view can be added here.
//         });
//     }
// }


package com.example.shopping.ui;

import com.example.shopping.model.OrderItem;
import com.example.shopping.service.CartService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("cart")
public class CartView extends VerticalLayout {

    private final CartService cartService;
    private final Grid<OrderItem> cartGrid = new Grid<>(OrderItem.class);

    @Autowired
    public CartView(CartService cartService) {
        this.cartService = cartService;

        setSizeFull();
        cartGrid.setColumns("productId", "quantity", "price");
        cartGrid.setItems(cartService.getCartItems());

        Button checkoutButton = new Button("Checkout", e -> {
            getUI().ifPresent(ui -> ui.navigate("checkout"));
        });

        Button clearButton = new Button("Clear Cart", e -> {
            cartService.clearCart();
            cartGrid.setItems(cartService.getCartItems());
            Notification.show("Cart cleared!");
        });

        add(cartGrid, checkoutButton, clearButton);
    }
}