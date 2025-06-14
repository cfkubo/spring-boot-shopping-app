package com.example.shopping.ui;

import com.example.shopping.model.NewProduct;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.example.shopping.service.ProductService;

@Route("")
public class MainView extends VerticalLayout {

    private final ProductService productService;
    private final Grid<NewProduct> productGrid = new Grid<>(NewProduct.class);

    @Autowired
    public MainView(ProductService productService) {
        this.productService = productService;
        addClassName("main-view");
        setSizeFull();
        configureGrid();
        add(productGrid);
        loadProducts();
    }

    private void configureGrid() {
        productGrid.setColumns("productName", "description", "price", "stockQuantity");
        productGrid.getColumns().forEach(col -> col.setAutoWidth(true));
        productGrid.asSingleSelect().addValueChangeListener(event -> {
            NewProduct selectedProduct = event.getValue();
            if (selectedProduct != null) {
                Notification.show("Selected: " + selectedProduct.getProductName());
            }
        });
    }

    private void loadProducts() {
        List<NewProduct> products = productService.findAll();
        productGrid.setItems(products);
    }
}