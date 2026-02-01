package com.cricket.util;

public class ScoreSummary {
    private int totalRuns;
    private int totalWickets;
    private int legalBalls;
    private String strikerName;
    private int strikerRuns;
    private String bowlerName;

    public int getTotalRuns() { return totalRuns; }
    public void setTotalRuns(int totalRuns) { this.totalRuns = totalRuns; }
    public int getTotalWickets() { return totalWickets; }
    public void setTotalWickets(int totalWickets) { this.totalWickets = totalWickets; }
    public int getLegalBalls() { return legalBalls; }
    public void setLegalBalls(int legalBalls) { this.legalBalls = legalBalls; }
    public String getStrikerName() { return strikerName; }
    public void setStrikerName(String strikerName) { this.strikerName = strikerName; }
    public int getStrikerRuns() { return strikerRuns; }
    public void setStrikerRuns(int strikerRuns) { this.strikerRuns = strikerRuns; }
    public String getBowlerName() { return bowlerName; }
    public void setBowlerName(String bowlerName) { this.bowlerName = bowlerName; }

    public String getOvers() {
        return (legalBalls / 6) + "." + (legalBalls % 6);
    }
}