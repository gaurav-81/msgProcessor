package com.message.processor.adjustments.functions;

import com.message.processor.adjustments.AdjustmentFunction;
import com.message.processor.sale.SalesEntry;

import java.math.BigDecimal;

import static com.message.processor.report.Report.printSalesEntry;

public class Add extends AdjustmentFunction {

    @Override
    public SalesEntry apply(SalesEntry entry) {
        BigDecimal quantity = new BigDecimal(entry.getQuantity());
        BigDecimal adjustment = quantity.multiply(this.adjustmentValue);
        System.out.println("\n\nBefore Adjustment");
        printSalesEntry(entry);
        entry.setTotalSale(adjustment);
        System.out.println("After Adjustment , Adjustment Value ADD:" + adjustmentValue);
        printSalesEntry(entry);
        return entry;
    }
}
