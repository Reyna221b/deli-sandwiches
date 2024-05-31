package org.pluralsight.services;

import org.pluralsight.models.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ReceiptFileManager
{
    String random = UUID.randomUUID().toString().substring(0, 4);
    public void writeOrderToFile(Order order)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-hhmmss");
        String filename = "receipts/" + dateFormat.format(new Date()) + random + ".txt";

        File receiptsFolder = new File("receipts");
        if (!receiptsFolder.exists()) {
            receiptsFolder.mkdir();
        }

        try (FileWriter writer = new FileWriter(filename)) {
            printOrderSummary(writer, order);
            System.out.println("-".repeat(16));
            writer.write("\nTotal ORDER PRICE: $" + order.getTotalPrice() + "\n");
            System.out.println("-".repeat(16));
        } catch (IOException e) {
            System.out.println("\nError occurred while saving order.");
            e.printStackTrace();
        }
    }

    private void printOrderSummary(FileWriter writer, Order order) throws IOException {
        writer.write("---- Order Details ----\n");
        writer.write("Date: " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "\n");
        writer.write("Order Number: " + random + "\n\n");

        if (!order.getSandwiches().isEmpty()) {
            writer.write("---- Sandwiches ----\n");
            int sandwichNumber = 1;
            for (Sandwich sandwich : order.getSandwiches()) {
                writer.write("Sandwich " + sandwichNumber + ": "+ "\n");
                writer.write(sandwich.getSandwichDescription(order) + "\n");
                writer.write("Price: $" + sandwich.calculateTotalCost() + "\n\n");
                sandwichNumber++;
            }
        }

        if (!order.getDrinks().isEmpty()) {
            writer.write("---- Drinks ----\n");
            for (Drinks drink : order.getDrinks()) {
                writer.write(drink.getSize() + " " + drink.getFlavor() + "\n");
                writer.write("Price: $" + drink.getPrice() + "\n\n");
            }
        }

        if (!order.getChips().isEmpty()) {
            writer.write("---- Chips ----\n");
            for (Chips chips : order.getChips()) {
                writer.write(chips.getType() + "\n");
                writer.write("Price: $" + chips.getPrice() + "\n\n");
            }
        }

    }

}
