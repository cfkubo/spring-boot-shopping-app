package com.example.shopping.service;

import com.example.shopping.model.NewProduct;
import com.example.shopping.repository.NewProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final NewProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(NewProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<NewProduct> findAll() {
        return productRepository.findAll();
    }

    public Optional<NewProduct> getProductById(Integer productId) {
        return productRepository.findById(productId);
    }

    public NewProduct addProduct(NewProduct product) {
        return productRepository.save(product);
    }

    public NewProduct updateProduct(Integer productId, NewProduct productDetails) {
        NewProduct product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setProductName(productDetails.getProductName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStockQuantity(productDetails.getStockQuantity());
        product.setCategory(productDetails.getCategory());
        product.setManufacturer(productDetails.getManufacturer());
        product.setReleaseDate(productDetails.getReleaseDate());
        product.setActive(productDetails.isActive());
        product.setWeightKg(productDetails.getWeightKg());
        product.setDimensionsCm(productDetails.getDimensionsCm());
        product.setRatingAvg(productDetails.getRatingAvg());
        product.setReviewCount(productDetails.getReviewCount());
        product.setMaterial(productDetails.getMaterial());
        product.setWarrantyMonths(productDetails.getWarrantyMonths());
        product.setLastUpdatedAt(productDetails.getLastUpdatedAt());
        return productRepository.save(product);
    }

    public void deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
    }

    // For CartView and ProductController compatibility
    public List<NewProduct> getAllProducts() {
        return productRepository.findAll();
    }

    public NewProduct createProduct(NewProduct product) {
        return productRepository.save(product);
    }

    public NewProduct updateProduct(Long id, NewProduct updatedProduct) {
        NewProduct product = productRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setProductName(updatedProduct.getProductName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setStockQuantity(updatedProduct.getStockQuantity());
        product.setCategory(updatedProduct.getCategory());
        product.setManufacturer(updatedProduct.getManufacturer());
        product.setReleaseDate(updatedProduct.getReleaseDate());
        product.setActive(updatedProduct.isActive());
        product.setWeightKg(updatedProduct.getWeightKg());
        product.setDimensionsCm(updatedProduct.getDimensionsCm());
        product.setRatingAvg(updatedProduct.getRatingAvg());
        product.setReviewCount(updatedProduct.getReviewCount());
        product.setMaterial(updatedProduct.getMaterial());
        product.setWarrantyMonths(updatedProduct.getWarrantyMonths());
        product.setLastUpdatedAt(updatedProduct.getLastUpdatedAt());
        return productRepository.save(product);
    }

    public boolean deleteProduct(Long id) {
        try {
            productRepository.deleteById(Math.toIntExact(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public NewProduct getProductById(Long id) {
        return productRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}