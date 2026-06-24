package com.bug_busters.food;
import java.util.HashMap;

public class food_list {

    // ✅ MAIN COURSE
    public HashMap<Integer, Item> getMainCourse() {
        HashMap<Integer, Item> menu = new HashMap<>();

        menu.put(1, new Item("Paneer Butter Masala", 250));
        menu.put(2, new Item("Dal Makhani", 200));
        menu.put(3, new Item("Veg Biryani", 180));
        menu.put(4, new Item("Shahi Paneer", 270));
        menu.put(5, new Item("Chole Bhature", 150));
        menu.put(6, new Item("Mixed Veg Curry", 220));
        menu.put(7, new Item("Mushroom Masala", 240));
        menu.put(8, new Item("Palak Paneer", 230));
        menu.put(9, new Item("Jeera Rice", 120));
        menu.put(10, new Item("Roti (2 pcs)", 40));


        return menu;
    }

    // ✅ FAST FOOD
    public HashMap<Integer, Item> getFastFood() {
        HashMap<Integer, Item> menu = new HashMap<>();

        menu.put(1, new Item("Burger", 120));
        menu.put(2, new Item("Pizza", 300));
        menu.put(3, new Item("French Fries", 100));
        menu.put(4, new Item("Veg Sandwich", 90));
        menu.put(5, new Item("Paneer Wrap", 150));
        menu.put(6, new Item("Chicken Nuggets", 180));
        menu.put(7, new Item("Hot Dog", 130));
        menu.put(8, new Item("Garlic Bread", 110));
        menu.put(9, new Item("Veg Roll", 140));
        menu.put(10, new Item("Cheese Corn Balls", 160));


        return menu;
    }

    // ✅ BEVERAGES
    public HashMap<Integer, Item> getBeverages() {
        HashMap<Integer, Item> menu = new HashMap<>();

        menu.put(1, new Item("Coke", 50));
        menu.put(2, new Item("Coffee", 80));
        menu.put(3, new Item("Juice", 70));
        menu.put(4, new Item("Lassi", 60));
        menu.put(5, new Item("Tea", 40));
        menu.put(6, new Item("Mango Shake", 90));
        menu.put(7, new Item("Cold Coffee", 100));
        menu.put(8, new Item("Green Tea", 60));
        menu.put(9, new Item("Orange Juice", 75));
        menu.put(10, new Item("Mineral Water", 30));


        return menu;
    }

    // ✅ EXTRAS
    public HashMap<Integer, Item> getExtras() {
        HashMap<Integer, Item> menu = new HashMap<>();

        menu.put(1, new Item("Extra Cheese", 40));
        menu.put(2, new Item("Sauce", 20));
        menu.put(3, new Item("Salad", 60));
        menu.put(4, new Item("Garlic Bread", 50));
        menu.put(5, new Item("French Fries", 70));
        menu.put(6, new Item("Coleslaw", 45));
        menu.put(7, new Item("Papad", 15));
        menu.put(8, new Item("Pickle", 10));
        menu.put(9, new Item("Chutney", 25));
        menu.put(10, new Item("Masala Papad", 35));


        return menu;
    }
}