package com.cricket.service;

import com.cricket.util.ScoreLog;
import com.cricket.dao.ScoreDAO;
import com.cricket.dao.MatchDAO;

public class ScoreService {
    private ScoreDAO scoreDAO = new ScoreDAO();
    private MatchDAO matchDAO = new MatchDAO();

    public boolean processBall(ScoreLog log) {
        // 1. Save the ball to DB first
        boolean saved = scoreDAO.insertBall(log);
        if (!saved) return false;

        // 2. Strike Rotation on Odd Runs (1, 3, 5)
        // Note: Wides/No-balls with odd runs also rotate strike in most rules
        if (log.getRuns() % 2 != 0) {
            rotateStrike(log.getMatchId());
        }

        // 3. Check for Over Completion (6 legal balls)
        if (!log.isWide() && !log.isNoBall()) {
            if (isOverComplete(log.getMatchId())) {
                rotateStrike(log.getMatchId()); // Rotate ends at end of over
                updateBowler(log.getMatchId());
            }
        }
        return true;
    }

    private void rotateStrike(int matchId) {
        // Swaps striker_id and non_striker_id in the 'matches' table
        matchDAO.swapPlayers(matchId);
    }

    private boolean isOverComplete(int matchId) {
        // Count legal balls in the current match
        int legalBalls = scoreDAO.getLegalBallCount(matchId);
        return (legalBalls > 0 && legalBalls % 6 == 0);
    }

    private void updateBowler(int matchId) {
        // Sets bowler_id to NULL so the scorer is forced 
        // to select a new bowler for the next over in the UI
        matchDAO.resetBowler(matchId);
    }
}