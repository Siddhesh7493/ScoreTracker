package com.cricket.dao;

import com.cricket.util.User;
import com.cricket.util.DBConnection;
import java.sql.*;

public class UserDAO {
    public User validateUser(String username, String password) {
        User user = null;
        String query = "SELECT id, username, role FROM users WHERE username = ? AND password = ?";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setRole(rs.getString("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}