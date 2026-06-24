package com.bug_busters.food;
import java.util.HashMap;
import java.util.Scanner;
import com.bug_busters.services.OrderService;

public class main_food {

    public void showMenu(Scanner scanner, int uid) {

        food_list fl = new food_list();
        OrderService os = new OrderService();

        while (true) {
            System.out.println("\n\t\t\t\t\t      ------ FOOD MENU ------");
            System.out.println("1. Main Course");
            System.out.println("2. Fast-Food");
            System.out.println("3. Beverages");
            System.out.println("4. Extra");
            System.out.println("5. Exit");
            System.out.print("\nEnter your choice: ");

            int choice = scanner.nextInt();
            HashMap<Integer, Item> menu = null;

            switch (choice) {
                case 1:
                    menu = fl.getMainCourse();
                    break;
                case 2:
                    menu = fl.getFastFood();
                    break;
                case 3:
                    menu = fl.getBeverages();
                    break;
                case 4:
                    menu = fl.getExtras();
                    break;
                case 5:
                    System.out.println("Exiting menu...");
                    return;
                default:
                    System.out.println("❌ Invalid choice");
                    continue;
            }

            // Show menu
            System.out.printf("\n%-10s %-50s %-6s\n", "No.", "Item", "Price");
            for (int key : menu.keySet()) {
                Item item = menu.get(key);
                System.out.printf("%-10d %-50s %-6d\n", key, item.name, item.price);
            }
            // Place order
            os.placeOrder(menu, scanner, uid);
        }
    }
}