package com.cricket.service;

import com.cricket.util.ScoreLog;
import com.cricket.dao.ScoreDAO;
import com.cricket.util.DBConnection;
import java.sql.*;

public class ScoreService {
    private ScoreDAO scoreDAO = new ScoreDAO();

    public boolean processBall(ScoreLog log) {
        // 1. Save ball to log (DAO already updated to handle striker_name/bowler_name)
        boolean saved = scoreDAO.insertBall(log);
        if (!saved) return false;

        // 2. Update the MATCH table with the manual names currently active
        String sql = "UPDATE matches SET striker_name = ?, current_bowler_name = ? WHERE match_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, log.getStrikerName());
            pst.setString(2, log.getBowlerName());
            pst.setInt(3, log.getMatchId());
            pst.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
        
        return true;
    }
}