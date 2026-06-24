package com.bug_busters.services;

import com.bug_busters.utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserServices {

    public void signUp(String name, String password) {
        try {
            String username = name.toLowerCase();

            System.out.println("\nHere are your credentials:");
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);

            Connection conn = DBConnection.getConnection();

            String query = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, username);
            stmt.setString(2, password);

            stmt.executeUpdate();

            System.out.println("\n✅ User registered successfully");

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔥 RETURN UID INSTEAD OF BOOLEAN
    public int signIn(String username, String password) {
        try {
            Connection conn = DBConnection.getConnection();

            String query = "SELECT uid FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, username.toLowerCase());
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int uid = rs.getInt("uid");
                conn.close();
                return uid; // ✅ success
            } else {
                conn.close();
                return -1; // ❌ invalid
            }

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}