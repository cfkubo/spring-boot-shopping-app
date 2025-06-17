// package com.example.shopping.ui;

// import com.example.shopping.model.NewProduct;
// import com.vaadin.flow.component.grid.Grid;
// import com.vaadin.flow.component.orderedlayout.VerticalLayout;
// import com.vaadin.flow.component.notification.Notification;
// import com.vaadin.flow.router.Route;
// import org.springframework.beans.factory.annotation.Autowired;
// import java.util.List;
// import com.example.shopping.service.ProductService;

// @Route("")
// public class MainView extends VerticalLayout {

//     private final ProductService productService;
//     private final Grid<NewProduct> productGrid = new Grid<>(NewProduct.class);

//     @Autowired
//     public MainView(ProductService productService) {
//         this.productService = productService;
//         addClassName("main-view");
//         setSizeFull();
//         configureGrid();
//         add(productGrid);
//         loadProducts();
//     }

//     private void configureGrid() {
//         productGrid.setColumns("productName", "description", "price", "stockQuantity");
//         productGrid.getColumns().forEach(col -> col.setAutoWidth(true));
//         productGrid.asSingleSelect().addValueChangeListener(event -> {
//             NewProduct selectedProduct = event.getValue();
//             if (selectedProduct != null) {
//                 Notification.show("Selected: " + selectedProduct.getProductName());
//             }
//         });
//     }

//     private void loadProducts() {
//         List<NewProduct> products = productService.findAll();
//         productGrid.setItems(products);
//     }
// }


package com.example.shopping.ui;

import com.example.shopping.model.NewProduct;
import com.example.shopping.service.CartService;
import com.example.shopping.service.ProductService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("")
public class MainView extends VerticalLayout {

    private final ProductService productService;
    private final CartService cartService;
    private final Grid<NewProduct> productGrid = new Grid<>(NewProduct.class);

    @Autowired
    public MainView(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;

        setSizeFull();
        configureGrid();
        add(productGrid, new Button("Go to Cart", e -> getUI().ifPresent(ui -> ui.navigate("cart"))));
        loadProducts();
    }

    private void configureGrid() {
        productGrid.setColumns("productName", "description", "price", "stockQuantity");
        productGrid.addComponentColumn(product -> {
            Button addToCart = new Button("Add to Cart");
            addToCart.addClickListener(e -> {
                cartService.addToCart(product, 1);
                Notification.show(product.getProductName() + " added to cart!");
            });
            return new HorizontalLayout(addToCart);
        }).setHeader("Actions");
    }

    private void loadProducts() {
        productGrid.setItems(productService.findAll());
    }
}