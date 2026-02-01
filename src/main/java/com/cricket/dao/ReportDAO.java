package com.cricket.dao;

import com.cricket.util.DBConnection;
import com.cricket.util.ScoreSummary;
import java.sql.*;

public class ReportDAO {
    public ScoreSummary getLiveScore(int matchId) {
        ScoreSummary s = new ScoreSummary();
        // SQL pulls the manually entered names from the matches table
        String sql = "SELECT " +
            "(SELECT COALESCE(SUM(runs),0) FROM score_log WHERE match_id = ?) as total_runs, " +
            "(SELECT COUNT(*) FROM score_log WHERE match_id = ? AND is_wicket = true) as total_wickets, " +
            "(SELECT COUNT(*) FROM score_log WHERE match_id = ? AND is_wide = false AND is_noball = false) as legal_balls, " +
            "m.striker_name, m.current_bowler_name, " +
            "(SELECT COALESCE(SUM(runs),0) FROM score_log WHERE match_id = ? AND striker_name = m.striker_name) as s_runs " +
            "FROM matches m WHERE m.match_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, matchId);
            pst.setInt(2, matchId);
            pst.setInt(3, matchId);
            pst.setInt(4, matchId);
            pst.setInt(5, matchId);
            
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    s.setTotalRuns(rs.getInt("total_runs"));
                    s.setTotalWickets(rs.getInt("total_wickets"));
                    s.setLegalBalls(rs.getInt("legal_balls"));
                    s.setStrikerName(rs.getString("striker_name"));
                    s.setBowlerName(rs.getString("current_bowler_name"));
                    s.setStrikerRuns(rs.getInt("s_runs"));
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return s;
    }
}