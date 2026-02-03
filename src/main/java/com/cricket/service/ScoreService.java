package com.cricket.service;

import com.cricket.util.ScoreLog;
import com.cricket.dao.ScoreDAO;
import com.cricket.util.DBConnection;
import java.sql.*;

public class ScoreService {
    private ScoreDAO scoreDAO = new ScoreDAO();

    public boolean processBall(ScoreLog log, String nonStriker) {
        scoreDAO.insertBall(log);
        try (Connection con = DBConnection.getConnection()) {
            // Update the current state based on manual input
            String updateSql = "UPDATE matches SET striker_name = ?, non_striker_name = ?, current_bowler_name = ? WHERE match_id = ?";
            try (PreparedStatement pst = con.prepareStatement(updateSql)) {
                pst.setString(1, log.getStrikerName());
                pst.setString(2, nonStriker);
                pst.setString(3, log.getBowlerName());
                pst.setInt(4, log.getMatchId());
                pst.executeUpdate();
            }

            // Standard Strike Rotation Logic (Odd runs swap striker/non-striker)
            if (log.getRuns() % 2 != 0 && !log.isWide() && !log.isNoBall()) {
                rotateStrike(log.getMatchId(), con);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return true;
    }

    private void rotateStrike(int matchId, Connection con) throws SQLException {
        String sql = "UPDATE matches SET striker_name = non_striker_name, non_striker_name = striker_name WHERE match_id = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, matchId);
            pst.executeUpdate();
        }
    }
}