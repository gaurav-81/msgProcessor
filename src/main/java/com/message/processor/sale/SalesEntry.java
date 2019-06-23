package com.message.processor.sale;

import com.message.processor.product.Product;

import java.math.BigDecimal;

public class SalesEntry {
    private Product product;
    private Integer quantity;
    private BigDecimal totalSale = new BigDecimal(0);

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(BigDecimal sale) {
        this.totalSale = sale.add(totalSale);
    }

    public void addQuantity(Integer quantityToAdd) {
        this.quantity = new Integer(quantity + quantityToAdd);
    }
}
