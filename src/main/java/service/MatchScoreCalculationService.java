package service;

import model.Match;
import model.MatchScore;

public class MatchScoreCalculationService {

    public void updatePoints(Match match, String player) {
        MatchScore matchScore = match.getMatchScore();
        matchScore.playPoint(player);
    }
}
