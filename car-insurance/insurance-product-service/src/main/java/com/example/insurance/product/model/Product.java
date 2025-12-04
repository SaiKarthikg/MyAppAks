package com.example.insurance.product.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Entity
@Table(name = "insurance_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    @NotBlank(message = "Product name must not be blank")
    @Column (name = "product_name", nullable = false)
    private String productName;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    @Column (name = "price", nullable = false)
    private BigDecimal price;

    @NotBlank(message = "Benefits must not be blank")
    @Column (name = "benefits", nullable = false)
    private String benefits;

    public Product() {}

    public Product(long productId, String productName, BigDecimal price, String benefits) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.benefits = benefits;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }
}
