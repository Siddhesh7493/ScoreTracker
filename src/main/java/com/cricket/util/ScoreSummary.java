package com.cricket.util;

public class ScoreSummary {

    private int matchId;
    private int totalRuns;
    private int totalWickets;
    private int totalBalls;
    private String currentOverStatus; // e.g., "1.4"

    public int getTotalRuns() { return totalRuns; }
    public void setTotalRuns(int totalRuns) { this.totalRuns = totalRuns; }
    public int getTotalWickets() { return totalWickets; }
    public void setTotalWickets(int totalWickets) { this.totalWickets = totalWickets; }
    // Add other getters/setters...
}