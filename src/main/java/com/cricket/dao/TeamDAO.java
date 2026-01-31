package com.cricket.dao;

import com.cricket.util.Team;
import com.cricket.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO {
    public List<Team> getAllTeams() {
        List<Team> teams = new ArrayList<>();
        String query = "SELECT * FROM teams";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                teams.add(new Team(rs.getInt("id"), rs.getString("name")));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return teams;
    }
}