package com.example.insurance.product.service;

import com.example.insurance.product.model.Product;
import com.example.insurance.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product sample;

    @BeforeEach
    void setup() {
        sample = new Product(1L, "Accident Cover", new BigDecimal("199.99"), "Medical, roadside");
    }

    @Test
    void findAll_returnsList() {
        when(productRepository.findAll()).thenReturn(Arrays.asList(sample));
        List<Product> products = productService.getAllProducts();
        assertEquals(1, products.size());
        assertEquals("Accident Cover", products.get(0).getProductName());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void getProductById_found() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(sample));
        Optional<Product> p = productService.getProductById(1L);
        assertTrue(p.isPresent());
        assertEquals(1L, p.get().getProductId());
        verify(productRepository).findById(1L);
    }

    @Test
    void getProductById_notFound_returnsEmptyOptional() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());
        Optional<Product> p = productService.getProductById(99L);
        assertTrue(p.isEmpty());
        verify(productRepository).findById(99L);
    }

    @Test
    void addProduct_saves() {
        when(productRepository.save(any(Product.class))).thenAnswer(inv -> {
            Product p = inv.getArgument(0);
            p.setProductId(5L);
            return p;
        });
        Product created = productService.createProduct(new Product(0L, "X", new BigDecimal("1.00"), "B"));
        assertEquals(5L, created.getProductId());
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void updateProduct_found_updates() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(sample));
        when(productRepository.save(any(Product.class))).thenAnswer(inv -> inv.getArgument(0));
        Product payload = new Product(0L, "Updated", new BigDecimal("299.99"), "New");
        Product updated = productService.updateProduct(1L, payload);
        assertEquals("Updated", updated.getProductName());
        assertEquals(new BigDecimal("299.99"), updated.getPrice());
        verify(productRepository).save(sample);
    }

    @Test
    void updateProduct_notFound_throws() {
        when(productRepository.findById(88L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> productService.updateProduct(88L, sample));
        verify(productRepository, never()).save(any());
    }

    @Test
    void deleteProduct_found_deletes() {
        when(productRepository.existsById(1L)).thenReturn(true);
        boolean result = productService.deleteProduct(1L);
        assertTrue(result);
        verify(productRepository).deleteById(1L);
    }

    @Test
    void deleteProduct_notFound_throws() {
        when(productRepository.existsById(2L)).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> productService.deleteProduct(2L));
        verify(productRepository, never()).deleteById(anyLong());
    }
}
