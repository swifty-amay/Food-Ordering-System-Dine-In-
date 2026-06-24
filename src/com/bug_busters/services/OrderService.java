package com.bug_busters.services;
import com.bug_busters.food.Item;
import com.bug_busters.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class OrderService {

    // Place order
    public void placeOrder(HashMap<Integer, Item> menu, Scanner scanner, int uid) {
        try {
            System.out.print("\nEnter the item numbers you want to order (comma separated): ");
            scanner.nextLine(); // clear buffer
            String input = scanner.nextLine();

            String[] parts = input.split(",");
            List<Item> orderedItems = new ArrayList<>();
            int total = 0;

            for (String part : parts) {
                try {
                    int num = Integer.parseInt(part.trim());
                    if (menu.containsKey(num)) {
                        Item item = menu.get(num);
                        orderedItems.add(item);
                        total += item.price;
                    } else {
                        System.out.println("❌ Invalid item number: " + num);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("❌ Invalid input: " + part);
                }
            }

            if (orderedItems.isEmpty()) {
                System.out.println("❌ No valid items selected.");
                return;
            }

            double gst = total * 0.05; // 5% GST
            double finalTotal = total + gst;

            // 🔥 Print bill
            System.out.println("\n\t\t\t\t\t        ------ BILL ------");
            System.out.printf("\n\n%-30s %10s\n", "Item", "Price");  // Column headers

            for (Item item : orderedItems) {
                System.out.printf("%-30s %10.2f\n", item.name, (double)item.price);
            }

            System.out.printf("\n%-30s %10.2f\n", "GST:", gst);
            System.out.printf("%-30s %10.2f\n", "Total:", finalTotal);

            // 🔥 Save order in DB
            Connection conn = DBConnection.getConnection();
            String query = "INSERT INTO orders (uid, item_names, total, ordered_at) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);

            StringBuilder itemNames = new StringBuilder();
            for (Item item : orderedItems) {
                if (itemNames.length() > 0) itemNames.append(", ");
                itemNames.append(item.name);
            }

            stmt.setInt(1, uid);
            stmt.setString(2, itemNames.toString());
            stmt.setDouble(3, finalTotal);
            stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));

            stmt.executeUpdate();
            conn.close();

            System.out.println("✅ Order placed successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}