package com.cricket.dao;

import com.cricket.util.DBConnection;
import com.cricket.util.Player;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
    public List<Player> getPlayersByTeam(String teamName) {
        List<Player> players = new ArrayList<>();
        String query = "SELECT * FROM players WHERE team_name = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, teamName);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Player p = new Player();
                p.setPlayerId(rs.getInt("player_id"));
                p.setTeamName(rs.getString("team_name"));
                p.setPlayerName(rs.getString("player_name"));
                players.add(p);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return players;
    }
}