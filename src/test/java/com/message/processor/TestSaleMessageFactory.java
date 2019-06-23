package com.message.processor;

import com.message.processor.product.Product;
import com.message.processor.sale.Sale;
import com.message.processor.sale.SaleMessage;

import java.math.BigDecimal;

/**
 * Used for generation of Test data.
 */
class TestSaleMessageFactory {
    public SaleMessage getSaleMessage(String product, Integer quantity) {
        switch (product) {
            case "apple":
                Product apple = new Product("apple", new BigDecimal(10));
                return new SaleMessage(new Sale(apple, quantity));

            case "banana":
                Product banana = new Product("banana", new BigDecimal(6));
                return new SaleMessage(new Sale(banana, quantity));

            case "mango":
                Product mango = new Product("mango", new BigDecimal(25));
                return new SaleMessage(new Sale(mango, quantity));
        }
        return null;
    }
}