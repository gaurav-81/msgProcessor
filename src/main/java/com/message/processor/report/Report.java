package com.message.processor.report;

import com.message.processor.sale.SalesBook;
import com.message.processor.sale.SalesEntry;

public class Report {
    private static SalesBook SALES_BOOK = SalesBook.getSalesBook();

    public static void printSalesEntry(SalesEntry entry) {
        System.out.println("PRODUCT\t\t\t|Quantity\t|Total Sale\t\t|");
        System.out.println(entry.getProduct().getName() + "\t\t\t|" + entry.getQuantity() + "\t\t\t|" +
                entry.getTotalSale() + "\t\t\t|");
    }

    public static void generate() {
        System.out.println("\n\n*************** Generating Report ***************");
        System.out.println("\tPRODUCT\t\t\t|Quantity\t|Total Sale\t\t|");
        int count = 0;
        for (SalesEntry entry : SALES_BOOK.getSalesLeadger().values()) {
            count++;
            System.out.println(count + ")\t" + entry.getProduct().getName() + "\t\t\t|" + entry.getQuantity() + "\t\t\t|" +
                    entry.getTotalSale() + "\t\t\t|");

        }
        System.out.println("*************************************************");
    }
}
