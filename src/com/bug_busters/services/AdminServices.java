package com.bug_busters.services;
import com.bug_busters.utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminServices {

    // 🔥 Admin login: return aid if valid, else -1
    public int adminSignIn(String adminName, String password) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT aid FROM admin WHERE admin_name = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, adminName);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int aid = rs.getInt("aid");
                conn.close();
                return aid;
            } else {
                conn.close();
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 🔥 Fetch all active orders (deleted = false)
    public List<Order> fetchOrders() {
        List<Order> orders = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT * FROM orders WHERE deleted = FALSE";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("order_id"),
                        rs.getInt("uid"),
                        rs.getString("item_names"),
                        rs.getInt("total"),
                        rs.getTimestamp("ordered_at")
                ));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    // 🔥 Soft delete orders by order IDs
    public void softDeleteOrders(List<Integer> orderIds) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "UPDATE orders SET deleted = TRUE WHERE order_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);

            for (int id : orderIds) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔥 Order class to hold order info
    public static class Order {
        public int orderId;
        public int uid;
        public String itemNames;
        public int total;
        public Timestamp orderedAt;

        public Order(int orderId, int uid, String itemNames, int total, Timestamp orderedAt) {
            this.orderId = orderId;
            this.uid = uid;
            this.itemNames = itemNames;
            this.total = total;
            this.orderedAt = orderedAt;
        }
    }
}