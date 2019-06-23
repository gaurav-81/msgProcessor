package com.message.processor.adjustments.functions;

import com.message.processor.adjustments.AdjustmentFunction;
import com.message.processor.sale.SalesEntry;

import java.math.BigDecimal;

import static com.message.processor.report.Report.printSalesEntry;

public class Multiply extends AdjustmentFunction {

    @Override
    public SalesEntry apply(SalesEntry entry) {
        BigDecimal quantity = new BigDecimal(entry.getQuantity());
        BigDecimal currentPrice = entry.getTotalSale().divide(quantity);
        BigDecimal newPrice = currentPrice.multiply(adjustmentValue).multiply(quantity);
        System.out.println("\n\nBefore Adjustment");
        printSalesEntry(entry);
        entry.setTotalSale(newPrice.subtract(entry.getTotalSale()));
        System.out.println("After Adjustment , Adjustment Value Multiply:" + adjustmentValue);
        printSalesEntry(entry);
        return entry;
    }
}
