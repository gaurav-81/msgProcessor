package com.message.processor.adjustments;

import com.message.processor.sale.SalesEntry;

import java.math.BigDecimal;
import java.util.function.Function;

public abstract class AdjustmentFunction implements Function<SalesEntry, SalesEntry> {
    protected BigDecimal adjustmentValue;

    public AdjustmentFunction adjust(BigDecimal adjustmentValue) {
        this.adjustmentValue = adjustmentValue;
        return this;
    }
}
