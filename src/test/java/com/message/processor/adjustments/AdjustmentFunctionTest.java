package com.message.processor.adjustments;

import com.message.processor.adjustments.functions.Add;
import com.message.processor.adjustments.functions.Multiply;
import com.message.processor.adjustments.functions.Subtract;
import com.message.processor.product.Product;
import com.message.processor.sale.SalesEntry;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class AdjustmentFunctionTest {

    @Test
    public void testAdd() {
        Product apple = new Product("apple", new BigDecimal(10));
        SalesEntry entry = new SalesEntry();
        entry.setQuantity(1);
        entry.setProduct(apple);
        entry.setTotalSale(new BigDecimal(10));
        new Add().adjust(new BigDecimal(2)).apply(entry);
        assertEquals(12, entry.getTotalSale().intValue());
    }

    @Test
    public void testSubtract() {
        Product apple = new Product("apple", new BigDecimal(10));
        SalesEntry entry = new SalesEntry();
        entry.setQuantity(1);
        entry.setProduct(apple);
        entry.setTotalSale(new BigDecimal(10));
        new Subtract().adjust(new BigDecimal(2)).apply(entry);
        assertEquals(8, entry.getTotalSale().intValue());
    }

    @Test
    public void testMultiPly() {
        Product apple = new Product("apple", new BigDecimal(10));
        SalesEntry entry = new SalesEntry();
        entry.setQuantity(1);
        entry.setProduct(apple);
        entry.setTotalSale(new BigDecimal(10));
        new Multiply().adjust(new BigDecimal(2)).apply(entry);
        assertEquals(20, entry.getTotalSale().intValue());
    }
}
