package com.message.processor.adjustments;

import com.message.processor.product.Product;

import java.math.BigDecimal;

public class Adjustment {
    private Product product;
    private AdjustmentType type;
    private BigDecimal adjustmentValue;

    public Adjustment(Product product, AdjustmentType adjustment, BigDecimal adjustmentValue) {
        this.product = product;
        this.type = adjustment;
        this.adjustmentValue = adjustmentValue;
    }

    public Product getProduct() {
        return product;
    }

    public AdjustmentType getType() {
        return type;
    }

    public BigDecimal getAdjustmentValue() {
        return adjustmentValue;
    }
}
