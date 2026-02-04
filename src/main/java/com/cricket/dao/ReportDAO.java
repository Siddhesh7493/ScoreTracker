package com.cricket.dao;

import com.cricket.util.DBConnection;
import com.cricket.util.ScoreSummary;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

        try (Connection con = DBConnection.getConnection()) {
            // 1. Fetch Main Scoreboard Data
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                for (int i = 1; i <= 11; i++) pst.setInt(i, matchId);
                pst.setInt(12, matchId);
                
                try (ResultSet rs = pst.executeQuery()) {
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
                }
            }

         // Replace the Wicket History block inside getLiveScore with this:
            List<String> fallOfWickets = new ArrayList<>();
            String wicketSql = "SELECT striker_name, total_at_wicket, wicket_num, legal_ball_at_wicket " +
                "FROM ( " +
                "  SELECT striker_name, is_wicket, " +
                "  (SELECT COALESCE(SUM(runs),0) FROM score_log s2 WHERE s2.match_id = s1.match_id AND s2.ball_id <= s1.ball_id) as total_at_wicket, " +
                "  (SELECT COUNT(*) FROM score_log s3 WHERE s3.match_id = s1.match_id AND s3.ball_id <= s1.ball_id AND s3.is_wicket = true) as wicket_num, " +
                "  (SELECT COUNT(*) FROM score_log s4 WHERE s4.match_id = s1.match_id AND s4.ball_id <= s1.ball_id AND s4.is_wide = false AND s4.is_noball = false) as legal_ball_at_wicket " +
                "  FROM score_log s1 WHERE match_id = ? " +
                ") sub " +
                "WHERE is_wicket = true ORDER BY wicket_num ASC";

            try (PreparedStatement wpst = con.prepareStatement(wicketSql)) {
                wpst.setInt(1, matchId);
                try (ResultSet wrs = wpst.executeQuery()) {
                    while (wrs.next()) {
                        int balls = wrs.getInt("legal_ball_at_wicket");
                        String over = (balls / 6) + "." + (balls % 6);
                        fallOfWickets.add(wrs.getInt("total_at_wicket") + "/" + wrs.getInt("wicket_num") + 
                            " (" + wrs.getString("striker_name") + ", " + over + " ov)");
                    }
                }
            }
            s.setWicketHistory(fallOfWickets);

        } catch (SQLException e) { // Added missing catch block
            e.printStackTrace();
        }
        return s; // Final return statement
    }
}