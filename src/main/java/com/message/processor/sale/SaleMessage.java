package com.message.processor.sale;

import com.message.processor.adjustments.Adjustment;
import com.message.processor.Message;

import java.util.Optional;

public class SaleMessage implements Message {

    private Sale sale;
    private Adjustment adjustment;

    public SaleMessage(Sale sale, Adjustment adjustment) {
        this.sale = sale;
        this.adjustment = adjustment;
    }

    public SaleMessage(Sale sale) {
        this.sale = sale;
    }

    @Override
    public Sale getSale() {
        return this.sale;
    }

    @Override
    public Optional<Adjustment> getAdjustment() {
        return Optional.ofNullable(adjustment);
    }
}
