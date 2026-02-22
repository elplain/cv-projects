import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Leaderboard leaderboard = new Leaderboard();
        Game game = new Game(leaderboard);

        while (true) {
            printMenu();
            int choice = Input.readIntInRange(sc, "Choose option: ", 1, 7);

            switch (choice) {
                case 1 -> playMatch(sc, game, leaderboard);
                case 2 -> viewLeaderboard(leaderboard);
                case 3 -> runSimulation(sc, game, leaderboard);
                case 4 -> comparePlayers(sc, leaderboard);
                case 5 -> searchHistory(sc, leaderboard);
                case 6 -> findXPlayers(sc, leaderboard);
                case 7 -> {
                    System.out.println("Goodbye!");
                    sc.close();
                    return;
                }
            }

            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("=== Precision Draw ===");
        System.out.println("1. Play Match");
        System.out.println("2. View Leaderboard");
        System.out.println("3. Run a Simulation");
        System.out.println("4. Compare Two Players");
        System.out.println("5. Search Player History");
        System.out.println("6. List Players with > x Match Wins");
        System.out.println("7. Exit");
    }

    private static void playMatch(Scanner sc, Game game, Leaderboard leaderboard) {
        System.out.println("\n--- Play Match ---");
        System.out.println("Tip: enter SimPlayer1..SimPlayer10 to use a simulated player.");

        String n1 = Input.readNonEmpty(sc, "Enter Player 1 name: ");
        String n2 = Input.readNonEmpty(sc, "Enter Player 2 name (different): ");

        while (n1.equalsIgnoreCase(n2)) {
            System.out.println("Names must be different.");
            n2 = Input.readNonEmpty(sc, "Enter Player 2 name: ");
        }

        Player p1 = leaderboard.getOrCreatePlayer(n1, Leaderboard.isSimName(n1));
        Player p2 = leaderboard.getOrCreatePlayer(n2, Leaderboard.isSimName(n2));

        game.playMatch(sc, p1, p2, true, true);
    }

    private static void viewLeaderboard(Leaderboard leaderboard) {
        System.out.println("\n--- Leaderboard ---");
        Player[] list = leaderboard.getPlayersSortedByWins();

        if (list.length == 0) {
            System.out.println("No players yet.");
            return;
        }

        System.out.printf("%-20s %-8s %-10s %-10s%n", "Player", "Wins", "Matches", "Type");
        System.out.println("------------------------------------------------------");
        for (Player p : list) {
            System.out.printf("%-20s %-8d %-10d %-10s%n",
                    p.getName(), p.getWins(), p.getMatchesPlayed(),
                    p.isSimulated() ? "SIM" : "REAL");
        }
    }

    private static void runSimulation(Scanner sc, Game game, Leaderboard leaderboard) {

        Random rng = new Random();
        String simAName = "SimPlayer" + (rng.nextInt(10) + 1);
        String simBName = "SimPlayer" + (rng.nextInt(10) + 1);
        while (simBName.equals(simAName)) {
            simBName = "SimPlayer" + (rng.nextInt(10) + 1);
        }

        Player simA = leaderboard.getOrCreatePlayer(simAName, true);
        Player simB = leaderboard.getOrCreatePlayer(simBName, true);

        System.out.println("\n==============================");
        System.out.println("SIM MATCH: " + simA.getName() + " vs " + simB.getName());
        System.out.println("==============================");


        game.playMatch(null, simA, simB, true, true);
    }


    private static void comparePlayers(Scanner sc, Leaderboard leaderboard) {
        System.out.println("\n--- Compare Players ---");
        String a = Input.readNonEmpty(sc, "First player: ");
        String b = Input.readNonEmpty(sc, "Second player: ");

        Player p1 = leaderboard.findPlayer(a);
        Player p2 = leaderboard.findPlayer(b);

        if (p1 == null || p2 == null) {
            System.out.println("One or both players not found.");
            return;
        }

        showStats(p1);
        System.out.println();
        showStats(p2);
    }

    private static void showStats(Player p) {
        double winPct = (p.getMatchesPlayed() == 0) ? 0 :
                (p.getWins() * 100.0 / p.getMatchesPlayed());

        System.out.println("Player: " + p.getName() + (p.isSimulated() ? " [SIM]" : " [REAL]"));
        System.out.println("Matches: " + p.getMatchesPlayed());
        System.out.println("Wins: " + p.getWins());
        System.out.printf("Win %%: %.2f%%%n", winPct);
    }

    private static void searchHistory(Scanner sc, Leaderboard leaderboard) {
        System.out.println("\n--- Player History ---");
        String name = Input.readNonEmpty(sc, "Enter name: ");
        Player p = leaderboard.findPlayer(name);

        if (p == null) {
            System.out.println("Player not found.");
            return;
        }

        MatchHistory h = p.getHistory();
        if (h.size() == 0) {
            System.out.println("No matches recorded.");
            return;
        }

        for (int i = 0; i < h.size(); i++) {
            Match m = h.get(i);
            System.out.printf("vs %-15s | Total Score: %-4d | %s%n",
                    m.getOpponent(), m.getTotalScore(),
                    m.isWon() ? "WIN" : "LOSS");
        }
    }

    private static void findXPlayers(Scanner sc, Leaderboard leaderboard) {
        int x = Input.readIntInRange(sc, "Enter x: ", 0, 999999);

        Player[] list = leaderboard.getPlayersSortedByWins();
        boolean found = false;

        for (Player p : list) {
            if (p.getWins() > x) {
                if (!found) {
                    System.out.println("Players with > " + x + " wins:");
                    found = true;
                }
                System.out.println(p.getName() + " (" + p.getWins() + ")");
            }
        }

        if (!found) System.out.println("None found.");
    }
}
