package com.message.processor.sale;

import com.message.processor.product.Product;

public class Sale {
    private Product product;
    private Integer quantity;

    public Sale(Product product) {
        this(product, 1);
    }

    public Sale(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
