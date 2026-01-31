package com.cricket.dao;

import com.cricket.util.ScoreLog;
import com.cricket.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet; // Added this import
import java.sql.SQLException;

public class ScoreDAO {
    public boolean insertBall(ScoreLog log) {
        String query = "INSERT INTO score_log (match_id, runs, is_wide, is_noball, is_wicket) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, log.getMatchId());
            pst.setInt(2, log.getRuns());
            pst.setBoolean(3, log.isWide());
            pst.setBoolean(4, log.isNoBall());
            pst.setBoolean(5, log.isWicket());
            return pst.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public int getLegalBallCount(int matchId) {
        String sql = "SELECT COUNT(*) FROM score_log WHERE match_id = ? " +
                     "AND is_wide = false AND is_noball = false";
        // Updated to use try-with-resources for ResultSet as well
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, matchId);
            try (ResultSet rs = pst.executeQuery()) { // Line 27
                if (rs.next()) return rs.getInt(1);
            }
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
        return 0;
    }
}