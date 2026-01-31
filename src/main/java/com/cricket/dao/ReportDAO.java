package com.cricket.dao;

import com.cricket.util.ScoreSummary;
import com.cricket.util.DBConnection;
import java.sql.*;

public class ReportDAO {
    public ScoreSummary getLiveScore(int matchId) {
        ScoreSummary summary = new ScoreSummary();
        String query = "SELECT SUM(runs) as total_runs, COUNT(*) FILTER (WHERE is_wicket = true) as wickets FROM score_log WHERE match_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, matchId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                summary.setTotalRuns(rs.getInt("total_runs"));
                summary.setTotalWickets(rs.getInt("wickets"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return summary;
    }
}