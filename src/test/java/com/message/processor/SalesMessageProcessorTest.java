package com.message.processor;

import com.message.processor.adjustments.Adjustment;
import com.message.processor.adjustments.AdjustmentType;
import com.message.processor.product.Product;
import com.message.processor.sale.Sale;
import com.message.processor.sale.SaleMessage;
import com.message.processor.sale.SalesBook;
import com.message.processor.sale.SalesEntry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SalesMessageProcessorTest {

    private Map<Product, SalesEntry> salesLeadger;
    private SalesMessageProcessor processor;
    private TestSaleMessageFactory saleMessageFactory;

    @Before
    public void setup() {
        salesLeadger = SalesBook.getSalesBook().getSalesLeadger();
        processor = SalesMessageProcessor.getMessageProcessor();
        processor.start();
        saleMessageFactory = new TestSaleMessageFactory();
    }

    @After
    public void cleanup() {
        processor.stop();
        salesLeadger.clear();
    }

    @Test
    public void testMessageType1() {
        Product apple = new Product("apple", new BigDecimal(10));
        processor.onMessage(new SaleMessage(new Sale(apple)));
        SalesEntry salesEntry = salesLeadger.get(apple);
        assertNotNull(salesEntry);
        assertEquals(10, salesEntry.getTotalSale().intValue());
    }

    @Test
    public void testMessageType2() {
        Product apple = new Product("apple", new BigDecimal(10));
        processor.onMessage(new SaleMessage(new Sale(apple, 1)));
        SalesEntry salesEntry = salesLeadger.get(apple);
        assertNotNull(salesEntry);
        assertEquals(10, salesEntry.getTotalSale().intValue());
    }

    @Test
    public void testMessageType3() {
        Product apple = new Product("apple", new BigDecimal(10));
        processor.onMessage(new SaleMessage(new Sale(apple, 1), new Adjustment(apple, AdjustmentType.ADD, new BigDecimal((2)))));
        SalesEntry salesEntry = salesLeadger.get(apple);
        assertNotNull(salesEntry);
        assertEquals(10, salesEntry.getTotalSale().intValue());
    }


    @Test
    public void testOnMessage() {
        processor.onMessage(saleMessageFactory.getSaleMessage("apple", 3));
        assertEquals(1, salesLeadger.size());
    }

    @Test
    public void testGenerateReportAfterTenMessages() {
        SaleMessage appleSaleMessage = saleMessageFactory.getSaleMessage("apple", 1);
        processor.onMessage(appleSaleMessage);
        processor.onMessage(saleMessageFactory.getSaleMessage("apple", 3));
        processor.onMessage(saleMessageFactory.getSaleMessage("mango", 3));
        processor.onMessage(saleMessageFactory.getSaleMessage("banana", 1));
        for (int i = 0; i < 7; i++)
            processor.onMessage(appleSaleMessage);
    }

    @Test
    public void testAdjustmentAfterFiftyMessages() {

        Product apple = new Product("apple", new BigDecimal(10));
        Product mango = new Product("mango", new BigDecimal(25));
        SaleMessage appleWithAdjustment = new SaleMessage(new Sale(apple, 1), new Adjustment(apple, AdjustmentType.ADD, new BigDecimal((2))));
        SaleMessage mangoWithAdjustment = new SaleMessage(new Sale(apple, 1), new Adjustment(mango, AdjustmentType.SUBTRACT, new BigDecimal(5)));
        SaleMessage appleSaleMessage = saleMessageFactory.getSaleMessage("apple", 1);
        processor.onMessage(appleSaleMessage);
        processor.onMessage(saleMessageFactory.getSaleMessage("apple", 3));
        processor.onMessage(saleMessageFactory.getSaleMessage("mango", 3));
        processor.onMessage(saleMessageFactory.getSaleMessage("banana", 1));
        processor.onMessage(mangoWithAdjustment);
        processor.onMessage(appleWithAdjustment);
        for (int i = 0; i < 45; i++)
            processor.onMessage(appleSaleMessage);
        assertEquals(51, processor.getAllMessages().size());
    }

    @Test
    public void testStop() {
        Product apple = new Product("apple", new BigDecimal(10));
        Product mango = new Product("mango", new BigDecimal(25));
        Product banana = new Product("banana", new BigDecimal(6));
        SaleMessage applesSaleMessage = new SaleMessage(new Sale(apple, 3));
        SaleMessage mangoSaleMessage = new SaleMessage(new Sale(mango, 3));
        SaleMessage bananaSaleMessage = new SaleMessage(new Sale(banana, 1));
        processor.onMessage(applesSaleMessage);
        processor.onMessage(mangoSaleMessage);
        processor.onMessage(bananaSaleMessage);
        assertEquals(3, salesLeadger.size());
        processor.stop();
        assertEquals(0, processor.getAllMessages().size());
    }

    @Test
    public void testPause() {
        processor.pause();
    }
}
