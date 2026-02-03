package com.cricket.util;

public class ScoreSummary {
    private int totalRuns, totalWickets, legalBalls;
    private String strikerName, nonStrikerName, bowlerName;
    private int strikerRuns, strikerBalls, nonStrikerRuns, nonStrikerBalls;
    private int bowlerDots, bowlerRuns, bowlerWickets, bowlerBalls;

    // Getters and Setters for all fields
    public int getTotalRuns() { return totalRuns; }
    public void setTotalRuns(int totalRuns) { this.totalRuns = totalRuns; }
    public int getTotalWickets() { return totalWickets; }
    public void setTotalWickets(int totalWickets) { this.totalWickets = totalWickets; }
    public int getLegalBalls() { return legalBalls; }
    public void setLegalBalls(int legalBalls) { this.legalBalls = legalBalls; }
    public String getStrikerName() { return strikerName; }
    public void setStrikerName(String strikerName) { this.strikerName = strikerName; }
    public String getNonStrikerName() { return nonStrikerName; }
    public void setNonStrikerName(String nonStrikerName) { this.nonStrikerName = nonStrikerName; }
    public String getBowlerName() { return bowlerName; }
    public void setBowlerName(String bowlerName) { this.bowlerName = bowlerName; }
    public int getStrikerRuns() { return strikerRuns; }
    public void setStrikerRuns(int strikerRuns) { this.strikerRuns = strikerRuns; }
    public int getStrikerBalls() { return strikerBalls; }
    public void setStrikerBalls(int strikerBalls) { this.strikerBalls = strikerBalls; }
    public int getNonStrikerRuns() { return nonStrikerRuns; }
    public void setNonStrikerRuns(int nonStrikerRuns) { this.nonStrikerRuns = nonStrikerRuns; }
    public int getNonStrikerBalls() { return nonStrikerBalls; }
    public void setNonStrikerBalls(int nonStrikerBalls) { this.nonStrikerBalls = nonStrikerBalls; }
    public void setBowlerDots(int bowlerDots) { this.bowlerDots = bowlerDots; }
    public void setBowlerRuns(int bowlerRuns) { this.bowlerRuns = bowlerRuns; }
    public void setBowlerWickets(int bowlerWickets) { this.bowlerWickets = bowlerWickets; }
    public void setBowlerBalls(int bowlerBalls) { this.bowlerBalls = bowlerBalls; }

    // Helper for main overs
    public String getOversFormatted() {
        return (legalBalls / 6) + "." + (legalBalls % 6);
    }

    // Helper for (O-D-R-W) format
    public String getBowlingStat() {
        String overs = (bowlerBalls / 6) + "." + (bowlerBalls % 6);
        return overs + "-" + bowlerDots + "-" + bowlerRuns + "-" + bowlerWickets;
    }
}