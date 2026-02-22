public class Leaderboard {
    private final PlayerScore players = new PlayerScore();

    public Player getOrCreatePlayer(String name, boolean simulated) {
        Player p = players.find(name);
        if (p != null) return p;
        p = new Player(name, simulated);
        players.add(p);
        return p;
    }

    public Player findPlayer(String name) {
        return players.find(name);
    }

    public Player[] getPlayersSortedByWins() {
        Player[] arr = players.toArray();
        sortByWinsDesc(arr);
        return arr;
    }

    // insertion sort O(n^2)
    private void sortByWinsDesc(Player[] a) {
        for (int i = 1; i < a.length; i++) {
            Player key = a[i];
            int j = i - 1;
            while (j >= 0 && a[j].getWins() < key.getWins()) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    // SimPlayer1 and SimPlayer10 detection
    public static boolean isSimName(String name) {
        if (name == null) return false;
        name = name.trim();
        if (!name.startsWith("SimPlayer")) return false;
        String suffix = name.substring("SimPlayer".length());
        try {
            int n = Integer.parseInt(suffix);
            return n >= 1 && n <= 10;
        } catch (Exception e) {
            return false;
        }
    }
}
