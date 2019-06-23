package com.message.processor;

import com.message.processor.adjustments.Adjustment;
import com.message.processor.adjustments.AdjustmentFactory;
import com.message.processor.product.Product;
import com.message.processor.report.Report;
import com.message.processor.sale.SalesBook;
import com.message.processor.sale.SalesEntry;

import java.util.*;

public class SalesMessageProcessor{

    public static final SalesMessageProcessor MESSAGE_PROCESSOR = new SalesMessageProcessor();

    private static AdjustmentFactory ADJUSTMENT_FUNCTION_FACTORY = AdjustmentFactory.getFactory();
    private static SalesBook SALES_BOOK = SalesBook.getSalesBook();
    private static final int TEN = 10;
    private static final int FIFTY = 50;
    private static int COUNT = 0;

    private final List<Message> allReceivedMessages = new ArrayList<>();
    private final Queue<Adjustment> adjustments = new ArrayDeque<>();

    private Boolean isRunning = true;

    private SalesMessageProcessor() {
    }

    public void onMessage(Message message) {

        if (isRunning) {

            COUNT++;
            allReceivedMessages.add(message);

            if (message.getAdjustment().isPresent()) {
                adjustments.add(message.getAdjustment().get());
            }

            this.SALES_BOOK.addToSalesBook(message);

            if (COUNT % TEN == 0) {
                Report.generate();
            }
            if (COUNT % FIFTY == 0) {
                this.pause();
                this.doAdjustment();
            }
        }
    }

    public Collection<Message> getAllMessages() {
        return allReceivedMessages;
    }

    public static SalesMessageProcessor getMessageProcessor() {
        return MESSAGE_PROCESSOR;
    }

    public void start() {
        isRunning = true;
    }

    public void stop() {
        COUNT = 0;
        allReceivedMessages.clear();
        adjustments.clear();
    }

    public void pause() {
        System.out.println("\n\nPausing for Adjustment...");
        isRunning = false;
    }

    private void doAdjustment() {
        Adjustment adjustment;
        while ((adjustment = adjustments.poll()) != null) {
            Product product = adjustment.getProduct();
            SalesEntry salesEntry = SALES_BOOK.getSalesLeadger().get(product);
            ADJUSTMENT_FUNCTION_FACTORY.getAdjustmentFunction(adjustment.getType()).adjust(adjustment.getAdjustmentValue()).apply(salesEntry);
        }
        isRunning = true;
    }
}

