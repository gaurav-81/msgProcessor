package com.message.processor.adjustments.functions;

import com.message.processor.adjustments.AdjustmentFunction;
import com.message.processor.sale.SalesEntry;

import java.math.BigDecimal;

import static com.message.processor.report.Report.printSalesEntry;

public class Subtract extends AdjustmentFunction {

    @Override
    public SalesEntry apply(SalesEntry salesEntry) {
        BigDecimal quantity = new BigDecimal(salesEntry.getQuantity());
        BigDecimal adjustment = quantity.multiply(this.adjustmentValue).multiply(new BigDecimal(-1));
        System.out.println("\n\nBefore Adjustment");
        printSalesEntry(salesEntry);
        salesEntry.setTotalSale(adjustment);
        System.out.println("After Adjustment, Adjustment Value SUBTRACT:" + adjustmentValue);
        printSalesEntry(salesEntry);
        return salesEntry;
    }
}