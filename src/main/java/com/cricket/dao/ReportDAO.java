package com.cricket.dao;

import com.cricket.util.DBConnection;
import com.cricket.util.ScoreSummary;
import java.sql.*;

public class ReportDAO {
    public ScoreSummary getLiveScore(int matchId) {
        ScoreSummary s = new ScoreSummary();
        String sql = "SELECT m.striker_name, m.non_striker_name, m.current_bowler_name, " +
            "(SELECT COALESCE(SUM(runs),0) FROM score_log WHERE match_id = ?) as total_runs, " +
            "(SELECT COUNT(*) FROM score_log WHERE match_id = ? AND is_wicket = true) as total_wickets, " +
            "(SELECT COUNT(*) FROM score_log WHERE match_id = ? AND is_wide = false AND is_noball = false) as legal_balls, " +
            "(SELECT COALESCE(SUM(runs),0) FROM score_log WHERE match_id = ? AND striker_name = m.striker_name) as s_runs, " +
            "(SELECT COUNT(*) FROM score_log WHERE match_id = ? AND striker_name = m.striker_name AND is_wide = false) as s_balls, " +
            "(SELECT COALESCE(SUM(runs),0) FROM score_log WHERE match_id = ? AND striker_name = m.non_striker_name) as ns_runs, " +
            "(SELECT COUNT(*) FROM score_log WHERE match_id = ? AND striker_name = m.non_striker_name AND is_wide = false) as ns_balls, " +
            "(SELECT COUNT(*) FROM score_log WHERE match_id = ? AND bowler_name = m.current_bowler_name AND runs = 0 AND is_wide = false AND is_noball = false) as b_dots, " +
            "(SELECT COALESCE(SUM(runs),0) FROM score_log WHERE match_id = ? AND bowler_name = m.current_bowler_name) as b_runs, " +
            "(SELECT COUNT(*) FROM score_log WHERE match_id = ? AND bowler_name = m.current_bowler_name AND is_wicket = true) as b_wickets, " +
            "(SELECT COUNT(*) FROM score_log WHERE match_id = ? AND bowler_name = m.current_bowler_name AND is_wide = false AND is_noball = false) as b_balls " +
            "FROM matches m WHERE m.match_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            for(int i=1; i<=11; i++) pst.setInt(i, matchId);
            pst.setInt(12, matchId);
            
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                s.setTotalRuns(rs.getInt("total_runs"));
                s.setTotalWickets(rs.getInt("total_wickets"));
                s.setLegalBalls(rs.getInt("legal_balls"));
                s.setStrikerName(rs.getString("striker_name"));
                s.setNonStrikerName(rs.getString("non_striker_name"));
                s.setBowlerName(rs.getString("current_bowler_name"));
                s.setStrikerRuns(rs.getInt("s_runs"));
                s.setStrikerBalls(rs.getInt("s_balls"));
                s.setNonStrikerRuns(rs.getInt("ns_runs"));
                s.setNonStrikerBalls(rs.getInt("ns_balls"));
                s.setBowlerDots(rs.getInt("b_dots"));
                s.setBowlerRuns(rs.getInt("b_runs"));
                s.setBowlerWickets(rs.getInt("b_wickets"));
                s.setBowlerBalls(rs.getInt("b_balls"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return s;
    }
}