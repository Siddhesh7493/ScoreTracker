package com.cricket.util;

public class Player {

    private int playerId;
    private String playerName;
    private int teamId;
    private String role; // Batsman, Bowler, All-rounder

    public int getPlayerId() { return playerId; }
    public void setPlayerId(int playerId) { this.playerId = playerId; }
    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }
    public int getTeamId() { return teamId; }
    public void setTeamId(int teamId) { this.teamId = teamId; }
}