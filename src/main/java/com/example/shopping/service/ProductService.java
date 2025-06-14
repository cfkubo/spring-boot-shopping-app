// package com.example.shopping.service;

// import com.example.shopping.model.NewProduct;
// import com.example.shopping.repository.NewProductRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class ProductService {

//     private final NewProductRepository productRepository;

//     @Autowired
//     public ProductService(NewProductRepository productRepository) {
//         this.productRepository = productRepository;
//     }

//     public List<NewProduct> getAllProducts() {
//         return productRepository.findAll();
//     }

//     public Optional<NewProduct> getProductById(Long productId) {
//         return productRepository.findById(productId);
//     }

//     public NewProduct addProduct(NewProduct product) {
//         return productRepository.save(product);
//     }

//     public NewProduct updateProduct(Long productId, NewProduct productDetails) {
//         NewProduct product = productRepository.findById(productId)
//                 .orElseThrow(() -> new RuntimeException("Product not found"));
//         product.setProductName(productDetails.getProductName());
//         product.setDescription(productDetails.getDescription());
//         product.setPrice(productDetails.getPrice());
//         product.setStockQuantity(productDetails.getStockQuantity());
//         product.setCategory(productDetails.getCategory());
//         product.setManufacturer(productDetails.getManufacturer());
//         product.setReleaseDate(productDetails.getReleaseDate());
//         product.setIsActive(productDetails.getIsActive());
//         product.setWeightKg(productDetails.getWeightKg());
//         product.setDimensionsCm(productDetails.getDimensionsCm());
//         product.setRatingAvg(productDetails.getRatingAvg());
//         product.setReviewCount(productDetails.getReviewCount());
//         product.setMaterial(productDetails.getMaterial());
//         product.setWarrantyMonths(productDetails.getWarrantyMonths());
//         product.setLastUpdatedAt(productDetails.getLastUpdatedAt());
//         return productRepository.save(product);
//     }

//     public void deleteProduct(Long productId) {
//         productRepository.deleteById(productId);
//     }
// }

package com.example.shopping.service;

import com.example.shopping.model.NewProduct;
import java.util.List;

public interface ProductService {
    List<NewProduct> findAll();
    List<NewProduct> getAllProducts();
    NewProduct getProductById(Long id);
    NewProduct createProduct(NewProduct product);
    NewProduct updateProduct(Long id, NewProduct updatedProduct);
    boolean deleteProduct(Long id);
}