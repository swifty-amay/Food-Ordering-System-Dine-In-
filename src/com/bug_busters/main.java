package com.bug_busters;
import java.util.Scanner;
import com.bug_busters.services.UserServices;
import com.bug_busters.food.main_food;
import com.bug_busters.services.AdminServices;
import java.util.List;
import java.util.ArrayList;

public class main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UserServices us = new UserServices();

        boolean exitProgram = false;

        while (!exitProgram) { // 🔥 Top-level menu loop
            System.out.println("\t\t\t\t\t\t -------------");
            System.out.println("\t\t\t\t\t\t W E L C O M E");
            System.out.println("\t\t\t\t\t\t -------------");

            System.out.println("Please let us know who you are?");
            System.out.println("\n1. User");
            System.out.println("2. Admin");
            System.out.println("3. Exit");
            System.out.print("\nI am (1, 2, or 3): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            if (choice == 1) { // USER
                boolean backToTopMenu = false;

                while (!backToTopMenu) { // User menu loop
                	System.out.println("\t\t\t\t\t\t----------------");
                    System.out.println("\t\t\t\t\t\tU S E R  M E N U");
                    System.out.println("\t\t\t\t\t\t----------------");
                    System.out.println("1. Sign In");
                    System.out.println("2. Sign Up");
                    System.out.println("3. Exit");
                    System.out.print("\nEnter your option: ");

                    int userChoice = scanner.nextInt();
                    scanner.nextLine(); 

                    if (userChoice == 1) { // Sign In
                        while (true) {
                            System.out.print("\nEnter username: ");
                            String username = scanner.nextLine();

                            System.out.print("Enter password: ");
                            String password = scanner.nextLine();

                            int uid = us.signIn(username, password);

                            if (uid != -1) {
//                                System.out.println("\t\t\t\tWelcome " + username + " 🎉");
                                System.out.println("\t\t\t\t\t      ----------------------");
                                System.out.println("\t\t\t\t\t      W E L C O M E  "+ username + " 🎉");
                                System.out.println("\t\t\t\t\t      ----------------------");

                                main_food mf = new main_food();
                                mf.showMenu(scanner, uid); // pass uid

                                break; // exit login loop
                            } else {
                                System.out.println("❌ Invalid credentials. Try again.\n");
                            }
                        }
                    } else if (userChoice == 2) { // Sign Up
                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();

                        String password;
                        while (true) {
                            System.out.print("Enter password (8 characters only): ");
                            password = scanner.nextLine();

                            if (password.length() != 8) {
                                System.out.println("❌ Password must be exactly 8 characters\n");
                            } else {
                                break;
                            }
                        }

                        us.signUp(name, password);
                        
                    } else if (userChoice == 3) { // Exit
                    	
                        backToTopMenu = true; // return to top-level menu
                        System.out.println("Returning to main menu...\n");
                    } else {
                        System.out.println("❌ Invalid option");
                    }
                }

            } else if (choice == 2) { // ADMIN
                AdminServices adminServices = new AdminServices();
                boolean backToTopMenu = false;

                while (!backToTopMenu) {
                	System.out.println("\t\t\t\t\t       --------------------");
                    System.out.println("\t\t\t\t\t       A D M I N  L O G I N");
                    System.out.println("\t\t\t\t\t       --------------------");
                    System.out.print("Enter admin name: ");
                    String adminName = scanner.nextLine();

                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    int aid = adminServices.adminSignIn(adminName, password);

                    if (aid != -1) {
                    	System.out.println("\t\t\t\t\t      ----------------------");
                        System.out.println("\t\t\t\t\t      W E L C O M E  "+ adminName + " 🎉");
                        System.out.println("\t\t\t\t\t      ----------------------");

                        boolean backToAdminMenu = false;

                        while (!backToAdminMenu) {
                            // 🔥 Fetch and show orders
                            List<AdminServices.Order> orders = adminServices.fetchOrders();
                            System.out.println("\n\t\t\t\t\t       ------ ORDERS ------\n\n");
                            if (orders.isEmpty()) {
                                System.out.println("No active orders");
                            } else {
                            	
                            	System.out.printf("%-8s %-10s %-40s %-10s %-20s\n", "OrderID", "UserID", "Items", "Total", "Ordered At");

                            	for (AdminServices.Order o : orders) {
                            	    System.out.printf(
                            	        "%-8d %-10d %-40s %-10.2f %-20s\n",
                            	        o.orderId,
                            	        o.uid,
                            	        o.itemNames,
                            	        (double) o.total,   // ✅ cast to double
                            	        o.orderedAt
                            	    );
                            	}
                            }

                            // 🔥 Admin options
                            System.out.println("\n1. Complete orders");
                            System.out.println("2. Refresh orders");
                            System.out.println("3. Logout");
                            System.out.print("\nEnter option: ");

                            int adminChoice = scanner.nextInt();
                            scanner.nextLine(); // clear buffer

                            switch (adminChoice) {
                                case 1:
                                    System.out.print("\nEnter order IDs to mark complete (comma separated): ");
                                    String input = scanner.nextLine();
                                    String[] parts = input.split(",");
                                    List<Integer> orderIds = new ArrayList<>();
                                    for (String s : parts) {
                                        try {
                                            orderIds.add(Integer.parseInt(s.trim()));
                                        } catch (NumberFormatException e) {
                                            System.out.println("❌ Invalid number: " + s);
                                        }
                                    }
                                    adminServices.softDeleteOrders(orderIds);
                                    System.out.println("✅ Orders updated");
                                    break;
                                case 2:
                                    System.out.println("🔄 Refreshing orders...");
                                    break; // loop will automatically refresh
                                case 3:
                                    backToAdminMenu = true;
                                    System.out.println("Logging out and returning to main menu...");
                                    break;
                                default:
                                    System.out.println("❌ Invalid option");
                            }
                        }
                        break; // exit login loop
                    } else {
                        System.out.println("❌ Invalid admin credentials. Try again.\n");
                    }
                }
            
            } else if (choice == 3) { // EXIT program
                exitProgram = true;
                System.out.println("Exiting program...");
            } else {
                System.out.println("❌ Invalid option");
            }
        }

        scanner.close();
    }
}