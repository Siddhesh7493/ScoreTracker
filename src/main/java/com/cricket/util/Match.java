package com.cricket.util;

public class Match {
    private int matchId;
    private String teamA;
    private String teamB;
    private String status;
    private String venue; // Add this field

    // Getters and Setters
    public int getMatchId() { return matchId; }
    public void setMatchId(int matchId) { this.matchId = matchId; }

    public String getTeamA() { return teamA; }
    public void setTeamA(String teamA) { this.teamA = teamA; }

    public String getTeamB() { return teamB; }
    public void setTeamB(String teamB) { this.teamB = teamB; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // Add these methods
    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }
}