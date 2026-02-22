public class Player {
    private final String name;
    private final boolean simulated;

    private int wins = 0;
    private int matches = 0;
    private final MatchHistory history = new MatchHistory();

    public Player(String n, boolean simulated) {
        this.name = n;
        this.simulated = simulated;
    }

    public String getName() {
        return name;
    }
    public boolean isSimulated() {
        return simulated;
    }

    public int getWins() {
        return wins;
    }
    public int getMatchesPlayed() {
        return matches;
    }
    public MatchHistory getHistory() {
        return history;
    }

    public void addWin() { wins++; }
    public void addMatch() { matches++; }
}
