// package com.example.shopping.controller;

// import com.example.shopping.model.NewProduct;
// import com.example.shopping.model.OrderItem;
// import com.example.shopping.service.CartService;
// import com.example.shopping.service.ProductService;

// import jakarta.servlet.http.HttpSession;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// import java.util.ArrayList;
// import java.util.List;

// @RestController
// @RequestMapping("/api/cart")
// // @SessionAttributes("cart")
// public class CartController {

//     private final CartService cartService;
//     private final ProductService productService;

//     @Autowired
//     public CartController(CartService cartService, ProductService productService) {
//         this.cartService = cartService;
//         this.productService = productService;
//     }

//     // Get all items in the cart
//     @GetMapping
//     public List<OrderItem> getCartItems() {
//         return cartService.getCartItems();
//     }

//     // Add a product to the cart
//     // @PostMapping("/add")
//     // public String addToCart(@RequestParam Long productId, @RequestParam(defaultValue = "1") int quantity) {
//     //     NewProduct product = productService.getProductById(productId);
//     //     if (product == null) {
//     //         return "Product not found";
//     //     }
//     //     cartService.addToCart(product, quantity);
//     //     return "Product added to cart";
//     // }

//     @PostMapping("/add")
//     public String addToCart(@RequestParam Long productId, @RequestParam(defaultValue = "1") int quantity, HttpSession session) {
//         // ...find product...
//         List<OrderItem> cartItems = (List<OrderItem>) session.getAttribute("cartItems");
//         if (cartItems == null) {
//             cartItems = new ArrayList<>();
//             session.setAttribute("cartItems", cartItems);
//         }
//         cartItems.add(new OrderItem(null, productId, quantity, product.getPrice()));
//         return "Product added to cart";
//     }

//     // Clear the cart
//     @PostMapping("/clear")
//     public String clearCart() {
//         cartService.clearCart();
//         return "Cart cleared";
//     }
// }


package com.example.shopping.controller;

import com.example.shopping.model.NewProduct;
import com.example.shopping.model.OrderItem;
import com.example.shopping.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final ProductService productService;

    @Autowired
    public CartController(ProductService productService) {
        this.productService = productService;
    }

    // Get all items in the cart
    @GetMapping
    public List<OrderItem> getCartItems(HttpSession session) {
        List<OrderItem> cartItems = (List<OrderItem>) session.getAttribute("cartItems");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
            session.setAttribute("cartItems", cartItems);
        }
        return cartItems;
    }

    // Add a product to the cart
    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, @RequestParam(defaultValue = "1") int quantity, HttpSession session) {
        NewProduct product = productService.getProductById(productId);
        if (product == null) {
            return "Product not found";
        }
        List<OrderItem> cartItems = (List<OrderItem>) session.getAttribute("cartItems");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
            session.setAttribute("cartItems", cartItems);
        }
        cartItems.add(new OrderItem(null, productId, quantity, product.getPrice()));
        return "Product added to cart";
    }

    // Clear the cart
    @PostMapping("/clear")
    public String clearCart(HttpSession session) {
        session.removeAttribute("cartItems");
        return "Cart cleared";
    }
}