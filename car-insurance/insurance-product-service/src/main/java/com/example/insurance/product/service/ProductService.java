package com.example.insurance.product.service;

import com.example.insurance.product.model.Product;
import com.example.insurance.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(@NotNull @Positive Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public Product createProduct(@Valid @NotNull Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(@NotNull @Positive Long id, @Valid @NotNull Product product) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
        existing.setProductName(product.getProductName());
        existing.setPrice(product.getPrice());
        existing.setBenefits(product.getBenefits());
        return productRepository.save(existing);
    }

    @Transactional
    public boolean deleteProduct(@NotNull @Positive Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product not found: " + id);
        }
        productRepository.deleteById(id);
        return true;
    }
}
