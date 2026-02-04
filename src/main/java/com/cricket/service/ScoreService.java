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
            // 1. Update general match state with current manual inputs
            String updateSql = "UPDATE matches SET striker_name = ?, non_striker_name = ?, current_bowler_name = ? WHERE match_id = ?";
            try (PreparedStatement pst = con.prepareStatement(updateSql)) {
                pst.setString(1, log.getStrikerName());
                pst.setString(2, nonStriker);
                pst.setString(3, log.getBowlerName());
                pst.setInt(4, log.getMatchId());
                pst.executeUpdate();
            }

            // 2. Handle Wicket: Set striker to NULL so scoreEntry.jsp prompts for new name
            if (log.isWicket()) {
                String wicketSql = "UPDATE matches SET striker_name = NULL WHERE match_id = ?";
                try (PreparedStatement pst = con.prepareStatement(wicketSql)) {
                    pst.setInt(1, log.getMatchId());
                    pst.executeUpdate();
                }
            } else {
                // 3. Normal Rotation (Odd runs)
                if (log.getRuns() % 2 != 0 && !log.isWide() && !log.isNoBall()) {
                    rotateStrike(log.getMatchId(), con);
                }
            }

            // 4. Over Completion Rotation: Every 6 legal balls
            if (!log.isWide() && !log.isNoBall()) {
                int legalBalls = getLegalBallCount(log.getMatchId(), con);
                if (legalBalls > 0 && legalBalls % 6 == 0) {
                    rotateStrike(log.getMatchId(), con);
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return true;
    }

    private int getLegalBallCount(int matchId, Connection con) throws SQLException {
        String sql = "SELECT COUNT(*) FROM score_log WHERE match_id = ? AND is_wide = false AND is_noball = false";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, matchId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return 0;
    }

    private void rotateStrike(int matchId, Connection con) throws SQLException {
        String sql = "UPDATE matches SET striker_name = non_striker_name, non_striker_name = striker_name WHERE match_id = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, matchId);
            pst.executeUpdate();
        }
    }
}