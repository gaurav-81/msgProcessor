package com.message.processor.sale;

import com.message.processor.Message;
import com.message.processor.product.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class SalesBook {
    private static final SalesBook SALES_BOOK = new SalesBook();
    private static final Map<Product, SalesEntry> salesLedger = new HashMap();

    public static SalesBook getSalesBook() {
        return SALES_BOOK;
    }

    public Map<Product, SalesEntry> getSalesLeadger() {
        return salesLedger;
    }

    public void addToSalesBook(Message message) {
        final Sale sale = message.getSale();
        SalesEntry salesEntry = salesLedger.get(sale.getProduct());
        if (null == salesEntry) {
            salesEntry = new SalesEntry();
            salesEntry.setProduct(sale.getProduct());
            salesEntry.setQuantity(sale.getQuantity());
            salesEntry.setTotalSale(sale.getProduct().getPrice().multiply(new BigDecimal(sale.getQuantity())));
            salesLedger.put(sale.getProduct(), salesEntry);
        } else {
            salesEntry.addQuantity(sale.getQuantity());
            salesEntry.setTotalSale(sale.getProduct().getPrice().multiply(new BigDecimal(sale.getQuantity())));
        }
    }


}
