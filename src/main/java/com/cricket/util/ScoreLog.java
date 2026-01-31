package com.cricket.util;

public class ScoreLog {
    private int logId;
    private int matchId;
    private int inning;
    private int overNum;
    private int ballNum;
    private int runs;
    private boolean isWide;
    private boolean isNoBall;
    private boolean isWicket;

    // Getters and Setters
    public int getMatchId() { return matchId; }
    public void setMatchId(int matchId) { this.matchId = matchId; }
    public int getRuns() { return runs; }
    public void setRuns(int runs) { this.runs = runs; }
    public boolean isWide() { return isWide; }
    public void setWide(boolean wide) { isWide = wide; }
    public boolean isNoBall() { return isNoBall; }
    public void setNoBall(boolean noBall) { isNoBall = noBall; }
    public boolean isWicket() { return isWicket; }
    public void setWicket(boolean wicket) { isWicket = wicket; }
    // Add other getters/setters as needed...
}