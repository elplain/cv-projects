public class Match {
    private final String opponent;
    private final int totalScore;
    private final boolean won;

    public Match(String opp, int score, boolean w) {
        opponent = opp;
        totalScore = score;
        won = w;
    }

    public String getOpponent() {
        return opponent;
    }
    public int getTotalScore() {
        return totalScore;
    }
    public boolean isWon() {
        return won;
    }
}
