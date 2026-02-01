package com.cricket.dao;

import com.cricket.util.DBConnection;
import com.cricket.util.Match;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchDAO {

    // 1. Create a new match with venue support
    public boolean createMatch(Match match) {
        String query = "INSERT INTO matches (team_a, team_b, status, venue) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, match.getTeamA());
            pst.setString(2, match.getTeamB());
            pst.setString(3, match.getStatus());
            pst.setString(4, match.getVenue()); // Cites venue support
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2. Fetch all live matches for selection
    public List<Match> getLiveMatches() {
        List<Match> matches = new ArrayList<>();
        String query = "SELECT * FROM matches WHERE status = 'live'";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                Match m = new Match();
                m.setMatchId(rs.getInt("match_id"));
                m.setTeamA(rs.getString("team_a"));
                m.setTeamB(rs.getString("team_b"));
                m.setStatus(rs.getString("status"));
                matches.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matches;
    }

    // 3. New Method: Set the initial/updated active players for the match
    public void updateActivePlayers(int matchId, int strikerId, int nonStrikerId, int bowlerId) {
        String sql = "UPDATE matches SET striker_id = ?, non_striker_id = ?, bowler_id = ? WHERE match_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, strikerId);
            pst.setInt(2, nonStrikerId);
            pst.setInt(3, bowlerId);
            pst.setInt(4, matchId);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 4. Swap Striker and Non-Striker (Rule: Strike Rotation)
    public void swapPlayers(int matchId) {
        // Atomic swap of striker and non-striker in the database
        String sql = "UPDATE matches SET striker_id = non_striker_id, " +
                     "non_striker_id = striker_id WHERE match_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, matchId);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 5. Clear Bowler ID (Rule: End of Over)
    public void resetBowler(int matchId) {
        String sql = "UPDATE matches SET bowler_id = NULL WHERE match_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, matchId);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 6. Clear Striker ID (Rule: Wicket/Out)
    public void resetStriker(int matchId) {
        String sql = "UPDATE matches SET striker_id = NULL WHERE match_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, matchId);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}